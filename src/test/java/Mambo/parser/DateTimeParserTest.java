package Mambo.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {

    @Test
    public void parseDateTest1() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("15-12-2003 1800")));
    }

    @Test
    public void parseDateTest2() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser
                        .parseDateTime("dEcEMber 15 2003 6pm")));
    }

    @Test
    public void parseDateTest3() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("15/12/03 18:00")));
    }

    @Test
    public void parseDateTest4() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("15 dEC 2003 6:00 pm")));
    }
}
