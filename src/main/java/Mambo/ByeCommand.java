package Mambo;

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
    public boolean continueRunning() {
        return false;
    }
}
