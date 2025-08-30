
public abstract class Command {
    private String argument;

    public Command(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return this.argument;
    }

    public abstract void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException;

    public boolean continueRunning() {
        return true;
    };

}
