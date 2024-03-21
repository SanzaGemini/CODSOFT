import java.io.InputStream;
import java.util.Scanner;

public class StudentGradeCalculator {
    private final Scanner scanner;
    public StudentGradeCalculator(){
        this.scanner = new Scanner(System.in);
    }

    public StudentGradeCalculator(InputStream in){
        this.scanner = new Scanner(in);
    }

}
