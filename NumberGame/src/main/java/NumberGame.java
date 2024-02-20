import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private final Scanner scanner;
    private final Random random = new Random();
    private int score;

    public NumberGame(){
        this.scanner = new Scanner(System.in);
    }

    public NumberGame(InputStream inputStream){
        this.scanner = new Scanner(inputStream);
    }

    public int generateNumber(int maxNumber) {
        return random.nextInt(maxNumber);
    }

    public String getAnswer() {
        String answer;
        try {
            System.out.println("Guess The Number?");
            answer = scanner.nextLine();
            return validateInput(answer) ? answer : getAnswer();
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean validateInput(String userInput) {
        return userInput.matches("\\d+");
    }

        public boolean validateGuess(String guess, int number) {
        int guessNumber = Integer.parseInt(guess);

        if(compareTo(guessNumber,number) ==0) return true;
        else if (compareTo(guessNumber,number) < 0){
            System.out.println("Lower");
            return false;
        }
        System.out.println("Higher");
        return false;
    }

    public short compareTo(int guess, int number) {
        return (short) (number - guess);
    }

     public int calcScore(String results){
        if (results.equals("You have guessed correctly")) {
          return 1;
        }
        return -1;
    }

    public String results(int guessLeft){
        if (guessLeft > 0) {
            return "You have guessed correctly";
        }
        return "You are out of guesses";
    }

    public boolean playAgain(){
        System.out.println("Would you like another try? Y/N");
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    public String displayScore() {
        return "Current Score: "+ score;
    }

    private void play(){
        int guessLeft = 8;
        int number = generateNumber(10);
        String answer;
        do{
            guessLeft--;
            answer = getAnswer();

        } while (!validateGuess(answer, number)&&guessLeft!=0);
        String results = results(guessLeft);
        this.score += calcScore(results);
    }

    public static void main(String[] args) {
        boolean play = true;
        NumberGame numberGame = new NumberGame();
        System.out.println("Hi :)\n Welcome To Number Game.\n");
        while (play){
            numberGame.play();
            play = numberGame.playAgain();
            System.out.println(numberGame.displayScore());
        }

    }
}
