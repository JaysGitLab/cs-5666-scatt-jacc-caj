import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.net.URL;
import org.json.JSONObject;

/**
 * @version 1.0
 * @author Clint Hall
 * @author Chris Waldon
 * @author James Ward
 * @author Erik Cole
 */
public class ScattTest {

    /**
     * Test Sb2.getSpriteCount.
     */
   /* @Test
    public void testGetSpriteCount() {
        String filePath = this
            .getClass()
            .getResource("WizardSpells.sb2")
            .getFile();
        Sb2 wizardSpells = new Sb2(filePath);
        int spriteCount = wizardSpells.getSpriteCount();
        assertTrue(spriteCount == 3);
    }*/
    /**
     * Test sb2 constructor with valid file path.
     */
    /*@Test
    public void testSb2Ctor1() {
        URL url = this.getClass().getResource("/WizardSpells.sb2");
        String filePath = url.getFile();
        Sb2 wizardSpells = new Sb2(filePath);
        assertTrue(wizardSpells.getJSONObject() instanceof org.json.JSONObject);
    }*/
    /**
     * Test getJSONObject.
     */
    @Test
    public void testGetJSONObject() {
        URL url = this.getClass().getResource("/project.json");
        String filePath = url.getFile();
        JSONObject jsonObj = Sb2.getJSONObject(filePath);
        assertTrue(jsonObj instanceof org.json.JSONObject);
    }
        
    /**
     * Test getFileContents with valid file path.
     */
    @Test
    public void testGetFileContents1() {
        String filePath = this
            .getClass()
            .getResource("/DummyForTestGetFileContents1.txt")
            .getFile();
        String contents = Sb2.getFileContents(filePath);
        assertEquals("You got the contents of DummyForTestGetFileContents1.txt", contents);
    }
}
