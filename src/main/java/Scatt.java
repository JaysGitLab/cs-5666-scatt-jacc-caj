import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @author Clint Hall
 * @author Chris Waldon
 * @author James Ward
 * @author Erik Cole
 */
public class Scatt {
    private final File sb2Dir;
    private final Notifier notifier;
    private int reporterFlags;

    /**
     * Constructor for production use.  Uses a GuiFileChooser for
     * user to select path.
     */
    public Scatt() {
        this(new GuiFileChooser(), new GuiNotifier(), Reporter.REPORT_ALL);
    }
    /**
     * Constructor for test methods.  Allows us to use a dummy FileChooser that
     * doesn't actually use a GUI.
     * @param fileChooser An implementation of FileChooser
     * @param reporterFlags An int representing a bit vector
     *    of flags to pass to the reporter, letting it know what
     *    you want it to print.  See the public constants in the Reporter class.
     */
    public Scatt(FileChooser fileChooser, int reporterFlags) {
        this(fileChooser, new ConsoleNotifier(), reporterFlags);
    }

    /**
     * Constructor with maximal flexibility.
     * @param fileChooser An implementation of FileChooser
     * @param notifier Am implementation of Notifier
     * @param reporterFlags An int representing a bit vector of flags that
     *	  you want to pass to the reporter.
     */
    public Scatt(FileChooser fileChooser, Notifier notifier, int reporterFlags) {
        sb2Dir = fileChooser.getDirectoryFromUser();
        this.reporterFlags = reporterFlags;
        this.notifier = new ConsoleNotifier();
    }
    /**
     * Just a dummy main method for now.
     * @param args command line arguments
     */
    public static void main(String... args) {
        Scatt sc = new Scatt();
        sc.generateReport();
    }

    /**
     * Generate report for Sb2's in targetDirectory.
     */
    public void generateReport() {
        if (sb2Dir == null) {
            throw new IllegalArgumentException("User clicked cancel or for "
                + "some other reason file chosen is null.");
        }
        File[] sb2Files = sb2Dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(".*\\.sb2");
            }
        });
        Arrays.sort(sb2Files, new FileComparator());
        List<Sb2> sb2s = new ArrayList<>();
        for (int i = 0; i < sb2Files.length; i++) {
            String sb2path = sb2Files[i].getAbsolutePath();
            sb2s.add(new Sb2(sb2path));
        }
        String reportPath = new File(sb2Dir, sb2Dir.getName()
            + Reporter.REPORT_SUFFIX).getAbsolutePath();
        Reporter reporter = new Reporter(reporterFlags);
        reporter.writeReport(reportPath, sb2s);
        notifier.notify("Report generated at " + reportPath);
    }
    /**
     * We need an OS neutral way to sort files for testing purposes.
     **/
    class FileComparator implements Comparator<File> {
        @Override
        public int compare(File f1, File f2) {
            return f1.getName().compareToIgnoreCase(f2.getName());
        }
        @Override
        public boolean equals(Object other) {
            return this == other;
        }
    }
}
