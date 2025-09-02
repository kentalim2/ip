package Mambo.command;

import Mambo.TaskListFile;
import Mambo.Ui;
import Mambo.task.TaskList;

public class ListCommand extends Command {

    public ListCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) {
        System.out.println(ui.respond("these are your tasks!\n" + tasks));
    }
}
