package Mambo.command;

import Mambo.TaskListFile;
import Mambo.Ui;
import Mambo.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) {
        System.out.println(ui.sendExit());
        ui.closeScanner();
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
