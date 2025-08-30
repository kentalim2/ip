/**
 * Represents a parser used to handle all operations which involve parsing an input
 *
 * @author kentalim2
 */
public class Parser {

    /**
     * Takes a line of input that is given to the chatbot and looks out for
     * specific commands that the chatbot offers functionality for
     * If user input is not any of the existing commands, it will throw an error
     *
     * @param input The line of input from user
     * @return The command that is given to the chatbot if any
     */
    public static Mambo.Command parseCommand(String input) throws MamboException {
        if (input.equalsIgnoreCase("bye")) {
            return Mambo.Command.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return Mambo.Command.LIST;
        } else if (input.toLowerCase().startsWith("mark")) {
            return Mambo.Command.MARK;
        } else if (input.toLowerCase().startsWith("unmark")) {
            return Mambo.Command.UNMARK;
        } else if (input.toLowerCase().startsWith("deadline")) {
            return Mambo.Command.DEADLINE;
        } else if (input.toLowerCase().startsWith("event")) {
            return Mambo.Command.EVENT;
        } else if (input.toLowerCase().startsWith("todo")) {
            return Mambo.Command.TODO;
        } else if (input.toLowerCase().startsWith("delete")) {
            return Mambo.Command.DELETE;
        } else {
            throw new MamboException("ummm not sure what that's supposed to mean. "
                    + "try one of the commands listed!");
        }
    }

    /**
     * Returns that argument as a string given an input that uses a command which has an argument,
     * by removing the letters of the command from the input
     *
     * @param input Text input given to chatbot
     * @param command The command which the chatbot is trying to perform
     * @return String representation of argument
     */
    public static String getArgument(Mambo.Command command, String input) {
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
     * @param argument The index of the task to mark
     * @param list The list of tasks
     */
    public static String handleMark(String argument, TaskList list) throws MamboException {
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
     * @param argument The index of the task to unmark
     * @param list The list of tasks
     */
    public static String handleUnmark(String argument, TaskList list) throws MamboException {
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
     * @param argument The number of the task to be deleted
     * @param list The current task list
     * @return Chatbot message to be sent based on outcome of function
     * @throws MamboException Occurs when trying to access out of bounds task or wrong command format
     */
    public static String handleDelete(String argument, TaskList list) throws MamboException {
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
     * @param taskDetails Created by calling split on the input to divide input into sections
     * @param command The command that it is executing
     * @param list The current task list
     * @return String representation of what chatbot will send back to user
     * @throws MamboException Caused by wrong formatting of command
     */
    public static String handleTaskAdding(String[] taskDetails,
                                           Mambo.Command command, TaskList list) throws MamboException {
        switch (command) {
        case TODO:
            // if no description attached to todo, throw an error message
            if (taskDetails[0].isEmpty()) {
                throw new MamboException("your description of a todo cant be empty!");
            }
            return list.addToList(new ToDoTask(taskDetails[0], false));
        case DEADLINE:
            // if deadline is not formatted correctly, [description, deadline], throw an error message
            if (taskDetails.length != 2) {
                throw new MamboException("are you sure you are following the proper formatfor deadline tasks? "
                        + "it should look like this: \"deadline *description* /by *time/date*\"");
            }
            return list.addToList(new DeadlineTask(taskDetails[0].trim(),
                    false, taskDetails[1].trim()));
        case EVENT:
            // if event is not formatted correctly, [description, start, end], throw an error
            if (taskDetails.length != 3) {
                throw new MamboException("are you sure you are following the proper format for events? "
                        + "it should look like this: \"event *description* /from *time* /to time\"");
            }
            return list.addToList(new EventTask(taskDetails[0].trim(),
                    false,
                    taskDetails[1].trim(),
                    taskDetails[2].trim()));
        }
        return "";
    }

    /**
     * Returns a line of the TaskList data file as its represented Task.
     * Throws MamboException when a task that corresponds to the text input is not found.
     *
     * @param nextLine One line of the data file used to store the current tasklist
     * @return Task represented by one line of data file
     * @throws MamboException When no task is found that corresponds to text input
     */
    public static Task parseFile(String nextLine) throws MamboException {
        String[] taskComponents = nextLine.split(" / ");

        switch (taskComponents[0]) {
        case "T":
            return new ToDoTask(taskComponents[2], Boolean.parseBoolean(taskComponents[1]));
        case "D":
            return new DeadlineTask(taskComponents[2], Boolean.parseBoolean(taskComponents[1]),
                    taskComponents[3]);
        case "E":
            return new EventTask(taskComponents[2], Boolean.parseBoolean(taskComponents[1]),
                    taskComponents[3], taskComponents[4]);
        default:
            throw new MamboException("Task not found");
        }
    }
}
