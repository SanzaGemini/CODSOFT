package codsoft.org;

import java.io.InputStream;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Scanner;

public class StudentGradeCalculator {
    private final Scanner scanner;

    List<String> subjects = List.of("Maths","Eng","LO","Geo","Hist","Life","Physc","Tour","Bus");
    HashMap<String,Integer> studentMarks = new HashMap<>();
    public StudentGradeCalculator(){
        this.scanner = new Scanner(System.in);
    }

    public StudentGradeCalculator(InputStream in){
        this.scanner = new Scanner(in);
    }

    public String getSubject() {
        System.out.println("Enter Subject: ");
        String subject = scanner.nextLine();
        return getValidateSubject(subject).equalsIgnoreCase("Invalid Subject") ? getSubject(): subject;
    }

    public String getValidateSubject(String subject) {
        for (String sub: subjects) {
            if (sub.equalsIgnoreCase(subject)) {
                return sub;
            }
        }
        return "Invalid Subject";
    }

    public boolean ValidateMarks(int marks) {
        return marks <=101 && marks >= 0;
    }

    public int getMarks() {
        System.out.println("Enter Marks: ");
        int marks;
        try{
         marks = Integer.parseInt(scanner.nextLine());
        } catch (IllegalFormatConversionException e){
            System.out.println("Please Enter A digit");
            return  getMarks();
        }
        return ValidateMarks(marks) ? marks: getMarks();
    }

    public int getTotalMarks() {
        int total = 0;
        for (Integer mark: studentMarks.values()
             ) {
            total= total + mark;
        }
        return total;
    }

    public void addSubjectAndMarks(String subject,int marks){
        studentMarks.put(subject,marks);
    }

    public int getAverage(int totalMarks) {
        return totalMarks/studentMarks.size();
    }

    public String getGrade(int marks) {
        if (marks >= 80) {
            return "Excellent";
        } else if (marks>=50) {
            return "Adequate";
        } else if (marks>=30) {
            return "Moderate";
        } else return "Fail";
    }

    public void displayWelcome(){
        System.out.println("Welcome To The Student Grade Calculator.");
        System.out.println("These are the available subjects on the system:");
        int count = 1;
        for (String subject: subjects
             ) {
            System.out.println(count +": "+ subject.toUpperCase());
            count++;
        }
        System.out.println();
    }

    public boolean addAnotherSubject(){
        System.out.println("Would You Like To Add Another Subject? (Yes/Y or No/N");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("y");
    }
    public void displayMarks(){
        System.out.println("===================\nSubjects And Marks");
        System.out.println("======================");
        for (String subject: studentMarks.keySet()
             ) {
            System.out.println(subject.toUpperCase()+"||"+studentMarks.get(subject));
            System.out.println("======================");
        }
        int total = getTotalMarks();
        int avg = getAverage(total);
        String grade = getGrade(avg);

        System.out.println("Total: " + total);
        System.out.println("Average: " + avg);
        System.out.println("Final Grade: " + grade);


    }

    public static void main(String[] args) {
        StudentGradeCalculator sGC = new StudentGradeCalculator();
        sGC.displayWelcome();
        do {
            String subject = sGC.getSubject();
            int marks = sGC.getMarks();
            sGC.addSubjectAndMarks(subject, marks);
        } while (sGC.addAnotherSubject());

        sGC.displayMarks();


    }
}
