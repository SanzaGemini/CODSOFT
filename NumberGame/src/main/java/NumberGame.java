import java.util.Random;

public class NumberGame {

    Random random = new Random();

    public NumberGame(){

    }

    public static void main(String[] args) {

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
}
