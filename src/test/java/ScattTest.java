import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
import org.junit.Test;
//import java.io.File;
import java.net.URL;
import org.json.JSONObject;
import java.util.Arrays;
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
     * Get the JSONObject for the Wizard project.
     * @return the JSONObject for the Wizard project.
     */
    private JSONObject getWizardJSONObject() {
        String filePath = getTestResourcePath("project.json");
        String jsonString = Sb2.getFileContents(filePath);
        return Sb2.createJSONObject(jsonString);
    }
    /**
     * Test getJSONObject.
     */
    @Test
    public void testGetJSONObject() {
        JSONObject jsonObj = getWizardJSONObject();
        assertTrue(jsonObj instanceof org.json.JSONObject);
    }
    /**
     * Test getSpriteNames.
     */
    @Test
    public void testGetSpriteNames() {
        Sb2 sb2 = new Sb2(getWizardJSONObject());
        String[] expected = {"Wizard Girl", "Creature", "Instructions"};
        String[] actual = sb2.getSpriteNames();
        assertTrue(sameContents(expected, sb2.getSpriteNames()));
    }
    /**
     * Test whether arrays have same contents regardless of order.
     * @param a One array.
     * @param b another array.
     * @return whether a and b have same contents
     */
    private boolean sameContents(Comparable[] a, Comparable[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }
    /**
     * Test getScriptCountForSprite.
     */
    @Test
    public void testGetScriptCountForSprite1() {
        Sb2 sb2 = new Sb2(getWizardJSONObject());
        assertEquals(sb2.getScriptCountForSprite("Wizard Girl"), 3);
        assertEquals(sb2.getScriptCountForSprite("Creature"), 4);
        assertEquals(sb2.getScriptCountForSprite("Instructions"), 0);
    }

}
