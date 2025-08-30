import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents an event task that has a start and end time
 *
 * @author kentalim2
 */
public class EventTask extends Task {
    private String start;
    private String end;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public EventTask(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;

        try {
            startDateTime = DateTimeParser.parseDateTime(start);
        } catch (DateTimeParseException e) {
            startDateTime = null;
        }

        try {
            endDateTime = DateTimeParser.parseDateTime(end);
        } catch (DateTimeParseException e) {
            endDateTime = null;
        }
    }

    public String getStart() {
        if (this.startDateTime != null) {
            return DateTimeParser.formatDateTime(startDateTime);
        } else {
            return this.start;
        }
    }

    public String getEnd() {
        if (this.endDateTime != null) {
            return DateTimeParser.formatDateTime(endDateTime);
        } else {
            return this.end;
        }

    }

    @Override
    public String convertToFileFormat() {
        return String.format("E / %s / %s / %s / %s",
                this.isMarked(), this.getDescription(), this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("(E) %s (from: %s -> to: %s)",
                super.toString(), getStart(), getEnd());
    }
}
