import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.File;

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
    /**
     * Early Method for testing GUI
     */
    @Test
    public void testGui() {
        Scatt s = new Scatt();
        FileChooser fc = new FileChooser();
        String testFile="src/test/java/TestUtils.java";
        File f = new File(testFile);
        fc.setFile(f);
        assertEquals(f.getPath(),testFile);
    }
}
