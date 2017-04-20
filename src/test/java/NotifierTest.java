import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
NotifierTest ensures that we notify the user of generated reports.
@author Erik Cole
@author Chris Waldon
@version 0.1
*/
public class NotifierTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream realStdOut = System.out;

    /**
    This redirects stdout into a stream that we can evaluate.
    */
    @Before
    public void stealStdOut() {
        System.setOut(new PrintStream(outContent));
    }

    /**
    This restores stdout to the default.
    */
    @After
    public void resetStdOut() {
        System.setOut(realStdOut);
    }

    /**
    This checks that the e2e test notifies the user of where
    the report was created.
    */
    @Test
    public void checkForMessage() {
        ScattTest.testEndToEnd("GoodSb2Dir", 0b111_1111);
    	assertThat(outContent.toString(), containsString("Report generated at"));
    }

    /**
    Test that the notifier puts the correct message in the log.
    */
    @Test
    public void checkNotifier() {
        String message = "Report generated at /";
	Notifier n = new ConsoleNotifier();
	n.notify(message);
	assertThat(outContent.toString(), containsString(message));
    }
}
