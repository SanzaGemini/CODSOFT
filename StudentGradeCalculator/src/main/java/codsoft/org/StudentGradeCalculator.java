package codsoft.org;

import java.io.InputStream;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Scanner;

public class StudentGradeCalculator {
    // Scanner object for reading input from the user
    private final Scanner scanner;

    // List of subjects available in the system
    List<String> subjects = List.of("Maths", "Eng", "LO", "Geo", "Hist", "Life", "Physc", "Tour", "Bus");

    HashMap<String,Integer> studentMarks = new HashMap<>();
    /**
     * Constructs a new StudentGradeCalculator object with the default InputStream (System.in).
     * This constructor initializes the scanner object with the default InputStream (System.in).
     */
    public StudentGradeCalculator(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructs a new StudentGradeCalculator object with the specified InputStream.
     * This constructor initializes the scanner object with the specified InputStream.
     *
     * @param in The InputStream to be used by the scanner.
     */
    public StudentGradeCalculator(InputStream in){
        this.scanner = new Scanner(in);
    }


    /**
     * Prompts the user to input a subject and validates it.
     * This method displays a message asking the user to input a subject,
     * reads the input from the console, and validates it against the available subjects.
     * If the input subject is invalid, the user is prompted to enter a valid subject again.
     *
     * @return The validated subject entered by the user.
     */
    public String getSubject() {
        // Prompt the user to input a subject
        System.out.println("Enter Subject: ");

        // Read the input subject from the console
        String subject = scanner.nextLine();

        // Validate the input subject against the available subjects
        return getValidateSubject(subject).equalsIgnoreCase("Invalid Subject") ? getSubject() : subject;
    }


    /**
     * Validates the provided subject against the available subjects.
     * This method checks if the provided subject matches any of the available subjects.
     * If a match is found (case-insensitive), the matched subject is returned.
     * If no match is found, "Invalid Subject" is returned.
     *
     * @param subject The subject to be validated.
     * @return The validated subject if it matches any of the available subjects; otherwise, "Invalid Subject".
     */
    public String getValidateSubject(String subject) {
        // Iterate over the available subjects
        for (String sub : subjects) {
            // Check if the provided subject matches any of the available subjects (case-insensitive)
            if (sub.equalsIgnoreCase(subject)) {
                // If a match is found, return the matched subject
                return sub;
            }
        }
        // If no match is found, return "Invalid Subject"
        return "Invalid Subject";
    }


    /**
     * Validates the marks entered by the user.
     * This method checks if the entered marks fall within the valid range of 0 to 100.
     *
     * @param marks The marks to be validated.
     * @return True if the marks are within the valid range (0 to 100), false otherwise.
     */
    public boolean validateMarks(int marks) {
        // Check if the marks fall within the valid range (0 to 100)
        return marks >= 0 && marks <= 100;
    }


    /**
     * Prompts the user to input marks for a subject.
     * This method displays a message asking the user to input marks for a subject,
     * reads the input from the console, and validates it to ensure it's a digit.
     * If the input is not a digit, it prompts the user to enter a digit again.
     *
     * @return The marks entered by the user for the subject.
     */
    public int getMarks() {
        // Prompt the user to input marks
        System.out.println("Enter Marks: ");

        int marks;
        try {
            // Attempt to parse the input as an integer
            marks = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // If the input is not a digit, prompt the user to enter a digit again
            System.out.println("Please Enter A digit");
            return getMarks();
        }

        // Validate the marks entered by the user
        return validateMarks(marks) ? marks : getMarks();
    }


    /**
     * Calculates the total marks obtained across all subjects.
     * This method iterates over the marks stored in the studentMarks map
     * and calculates the total marks obtained across all subjects.
     *
     * @return The total marks obtained across all subjects.
     */
    public int getTotalMarks() {
        // Initialize total marks variable
        int total = 0;

        // Iterate over the marks stored in the studentMarks map
        for (Integer mark : studentMarks.values()) {
            // Accumulate the marks to calculate the total
            total += mark;
        }

        // Return the total marks obtained across all subjects
        return total;
    }


    /**
     * Adds a subject and corresponding marks to the student's record.
     * This method associates the provided subject with the specified marks
     * and adds them to the studentMarks map, which stores the subjects and their marks.
     *
     * @param subject The subject to be added.
     * @param marks The marks obtained in the subject to be added.
     */
    public void addSubjectAndMarks(String subject, int marks) {
        // Associate the provided subject with the specified marks and add them to the studentMarks map
        studentMarks.put(subject, marks);
    }


    /**
     * Calculates the average marks based on the total marks and the number of subjects.
     * This method calculates the average marks by dividing the total marks by the number of subjects.
     *
     * @param totalMarks The total marks obtained across all subjects.
     * @return The average marks calculated based on the total marks and the number of subjects.
     */
    public int getAverage(int totalMarks) {
        // Calculate the average marks by dividing the total marks by the number of subjects
        return totalMarks / studentMarks.size();
    }


    /**
     * Determines the grade based on the provided marks.
     * This method takes the marks as input and returns a corresponding grade based on the following criteria:
     * - Excellent: if marks are 80 or higher
     * - Adequate: if marks are between 50 and 79 (inclusive)
     * - Moderate: if marks are between 30 and 49 (inclusive)
     * - Fail: if marks are below 30
     *
     * @param marks The marks for which the grade is to be determined.
     * @return A String representing the grade based on the provided marks.
     */
    public String getGrade(int marks) {
        // Determine the grade based on the provided marks
        if (marks >= 80) {
            return "Excellent";
        } else if (marks >= 50) {
            return "Adequate";
        } else if (marks >= 30) {
            return "Moderate";
        } else {
            return "Fail";
        }
    }


    /**
     * Displays a welcome message and lists available subjects.
     * This method prints a welcome message to the user and lists the available subjects
     * that can be entered for calculating grades.
     */
    public void displayWelcome(){
        // Print a welcome message to the user
        System.out.println("Welcome To The Student Grade Calculator.");
        System.out.println("These are the available subjects on the system:");

        // Initialize a count to track the number of subjects
        int count = 1;

        // Iterate over each subject in the subjects list
        for (String subject: subjects) {
            // Print the subject along with its corresponding number (count)
            System.out.println(count +": "+ subject.toUpperCase());
            count++;
        }

        // Print a newline for formatting
        System.out.println();
    }


    /**
     * Prompts the user to decide whether to add another subject.
     * This method displays a message asking the user if they would like to add another subject,
     * and then reads the user's input from the console.
     *
     * @return True if the user chooses to add another subject by entering "Yes" or "Y" (case-insensitive),
     *         false otherwise.
     */
    public boolean addAnotherSubject(){
        // Prompt the user to decide whether to add another subject
        System.out.println("Would You Like To Add Another Subject? (Yes/Y or No/N)");

        // Read the user's input from the console
        String answer = scanner.nextLine();

        // Return true if the user enters "Yes" or "Y" (case-insensitive), false otherwise
        return answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("y");
    }

    /**
     * Displays the subjects, marks, total, average, and final grade for the student.
     * This method prints the subjects and corresponding marks stored in the studentMarks map.
     * It also calculates the total marks, average marks, and final grade based on the marks entered,
     * and displays them along with the subjects.
     */
    public void displayMarks(){
        // Print a header for the display
        System.out.println("===================\nSubjects And Marks");
        System.out.println("======================");

        // Iterate over each subject and its marks in the studentMarks map
        for (String subject: studentMarks.keySet()) {
            // Print the subject and its marks in uppercase
            System.out.println(subject.toUpperCase()+"||"+studentMarks.get(subject));
            System.out.println("======================");
        }

        // Calculate total marks, average marks, and final grade
        int total = getTotalMarks();
        int avg = getAverage(total);
        String grade = getGrade(avg);

        // Display the total marks, average marks, and final grade
        System.out.println("Total: " + total);
        System.out.println("Average: " + avg);
        System.out.println("Final Grade: " + grade);
    }

    /**
     * The main method to execute the student grade calculator program.
     * This method creates an instance of the StudentGradeCalculator class,
     * displays a welcome message, and then enters a loop to input subjects
     * and marks for each subject. It continues adding subjects and marks
     * until the user chooses to stop. Finally, it displays the entered marks
     * along with the calculated grades.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of StudentGradeCalculator
        StudentGradeCalculator sGC = new StudentGradeCalculator();

        // Display a welcome message to the user
        sGC.displayWelcome();

        // Enter a loop to input subjects and marks until the user chooses to stop
        do {
            // Prompt the user to input a subject
            String subject = sGC.getSubject();

            // Prompt the user to input marks for the subject
            int marks = sGC.getMarks();

            // Add the subject and marks to the calculator
            sGC.addSubjectAndMarks(subject, marks);
        } while (sGC.addAnotherSubject());

        // Display the entered marks along with the calculated grades
        sGC.displayMarks();
    }

}
