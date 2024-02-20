import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NumberGameTest {

    @Test
    public void getGeneratesRandomNumber(){
        NumberGame numberGame = new NumberGame();
        Assertions.assertTrue(numberGame.generateNumber(9)<10);
    }
    
    @Test
    public void getAnswer(){

        String guess = "Guess\n10\n";
        InputStream inputStream = new ByteArrayInputStream(guess.getBytes());

        numberGame = new NumberGame(inputStream);

        Assertions.assertEquals("10",numberGame.getAnswer());
    }

    @Test
    public void validateGuessDataType(){
        numberGame = new NumberGame();
        Assertions.assertFalse(numberGame.validateInput("Guess"));
        Assertions.assertTrue(numberGame.validateInput("1"));
        Assertions.assertTrue(numberGame.validateInput("100"));
        Assertions.assertFalse(numberGame.validateInput("13u"));
    }

    @Test
    public void compareTo(){
        numberGame = new NumberGame();
        Assertions.assertEquals(numberGame.compareTo(10,5),-5);
        Assertions.assertEquals(numberGame.compareTo(10,7),-3);
        Assertions.assertEquals(numberGame.compareTo(10,15),5);
    }

    @Test
    public void validateGuess(){
        numberGame = new NumberGame();

        Assertions.assertFalse(numberGame.validateGuess("10",200));
        Assertions.assertTrue(numberGame.validateGuess("20",20));
    }
}
