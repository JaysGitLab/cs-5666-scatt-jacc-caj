import org.json.JSONObject;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
/**
 * @version 1.0
 * @author B. Clint Hall
 */
public class Sb2 {
    private JSONObject sb2Json;
    /**
     * Construct an Sb2 object from a filePath.
     * @param filePath Path to sb2 file.
     */
    public Sb2(String filePath) {
        // Path to dest where .sb2 should be extracted
        String destPath = "";
        extractSb2(filePath, destPath);

        String pathToPackageJson = destPath + File.separator + "package.json";
        String jsonString = getFileContents(pathToPackageJson);
        sb2Json = getJSONObject(jsonString);
    }
    /**
     * Return underlying JSONObject.
     * @return The underlying JSONObject.
     */
    public JSONObject getJSONObject() {
        return sb2Json;
    }

    /**
     * Return the number of Sprites in Sb2 object.
     * @return The number of sprites in project
     */
    public int getSpriteCount() {
        return 0;
    }

    /**
     * Unzip sb2 file.
     * @param sb2Path Path to the sb2 file.
     * @param destPath Path to directory where sb2 should be extracted
     */
    public static void extractSb2(String sb2Path, String destPath){

    }
    /**
     * Given a file path return a String of file contents.
     * @param pathStr Path to the file
     * @return contents of file
     */
    public static String getFileContents(String pathStr) {
        Path path = Paths.get(pathStr);
        String fileString = null;
        try {
            fileString = Files.lines(path).collect(Collectors.joining("\n"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return fileString;
    }
    /**
     * Given a path to a json file, return a JSONObject.
     * @param pathStr Path to the json file
     */
    public static JSONObject getJSONObject(String jsonString){
        return new JSONObject(jsonString);
    }

    /**
     * Print the current working directory.  Useful for debugging.
     * Thank you http://stackoverflow.com/a/15954821
     */
    public static void printCwd() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
    }

}
