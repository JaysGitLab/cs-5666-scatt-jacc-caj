import org.json.JSONObject;
import org.json.JSONArray;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;

/**
 * @version 1.0
 * @author B. Clint Hall
 */
public class Sb2 {
    private JSONObject stage;
    private Map<String, JSONObject> spriteMap;
    /**
     * Construct an Sb2 object from a filePath.
     * @param filePath Path to sb2 file.
     * @throws IOException passed from extractSb2(String, String)
     */
    public Sb2(String filePath) throws IOException {
        // Path to dest where .sb2 should be extracted
        String destPath = "";
        extractSb2(filePath, destPath);

        String pathToPackageJson = destPath + File.separator + "package.json";
        String jsonString = getFileContents(pathToPackageJson);
        init(createJSONObject(jsonString));
    }
    /**
     * Construct an Sb2 object from a JSONObject. Useful for testing.
     * @param stage JSONObject from which to construct Sb2
     */
    public Sb2(JSONObject stage) {
        init(stage);
    }
    /**
     * Function to be called from all constructors.
     * @param stage JSONObject which is underlying data structure for Sb2.
     */
    public void init(JSONObject stage) {
        this.stage = stage;
        JSONArray stageChildren = stage.optJSONArray("children");
        if (stageChildren == null) {
            stageChildren = new JSONArray();
        }
        spriteMap = new HashMap<String, JSONObject>();
        for (int i = 0; i < stageChildren.length(); i++) {
            JSONObject child = stageChildren.optJSONObject(i);
            if (isSprite(child)) {
                spriteMap.put(child.getString("objName"), child);
            }
        }
    }
    
    /**
     * Return underlying JSONObject.
     * @return The underlying JSONObject.
     */
    public JSONObject getJSONObject() {
        return stage;
    }

    /**
     * Check whether a stage child is a Sprite.  It could also be
     * a StageMonitor.
     * @param stageChild A JSONObject that may represent a Sprite or a
     *        StageMonitor
     * @return whether the stageChild is a Sprite.
     */
    private boolean isSprite(JSONObject stageChild) {
        return stageChild != null && stageChild.has("spriteInfo");
    }
    /**
     * Each sprite has a unique name.
     * This method returns these names in a array of strings.
     * @return Array of sprite names.
     */
    public String[] getSpriteNames() {
        String[] spriteNames = new String[spriteMap.size()];
        return spriteMap.keySet().toArray(spriteNames);
    }
    /**
     * Return the number of Scripts associated with a particular Sprite.
     * @param spriteName The name of the Sprite.
     * @return The number of Scripts associated with the Sprite.
     */
    public int getScriptCountForSprite(String spriteName) {
        JSONObject sprite = spriteMap.get(spriteName);
        if(sprite == null){
            throw new IllegalArgumentException(
                spriteName + " is not the name of a Sprite in this project");
        }
        JSONArray scripts = sprite.optJSONArray("scripts");
        if (scripts == null) {
            return 0;
        } else {
            return scripts.length();
        }
    }
     
    /**
     * Unzip sb2 file.
     * @param sb2Path Path to the sb2 file.
     * @param destPath Path to directory where sb2 should be extracted
     * @throws IOException if something goes wrong.
     */
    public static void extractSb2(String sb2Path, String destPath) throws IOException {

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
