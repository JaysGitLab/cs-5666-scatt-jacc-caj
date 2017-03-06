package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import scatt.Scatt;

public class ScattTest{
    @Test
    public void testHello(){
        Scatt s = new Scatt();
        assertEquals(s.hello(), "Hello from Scatt");
    }
}
