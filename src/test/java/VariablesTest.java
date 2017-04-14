import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.IOException;

/**
 * This test class is meant to test that our Sb2 program can correctly count
 * the number of global variables, script variables, and sprite variables.
 * @version 1
 * @author James Ward
 */

public class VariablesTest {
    /**
     * Test that the Sb2 can accurately count the number of global variables.
     */
    @Test
    public void testGlobalVariablesCount() {
        Sb2 scattariaSb2 = new Sb2(Utils.getScratchariaJSONObject());
        int actual = scattariaSb2.getGlobalVariableCount();
        int expected = 23;
        assertEquals(expected, actual); 
    }
    /**
     * Test that the Sb2 can accurately count variables in sprites.
     * @throws IOException if looking for sprite that doesnt exist
     */
    @Test
    public void testSpriteVariableCount() throws IOException {
        Sprites scratchariaSprites = new Sprites(Utils.getScratchariaJSONObject());
        int actual;
        try {
            actual = scratchariaSprites.getSpriteVariableCount("Sun2");
        } catch (IOException e) {
            throw new IOException("Wrong sprite name");
        }
        int expected = 2;
        assertEquals(expected, actual);
    }
}
