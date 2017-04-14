/**
Notifier defines an interface to send messages to the user.
@author Erik Cole
@author Chris Waldon
@version 0.1
*/
public interface Notifier {
    /**
    notify sends a message to the user.
    @param message - what do you want to say to the user.
    */
    void notify(String message);
}
