import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Arrays;
import java.io.Writer;
import java.io.FileNotFoundException;

/**
 * @version 1
 * @author James Ward
 * @author Clint Hall
 */
public class Reporter {
    private static final String TAB = "    ";

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
        /*File reportFile = new File(destPath, sb2.getName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile))) {
            bw.write("Hi, I'm a file!");
            bw.newLine();
        } catch (IOException e) {

        }
        return reportFile();*/
    }
    /**
     * Write report to a PrintWriter.
     * @param pw The PrintWriter.
     * @param sb2List List of Sb2 objects which are the subject of the report.
     */
    public void writeReport(PrintWriter pw, List<Sb2> sb2List) {
        pw.write("Scratch Report\n\n");
        pw.write("Number of projects: " + sb2List.size() + "\n\n");
        for (int i = 0; i < sb2List.size(); i++) {
            Sb2 sb2 = sb2List.get(i);
            reportProject(i + 1, pw, sb2List.get(i));
        }
    }
    /**
     * Report one Scratch project, represented by one Sb2 object from the list.
     * @param projectNo The index of the project in the list.  Starting from 1.
     * @param pw The PrintWriter.
     * @param sb2 The sb2.
     */
    private void reportProject(int projectNo, PrintWriter pw, Sb2 sb2) {
        pw.write("Project " + projectNo + ": " + sb2.getName() + "\n");
        String[] spriteNames = sb2.getSpriteNames();
        Arrays.sort(spriteNames);
        pw.write(spriteNames.length + " sprites\n");
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
        pw.write("\n" + tab + "Sprite " + spriteNo + ": " + spriteName + "\n");
        int[] scriptLengths = sb2.getScriptLengthsForSprite(spriteName);
        pw.write(tab + scriptLengths.length + " scripts\n");
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
        pw.write(tab + "Script " + scriptNo + "\n");
        tab = tab + TAB;
        pw.write(tab + "length = " + scriptLength + "\n");
    }

}
