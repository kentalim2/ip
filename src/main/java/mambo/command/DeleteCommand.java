package mambo.command;

import mambo.MamboException;
import mambo.TaskListFile;
import mambo.Ui;
import mambo.task.TaskList;

/**
 * Represents a single "delete" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String argument) {
        super(argument);
    }

    /**
     * Executes command which handles the deletion of a task from the list.
     * Prints out confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when trying to delete a task not in the list or integers
     * are not used to refer to the task trying to be deleted
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @throws MamboException Occurs when trying to access out of bounds task or wrong command format
     */
    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException {
        try {
            int index = Integer.parseInt(this.getArgument());

            // to catch if trying to access out of bounds index
            if (index < 1 || index > tasks.listSize()) {
                throw new MamboException("theres nothing to delete at that number!");
            }
            System.out.println(ui.respond(tasks.deleteTask(index)));

        } catch (NumberFormatException e) {
            // throw error when an exception is caught due to the argument not being an integer
            throw new MamboException("hey!! this is not the right way to use delete. "
                    + "make sure you follow the format \"delete *integer*\"!");
        }
    }
}
