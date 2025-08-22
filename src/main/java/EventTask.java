/**
 * Represents a task that has a set start and end time
 *
 * @author kentalim2
 */
public class EventTask extends Task {
    private String start;
    private String end;

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("(E) %s (from: %s -> to: %s)",
                             super.toString(), this.start, this.end);
    }
}
