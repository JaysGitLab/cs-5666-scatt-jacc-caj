import org.json.JSONArray;
import java.util.Map;
/**
 * @author B. Clint Hall
 * @version 1
 */
public class Script{
    private final static int INDEX_OF_BLOCK_TUPLE_ARRAY_IN_SCRIPT_TUPLE = 2;
    private JSONArray blocks = null;
    
    public Script(JSONArray scriptTuple){
        if (scriptTuple != null) {
            blocks = scriptTuple.optJSONArray(INDEX_OF_BLOCK_TUPLE_ARRAY_IN_SCRIPT_TUPLE);
        }
        if (blocks == null) {
            blocks = new JSONArray();
        }
    }
    /**
     * Counts the number of blocks of each type for this script
     * of block tuples.
     * @return an integer array in which each int is a category count.  Uses teh order
     * of {@code ScriptSpecs.getCommandsByType}.
     */
    public int[] sumBlocksByCategory(){
        int[] blocksByCategory = new int[ScriptSpecs.getCategories().length];
        sumBlocksByCategory(blocksByCategory);
        return blocksByCategory;
    }
    /**
     * Counts the number of blocks of each type for this script
     * of block tuples.
     * @param blocksByCategory an integer array. Uses the order
     * of {@code ScriptSpecs.getCommandsByType}. Integer at each index
     * will be incremented for each block of corresponding type.
     */
    public void sumBlocksByCategory(int[] blocksByCategory){
        sumBlocksByCategory(blocks, blocksByCategory);
    }
    /**
     * Get the number of blocks in the script.
     * @return the number of blocks in the script.
     */
    public int getLength() {
        return blocks.length();
    }
    /**
     * Counts the number of blocks of each type in a JSONArray
     * of block tuples and adds them into {@code blocksByCategory}
     * @param blocksByCategory The array in which to increment block
     * counts by type.
     */
    private static void sumBlocksByCategory(JSONArray blocks, int[] blocksByCategory) {
        Map<String, Integer> commandsByType = ScriptSpecs.getCommandsByType();
        for (int j = 0; j < blocks.length(); j++) {
            JSONArray block = blocks.optJSONArray(j);
            String command = block.optString(0);
            Integer commandTypeInt = commandsByType.get(command);
            if (commandTypeInt == null) {
                commandTypeInt = 0;
            }
            blocksByCategory[commandTypeInt]++;
            handleNesting(block, command, blocksByCategory);
        }
    }
    /**
     * If there are nested JSONArrays of block tuples in blockTuple param, then
     * they will be counted in getBlocksByCategory.
     * @param blockTuple see above
     * @param command the command type of blockTuple
     * @param blocksByCategory The array in which to incremenet block
     * counts by type
     */
    private static void handleNesting(
            JSONArray parentBlock,
            String command,
            int[] blocksByCategory) {
        int[] nestedBlockTupleArrayIndexes = ScriptSpecs.getNestedBlockTupleArrayIndexes(command);
        for (int index : nestedBlockTupleArrayIndexes) {
            JSONArray childBlocks = parentBlock.optJSONArray(index);
            if (childBlocks != null) {
                sumBlocksByCategory(childBlocks, blocksByCategory);
            }
        }
    }
    /**
     * Get the total blocks by category for an array of scripts
     * @param scripts The array of scripts
     * @return the total blocks by category for the scripts.
     * Uses the order of {@code ScriptSpecs.getCommandsByType}.
     */
    public static int[] sumBlocksByCategory(Script[] scripts){
        int[] blocksByCategory = new int[ScriptSpecs.getCategories().length];
        for (int i = 0; i < scripts.length; i++) {
            scripts[i].sumBlocksByCategory(blocksByCategory);
        }
        return blocksByCategory;
    }
}
