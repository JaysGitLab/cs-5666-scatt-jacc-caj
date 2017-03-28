import org.json.JSONObject;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.io.IOException;

/**
 * @version 1.0
 * @author B. Clint Hall
 */
public class Sb2 {
    private JSONObject stage;
    private Sprites sprites;
    private static final int UNZIP_BUFFER_SIZE = 2048;

    /**
     * Construct an Sb2 object from a filePath.
     * @param filePath Path to sb2 file.
     * @throws IOException passed from extractSb2(String, String)
     */
    public Sb2(String filePath) throws IOException {
        // Path to dest where .sb2 should be extracted.
	// Uses operating system default temporary directory
        String destPath = System.getProperty("java.io.tmpdir");
        extractSb2(filePath, destPath);

        String pathToPackageJson = destPath + File.separator + "project.json";
        String jsonString = getFileContents(pathToPackageJson);
        configureWithJson(createJSONObject(jsonString));
    }
    /**
     * Construct an Sb2 object from a JSONObject. Useful for testing.
     * @param stage JSONObject from which to construct Sb2
     */
    public Sb2(JSONObject stage) {
        configureWithJson(stage);
    }
    /**
     * Function to be called from all constructors.
     * @param stage JSONObject which is underlying data structure for Sb2.
     */
    public void configureWithJson(JSONObject stage) {
        this.stage = stage;
        this.sprites = new Sprites(stage);
    }

    /**
     * Return underlying JSONObject.
     * @return The underlying JSONObject.
     */
    public JSONObject getJSONObject() {
        return stage;
    }
    /**
     * Unzip sb2 file.
     * @param sb2Path Path to the sb2 file.
     * @param destPath Path to directory where sb2 should be extracted
     * @throws IOException if something goes wrong.
     */
    public static void extractSb2(String sb2Path, String destPath) throws IOException {
        // Thanks to Oracle for code structure:
        // http://www.oracle.com/technetwork/articles/java/compress-1565076.html
        BufferedOutputStream dest;
        BufferedInputStream input;
        FileOutputStream fileOut;
        ZipEntry entry;
        ZipFile zipFile;
	try {
            zipFile = new ZipFile(sb2Path);
            Enumeration e = zipFile.entries();
            while (e.hasMoreElements()) {
		entry = (ZipEntry) e.nextElement();
		input = new BufferedInputStream(zipFile.getInputStream(entry));
		int count;
		byte data[] = new byte[UNZIP_BUFFER_SIZE];
		fileOut = new FileOutputStream(destPath + "/" + entry.getName());
		dest = new BufferedOutputStream(fileOut, UNZIP_BUFFER_SIZE);
		while ((count = input.read(data, 0, UNZIP_BUFFER_SIZE)) != -1) {
		    dest.write(data, 0, count);
		}
		dest.flush();
		dest.close();
		input.close();
            }
	} catch (IOException e) {
	    throw e;
	}
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileString;
    }
    /**
     * Given a path to a json file, return a JSONObject.
     * @param jsonString Path to the json file
     * @return org.json.JSONObject
     */
    public static JSONObject createJSONObject(String jsonString) {
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
