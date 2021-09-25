package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Booking's slot.
 * Each slot has a start time and end time.
 */
public enum BookingSlot {
    SLOT1(LocalTime.of(0, 0), LocalTime.of(1, 30)),
    SLOT2(LocalTime.of(1, 30), LocalTime.of(3, 0)),
    SLOT3(LocalTime.of(3, 0), LocalTime.of(4, 30)),
    SLOT4(LocalTime.of(4, 30), LocalTime.of(6, 0)),
    SLOT5(LocalTime.of(6, 0), LocalTime.of(7, 30)),
    SLOT6(LocalTime.of(7, 30), LocalTime.of(9, 0)),
    SLOT7(LocalTime.of(9, 0), LocalTime.of(10, 30)),
    SLOT8(LocalTime.of(10, 30), LocalTime.of(12, 0)),
    SLOT9(LocalTime.of(12, 0), LocalTime.of(13, 30)),
    SLOT10(LocalTime.of(13, 30), LocalTime.of(15, 0)),
    SLOT11(LocalTime.of(15, 0), LocalTime.of(16, 30)),
    SLOT12(LocalTime.of(16, 30), LocalTime.of(18, 0)),
    SLOT13(LocalTime.of(18, 0), LocalTime.of(19, 30)),
    SLOT14(LocalTime.of(19, 30), LocalTime.of(21, 0)),
    SLOT15(LocalTime.of(21, 0), LocalTime.of(22, 30)),
    SLOT16(LocalTime.of(22, 30), LocalTime.of(0, 0));

    public static final String MESSAGE_CONSTRAINTS =
            "Time should be of the format HHmm";
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructs a {@code BookingSlot}
     *
     * @param startTime A valid start time.
     * @param endTime A valid end time.
     */
    BookingSlot(LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(startTime, endTime);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns true if a given string is a valid time in the specified format (HHmm).
     *
     * @param test String to test.
     * @return true if a given string is a valid time in the specified format (HHmm).
     */
    public static boolean isValidTime(String test) {
        requireNonNull(test);

        try {
            LocalTime.parse(test, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a {@code BookingSlot} whose start time is within 45 minutes of the input booking time.
     *
     * @param bookingTimeString Time to be converted to a BookingSlot.
     * @return BookingSlot whose start time is within 45 minutes of the input booking time.
     */
    public static BookingSlot getNearestSlotFromTimeString(String bookingTimeString) {
        requireNonNull(bookingTimeString);

        LocalTime bookingTime = LocalTime.parse(bookingTimeString);
        BookingSlot bookingSlot = null;
        for (BookingSlot bs: BookingSlot.values()) {
            Duration timeDiff = Duration.between(bs.startTime, bookingTime).abs();
            if (timeDiff.toMinutes() <= 45) {
                bookingSlot = bs;
                break;
            }
        }
        return bookingSlot;
    }
}
