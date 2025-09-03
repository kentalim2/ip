package Mambo.command;

import Mambo.MamboException;
import Mambo.TaskListFile;
import Mambo.Ui;
import Mambo.task.TaskList;

public class FindCommand extends Command{

    public FindCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException {

    }
}
