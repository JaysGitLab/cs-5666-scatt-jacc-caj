import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @version 1.0
 * @author Clint Hall
 * @author Chris Waldon
 * @author James Ward
 * @author Erik Cole
 */
public class ScattTest {

    /**
     * Just a dummy test for a dummy method.
     */
    @Test
    public void testHello() {
        Scatt s = new Scatt();
        assertEquals(s.hello(), "Hello from Scatt");
    }
}
