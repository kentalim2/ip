package mambo.command;

import mambo.TaskListFile;
import mambo.Ui;
import mambo.task.TaskList;

/**
 * Represents a single "bye" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class ByeCommand extends Command {

    public ByeCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) {
        System.out.println(ui.sendExit());
        ui.closeScanner();
    }

    /**
     * Returns false boolean to end chatbot program
     * @return False to signify the end of the program
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
