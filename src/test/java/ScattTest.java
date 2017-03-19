import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.net.URL;
import java.io.File;
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
     * Get a string representing a path to a project test resource.
     * Project test resources should be put in src/test/resources.
     * @param resName The name of the resource file
     * @return String representation of path to resource
     */
    private String getTestResource(String resName) {
        URL url = this.getClass().getResource("/" + resName);
        String filePath = url.getFile();
        return filePath;
    }
    /**
     * Test sb2 constructor with valid file path.
     */
    @Test
    public void testSb2Ctor1() {
        String filePath = getTestResource("WizardSpells.sb2");
        Sb2 wizardSpells = new Sb2(filePath);
        assertTrue(wizardSpells.getJSONObject() instanceof org.json.JSONObject);
    }
    /**
     * Test Sb2.getSpriteCount.
     */
    @Test
    public void testGetSpriteCount() {
        String filePath = getTestResource("WizardSpells.sb2");
        Sb2 wizardSpells = new Sb2(filePath);
        int spriteCount = wizardSpells.getSpriteCount();
        assertTrue(spriteCount == 3);
    }

    /**
     * Test extract Sb2. With valid path.
     */
    @Test
    public void testExtractSb2() {
        String filePath = getTestResource("test.zip");
        String destPath = getTestResource("sb2extract");
        Sb2.extractSb2(filePath, destPath);
        File destDir = new File(destPath);
        String[] childrenNames = {"a.txt", "b.txt", "c.txt"};
        assertEquals(destDir.list(), childrenNames);
    }
    /**
     * Test getFileContents with valid file path.
     */
    @Test
    public void testGetFileContents1() {
        String filePath = getTestResource("DummyForTestGetFileContents1.txt");
        String contents = Sb2.getFileContents(filePath);
        assertEquals("You got the contents of DummyForTestGetFileContents1.txt", contents);
    }
    /**
     * Test getJSONObject.
     */
    @Test
    public void testGetJSONObject() {
        String filePath = getTestResource("project.json");
        String jsonString = Sb2.getFileContents(filePath);
        JSONObject jsonObj = Sb2.getJSONObject(jsonString);
        assertTrue(jsonObj instanceof org.json.JSONObject);
    }


}
