import java.util.Scanner;

public class Mambo {
    private static final String line = """
            \n─────────────────────────────────\
            ──────────────────────────────
            """;

    private enum Command {
        BYE, MARK, UNMARK, LIST, UNKNOWN
    }

    /**
     * Takes a message that the chatbot is supposed to send and formats it with 2 long continuous lines.
     *
     * @param message
     * @return formatted message block to be sent by chatbot
     */
    private static String response(String message) {
        return line + message + line;
    }

    /**
     * Takes a line of input that is given to the chatbot and parses out
     * specific commands that the chatbot offers functionality for
     *
     * @param input the line of input from user
     * @return the command that is given to the chatbot if any
     */
    private static Command parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return Command.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else {
            return Command.UNKNOWN;
        }
    }

    /**
     * Given an input that uses a command which has an argument which is an integer,
     * returns that argument as a string
     * @param input given to chatbot
     * @param command the command which the chatbot is trying to perform
     * @return numerical argument
     */
    private static String getArgument(Command command, String input) {
        switch (command) {
            case MARK:
                return input.substring(4).trim();
            case UNMARK:
                return input.substring(6).trim();
            default:
                return "";
        }
    }

    /**
     * Handles the marking of tasks on the list
     *
     * @param argument the index of the task to mark
     * @param list the list of tasks
     */
    private static String handleMark(String argument, TaskList list) {
        int index = Integer.parseInt(argument);
        return list.markTask(index);
    }

    /**
     * Handles the unmarking of tasks on the list
     *
     * @param argument the index of the task to unmark
     * @param list the list of tasks
     */
    private static String handleUnmark(String argument, TaskList list) {
        int index = Integer.parseInt(argument);
        return list.unmarkTask(index);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        TaskList list = new TaskList();

        System.out.println(response("hi! I'm Mambo, your personal assistant and chatbot!\n"
                                    + "what can I do for you today? ei, ei mun!"));
        while (isRunning) {
            String input = scanner.nextLine().trim();
            Command command = parseCommand(input);

            switch (command) {
                case BYE:
                    System.out.println(response("byee, see you again!"));
                    isRunning = false;
                    break;
                case LIST:
                    System.out.println(response("these are your tasks!\n" + list));
                    break;
                case MARK:
                    String marked = getArgument(command,input);
                    System.out.println(response(handleMark(marked, list)));
                    break;
                case UNMARK:
                    String unmarked = getArgument(command, input);
                    System.out.println(response(handleUnmark(unmarked, list)));
                    break;
                case UNKNOWN:
                    list.addToList(new Task(input));
                    System.out.println(response("I've added the task to your list:\n" + input));
                    break;
            }
        }

        scanner.close();
    }
}
