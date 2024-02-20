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
}
