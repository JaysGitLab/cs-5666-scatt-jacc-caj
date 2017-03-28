import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
import org.junit.Test;
//import java.io.File;
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
     * Test sb2 constructor with valid file path.
     */
/*    @Test
    public void testSb2Constructor1() {
        String filePath = Utils.getTestResourcePath("WizardSpells.sb2");
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
        String filePath = Utils.getTestResourcePath("test.zip");
        String destPath = Utils.getTestResourcePath("sb2extract");
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
        String filePath = Utils.getTestResourcePath("DummyForTestGetFileContents1.txt");
        String contents = Sb2.getFileContents(filePath);
        assertEquals("You got the contents of DummyForTestGetFileContents1.txt", contents);
    }
    /**
     * Test getJSONObject.
     */
    @Test
    public void testGetJSONObject() {
        JSONObject jsonObj = Utils.getWizardJSONObject();
        assertTrue(jsonObj instanceof org.json.JSONObject);
    }


}
