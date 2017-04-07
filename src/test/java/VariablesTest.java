import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.StringWriter;

/**
 * This test class is meant to test that our Sb2 program can correctly count
 * the number of global variables, script variables, and sprite variables.
 * @version 1
 * @author James Ward
 */

public class VariableTest {
    /*
     * Test that the Sb2 can accurately count the number of global variables.
     */
    @Test
    public void testGlobalVariablesCount(){
        Sb2 scattariaSb2 = new Sb2(Utils.getScratchariaJSONObject());
        int expectedGlobVar = scattariaSb2.getGlobalVariableCount();
        int actual = 23;
        assertEquals(expectedGlobVar, actual); 
    }
}
