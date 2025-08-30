package Mambo.command;

import Mambo.MamboException;
import Mambo.TaskListFile;
import Mambo.Ui;
import Mambo.task.TaskList;

public class EventCommand extends Command {

    public EventCommand(String argument) {
        super(argument);
    }

    /**
     * Adds an event task to the current list.
     * Gets description and deadline of task by splitting argument at "/from" and "/to".
     * Prints out confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when the argument of the event task does not follow the required format.
     *
     * @param tasks Mambo.Mambo.Mambo.Task.Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @throws MamboException Throws exception non-proper format is used to add task
     */
    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException {
        String[] taskDetails = this.getArgument().split("/from|/to");
        // if event is not formatted correctly, [description, start, end], throw an error
        if (taskDetails.length != 3) {
            throw new MamboException("are you sure you are following the proper format for events? "
                    + "it should look like this: \"event *description* /from *time* /to time\"");
        }
        System.out.println(ui.respond(tasks.addToList(new Mambo.task.EventTask(taskDetails[0].trim(),
                false,
                taskDetails[1].trim(),
                taskDetails[2].trim()))));
    }
}
