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
        Sprites sprites = new Sprites(Utils.getWizardJSONObject());
        String[] expected = {"Wizard Girl", "Creature", "Instructions"};
        String[] actual = sprites.getSpriteNames();
        assertTrue(Utils.sameContents(expected, sprites.getSpriteNames()));
    }
    /**
     * Test getScriptCountForSprite.
     */
    @Test
    public void testGetScriptCountForSprite1() {
        Sprites sprites = new Sprites(Utils.getWizardJSONObject());
        assertEquals(sprites.getScriptCountForSprite("Wizard Girl"), 3);
        assertEquals(sprites.getScriptCountForSprite("Creature"), 4);
        assertEquals(sprites.getScriptCountForSprite("Instructions"), 0);
    }
    /**
     * Test getScriptLengthsForSprite.
     */
    @Test
    public void testGetScriptLengthsForSprite1() {
        Sprites sprites = new Sprites(Utils.getWizardJSONObject());
        assertTrue(Arrays.equals(
            sprites.getScriptLengthsForSprite("Wizard Girl"),
            new int[]{4, 4, 4}
        ));
        assertTrue(Arrays.equals(
            sprites.getScriptLengthsForSprite("Creature"),
            new int[]{2, 2, 2, 2}
        ));
        assertTrue(Arrays.equals(
            sprites.getScriptLengthsForSprite("Instructions"),
            new int[0]
        ));

    }

}
