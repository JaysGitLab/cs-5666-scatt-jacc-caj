import java.net.URL;
import org.json.JSONObject;
import java.util.Arrays;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

/**
 * @version 1.0
 * @author B. Clint Hall
 */
public class Utils {
    /**
     * Get a string representing a path to a project test resource.
     * Project test resources should be put in src/test/resources.
     * @param resName The name of the resource file
     * @return String representation of path to resource
     */
    static String getTestResourcePath(String resName) {
        // Thank you James Lorenzen
        // http://jlorenzen.blogspot.co.uk/2007/06/proper-way-to-access-file-resources-in.html
        URL url = new Utils().getClass().getResource("/" + resName);
        String filePath = url.getFile();
        return filePath;
    }

    /**
     * Given a file path return a String of file contents.
     * @param pathStr Path to the file
     * @return contents of file
     * @throws IOException If there is a problem reading the file.
     */
    public static String getFileContents(String pathStr) throws IOException {
        Path path = Paths.get(pathStr);
        return Files.lines(path).collect(Collectors.joining("\n"));
    }
    /**
     * Get the contents of a resource file as a string.
     * @param resourceName The name of the resource file.
     * @return The contents of the file as a String.
     */
    static String getResourceContent(String resourceName) {
        String filePath = getTestResourcePath(resourceName);
        String str = Sb2.getFileContents(filePath);
        return str;
    }        
    
    /**
     * Get the JSONObject for the Wizard project.
     * @return the JSONObject for the Wizard project.
     */
    static JSONObject getWizardJSONObject() {
        return Sb2.createJSONObject(getResourceContent("project.json"));
    }
    /**
     * Test whether arrays have same contents regardless of order.
     * @param a One array.
     * @param b another array.
     * @return whether a and b have same contents
     */
    static boolean sameContents(Comparable[] a, Comparable[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

}
