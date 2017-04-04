import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
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
        //The last asserion in this method fails.
        
        List<Sb2> sb2List = new ArrayList<Sb2>();
        Sb2 wizardSb2 = new Sb2(Utils.getWizardJSONObject(), "WizardProject");
        sb2List.add(wizardSb2);

        StringWriter sw = new StringWriter();
        Reporter reporter = new Reporter();
        new Reporter().writeReport(sw, sb2List);
        String output = sw.toString();

        // Over the next few lines I write the output to a file so you can run
        // diff on it if you want.
        byte[] bytes = output.getBytes();
        try {
            Path path = Paths.get("testReportOutput.txt");
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String expectedOutput = Utils.getResourceContent("WizardReport.txt");
        //Now I split expected and actual into lines and compare each line.
        //Each line by line comparison passes.
        String[] expectedLines = expectedOutput.split("\n");
        String[] actualLines = output.split("\n");
        for (int i = 0; i < actualLines.length; i++) {
            assertEquals("Line " + i, expectedLines[i], actualLines[i]);
        }
        assertEquals(expectedOutput.length(), output.length());
        // As promised, this still fails.
        assertEquals(expectedOutput, output);
    }
}
