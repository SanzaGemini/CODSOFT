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
}
