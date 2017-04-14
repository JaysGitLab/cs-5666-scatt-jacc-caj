import java.util.Map;
import java.util.HashMap;

/**
 * @author Clint Hall
 * @author James Ward
 * @version 1
 */
public class ScriptSpecs {
    private static final String[][] COMMANDS = {
        {"forward:", "1"},
        {"turnRight:", "1"},
        {"turnLeft:", "1"},
        {"heading:", "1"},
        {"pointTowards:", "1"},
        {"gotoX:y:", "1"},
        {"gotoSpriteOrMouse:", "1"},
        {"glideSecs:toX:y:elapsed:from:", "1"},
        {"changeXposBy:", "1"},
        {"xpos:", "1"},
        {"changeYposBy:", "1"},
        {"ypos:", "1"},
        {"bounceOffEdge", "1"},
        {"setRotationStyle", "1"},
        {"xpos", "1"},
        {"ypos", "1"},
        {"heading", "1"},
        {"say:duration:elapsed:from:", "2"},
        {"say:", "2"},
        {"think:duration:elapsed:from:", "2"},
        {"think:", "2"},
        {"show", "2"},
        {"hide", "2"},
        {"lookLike:", "2"},
        {"nextCostume", "2"},
        {"startScene", "2"},
        {"changeGraphicEffect:by:", "2"},
        {"setGraphicEffect:to:", "2"},
        {"filterReset", "2"},
        {"changeSizeBy:", "2"},
        {"setSizeTo:", "2"},
        {"comeToFront", "2"},
        {"goBackByLayers:", "2"},
        {"costumeIndex", "2"},
        {"sceneName", "2"},
        {"scale", "2"},
        {"playSound:", "3"},
        {"doPlaySoundAndWait", "3"},
        {"stopAllSounds", "3"},
        {"playDrum", "3"},
        {"rest:elapsed:from:", "3"},
        {"noteOn:duration:elapsed:from:", "3"},
        {"instrument:", "3"},
        {"changeVolumeBy:", "3"},
        {"setVolumeTo:", "3"},
        {"volume", "3"},
        {"changeTempoBy:", "3"},
        {"setTempoTo:", "3"},
        {"tempo", "3"},
        {"clearPenTrails", "4"},
        {"stampCostume", "4"},
        {"putPenDown", "4"},
        {"putPenUp", "4"},
        {"penColor:", "4"},
        {"changePenHueBy:", "4"},
        {"setPenHueTo:", "4"},
        {"changePenShadeBy:", "4"},
        {"setPenShadeTo:", "4"},
        {"changePenSizeBy:", "4"},
        {"penSize:", "4"},
        {"whenGreenFlag", "5"},
        {"whenKeyPressed", "5"},
        {"whenClicked", "5"},
        {"whenSceneStarts", "5"},
        {"whenSensorGreaterThan", "5"},
        {"whenIReceive", "5"},
        {"broadcast:", "5"},
        {"doBroadcastAndWait", "5"},
        {"wait:elapsed:from:", "6"},
        {"doRepeat", "6"},
        {"doForever", "6"},
        {"doIf", "6"},
        {"doIfElse", "6"},
        {"doWaitUntil", "6"},
        {"doUntil", "6"},
        {"stopScripts", "6"},
        {"whenCloned", "6"},
        {"createCloneOf", "6"},
        {"deleteClone", "6"},
        {"touching:", "7"},
        {"touchingColor:", "7"},
        {"color:sees:", "7"},
        {"distanceTo:", "7"},
        {"doAsk", "7"},
        {"answer", "7"},
        {"keyPressed:", "7"},
        {"mousePressed", "7"},
        {"mouseX", "7"},
        {"mouseY", "7"},
        {"soundLevel", "7"},
        {"senseVideoMotion", "7"},
        {"setVideoState", "7"},
        {"setVideoTransparency", "7"},
        {"timer", "7"},
        {"timerReset", "7"},
        {"getAttribute:of:", "7"},
        {"timeAndDate", "7"},
        {"timestamp", "7"},
        {"getUserName", "7"},
        {"+", "8"},
        {"-", "8"},
        {"*", "8"},
        {"/", "8"},
        {"randomFrom:to:", "8"},
        {"<", "8"},
        {"=", "8"},
        {">", "8"},
        {"&", "8"},
        {"|", "8"},
        {"not", "8"},
        {"concatenate:with:", "8"},
        {"letter:of:", "8"},
        {"stringLength:", "8"},
        {"%", "8"},
        {"rounded", "8"},
        {"computeFunction:of:", "8"},
        {"SET_VAR", "9"},
        {"CHANGE_VAR", "9"},
        {"showVariable:", "9"},
        {"hideVariable:", "9"},
        {"append:toList:", "12"},
        {"deleteLine:ofList:", "12"},
        {"insert:at:ofList:", "12"},
        {"setLine:ofList:to:", "12"},
        {"getLine:ofList:", "12"},
        {"lineCountOfList:", "12"},
        {"list:contains:", "12"},
        {"showList:", "12"},
        {"hideList:", "12"},
    };  
    private static final String[] CATEGORIES = {
        "undefined",
        "Motion",
        "Looks",
        "Sound",
        "Pen",
        "Events",
        "Control",
        "Sensing",
        "Operators",
        "Data",
        "More Blocks",
        "Parameter",
        "List",
        "Extension"
    };
    private static Map<String, Integer> commandsByType = null;
    /**
     * Returns a map in which the keys are the command opcodes that can appear
     * in Scratch scripts and the values are the category to which the command belongs.
     * @return the above specified map.
     */
    public static Map<String, Integer> getCommandsByType() {
        if (commandsByType == null) {
            commandsByType = new HashMap<>();
            for (int i = 0; i < COMMANDS.length; i++) {
                String[] command = COMMANDS[i];
                commandsByType.put(command[0], Integer.valueOf(command[1]));
            }
        }
        return commandsByType;
    }
    /**
     * Returns an array of the category names.  The indexes in the array correspond to the
     * Integers in the commandsByType map.
     * @return Array of category names.
     */
    public static String[] getCategories(){
        return CATEGORIES;
    }
}
