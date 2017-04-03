import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import org.json.JSONObject;
import java.io.IOException;
import java.util.stream.Collectors;


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
    @Test
    public void testSb2Constructor1() {
        String filePath = Utils.getTestResourcePath("WizardSpells.sb2");
        try {
            Sb2 wizardSpells = new Sb2(filePath);
            assertTrue(wizardSpells.getJSONObject() instanceof org.json.JSONObject);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Constructor threw an error");
        }
    }

    /**
     * Test extractSb2 with valid path.
     */
    @Test
    public void testExtractSb2() {
        String filePath = Utils.getTestResourcePath("WizardSpells.sb2");
        String content = "";
        try {
            content = Extractor.getProjectJSON(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePathStr = Utils.getTestResourcePath("project.json");
        try {
            String expectedContent = Files.readAllLines(Paths.get(filePathStr))
                	.stream().collect(Collectors.joining("\n"));
            assertEquals(new JSONObject(expectedContent).toString(),
                         new JSONObject(content).toString());
        } catch (IOException e) {
            fail("Exception reading test resource file");
        }
    }

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
    /**
     * Early Method for testing GUI
     */
    /*@Test
    public void testGui() {
       // Scatt s = new Scatt();
       // s.showUI();
       // String testFile="src/test/java/TestUtils.java";
       // File f = new File(testFile); 
       // FileChooser fc = new FileChooser();
       // fc.setFile(f);
       // assertEquals(fc.getPath(),testFile);
    }*/
}
