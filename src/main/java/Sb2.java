import org.json.JSONObject;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.io.IOException;

/**
 * @version 1.0
 * @author B. Clint Hall
 */
public class Sb2 {
    private JSONObject stage;
    private Sprites sprites;
    private String name;

    /**
     * Construct an Sb2 object from a filePath.
     * @param filePath Path to sb2 file.
     * @throws IOException passed from extractSb2(String, String)
     */
    public Sb2(String filePath) throws IOException {
        configureWithJson(createJSONObject(Extractor.getProjectJSON(filePath)));
    }
    /**
     * Construct an Sb2 using a JSONObject and a name.  Used in testing.
     * @param stage The JSONObject.
     * @param name The name for the Scratch project. Normally the name of the .sb2 file.
     */
    public Sb2(JSONObject stage, String name) {
        this.name = name;
        configureWithJson(stage);
    }
    /**
     * Construct an Sb2 object from a JSONObject. Useful for testing.
     * @param stage JSONObject from which to construct Sb2
     */
    public Sb2(JSONObject stage) {
        this(stage, "ScratchProject");
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
    /**
     * Return the name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    //Sb2 has a Sprites. These are the sprite methods
    /**
     * Each sprite has a unique name.
     * This method returns these names in a array of strings.
     * @return Array of sprite names.
     */
    public String[] getSpriteNames() {
        return sprites.getSpriteNames();
    }
    /**
     * Return the number of Scripts associated with a particular Sprite.
     * @param spriteName The name of the Sprite.
     * @return The number of Scripts associated with the Sprite.
     */
    public int getScriptCountForSprite(String spriteName) {
        return sprites.getScriptCountForSprite(spriteName);
    }
    /**
     * Count the number of blocks in each script for the given Sprite.
     * @param spriteName The name of the sprite in question.
     * @return An array with the lengths of each script
     *         associated with the Sprite.
     */
    public int[] getScriptLengthsForSprite(String spriteName) {
        return sprites.getScriptLengthsForSprite(spriteName);
    }
}

