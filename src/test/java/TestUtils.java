
import java.awt.Component;
import java.awt.Container;

 /**
 * TestUtils.java impolements helper methods for testing.
 * @author Erik Cole
 * @author Chris Waldon
 * @version 0.0.1
 *
 * Inspired by:
 * http://www.javaworld.com/article/2073056/swing-gui-programming/
 * automate-gui-tests-for-swing-applications.html
 */
public class TestUtils {
    /**
     * getChildNamed returns the child of the swing component with the provided
     * name.
     * @param parent - The component to search within
     * @param childName - The name of the child we are searching for
     * @return the component that has the provided name, or null if nonexistent
     */
    public static Component getChildNamed(Component parent, String childName) {
        if (childName.equals(parent.getName())) {
            return parent;
        }
        if (parent instanceof Container) {
            Component[] children = ((Container) parent).getComponents();

            for (Component child : children) {
                Component foundChild = getChildNamed(child, childName);
                if (foundChild != null) {
                    return foundChild;
                }
            }
        }
        return null;
    }
}
