import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
import org.junit.Test;
//import java.io.File;
import java.net.URL;
import org.json.JSONObject;
//import java.io.IOException;

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
    private String getTestResourcePath(String resName) {
        // Thank you James Lorenzen
        // http://jlorenzen.blogspot.co.uk/2007/06/proper-way-to-access-file-resources-in.html
        URL url = this.getClass().getResource("/" + resName);
        String filePath = url.getFile();
        return filePath;
    }
    /**
     * Test sb2 constructor with valid file path.
     */
/*    @Test
    public void testSb2Constructor1() {
        String filePath = getTestResourcePath("WizardSpells.sb2");
        try {
            Sb2 wizardSpells = new Sb2(filePath);
            assertTrue(wizardSpells.getJSONObject() instanceof org.json.JSONObject);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Constructor threw an error");
        }
    }*/
    /**
     * Test Sb2.countSprites.
     */
/*    @Test
    public void testCountSprites() {
        String filePath = getTestResourcePath("WizardSpells.sb2");
        try {
            Sb2 wizardSpells = new Sb2(filePath);
            int spriteCount = wizardSpells.countSprites();
            assertTrue(spriteCount == 3);
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue("Constructor threw an error", false);
        }
    }/*

    /**
     * Test extractSb2. With valid path.
     */
/*    @Test
    public void testExtractSb2() {
        String filePath = getTestResourcePath("test.zip");
        String destPath = getTestResourcePath("sb2extract");
        try {
            Sb2.extractSb2(filePath, destPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File destDir = new File(destPath);
        String[] childrenNames = {"a.txt", "b.txt", "c.txt"};
        assertEquals(destDir.list(), childrenNames);
    }*/
    /**
     * Test getFileContents with valid file path.
     */
    @Test
    public void testGetFileContents1() {
        String filePath = getTestResourcePath("DummyForTestGetFileContents1.txt");
        String contents = Sb2.getFileContents(filePath);
        assertEquals("You got the contents of DummyForTestGetFileContents1.txt", contents);
    }
    /**
     * Test getJSONObject.
     */
    @Test
    public void testGetJSONObject() {
        String filePath = getTestResourcePath("project.json");
        String jsonString = Sb2.getFileContents(filePath);
        JSONObject jsonObj = Sb2.createJSONObject(jsonString);
        assertTrue(jsonObj instanceof org.json.JSONObject);
    }


}
