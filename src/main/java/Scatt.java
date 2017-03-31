import org.json.JSONObject;
import org.json.JSONArray;
import java.io.File;
/**
 * @version 1.0
 * @author Clint Hall
 * @author Chris Waldon
 * @author James Ward
 * @author Erik Cole
 */
public class Scatt {
String sb2dir;
Sb2 sb2;
    /**
     * Just a dummy main method for now.
     * @param args command line arguments
     */
    public static void main(String... args) {
        System.out.print("Hello from Scatt");
        //JSONObject obj = new JSONObject();
        //JSONArray ary = new JSONArray();
        Scatt sc = new Scatt();
        sc.showUI();
    }
    
    public void showUI(){   
        FileChooser.createAndShowGUI(
        (File f) -> {
            sb2dir = f.getPath();
            System.out.print("File Path Chosen:"+sb2dir);
            sb2 = new Sb2(f.getPath()
        }
    );
    


    }
    /**
     * Just a method to make sure test, compile and style work.
     * Get rid of it when we have a real method.
     * @return Just a "Hello" string
     */
    public String hello() {
        return "Hello from Scatt";
    }
}
