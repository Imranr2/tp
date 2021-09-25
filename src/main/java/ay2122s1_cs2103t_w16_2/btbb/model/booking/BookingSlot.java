package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public enum BookingSlot {
    SLOT1(LocalTime.of(0,0), LocalTime.of(1,30));

    public static final String MESSAGE_CONSTRAINT =
            "Time should be of the format HHmm";
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    private LocalTime startTime;
    private LocalTime endTime;

    BookingSlot(LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(startTime, endTime);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isValidTime(String time) {
        requireNonNull(time);

        try {
            LocalTime.parse(time, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public BookingSlot getNearestSlotFromTimeString(String bookingTimeString) {
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
