package mambo.command;

import mambo.MamboException;
import mambo.TaskListFile;
import mambo.Ui;
import mambo.task.DeadlineTask;
import mambo.task.TaskList;

/**
 * Represents a single "deadline" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String argument) {
        super(argument);
    }

    /**
     * Adds a deadline task to the current list.
     * Gets description and deadline of task by splitting argument at "/by".
     * Prints out confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when the argument of the deadline task does not follow the required format.
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @throws MamboException Throws exception when non-proper format is used to add task
     */
    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException {
        String[] taskDetails = this.getArgument().split("/by");
        // if deadline is not formatted correctly, [description, deadline], throw an error message
        if (taskDetails.length != 2) {
            throw new MamboException("are you sure you are following the proper format for deadline tasks? "
                    + "it should look like this: \"deadline *description* /by *time/date*\"");
        }
        System.out.println(ui.respond(tasks.addToList(new DeadlineTask(taskDetails[0].trim(),
                false, taskDetails[1].trim()))));
    }
}
