package mambo;

import java.util.Scanner;

/**
 * Represents the system used to handle all user interface (Ui) operations of the chatbot.
 * Provides functionality for reading an input from the user and formatting the chatbot message
 * with two horizontal lines
 */
public class Ui {
    private static final String LINE = "\n───────────────────────────────────────────────────────────────"
            + "───────────────────────────────────────────────────────────────────────\n";

    // list of commands available communicated by the chatbot at the start
    private static final String COMMAND_LIST = "List of commands:\n"
            + "\"bye\": exit the chat\n"
            + "\"list\": show the current list\n"
            + "\"todo *task*\": add a todo task to the list\n"
            + "\"deadline *task* /by *deadline*\": add a deadline task by the specified deadline\n"
            + "\"event *task* /from *start* /to *end*\": add event task with start and end time/date\n"
            + "\"mark *number*\": mark a task at *number* on the list to be done\n"
            + "\"unmark *number*\": unmark a task at *number* on the list\n"
            + "\"delete *number*\": delete task number *number* on the list";

    private Scanner sc;

    /**
     * Returns a message that the chatbot is supposed to send formatted with 2 long continuous lines.
     *
     * @param message
     * @return formatted message block to be sent by chatbot
     */
    public String respond(String message) {
        return LINE + message + LINE;
    }

    /**
     * Starts the chatbot by opening a new scanner to take in the inputs of users.
     * Also sends a welcome message to the users.
     *
     * @return Confirmation message that chatbot is running
     */
    public String sendEntry() {
        this.sc = new Scanner(System.in);

        return respond("hi! I'm Mambo.Mambo, your personal assistant and chatbot!\n"
                + "what can I do for you today? ei, ei mun!")
                + COMMAND_LIST + LINE;
    }

    /**
     * Sends an exit message through to the chatbot for the user to see
     * @return Exit message as a String
     */
    public String sendExit() {
        return respond("byee, see you again!");
    }

    public void closeScanner() {
        sc.close();
    }

    /**
     * Returns user input as a String after trimming down the whitespaces.
     *
     * @return Input given by user after trimming whitespaces on sides of input
     */
    public String readInput() {
        return sc.nextLine().trim();
    }

}
