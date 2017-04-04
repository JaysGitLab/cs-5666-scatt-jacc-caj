import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.Writer;
import java.io.FileNotFoundException;

public class Reporter{
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
    private void writeProject(PrintWriter pw, Sb2 sb2) {
        
    }
    public void writeReport(PrintWriter pw, List<Sb2> sb2List) {
        pw.write("Scratch Report\n\n");
        pw.printf("Number of projects: %d\n\n", sb2List.size());
        for (int i = 0; i < sb2List.size(); i++) {
            Sb2 sb2 = sb2List.get(i);
            pw.printf("Project %d: " + sb2.getName() + "\n", (i+1));
            writeProject(pw, sb2List.get(i));
        }
    }
}
