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
//Sb2 sb2;
    /**
     * Just a dummy main method for now.
     * @param args command line arguments
     */
    public static void main(String... args) {
        //JSONObject obj = new JSONObject();
        //JSONArray ary = new JSONArray();
        Scatt sc = new Scatt();
        sc.showUI();
    }
   /**
    * Creating UI and getting filepath for directory.
    */

    public void showUI() {   
        File file = FileChooser.getDirectoryFromUser();
        System.out.println(file.getPath());
    }
}
