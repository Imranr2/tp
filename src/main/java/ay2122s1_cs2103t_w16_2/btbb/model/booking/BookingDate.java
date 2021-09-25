package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class BookingDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the format dd-mm-yyyy";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            .withResolverStyle(ResolverStyle.STRICT);

    private LocalDate bookingDate;

    public BookingDate(LocalDate bookingDate) {
        requireNonNull(bookingDate);

        this.bookingDate = bookingDate;
    }

    public boolean isValidBookingDate(String bookingDate) {
        requireNonNull(bookingDate);

        try {
            LocalDate.parse(bookingDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public BookingDate fromDateString(String bookingDateString) {
        requireNonNull(bookingDateString);

        LocalDate bookingDate = LocalDate.parse(bookingDateString, DATE_FORMAT);
        return new BookingDate(bookingDate);
    }
}
