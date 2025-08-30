public class MarkCommand extends Command {

    public MarkCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the mark command and handles the marking of tasks on the list by parsing out
     * index of list the user wants to mark and passing it into markTask().
     * Prints out confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when a non integer is passed through or the number
     * is out of bounds of the list.
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @throws MamboException Throws exception when non integer is passed through or item is out of bounds
     */
    @Override
    public void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException {
        try {
            int index = Integer.parseInt(this.getArgument());

            // to catch if trying to access out of bounds index
            if (index < 1 || index > tasks.listSize()) {
                throw new MamboException("your list doesnt have a task at that number dummy!");
            }

            System.out.println(ui.respond(tasks.markTask(index)));
        } catch (NumberFormatException e) {
            // throw error when an exception is caught due to the argument not being an integer
            throw new MamboException("hey!! this is not the right way to use mark. "
                    + "make sure you follow the format \"mark *integer*\"!");
        }
    }
}
