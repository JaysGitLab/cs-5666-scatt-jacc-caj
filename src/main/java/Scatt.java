import java.io.File;
/**
 * @version 1.0
 * @author Clint Hall
 * @author Chris Waldon
 * @author James Ward
 * @author Erik Cole
 */
public class Scatt {
    String sb2dir;
    FileChooser fileChooser;

    /**
     * Constructor for production use.  Uses a GuiFileChooser for
     * user to select path.
     */
    public Scatt() {
        this(new GuiFileChooser());
    }
    /**
     * Constructor for test methods.  Allows us to use a dummy FileChooser that
     * doesn't actually use a GUI.
     * @param fileChooser An implementation of FileChooser
     */
    public Scatt(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
    /**
     * Just a dummy main method for now.
     * @param args command line arguments
     */
    public static void main(String... args) {
        Scatt sc = new Scatt();
        sc.showUI();
    }
   /**
    * Creating UI and getting filepath for directory.
    */

    public void showUI() {   
        File file = fileChooser.getDirectoryFromUser();
        System.out.println(file.getPath());
    }

    /**
     * Generate report for Sb2's in targetDirectory.
     */
    public void generateReport() {

    }
}
