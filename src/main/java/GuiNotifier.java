import javax.swing.JOptionPane;

/**
GuiNotifier sends messages to the user via popups.
@author Erik Cole
@author Chris Waldon
@version 0.1
*/
public class GuiNotifier implements Notifier {
    /**
    notify creates a popup containing message.
    @param message - the message that you want to display 
    */
    public void notify(String message) {
        JOptionPane.showMessageDialog(null, message, "",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
}
