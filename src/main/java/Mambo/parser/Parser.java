package Mambo.parser;

import Mambo.MamboException;
import Mambo.command.ByeCommand;
import Mambo.command.Command;
import Mambo.command.DeadlineCommand;
import Mambo.command.DeleteCommand;
import Mambo.command.EventCommand;
import Mambo.command.ListCommand;
import Mambo.command.MarkCommand;
import Mambo.command.ToDoCommand;
import Mambo.command.UnmarkCommand;
import Mambo.task.DeadlineTask;
import Mambo.task.EventTask;
import Mambo.task.Task;
import Mambo.task.ToDoTask;

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
    public static Command parseCommand(String input) throws MamboException {
        if (input.equalsIgnoreCase("bye")) {
            return new ByeCommand("");
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand("");
        } else if (input.toLowerCase().startsWith("mark")) {
            return new MarkCommand(input.substring(4).trim());
        } else if (input.toLowerCase().startsWith("unmark")) {
            return new UnmarkCommand(input.substring(6).trim());
        } else if (input.toLowerCase().startsWith("deadline")) {
            return new DeadlineCommand(input.substring(8).trim());
        } else if (input.toLowerCase().startsWith("event")) {
            return new EventCommand(input.substring(5).trim());
        } else if (input.toLowerCase().startsWith("todo")) {
            return new ToDoCommand(input.substring(4).trim());
        } else if (input.toLowerCase().startsWith("delete")) {
            return new DeleteCommand(input.substring(6).trim());
        } else {
            throw new MamboException("ummm not sure what that's supposed to mean. "
                    + "try one of the commands listed!");
        }
    }

    /**
     * Returns a line of the Mambo.Task.Task.TaskList data file as its represented Task.
     * Throws MamboException when a task that corresponds to the text input is not found.
     *
     * @param nextLine One line of the data file used to store the current tasklist
     * @return Task represented by one line of data file
     * @throws MamboException When no task is found that corresponds to text input
     */
    public static Task parseFile(String nextLine) throws MamboException {
        try {
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
                throw new MamboException("task not found");
            }
        } catch (Exception e) {
            throw new MamboException("file is corrupted");
        }
    }
}
