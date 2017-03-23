import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Map;
import java.util.HashMap;

/**
 * @version 1.0
 * @author B. Clint Hall
 */
public class Sprites {
    private Map<String, JSONObject> spriteMap;
    /**
     * The root of the sb2 json object is a Stage object.
     * This constructs a Sprites object from a Stage JSONObject.
     * @param stage root of an Sb2 file
     */
    public Sprites(JSONObject stage) {
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
     * Each script is represented by an json array of block tuples,
     * where each block tuple is like a line of code. A Sprite may have
     * multiple Scripts associated with it.  This function returns an array
     * of json arrays of block tuples for the requested Sprite.
     * @param spriteName The name of the sprite
     * @return An array block tuples.  Each block tuple is represented as
     *         a JSONArray.
     */
    private JSONArray[] getScripts(String spriteName) {
        JSONObject sprite = spriteMap.get(spriteName);
        if (sprite == null) {
            throw new IllegalArgumentException(
                spriteName + " is not the name of a Sprite in this project");
        }
        JSONArray scriptTuples = sprite.optJSONArray("scripts");
        if (scriptTuples == null) {
            return new JSONArray[0];
        } else {
            JSONArray[] blockTuples = new JSONArray[scriptTuples.length()];
            for (int i = 0; i < blockTuples.length; i++) {
                blockTuples[i] = getBlockTuple(scriptTuples.optJSONArray(i));
            }
            return blockTuples;
        }
    }
    /**
     * Given a script tuple, return it's block tuple.
     * @param scriptTuple A script tuple JSONArray
     * @return a blockTuple JSONArray
     */
    private JSONArray getBlockTuple(JSONArray scriptTuple) {
        if (scriptTuple == null) {
            return new JSONArray();
        } else {
            JSONArray blockTuple = scriptTuple.getJSONArray(2);
            return blockTuple == null ? new JSONArray() : blockTuple;
        }
    }
    /**
     * Return the number of Scripts associated with a particular Sprite.
     * @param spriteName The name of the Sprite.
     * @return The number of Scripts associated with the Sprite.
     */
    public int getScriptCountForSprite(String spriteName) {
        return getScripts(spriteName).length;
    }
    /**
     * Count the number of blocks in each script for the given Sprite.
     * @param spriteName The name of the sprite in question.
     * @return An array with the lengths of each script
     *         associated with the Sprite.
     */
    public int[] getScriptLengthsForSprite(String spriteName) {
        JSONArray[] scripts = getScripts(spriteName);
        int[] lengths = new int[scripts.length];
        for (int i = 0; i < scripts.length; i++) {
            lengths[i] = scripts[i].length();
        }
        return lengths;
    }
}
