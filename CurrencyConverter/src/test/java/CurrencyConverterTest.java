import codsoft.org.CurrencyConverter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {

    @Test
    public void getBaseCurrency(){
        String guess = "USD\n";
        InputStream inputStream = new ByteArrayInputStream(guess.getBytes());

        CurrencyConverter converter = new CurrencyConverter(inputStream);

        assertEquals("USD",converter.getCurrency("BASE"));
    }

    @Test
    public void getInvalidBaseCurrency(){
        String guess = "CodeSoftCurrency\nUSD\n";
        InputStream inputStream = new ByteArrayInputStream(guess.getBytes());

        CurrencyConverter converter = new CurrencyConverter(inputStream);

        assertEquals("USD",converter.getCurrency("BASE"));
    }

    @Test
    public void getAmount(){
        String amount = "123\n";
        InputStream inputStream = new ByteArrayInputStream(amount.getBytes());

        CurrencyConverter converter = new CurrencyConverter(inputStream);

        assertEquals(123,converter.getAmount());
    }

    @Test
    public void getInvalidAmount(){
        String amount = "abc\n123\n";
        InputStream inputStream = new ByteArrayInputStream(amount.getBytes());

        CurrencyConverter converter = new CurrencyConverter(inputStream);

        assertEquals(123,converter.getAmount());
    }

    @Test
    public void calcAmount(){
        String amount = "10\n";
        InputStream inputStream = new ByteArrayInputStream(amount.getBytes());

        CurrencyConverter converter = new CurrencyConverter(inputStream);
        converter.setAmount(converter.getAmount());

        assertEquals(100,converter.calcAmount(1,10));
    }

}
