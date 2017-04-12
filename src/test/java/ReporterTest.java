import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.io.StringWriter;
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
        List<Sb2> sb2List = new ArrayList<Sb2>();
        Sb2 wizardSb2 = new Sb2(Utils.getWizardJSONObject(), "WizardProject");
        sb2List.add(wizardSb2);

        StringWriter sw = new StringWriter();
        Reporter reporter = new Reporter();
        new Reporter().writeReport(sw, sb2List);
        String actualOutput = sw.toString();
        String expectedOutput = Utils.getResourceContent("WizardReport.txt");
        if (!actualOutput.equals(expectedOutput)) {
            Utils.diffStrings(expectedOutput, actualOutput);
        }
        assertEquals(expectedOutput, actualOutput);
    }
}
