import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.io.Writer;
import java.io.FileNotFoundException;

/**
 * @version 1
 * @author James Ward
 * @author Clint Hall
 */
public class Reporter {
    public static final String REPORT_SUFFIX = "_Report.txt";
    public static final int REPORT_ALL          = 0xFFFFFFFF;
    public static final int NUM_PROJECTS        = 0b1;
    public static final int PROJECT_HEADERS     = 0b10;
    public static final int SPRITES_PER_PROJECT = 0b100;
    public static final int SPRITE_HEADERS      = 0b1000;
    public static final int SCRIPTS_PER_SPRITE  = 0b1_0000;
    public static final int SCRIPT_HEADERS      = 0b10_0000;
    public static final int SCRIPT_LENGTHS      = 0b100_0000;
    private static final String TAB = "    ";
    private int whatToReport;
    /**
     * Constructor to configure what gets reported and what doesn't.
     * @param bitVector an int representing a bit vector indicating which
     *    things should get reported.  See public static final int's in Reporter.
     */
    public Reporter(int bitVector) {
        whatToReport = bitVector;
    }
    /**
     * Default constructor.  Reports everything.
     */
    public Reporter() {
        this(REPORT_ALL);
    }
    /**
     * Determine whether a flag is set in the what to report bit vector.
     * @param flag One of the public static final int's
     *   indicating what should be reported.
     * @return whether to report the thing indicated by the flag
     */
    private boolean yes(int flag) {
        return (whatToReport & flag) != 0;
    }
    
    /**
     * Write report to a writer.
     * @param writer The writer.
     * @param sb2List List of Sb2 objects which are the subject of the report.
     */
    public void writeReport(Writer writer, List<Sb2> sb2List) {
        try (PrintWriter printWriter = new PrintWriter(writer)) {
            writeReport(printWriter, sb2List);
        }
    }
    /**
     * Write report to a file.
     * @param destPath Path to the destinatin file.
     * @param sb2List List of Sb2 objects which are the subject of the report.
     */
    public void writeReport(String destPath, List<Sb2> sb2List) {
        try (PrintWriter printWriter = new PrintWriter(new File(destPath))) {
            writeReport(printWriter, sb2List);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Write report to a PrintWriter.
     * @param pw The PrintWriter.
     * @param sb2List List of Sb2 objects which are the subject of the report.
     */
    public void writeReport(PrintWriter pw, List<Sb2> sb2List) {
        pw.write("Scratch Report\n\n");
        if (yes(NUM_PROJECTS)) {
            pw.write("Number of projects: " + sb2List.size() + "\n");
        }
        for (int i = 0; i < sb2List.size(); i++) {
            Sb2 sb2 = sb2List.get(i);
            reportProject(i + 1, pw, sb2);
        }
    }
    /**
     * Report one Scratch project, represented by one Sb2 object from the list.
     * @param projectNo The index of the project in the list.  Starting from 1.
     * @param pw The PrintWriter.
     * @param sb2 The sb2.
     */
    private void reportProject(int projectNo, PrintWriter pw, Sb2 sb2) {
        if (yes(PROJECT_HEADERS)) {
            pw.write("\n\nProject " + projectNo + ": " + sb2.getName() + "\n");
        }
        String[] spriteNames = sb2.getSpriteNames();
        if (yes(SPRITES_PER_PROJECT)) {
            pw.write(spriteNames.length + " sprites\n");
        }
        for (int i = 0; i < spriteNames.length; i++) {
            reportSprite(i + 1, spriteNames[i], pw, sb2);
        }
    }
    /**
     * Report one Sprite from a Scratch project.
     * @param spriteNo The index of the Sprite in the list.  Starting from 1.
     * @param spriteName The name of the Sprite.
     * @param pw The PrintWriter.
     * @param sb2 The sb2.
     */
    private void reportSprite(int spriteNo, String spriteName, PrintWriter pw, Sb2 sb2) {
        String tab = TAB;
        if (yes(SPRITE_HEADERS)) {
            pw.write("\n" + tab + "Sprite " + spriteNo + ": " + spriteName + "\n");
        }
        int[] scriptLengths = sb2.getScriptLengthsForSprite(spriteName);
        if (yes(SCRIPTS_PER_SPRITE)) {
            pw.write(tab + scriptLengths.length + " scripts\n");
        }
        for (int i = 0; i < scriptLengths.length; i++) {
            reportScript(i + 1, scriptLengths[i], pw);
        }
    }
    /**
     * Report one Script from a Sprite project.
     * @param scriptNo The index of the Script in the list.  Starting from 1.
     * @param scriptLength The length of the script.
     * @param pw The PrintWriter.
     */
    private void reportScript(int scriptNo, int scriptLength, PrintWriter pw) {
        String tab = TAB + TAB;
        if (yes(SCRIPT_HEADERS)) {
            pw.write(tab + "Script " + scriptNo + "\n");
        }
        tab = tab + TAB;
        if (yes(SCRIPT_LENGTHS)) {
            pw.write(tab + "length = " + scriptLength + "\n");
        }
    }

}
