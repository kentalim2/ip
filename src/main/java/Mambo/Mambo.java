package Mambo;

import Mambo.command.Command;
import Mambo.parser.Parser;
import Mambo.task.TaskList;

public class Mambo {

    /**
     * Runs an instance of the Mambo chatbot
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

    public static void main(String[] args) {
        new Mambo().run();
    }
}
