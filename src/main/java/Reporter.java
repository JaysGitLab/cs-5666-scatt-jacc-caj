import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Writer;
import java.io.FileNotFoundException;

public class Reporter{
    String TAB = "    ";
    public Reporter(){
    }
    public void writeReport(Writer writer, List<Sb2> sb2List){
        try(PrintWriter printWriter = new PrintWriter(writer)){
            writeReport(printWriter, sb2List);
        }
        
    }
    public void writeReport(String destPath, List<Sb2> sb2List){
        try(PrintWriter printWriter = new PrintWriter(new File(destPath))){
            writeReport(printWriter, sb2List);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*File reportFile = new File(destPath, sb2.getName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile))){
            bw.write("Hi, I'm a file!");
            bw.newLine();
        } catch (IOException e){

        }
        return reportFile();*/
    }

    public void writeReport(PrintWriter pw, List<Sb2> sb2List) {
        pw.write("Scratch Report\n\n");
        pw.write("Number of projects: " + sb2List.size() + "\n\n");
        for (int i = 0; i < sb2List.size(); i++) {
            Sb2 sb2 = sb2List.get(i);
            reportProject(i + 1, pw, sb2List.get(i));
        }
    }
    private void reportProject(int projectNo, PrintWriter pw, Sb2 sb2) {
        pw.write("Project " + projectNo + ": " + sb2.getName() + "\n");
        String[] spriteNames = sb2.getSpriteNames();
        Arrays.sort(spriteNames);
        pw.write(spriteNames.length + " sprites\n");
        for(int i=0; i<spriteNames.length; i++){
            reportSprite(i+1, spriteNames[i], pw, sb2);
        }
    }
    private void reportSprite(int spriteNo, String spriteName, PrintWriter pw, Sb2 sb2) {
        String tab = TAB;
        pw.write("\n" + tab + "Sprite " + spriteNo + ": " + spriteName + "\n");
        int[] scriptLengths = sb2.getScriptLengthsForSprite(spriteName);
        pw.write(tab + scriptLengths.length + " scripts\n");
        for(int i=0; i<scriptLengths.length; i++){
            reportScript(i+1, scriptLengths[i], pw);
        }
    }
    private void reportScript(int scriptNo, int scriptLength, PrintWriter pw) {
        String tab = TAB + TAB;
        pw.write(tab + "Script " + scriptNo+ "\n");
        tab = tab + TAB;
        pw.write(tab + "length = " + scriptLength + "\n");
    }

}
