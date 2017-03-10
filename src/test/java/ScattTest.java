import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    /**
     * Test Sb2.getSpriteCount.
     */
    @Test
    public void testGetSpriteCount() {
        String filePath = "WizardSpells.sb2";
        Sb2 wizardSpells = new Sb2(filePath);
        int spriteCount = wizardSpells.getSpriteCount();
        assertTrue(spriteCount == 3);
    }
    /**
     * Test sb2 constructor with valid file path.
     */
    @Test
    public void testSb2Ctor1() {
        String filePath = "WizardSpells.sb2";
        Sb2 wizardSpells = new Sb2(filePath);
        assertTrue(wizardSpells.getJSONObject() instanceof org.json.JSONObject);
    }
    /**
     * Test getFileContents with valid file path.
     */
    @Test
    public void testGetFileContents1() {
        String contents = Sb2.getFileContents("DummyForTestGetFileContents1.txt");
        assertEquals("You got the contents of DummyForTestGetFileContents1.txt", contents);
    }
}
