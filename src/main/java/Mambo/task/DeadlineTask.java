package Mambo.task;

import Mambo.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that has a set deadline
 *
 * @author kentalim2
 */
public class DeadlineTask extends Task {
    private String deadline;
    private LocalDateTime deadlineDateTime;

    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
        try {
           deadlineDateTime = DateTimeParser.parseDateTime(deadline);

        } catch (DateTimeParseException e) {
            deadlineDateTime = null;
        }

    }

    public String getDeadline() {
        if (this.deadlineDateTime != null) {
            return DateTimeParser.formatDateTime(deadlineDateTime);
        } else {
            return this.deadline;
        }
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D / %s / %s / %s",
                this.isMarked(), this.getDescription(), getDeadline());
    }

    @Override public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), getDeadline());
    }
}
