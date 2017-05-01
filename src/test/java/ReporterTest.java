import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.io.StringWriter;
import java.io.IOException;
import java.io.File;
/**
 * This test class is meant to test that the reporter outputs the appropriate
 * information in relation to the ouptut from SB2 and the given test JSON
 * file.
 * @version 1
 * @author James Ward
 * @author Clint Hall
 */
public class ReporterTest {
    /**
     * Test the Reporter with one straightforward Sb2. Note well: the
     * actual output will end with a new line character. So, be sure
     * the expected output also ends with a new line character.
     */
    @Test
    public void testWizardReport() {
        testReporterConfig(Reporter.REPORT_ALL, "WizardReport.txt");
    }

    /**
     * Test minimal report.  Tell reporter to only print number of projects.
     */
    @Test
    public void testMinimalReport() {
        testReporterConfig(Reporter.NUM_PROJECTS, "MinimalReport");
    }

    /**
     * Test NUM_PROJECTS and PROJECT_HEADERS.
     */
    @Test
    public void testThroughProjectHeaders() {
        testReporterConfig(
            Reporter.NUM_PROJECTS | Reporter.PROJECT_HEADERS,
            "WithProjectHeaders");
    }

    /**
     * Test odd combo.
     */
    @Test
    public void testOddCombo() {
        testReporterConfig(
            Reporter.NUM_PROJECTS 
            | Reporter.PROJECT_HEADERS
            | Reporter.SPRITE_HEADERS
            | Reporter.SCRIPT_LENGTHS,
            "OddCombo");
    }

    /**
     * This makes the resource directory unwritable in order to
     * generate an exception.
     */
    @Test(expected = IOException.class)
    public void testUnwritableDir() {
        // remove write permission from directory
        String directory = Utils.getTestResourcePath("GoodSb2Dir_unwritable");
        File resourceDir = new File(directory);
        // remove write for all users
        resourceDir.setWritable(false, false);
        System.out.println("Removing write from " + resourceDir);

        //call testReporterConfig
        ScattTest.prepEndToEnd(resourceDir, Reporter.REPORT_ALL);
        //set write permission on directory
        resourceDir.setWritable(true, true);
    }

    /**
     * Test the reporter configuration flags.
     * @param bitVector the bit vector specifying what to report.
     * @param expectedOutputFileName The name of the file in
     *          src/test/resources/ReportTestFiles in which the
     *          expected output can be found.
     */
    private void testReporterConfig(int bitVector, String expectedOutputFileName) {
        List<Sb2> sb2List = new ArrayList<Sb2>();
        Sb2 wizardSb2 = new Sb2(Utils.getWizardJSONObject(), "WizardProject");
        sb2List.add(wizardSb2);
        StringWriter sw = new StringWriter();
        Reporter reporter = new Reporter(bitVector);
        reporter.writeReport(sw, sb2List);
        String actualOutput = sw.toString();
        //Uncomment the following line when you want actual output printed to file.
        //Utils.writeToFile("Actual_" + expectedOutputFileName, actualOutput);
        String expectedOutput = Utils.getResourceContent(
            "ReportTestFiles/" + expectedOutputFileName);
        if (!actualOutput.equals(expectedOutput)) {
            Utils.diffStrings(expectedOutput, actualOutput);
        }
        assertEquals(expectedOutput, actualOutput);
    }
}
