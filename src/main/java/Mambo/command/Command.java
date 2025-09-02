package Mambo.command;

import Mambo.MamboException;
import Mambo.TaskListFile;
import Mambo.Ui;
import Mambo.task.TaskList;

public abstract class Command {
    private final String argument;

    public Command(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return this.argument;
    }

    public abstract void execute(Ui ui, TaskList tasks, TaskListFile file) throws MamboException;

    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean equals(Object c) {
        if (c == null) {
            return false;
        }
        if (this.getClass() != c.getClass()) {
            return false;
        }

        Command obj = (Command) c;
        return this.argument.equals(obj.argument);
    }
}
