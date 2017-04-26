import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.util.Arrays;

/**
 * @version 1.0
 * @author B. Clint Hall
 */
public class Sprites {
    private final static int BLOCK_TUPLE_INDEX = 2;
    private Map<String, JSONObject> spriteMap;
    private Map<String, Script[]> scriptsMap = new HashMap<>();
    /**
     * The root of the sb2 json object is a Stage object.
     * This constructs a Sprites object from a Stage JSONObject.
     * @param stage root of an Sb2 file
     */
    public Sprites(JSONObject stage) {
        JSONArray stageChildren = stage.optJSONArray("children");
        spriteMap = new HashMap<String, JSONObject>();
        if (stageChildren == null) {
            return;
        }
        for (int i = 0; i < stageChildren.length(); i++) {
            JSONObject child = stageChildren.optJSONObject(i);
            if (isSprite(child)) {
                addSpriteToSpriteMap(child);
            }
        }
    }
    /**
     * Add sprite to spriteMap. Guarantee unique name.
     * @param sprite A Sprite JSONObject
     */
    private void addSpriteToSpriteMap(JSONObject sprite) {
        String spriteName = sprite.optString("objName");
        //make sure the Sprite has a name
        if (spriteName == "") {
            spriteName = "NO_NAME";
            sprite.put("objName", spriteName);
        }
        //if there is already a sprite with this name, let's add '_0' to it's name.
        if (spriteMap.containsKey(spriteName)) {
            JSONObject sameNameSprite = spriteMap.get(spriteName);
            sameNameSprite.put("objName", spriteName + "_0");
            spriteMap.put(spriteName + "_0", sameNameSprite);
            spriteMap.remove(spriteName);
        }
        //let's add '_n' to this Sprites name, where n is the smallest unused positive int
        if (spriteMap.containsKey(spriteName + "_0")) {
            int count = 1;
            while (spriteMap.containsKey(spriteName + "_" + count)) {
                count++;
            }
            spriteName = spriteName + "_" + count;
            sprite.put("objName", spriteName);
        } 
        spriteMap.put(spriteName, sprite);
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
        spriteNames = spriteMap.keySet().toArray(spriteNames);
        Arrays.sort(spriteNames);
        return spriteNames;
    }

    /**
     * Returns the block tuples for a script with index = {@code index} and
     * sprite name = {@code spriteName}.
     * @param
     */

    /**
     * Sprites may have scripts associated with them. 
     * @param spriteName The name of the sprite
     * @return an array of the Script objects for the sprite. 
     */
    public Script[] getScriptsForSprite(String spriteName) {
        Script[] scripts = scriptsMap.get(spriteName);
        if (scripts != null) {
            return scripts;
        }
        JSONObject sprite = spriteMap.get(spriteName);
        if (sprite == null) {
            throw new IllegalArgumentException(
                spriteName + " is not the name of a Sprite in this project");
        }
        JSONArray jsonArrayOfScriptTuples = sprite.optJSONArray("scripts");
        scripts = Script.getScriptArray(jsonArrayOfScriptTuples);
        scriptsMap.put(spriteName, scripts);
        return scripts;
    }
    /**
     * Return the number of Scripts associated with a particular Sprite.
     * @param spriteName The name of the Sprite.
     * @return The number of Scripts associated with the Sprite.
     */
    public int getScriptCountForSprite(String spriteName) {
        return getScriptsForSprite(spriteName).length;
    }
    /**
     * Count the number of blocks in each script for the given Sprite.
     * @param spriteName The name of the sprite in question.
     * @return An array with the lengths of each script
     *         associated with the Sprite.
     */
    public int[] getScriptLengthsForSprite(String spriteName) {
        Script[] scripts = getScriptsForSprite(spriteName);
        int[] lengths = new int[scripts.length];
        for (int i = 0; i < scripts.length; i++) {
            lengths[i] = scripts[i].getLength();
        }
        return lengths;
    }
    
    /**
     * Public jsonArrayOfScriptTuples method for getting the number of variables
        scripts = Script.getScriptArray(jsonArrayOfScriptTuples);

        * associated with a particular sprite.
     * @param sprite the name of the sprite whose info you desire
     * @return the number of variables associated with a sprite 
     * @throws IOException if the sprite sought does not exist 
     */ 
    public int getSpriteVariableCount(String sprite) throws IOException {
        if (spriteMap.containsKey(sprite)) {
            JSONObject spriteObj = spriteMap.get(sprite);
            JSONArray spriteVars = spriteObj.optJSONArray("variables");
            if (spriteVars == null) {
                return 0;
            }
            return spriteVars.length();
        } else {
            throw new IOException("You should not be searching for sprites"
                + " that don't exist.");
        }
    }
}
