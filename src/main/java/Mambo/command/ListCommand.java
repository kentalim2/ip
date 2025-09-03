package Mambo.command;

import Mambo.MamboException;
import Mambo.TaskListFile;
import Mambo.Ui;
import Mambo.task.TaskList;

/**
 * Represents a single "list" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class ListCommand extends Command {

    public ListCommand(String argument) {
        super(argument);
    }

    /**
     * Displays the current list of tasks to the user
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @throws MamboException Throws exception non-proper format is used to add task
     */
    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) {
        System.out.println(ui.respond("these are your tasks!\n" + tasks));
    }
}
