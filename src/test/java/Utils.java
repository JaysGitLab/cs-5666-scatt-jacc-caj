import java.net.URL;
import org.json.JSONObject;
import java.util.Arrays;


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
     * Get the JSONObject for the Wizard project.
     * @return the JSONObject for the Wizard project.
     */
    static JSONObject getWizardJSONObject() {
        String filePath = getTestResourcePath("project.json");
        String jsonString = Sb2.getFileContents(filePath);
        return Sb2.createJSONObject(jsonString);
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
