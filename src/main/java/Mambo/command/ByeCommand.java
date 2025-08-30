package Mambo.command;

import Mambo.task.TaskList;
import Mambo.TaskListFile;
import Mambo.Ui;

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
    public boolean continueRunning() {
        return false;
    }
}
