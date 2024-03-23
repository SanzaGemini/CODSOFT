import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    // Scanner object for reading input from the user
    private final Scanner scanner;

    // Random object for generating random numbers
    private final Random random = new Random();

    // Variable to store the score
    private int score;

    /**
     * Constructs a new NumberGame object with the default InputStream (System.in).
     * This constructor initializes the scanner object with the default InputStream (System.in).
     */
    public NumberGame() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructs a new NumberGame object with the specified InputStream.
     * This constructor initializes the scanner object with the specified InputStream.
     *
     * @param inputStream The InputStream to be used by the scanner.
     */
    public NumberGame(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Generates a random number between 0 (inclusive) and the specified maximum number (exclusive).
     *
     * @param maxNumber The upper bound (exclusive) of the random number to be generated.
     * @return The randomly generated number.
     */
    public int generateNumber(int maxNumber) {
        return random.nextInt(maxNumber);
    }

    /**
     * Prompts the user to guess a number and validates the input.
     * This method displays a message asking the user to guess a number,
     * reads the input from the console, and validates it.
     * If the input is valid, it returns the answer.
     * If the input is invalid, the user is prompted to enter a valid input again.
     *
     * @return The validated answer entered by the user.
     */
    public String getAnswer() {
        try {
            // Prompt the user to guess the number
            System.out.println("Guess The Number?");

            // Read the input from the console
            String answer = scanner.nextLine();

            // Validate the input
            return validateInput(answer) ? answer : getAnswer();
        } catch (NoSuchElementException e) {
            // If an exception occurs (e.g., end of input), print the error message and return null
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Validates the user input to ensure it consists of digits only.
     * This method checks if the provided user input contains only digits (0-9).
     *
     * @param userInput The input to be validated.
     * @return True if the input consists of digits only; otherwise, false.
     */
    public boolean validateInput(String userInput) {
        // Use regular expression to check if the input consists of digits only
        return userInput.matches("\\d+");
    }

    /**
     * Validates the user's guess against the target number and provides feedback.
     * This method compares the user's guess with the target number and provides feedback
     * indicating whether the guess is higher, lower, or correct.
     *
     * @param guess The user's guess as a string.
     * @param number The target number to be guessed.
     * @return True if the guess is correct; otherwise, false.
     * @throws NumberFormatException if the user's guess cannot be parsed as an integer.
     */
    public boolean validateGuess(String guess, int number) throws NumberFormatException {
        // Parse the user's guess to an integer
        int guessNumber = Integer.parseInt(guess);

        // Compare the user's guess with the target number
        if (compareTo(guessNumber, number) == 0) {
            // If the guess is correct, return true
            return true;
        } else if (compareTo(guessNumber, number) < 0) {
            // If the guess is lower than the target number, print "Lower" and return false
            System.out.println("Lower");
            return false;
        }

        // If the guess is higher than the target number, print "Higher" and return false
        System.out.println("Higher");
        return false;
    }

    /**
     * Compares the user's guess with the target number.
     * This method compares the user's guess with the target number and returns
     * a value indicating whether the guess is lower, equal to, or higher than
     * the target number.
     *
     * @param guess The user's guess.
     * @param number The target number.
     * @return A short value indicating the comparison result:
     *         - Negative if the guess is lower than the target number.
     *         - Zero if the guess is equal to the target number.
     *         - Positive if the guess is higher than the target number.
     */
    public short compareTo(int guess, int number) {
        // Calculate the difference between the guess and the target number
        int difference = number - guess;

        // Convert the difference to a short value
        return (short) difference;
    }

    /**
     * Calculates the score based on the game result.
     * This method calculates the score based on the result of the game.
     * If the user has guessed the number correctly, the score is increased by 1.
     * If the user has not guessed the number correctly, the score is decreased by 1.
     *
     * @param results The result of the game (e.g., "You have guessed correctly").
     * @return The score calculated based on the game result.
     */
    public int calcScore(String results) {
        // Check if the user has guessed correctly
        if (results.equals("You have guessed correctly")) {
            // If the user has guessed correctly, return 1 (increased score)
            return 1;
        }

        // If the user has not guessed correctly, return -1 (decreased score)
        return -1;
    }

    /**
     * Determines the game result based on the number of guesses left.
     * This method determines the result of the game based on the number of guesses left.
     * If the user still has guesses left, the result is "You have guessed correctly".
     * If the user has run out of guesses, the result is "You are out of guesses".
     *
     * @param guessLeft The number of guesses left.
     * @return The result of the game based on the number of guesses left.
     */
    public String results(int guessLeft) {
        // Check if the user still has guesses left
        if (guessLeft > 0) {
            // If the user has guesses left, return "You have guessed correctly"
            return "You have guessed correctly";
        }

        // If the user has run out of guesses, return "You are out of guesses"
        return "You are out of guesses";
    }

    /**
     * Prompts the user if they want to play the game again.
     * This method displays a message asking the user if they want to play the game again,
     * reads the input from the console, and returns true if the user wants to play again (input is "Y" or "y"),
     * or false if the user does not want to play again (input is anything other than "Y" or "y").
     *
     * @return True if the user wants to play again; otherwise, false.
     */
    public boolean playAgain() {
        // Prompt the user if they want to play again
        System.out.println("Would you like another try? Y/N");

        // Read the input from the console and return true if it's "Y" or "y", otherwise return false
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    /**
     * Displays the current score.
     * This method constructs a string containing the current score and returns it.
     *
     * @return A string representing the current score.
     */
    public String displayScore() {
        // Construct a string containing the current score
        return "Current Score: " + score;
    }

    /**
     * Plays the number guessing game.
     * This method initializes the game parameters, such as the number of guesses allowed and the target number.
     * It then repeatedly prompts the user for guesses until either the user guesses the correct number or runs out of guesses.
     * After each guess, the method updates the game's score based on the result.
     */
    private void play() {
        // Initialize game parameters
        int guessLeft = 8; // Number of guesses allowed
        int number = generateNumber(10); // Generate a random number between 0 and 9
        String answer;

        // Continue the game until the user guesses the correct number or runs out of guesses
        do {
            System.out.println("Number Of Guesses Left: " + guessLeft);
            // Decrement the number of guesses left
            guessLeft--;

            // Prompt the user for a guess
            answer = getAnswer();

            // Validate the user's guess and check if it matches the target number
        } while (!validateGuess(answer, number) && guessLeft != 0);

        // Determine the game result based on the number of guesses left
        String results = results(guessLeft);

        // Update the game's score based on the result
        this.score += calcScore(results);
    }

    /**
     * The entry point of the NumberGame program.
     * This method initializes the game and starts the game loop.
     * It prompts the user to play the game, plays the game, and then asks if the user wants to play again.
     * After each game iteration, it displays the current score.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize variables
        boolean play = true; // Flag to control the game loop
        NumberGame numberGame = new NumberGame(); // Create a new instance of the NumberGame

        // Display a welcome message
        System.out.println("Hi :)\nWelcome To Number Game.\n");

        // Game loop
        while (play) {
            // Play the game
            numberGame.play();

            // Ask if the user wants to play again
            play = numberGame.playAgain();

            // Display the current score
            System.out.println(numberGame.displayScore());
        }
    }

}
