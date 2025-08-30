import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DateTimeParser {

    private static final DateTimeFormatter[] DATE_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yy"),
            DateTimeFormatter.ofPattern("dd-MM-yy"),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMM yyyy").toFormatter(),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMM dd yyyy").toFormatter(),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMMM dd yyyy").toFormatter(),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMMM yyyy").toFormatter(),
            DateTimeFormatter.ofPattern("dd/M/yy"),
            DateTimeFormatter.ofPattern("M/dd/yy"),
            DateTimeFormatter.ofPattern("yyyy-M-dd"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("M/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd-M-yyyy"),
            DateTimeFormatter.ofPattern("M-dd-yyyy"),
            DateTimeFormatter.ofPattern("M-dd-yy"),
            DateTimeFormatter.ofPattern("dd-M-yy")
    };

    private static final DateTimeFormatter[] TIME_FORMATS = {
            DateTimeFormatter.ofPattern("HHmm"),
            DateTimeFormatter.ofPattern("HH:mm"),
            DateTimeFormatter.ofPattern("hh:mm a"),
            DateTimeFormatter.ofPattern("h:mm a"),
            DateTimeFormatter.ofPattern("ha")
    };


    private static final DateTimeFormatter DISPLAY_DATETIME = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private static final Pattern DATETIME_PATTERN =
        Pattern.compile("^(.*?)\\s+"
                        + "(\\d{3,4}"
                        + "|"
                        + "\\d{1,2}:\\d{2}"
                        + "|"
                        + "\\d{1,2}:\\d{2}\\s*[ap]m"
                        + "|"
                        + "\\d{1,2}\\s*[ap]m)$",
                Pattern.CASE_INSENSITIVE);

    public static LocalDate parseDate(String input) throws DateTimeParseException {
        for (DateTimeFormatter format : DATE_FORMATS) {
            try {
                return LocalDate.parse(input, format);

            } catch (DateTimeParseException e) {
                continue;
            }

        }
        throw new DateTimeParseException("no date", input, 0);
    }

    public static LocalTime parseTime(String input) throws DateTimeParseException {
        for (DateTimeFormatter format : TIME_FORMATS) {
            try {
                return LocalTime.parse(input, format);

            } catch (DateTimeParseException e) {
                continue;
            }

        }
        throw new DateTimeParseException("no time", input, 0);
    }

    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        try {
            Matcher matcher = DATETIME_PATTERN.matcher(input.trim());

            // if input matches format, split into date and time inputs and parse through each to get date/time
            if (matcher.matches()) {
                String date = matcher.group(1).trim();
                String time = matcher.group(2).trim();
                LocalDate localDate = parseDate(date);
                LocalTime localTime = parseTime(time);
                return LocalDateTime.of(localDate, localTime);
            }

        } catch (DateTimeParseException e) {

        }

        try {
            LocalDate date = parseDate(input);
            return LocalDateTime.of(date, LocalTime.of(23, 59));
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("no valid date or time", input, 0);
        }

    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_DATETIME);

    }

    public static String formatDate(LocalDate date) {
        return date.format(DISPLAY_DATE);
    }
}
