/**
ConsoleNotifier sends messages to the user in stdout.
@author Erik Cole
@author Chris Waldon
@version 0.1
*/
public class ConsoleNotifier implements Notifier {
    /**
    notify prints a message in stdout.
    @param message - the message that you want to print
    */
    public void notify(String message) {
        System.out.println(message);
    }
}
