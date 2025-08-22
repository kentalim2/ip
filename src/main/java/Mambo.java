import java.util.Scanner;

public class Mambo {
    private static final String line = "\n───────────────────────────────────────────────────────────────"
            + "───────────────────────────────────────────────────────────────────────\n";

    // list of commands available communicated by the chatbot at the start
    private static final String commandList = "List of commands:\n"
            + "\"bye\": exit the chat\n"
            + "\"list\": show the current list\n"
            + "\"todo *task*\": add a todo task to the list\n"
            + "\"deadline *task* /by *deadline*\": add a deadline task by the specified deadline\n"
            + "\"event *task* /from *start* /to *end*\": add event task with start and end time/date\n"
            + "\"mark *number*\": mark a task at *number* on the list to be done\n"
            + "\"unmark *number*\": unmark a task at *number* on the list\n"
            + "\"delete *number*\": delete task number *number* on the list";


    private enum Command {
        BYE, MARK, UNMARK, LIST, TODO, EVENT, DEADLINE, DELETE
    }

    /**
     * Takes a message that the chatbot is supposed to send and formats it with 2 long continuous lines.
     *
     * @param message
     * @return formatted message block to be sent by chatbot
     */
    private static String respond(String message) {
        return line + message + line;
    }

    /**
     * Takes a line of input that is given to the chatbot and looks out for
     * specific commands that the chatbot offers functionality for
     * If user input is not any of the existing commands, it will throw an error
     *
     * @param input the line of input from user
     * @return the command that is given to the chatbot if any
     */
    private static Command parseCommand(String input) throws MamboException {
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
            } else if (input.toLowerCase().startsWith("delete")) {
                return Command.DELETE;
            } else {
                throw new MamboException("ummm not sure what that's supposed to mean. "
                        + "try one of the commands listed!");
            }
    }

    /**
     * Given an input that uses a command which has an argument, returns that argument as a string
     * by removing the letters of the command from the input
     * @param input given to chatbot
     * @param command the command which the chatbot is trying to perform
     * @return string representation of argument
     */
    private static String getArgument(Command command, String input) {
        return switch (command) {
            case MARK, TODO -> input.substring(4).trim();
            case EVENT -> input.substring(5).trim();
            case UNMARK, DELETE -> input.substring(6).trim();
            case DEADLINE -> input.substring(8).trim();
            default -> "";
        };
    }

    /**
     * Handles the marking of tasks on the list by parsing out index of list the
     * user wants to mark and passing it into markTask().
     * Throws an exception when a non integer is passed through or the number
     * is out of bounds of the list.
     *
     * @param argument the index of the task to mark
     * @param list the list of tasks
     */
    private static String handleMark(String argument, TaskList list) throws MamboException {
        try {
            int index = Integer.parseInt(argument);

            // to catch if trying to access out of bounds index
            if (index < 1 || index > list.listSize()) {
                throw new MamboException("your list doesnt have a task at that number dummy!");
            }

            return list.markTask(index);
        } catch (NumberFormatException e) {
            // throw error when an exception is caught due to the argument not being an integer
            throw new MamboException("hey!! this is not the right way to use mark. "
                    + "make sure you follow the format \"mark *integer*\"!");
        }
    }

    /**
     * Handles the unmarking of tasks on the list by parsing out index of list the
     * user wants to unmark and passing it into unmarkTask()
     * Throws an exception when a non integer is passed through or the number
     * is out of bounds of the list.
     *
     * @param argument the index of the task to unmark
     * @param list the list of tasks
     */
    private static String handleUnmark(String argument, TaskList list) throws MamboException {
        try {
            int index = Integer.parseInt(argument);

            // to catch if trying to access out of bounds index
            if (index < 1 || index > list.listSize()) {
                throw new MamboException("your list doesnt have a task at that number dummy!");
            }

            return list.unmarkTask(index);
        } catch (NumberFormatException e) {
            // throw error when an exception is caught due to the argument not being an integer
            throw new MamboException("hey!! this is not the right way to use unmark. "
                    + "make sure you follow the format \"unmark *integer*\"!");
        }
    }

    /**
     * Handles the deletion of a task from the list
     * Throws an exception when trying to delete a task not in the list or integers
     * are not used to refer to the task trying to be deleted
     *
     * @param argument the number of the task to be deleted
     * @param list the current task list
     * @return chatbot message to be sent based on outcome of function
     * @throws MamboException when trying to access out of bounds task or wrong command format
     */
    private static String handleDelete(String argument, TaskList list) throws MamboException {
        try {
            int index = Integer.parseInt(argument);

            // to catch if trying to access out of bounds index
            if (index < 1 || index > list.listSize()) {
                throw new MamboException("theres nothing to delete at that number!");
            }
            return list.deleteTask(index);

        } catch (NumberFormatException e) {
            // throw error when an exception is caught due to the argument not being an integer
            throw new MamboException("hey!! this is not the right way to use delete. "
                    + "make sure you follow the format \"delete *integer*\"!");
        }
    }

    /**
     * Handles the adding of various tasks to task list.
     * Throws exception if improper format is used by making sure the comparing the size
     * of the split() array returned with the number of segments in each command
     *
     * @param taskDetails created by calling split on the input to divide input into sections
     * @param command the command that it is executing
     * @param list the current task list
     * @return String representation of what chatbot will send back to user
     * @throws MamboException which is caused by wrong formatting of command
     */
    private static String handleTaskAdding(String[] taskDetails,
                                           Command command, TaskList list) throws MamboException {
        switch (command) {
            case TODO:
                // if no description attached to todo, throw an error message
                if (taskDetails[0].isEmpty()) {
                    throw new MamboException("your description of a todo cant be empty!");
                }
                return list.addToList(new ToDoTask(taskDetails[0]));
            case DEADLINE:
                // if deadline is not formatted correctly, [description, deadline], throw an error message
                if (taskDetails.length != 2) {
                    throw new MamboException("are you sure you are following the proper formatfor deadline tasks? "
                            + "it should look like this: \"deadline *description* /by *time/date*\"");
                }
                return list.addToList(new DeadlineTask(taskDetails[0].trim(),
                        taskDetails[1].trim()));
            case EVENT:
                // if event is not formatted correctly, [description, start, end], throw an error
                if (taskDetails.length != 3) {
                    throw new MamboException("are you sure you are following the proper format for events? "
                            + "it should look like this: \"event *description* /from *time* /to time\"");
                }
                return list.addToList(new EventTask(taskDetails[0].trim(),
                        taskDetails[1].trim(),
                        taskDetails[2].trim()));
        }
        return "";
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

        System.out.println(respond("hi! I'm Mambo, your personal assistant and chatbot!\n"
                + "what can I do for you today? ei, ei mun!")
                + commandList + line);
        while (isRunning) {
            try {
                String input = scanner.nextLine().trim();
                Command command = parseCommand(input);

                switch (command) {
                    case BYE:
                        System.out.println(respond("byee, see you again!"));
                        isRunning = false;
                        break;
                    case LIST:
                        System.out.println(respond("these are your tasks!\n" + list));
                        break;
                    case MARK:
                        String marked = getArgument(command, input);
                        System.out.println(respond(handleMark(marked, list)));
                        break;
                    case UNMARK:
                        String unmarked = getArgument(command, input);
                        System.out.println(respond(handleUnmark(unmarked, list)));
                        break;
                    case TODO:
                        String[] task = new String[] { getArgument(command, input) };
                        System.out.println(respond(handleTaskAdding(task, command, list)));
                        break;
                    case EVENT:
                        // splits the event task into [description, start, end]
                        String[] eventDetails = getArgument(command, input).split("/from|/to");
                        System.out.println(respond(handleTaskAdding(eventDetails, command, list)));
                        break;
                    case DEADLINE:
                        // splits the deadline task into [description, deadline]
                        String[] taskDetails = getArgument(command, input).split("/by");
                        System.out.println(respond(handleTaskAdding(taskDetails, command, list)));
                        break;
                    case DELETE:
                        String toDelete = getArgument(command, input);
                        System.out.println(respond(handleDelete(toDelete, list)));
                        break;
                }
            // catch any exceptions thrown by any of the commands
            } catch (MamboException e) {
                System.out.println(respond(e.getMessage()));
            }

        }

        scanner.close();
    }
}
