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
}
