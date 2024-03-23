import codsoft.org.StudentGradeCalculator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


import static org.junit.jupiter.api.Assertions.*;

public class StudentGradeCalculatorTest {
    public StudentGradeCalculator studentGradeCalculator;
    @Test
    public void getSubject(){
        String guess = "Maths\n10\n";
        InputStream inputStream = new ByteArrayInputStream(guess.getBytes());

        studentGradeCalculator = new StudentGradeCalculator(inputStream);
        //Assert Subject
        assertEquals("Maths",studentGradeCalculator.getSubject());
    }

    @Test
    public void getSubjectInvalid(){
        String guess = "Cooking\nMaths\n";
        InputStream inputStream = new ByteArrayInputStream(guess.getBytes());

        studentGradeCalculator = new StudentGradeCalculator(inputStream);
        //Assert Subject
        assertEquals("Maths",studentGradeCalculator.getSubject());
    }
    @Test
    public void InvalidSubject(){

        studentGradeCalculator = new StudentGradeCalculator();
        //Assert Subject
        assertEquals("Invalid Subject",studentGradeCalculator.getValidateSubject("Softlife"));
    }

    @Test
    public void getMarks(){
        String guess = "10\n";
        InputStream inputStream = new ByteArrayInputStream(guess.getBytes());

        studentGradeCalculator = new StudentGradeCalculator(inputStream);
        //Assert Subject
        assertEquals(10,studentGradeCalculator.getMarks());
    }

    @Test
    public void getInvalidMarks(){
        String guess = "200\n10\n";
        InputStream inputStream = new ByteArrayInputStream(guess.getBytes());

        studentGradeCalculator = new StudentGradeCalculator(inputStream);
        //Assert Subject
        assertEquals(10,studentGradeCalculator.getMarks());
    }

    @Test
    public void calcTotalMarks(){
        studentGradeCalculator = new StudentGradeCalculator();
        addToMarks(studentGradeCalculator);
        assertEquals(100,studentGradeCalculator.getTotalMarks());
    }

    @Test
    public void calcAverMarks(){
        studentGradeCalculator = new StudentGradeCalculator();
        addToMarks(studentGradeCalculator);
        assertEquals(25,studentGradeCalculator.getAverage(studentGradeCalculator.getTotalMarks()));
    }

    @Test
    public void getGrade(){
        studentGradeCalculator = new StudentGradeCalculator();
        addToMarks(studentGradeCalculator);
        int avg = studentGradeCalculator.getAverage(studentGradeCalculator.getTotalMarks());
        assertEquals("Fail",studentGradeCalculator.getGrade(avg));
        assertEquals("Moderate",studentGradeCalculator.getGrade(45));
        assertEquals("Adequate",studentGradeCalculator.getGrade(60));
        assertEquals("Excellent",studentGradeCalculator.getGrade(85));

    }

    public void addToMarks(StudentGradeCalculator sGC){
        sGC.addSubjectAndMarks("maths",20);
        sGC.addSubjectAndMarks("aths",30);
        sGC.addSubjectAndMarks("mths",10);
        sGC.addSubjectAndMarks("mahs",40);
    }
}
