/**
 * Represents a task that has a set deadline
 *
 * @author kentalim2
 */
public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D / %s / %s / %s",
                this.isMarked(), this.getDescription(), this.deadline);
    }

    @Override public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), this.deadline);
    }
}
