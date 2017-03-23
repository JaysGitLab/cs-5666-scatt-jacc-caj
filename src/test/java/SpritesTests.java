import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.Arrays;


/**
 * Class to test functionality of Sprites class.
 * @version 1.0
 * @author B. Clint Hall
 */
public class SpritesTests {
    /**
     * Test getSpriteNames.
     */
    @Test
    public void testGetSpriteNames() {
        Sb2 sb2 = new Sb2(Utils.getWizardJSONObject());
        String[] expected = {"Wizard Girl", "Creature", "Instructions"};
        String[] actual = sb2.getSpriteNames();
        assertTrue(Utils.sameContents(expected, sb2.getSpriteNames()));
    }
    /**
     * Test getScriptCountForSprite.
     */
    @Test
    public void testGetScriptCountForSprite1() {
        Sb2 sb2 = new Sb2(Utils.getWizardJSONObject());
        assertEquals(sb2.getScriptCountForSprite("Wizard Girl"), 3);
        assertEquals(sb2.getScriptCountForSprite("Creature"), 4);
        assertEquals(sb2.getScriptCountForSprite("Instructions"), 0);
    }
    /**
     * Test getScriptLengthsForSprite.
     */
    @Test
    public void testGetScriptLengthsForSprite1() {
        Sb2 sb2 = new Sb2(Utils.getWizardJSONObject());
        assertTrue(Arrays.equals(
            sb2.getScriptLengthsForSprite("Wizard Girl"),
            new int[]{4, 4, 4}
        ));
        assertTrue(Arrays.equals(
            sb2.getScriptLengthsForSprite("Creature"),
            new int[]{2, 2, 2, 2}
        ));
        assertTrue(Arrays.equals(
            sb2.getScriptLengthsForSprite("Instructions"),
            new int[0]
        ));

    }

}
