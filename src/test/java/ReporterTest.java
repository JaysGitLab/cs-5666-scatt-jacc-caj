import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * This test class is meant to test that the reporter outputs the appropriate
 * information in relation to the ouptut from SB2 and the given test JSON
 * file.
 * 
 * The current output I'm expecting (VERY subject to change)  will be
 * Sprites: #
 *  - SpriteName
 *     - Length: #
 *  - SpriteName
 *     - Length: #
 * This is important to note to be able to better understand the tests below.
 *
 * NOTE: If we could extract a student's name from the file name it would be
 * really handy to have the student's name at the top of the file for teacher's
 * review purposes.    
 */

class ReporterTest{
    
    //We need the sb2 to store it's name
    @Test
    public void testFileName(){
        Sb2 sb2 = new Sb2(Utils.getWizardJSONObject(), "WizardProject");
        Reporter reporter = new Reporter("");
        File report = reporter.writeReport(sb2);
        assertEquals(report.getName(), sb2.getName() + ".txt");
    }
    
    //We need the sb2 to test for numbers of sprites
    @Test
    public void testNumSprites(){
        Sb2 sb2 = new Sb2(Utils.getWizardJSONObject(), "WizardProject");
        Reporter reporter = new Reporter("");
        File report = reporter.writeReport(sb2);
        /*
        String spritesOutput = Files.readAllLines(Paths.get(report)).get(1);
        assertEquals(report.getNumSprites()
        */
    }

    //We need the sb2 to test for number of scripts for a given sprite
    @Test
    public void testNumScripts(){
    }

    //We need the sb2 to test for script length for a given sprite
    @Test
    public void testScriptLength(){
    }
}
