import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @version 1.0
 * @author B. Clint Hall
 * @author Chris Waldon
 */
public class Extractor {
    /**
     * Unzip sb2 file and return project.json content as a string.
     * @param sb2Path Path to the sb2 file.
     * @throws IOException if something goes wrong.
     * @return the contents of the Sb2's project.json file as a string
     */
    public static String getProjectJSON(String sb2Path) throws IOException {
        // Thanks to Oracle for code structure:
        // http://www.oracle.com/technetwork/articles/java/compress-1565076.html
        ZipFile zipFile = new ZipFile(sb2Path);
        ZipEntry entry = zipFile.getEntry("project.json");
	try (BufferedReader input = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));){

            
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
		sb.append(line).append("\n");
            }
            return sb.toString();
	} catch (IOException e) {
	    throw e;
	}
    }
}
