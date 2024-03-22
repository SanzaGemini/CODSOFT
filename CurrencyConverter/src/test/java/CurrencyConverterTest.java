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

}
