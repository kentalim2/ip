/**
 * Represents a task that is to be tracked by the chatbot
 * Supports common functionality such as marking a task as done
 *
 * @author kentalim2
 */
public class Task {
    private boolean isDone;
    private final String description;

    /**
     * Constructs a new task with the specified description.
     * The task is not completed by default and isDone is set to false
     *
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }

    }


}
