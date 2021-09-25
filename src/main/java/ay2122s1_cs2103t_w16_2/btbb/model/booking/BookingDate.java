package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Booking's date.
 */
public class BookingDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the format dd-mm-yyyy";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            .withResolverStyle(ResolverStyle.STRICT);

    private LocalDate bookingDate;

    /**
     * Constructs a {@code BookingDate}.
     *
     * @param bookingDate A valid bookingDate.
     */
    public BookingDate(LocalDate bookingDate) {
        requireNonNull(bookingDate);

        this.bookingDate = bookingDate;
    }

    /**
     * Returns true if a given string is a valid date in the specified format (dd-MM-yyyy).
     *
     * @param test String to test.
     * @return true if a given string is a valid date in the specified format (dd-MM-yyyy).
     */
    public static boolean isValidBookingDate(String test) {
        requireNonNull(test);

        try {
            LocalDate.parse(test, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Converts the input booking date string to a {@code BookingDate}.
     *
     * @param bookingDateString String to be converted.
     * @return BookingDate that has been converted from a string.
     */
    public static BookingDate fromDateString(String bookingDateString) {
        requireNonNull(bookingDateString);

        LocalDate bookingDate = LocalDate.parse(bookingDateString, DATE_FORMAT);
        return new BookingDate(bookingDate);
    }
}
