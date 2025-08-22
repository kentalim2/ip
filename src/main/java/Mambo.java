import java.util.Scanner;

public class Mambo {
    private static final String line = "\n────────────────────────────────"
            + "──────────────────────────────\n";

    private static final String commandList = "List of commands:\n"
            + "\"bye\": exit the chat\n"
            + "\"list\": show the current list\n"
            + "\"todo (task)\": add a todo task to the list\n"
            + "\"deadline (task) /by (deadline)\": add a deadline task by the specified deadline\n"
            + "\"event (task) /from (start) /to (end)\": add event task with start and end time/date\n"
            + "\"mark (number)\": mark a task at (number) on the list to be done\n"
            + "\"unmark (number)\": unmark a task at (number) on the list";


    private enum Command {
        BYE, MARK, UNMARK, LIST, UNKNOWN, TODO, EVENT, DEADLINE
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
     * Takes a line of input that is given to the chatbot and looks out for
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
        } else if (input.toLowerCase().startsWith("mark")) {
            return Command.MARK;
        } else if (input.toLowerCase().startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.toLowerCase().startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.toLowerCase().startsWith("event")) {
            return Command.EVENT;
        } else if (input.toLowerCase().startsWith("todo")) {
            return Command.TODO;
        } else {
            return Command.UNKNOWN;
        }
    }

    /**
     * Given an input that uses a command which has an argument, returns that argument as a string
     *
     * @param input given to chatbot
     * @param command the command which the chatbot is trying to perform
     * @return string representation of argument
     */
    private static String getArgument(Command command, String input) {
        switch (command) {
            case MARK, TODO:
                return input.substring(4).trim();
            case EVENT:
                return input.substring(5).trim();
            case UNMARK:
                return input.substring(6).trim();
            case DEADLINE:
                return input.substring(8).trim();
            default:
                return "";
        }
    }

    /**
     * Handles the marking of tasks on the list by parsing out index of list the
     * user wants to mark and passing it into markTask()
     *
     * @param argument the index of the task to mark
     * @param list the list of tasks
     */
    private static String handleMark(String argument, TaskList list) {
        int index = Integer.parseInt(argument);
        return list.markTask(index);
    }

    /**
     * Handles the unmarking of tasks on the list by parsing out index of list the
     * user wants to unmark and passing it into unmarkTask()
     *
     * @param argument the index of the task to unmark
     * @param list the list of tasks
     */
    private static String handleUnmark(String argument, TaskList list) {
        int index = Integer.parseInt(argument);
        return list.unmarkTask(index);
    }

    /**
     * Chatbot ends and exits when user types "bye"
     * Show list when user types "list"
     * When user types "mark" and "unmark", marks and unmarks tasks in list respectively
     * Adds ToDoTask, Event and Deadline task when respective task types are entered by user
     *
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        TaskList list = new TaskList();

        System.out.println(response("hi! I'm Mambo, your personal assistant and chatbot!\n"
                + "what can I do for you today? ei, ei mun!")
                + commandList + line);
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
                case TODO:
                    String task = getArgument(command, input);
                    System.out.println(response(list.addToList(new ToDoTask(task))));
                    break;
                case EVENT:
                    //splits the event task into [description, start, end]
                    String[] eventDetails = getArgument(command, input).split("/from|/to");
                    System.out.println(response(list.addToList(new EventTask(eventDetails[0].trim(),
                            eventDetails[1].trim(),
                            eventDetails[2].trim()))));
                    break;
                case DEADLINE:
                    //splits the deadline task into [description, deadline]
                    String[] taskDetails = getArgument(command, input).split("/by");
                    System.out.println(response(list.addToList(new DeadlineTask(taskDetails[0].trim(),
                            taskDetails[1].trim()))));
                    break;
                case UNKNOWN:
                    System.out.println(response("ummm not sure what that's supposed to mean."
                            + "try one of the commands listed!"));
                    break;
            }
        }

        scanner.close();
    }
}
