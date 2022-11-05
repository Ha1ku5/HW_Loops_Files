import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HW_Loops_Files {
    public static void main(String[] args) {
        //creates an input stream from the file to a scanner, catching any FileNotFoundExceptions
        Scanner file = null;
        try {
            file = new Scanner(new FileInputStream("courseData.txt"));

        } catch (FileNotFoundException e) {
            System.out.println( e.getMessage());
            System.exit(-1);
           //no file found, though, there should always be a file as specified by the project description
        }
        //initializes unchanging variables (used for weighted grade average)
        double proWeight = file.nextDouble();
        double midtermWeight = file.nextDouble();
        double finalExamWeight = file.nextDouble();

        //prints header for first class
        int firstClassNo = file.nextInt();
        System.out.println("Grade Data For Class " + firstClassNo);
        System.out.println(" ID   Programs  Midterm  Final  Weighted Average  Programs grade");
        System.out.println(" --   --------  -------  -----  ----------------  --------------");

        //keeps a running total of number of students and total
        int noOfStudents = 0;
        double totalGrade = 0;

        while(file.hasNextInt()){ //while the file has more data, keep running
            int iD = file.nextInt();

            if(iD == 0) { //case for when the end of a class is reached
                //varTest(totalGrade, noOfStudents);
                System.out.printf("Class average: %.2f%n", totalGrade / noOfStudents);
                if(!file.hasNextInt())
                    System.exit(0);
                int classNo = file.nextInt();

                //printing the header for a class, not the Java kind
                System.out.println("\nGrade Data For Class " + classNo);
                System.out.println(" ID   Programs  Midterm  Final  Weighted Average  Programs grade");
                System.out.println(" --   --------  -------  -----  ----------------  --------------");
                iD = file.nextInt();

                //resets variables used in total weighted class average calculation for next class
                noOfStudents = 0;
                totalGrade = 0;
            }
            //gets grades for a student from courseData.txt, and calculates weighted average
            int programsGrade = file.nextInt();
            int midtermGrade = file.nextInt();
            int finalGrade = file.nextInt();
            double average = (proWeight*programsGrade + midtermWeight*midtermGrade
                    + finalExamWeight*finalGrade);

            //adds weighted average for current student to total class grade
            totalGrade += average;

            //defines if the student passed, set to fail if grade is below a 70%
            String passFail = "Fail";
            if(programsGrade >= 70)
                passFail = "Pass";

            //printing the current student's data
            System.out.printf("%d     %d       %d       %d        %.2f             %s   %n"
                    , iD, programsGrade, midtermGrade, finalGrade, average , passFail);
            noOfStudents++;
        }
    }
    //method used for testing, not used in final program
    public static void varTest(double totalGrade, int noOfStudents){
        System.out.println(totalGrade); //test
        System.out.println(noOfStudents); //test
        System.out.println("Reached the end of a class"); //test
    }
}
