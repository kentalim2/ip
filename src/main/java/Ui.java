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

    /**
     * Takes a message that the chatbot is supposed to send and formats it with 2 long continuous lines.
     * Returns that message.
     *
     * @param message
     * @return formatted message block to be sent by chatbot
     */
    public String respond(String message) {
        return LINE + message + LINE;
    }

    public String sendEntry() {
        return respond("hi! I'm Mambo, your personal assistant and chatbot!\n"
                + "what can I do for you today? ei, ei mun!")
                + COMMAND_LIST + LINE;
    }

    public String sendExit() {
        return respond("byee, see you again!");
    }


}
