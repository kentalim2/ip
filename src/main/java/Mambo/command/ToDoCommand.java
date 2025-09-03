package Mambo.command;

import Mambo.MamboException;
import Mambo.TaskListFile;
import Mambo.Ui;
import Mambo.task.TaskList;
import Mambo.task.ToDoTask;

/**
 * Represents a single "todo" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class ToDoCommand extends Command {

    public ToDoCommand(String argument) {
        super(argument);
    }

    /**
     * Adds a todo task to the current list.
     * Prints out confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when the argument of the todo task is empty.
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @throws MamboException Throws exception non-proper format is used to add task
     */
    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException {
        // if no description attached to todo, throw an error message
        if (this.getArgument().isEmpty()) {
            throw new MamboException("your description of a todo cant be empty!");
        }
        System.out.println(ui.respond(tasks.addToList(new ToDoTask(this.getArgument(),
                false))));
    }


}
