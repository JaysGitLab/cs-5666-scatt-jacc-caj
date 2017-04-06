import java.io.File;
/**
 * Interface with a method that returns a file.
 * @version 1
 * @author Clint Hall
 */
public interface FileChooser {

    /**
     * Gets a file from the file chooser.
     * @return The file chosen
     */
    public File getDirectoryFromUser();
}

