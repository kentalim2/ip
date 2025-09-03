package Mambo;

import Mambo.command.Command;
import Mambo.parser.Parser;
import Mambo.task.TaskList;

/**
 * Represents the Mambo chatbot, supporting the functionality of running the program.
 *
 * @author kentalim2
 */
public class Mambo {

    /**
     * Runs an instance of the Mambo chatbot.
     * Starts by loading existing task list if any into system.
     * The chatbot will read inputs from user line by line and execute the respective command.
     * This function then makes sure current task list is saved locally when the chatbot is closed.
     */
    public void run() {
        Ui ui = new Ui();
        boolean isRunning = true;
        TaskList list;
        TaskListFile file = new TaskListFile();

        file.initializeFile();
        list = file.loadFile();
        System.out.println(ui.sendEntry());

        while (isRunning) {
            try {
                String input = ui.readInput();
                Command c = Parser.parseCommand(input);
                c.execute(ui, list, file);
                isRunning = c.isRunning();

              // catch any exceptions thrown by any of the commands
            } catch (MamboException e) {
                System.out.println(ui.respond(e.getMessage()));
            }

        }
        file.saveFile(list);
    }

    /**
     * Creates a new instance of the Mambo chatbot and runs it
     */
    public static void main(String[] args) {
        new Mambo().run();
    }
}
