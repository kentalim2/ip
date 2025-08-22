/**
 * Represents a task that has a set deadline
 *
 * @author kentalim2
 */
public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), this.deadline);
    }
}
