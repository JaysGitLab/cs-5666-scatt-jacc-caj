import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
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
        Sb2 wizardSpells = new Sb2(filePath);
        assertTrue(wizardSpells.getJSONObject() instanceof org.json.JSONObject);
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
     * Test behavior when file path is null.
     */
   /* @Test
    public void testBehaviorWhenFilePathIsNull(){
        
    }*/

    /**
     * Test behavior in best case. File path to directory full of Sb2s.
     */
    @Test
    public void testFilePathGoodCase() {
        File targetDirectory = new File(Utils.getTestResourcePath("GoodSb2Dir"));
        File reportFile = new File(targetDirectory, "scattReport.txt");
        reportFile.delete();
        Scatt scatt = new Scatt(new FileChooser() {
            @Override
            public File getDirectoryFromUser() {
                return targetDirectory;
            }
        });
        scatt.generateReport();
        String expected = Utils.getResourceContent("GoodSb2Dir/scattReport.txt");
        String actual = Utils.getResourceContent("GoodSb2Dir.txt");
        assertEquals(expected, actual);
    }
}
