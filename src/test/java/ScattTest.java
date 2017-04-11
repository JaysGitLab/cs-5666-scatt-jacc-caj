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
    @Test(expected = IllegalArgumentException.class)
    public void testBehaviorWhenFilePathIsNull(){
        Scatt scatt = new Scatt(new FileChooser() {
            @Override
            public File getDirectoryFromUser() {
                return null;
            }
        });
        scatt.generateReport();
    }

    /**
     * Test behavior in best case. File path to directory full of Sb2s.
     */
    @Test
    public void testFilePathGoodCase() {
        testEndToEnd("GoodSb2Dir");
    }

    /**
     * Test behavior when a .sb2 does not contain a project.json.
     */
    @Test
    public void testBehaviorWithMissingJson(){
        testEndToEnd("MissingJson");
    }

    /**
     * A method to make end to end tests easy.  Make a directory containing
     * the test material in the src/test/resources.  Say you name it TestCaseDir.
     * Then put a file called TestCaseDir.txt containing the expected report text
     * src/test/resources.  Then call testEndToEnd("TestCaseDir") and this method
     * will make sure that the actual output matches the expected output.
     * @param testDirName The name of the directory in src/test/resources/ from which
     *    to generate a report.
     */
    private void testEndToEnd(String testDirName) {
        File testDir = new File(Utils.getTestResourcePath(testDirName));
        Scatt scatt = new Scatt(new FileChooser() {
            @Override
            public File getDirectoryFromUser() {
                return testDir;
            }
        });
        File reportFile = new File(testDir, testDir.getName() + Reporter.REPORT_SUFFIX);
        reportFile.delete();

        scatt.generateReport();
        String expected = "";
        String actual = "";
        try {
            actual = Utils.getFileContents(reportFile.getAbsolutePath());
        } catch (IOException e) {
            fail("The test failed to generate a report.");
        }
        try {
            String expectedFilePath = Utils.getTestResourcePath(testDir.getName() + "_Report.txt");
            expected = Utils.getFileContents(expectedFilePath);
        } catch (IOException e) {
            fail("You need to put a file called " + testDir.getName() + "_Report.txt containing"
                 + " the expected report contents in the test resources directory");
        }
        if (!actual.equals(expected)) {
            Utils.diffStrings(actual, expected);
        }
        assertEquals(expected, actual);
    }
}
