import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NumberGameTest {

    @Test
    public void getGeneratesRandomNumber(){
        NumberGame numberGame = new NumberGame();
        Assertions.assertTrue(numberGame.generateNumber(9)<10);
    }
}
