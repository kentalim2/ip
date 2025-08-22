/**
 * Represents a task which does not have any deadlines or dates attached to it
 *
 * @author kentalim2
 */
public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "(T) " + super.toString();
    }
}
