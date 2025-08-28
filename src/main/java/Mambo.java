import java.util.Scanner;

public class Mambo {

    private Ui ui = new Ui();

    public enum Command {
        BYE, MARK, UNMARK, LIST, TODO, EVENT, DEADLINE, DELETE
    }

    /**
     * Runs an instance of the Mambo chatbot
     * Chatbot ends and exits when user types "bye"
     * Show list when user types "list"
     * When user types "mark" and "unmark", marks and unmarks tasks in list respectively
     * Adds ToDoTask, Event and Deadline task when respective task types are entered by user
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        TaskList list;
        TaskListFile file = new TaskListFile();

        file.initializeFile();
        list = file.loadFile();
        System.out.println(ui.sendEntry());

        while (isRunning) {
            try {
                String input = scanner.nextLine().trim();
                Command command = Parser.parseCommand(input);

                switch (command) {
                case BYE:
                    System.out.println(ui.sendExit());
                    isRunning = false;
                    break;
                case LIST:
                    System.out.println(ui.respond("these are your tasks!\n" + list));
                    break;
                case MARK:
                    String marked = Parser.getArgument(command, input);
                    System.out.println(ui.respond(Parser.handleMark(marked, list)));
                    break;
                case UNMARK:
                    String unmarked = Parser.getArgument(command, input);
                    System.out.println(ui.respond(Parser.handleUnmark(unmarked, list)));
                    break;
                case TODO:
                    String[] task = new String[]{Parser.getArgument(command, input)};
                    System.out.println(ui.respond(Parser.handleTaskAdding(task, command, list)));
                    break;
                case EVENT:
                    // splits the event task into [description, start, end]
                    String[] eventDetails = Parser.getArgument(command, input).split("/from|/to");
                    System.out.println(ui.respond(Parser.handleTaskAdding(eventDetails, command, list)));
                    break;
                case DEADLINE:
                    // splits the deadline task into [description, deadline]
                    String[] taskDetails = Parser.getArgument(command, input).split("/by");
                    System.out.println(ui.respond(Parser.handleTaskAdding(taskDetails, command, list)));
                    break;
                case DELETE:
                    String toDelete = Parser.getArgument(command, input);
                    System.out.println(ui.respond(Parser.handleDelete(toDelete, list)));
                    break;
                }
                // catch any exceptions thrown by any of the commands
            } catch (MamboException e) {
                System.out.println(ui.respond(e.getMessage()));
            }

        }
        file.saveFile(list);
        scanner.close();
    }

    public static void main(String[] args) {
        new Mambo().run();
    }
}
