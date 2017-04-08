import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
        int expectedGlobVar = scattariaSb2.getGlobalVariableCount();
        int actual = 23;
        assertEquals(expectedGlobVar, actual); 
    }
    /**
     * Test that the Sb2 can accurately count variables in sprites
     */
    @Test
    public void testSpriteVariableCount() throws IOException {
        Sprites scratchariaSprites = new Sprites(Utils.getScratchariaJSONObject());
        try {
        int expectedSpriteVar = scratchariaSprites.getSpriteVariableCount("Sun2");
        } catch (IOException) {
            throw new IOException("Wrong sprite name");
        }
        int actual = 2;
        assertEquals(actual, expectedSpriteVar);
    }
}
