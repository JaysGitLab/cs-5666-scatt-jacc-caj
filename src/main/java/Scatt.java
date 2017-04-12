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
    private int reporterFlags;

    /**
     * Constructor for production use.  Uses a GuiFileChooser for
     * user to select path.
     */
    public Scatt() {
        this(new GuiFileChooser(), Reporter.REPORT_ALL);
    }
    /**
     * Constructor for test methods.  Allows us to use a dummy FileChooser that
     * doesn't actually use a GUI.
     * @param fileChooser An implementation of FileChooser
     */
    public Scatt(FileChooser fileChooser, int reporterFlags) {
        sb2Dir = fileChooser.getDirectoryFromUser();
        this.reporterFlags = reporterFlags;
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
