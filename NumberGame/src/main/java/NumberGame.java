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
}
