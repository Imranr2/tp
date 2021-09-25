package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * Represents a Booking in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {
    private final Client client;
    private BookingDate bookingDate;
    private BookingSlot bookingSlot;

    /**
     * Constructs a booking.
     *
     * @param client Person to whom this booking belongs to.
     */
    public Booking(Client client) {
        requireAllNonNull(client);
        this.client = client;
    }

    /**
     * Constructs a booking.
     *
     * @param client Person to whom this booking belongs to.
     * @param bookingDate Date of the booking.
     * @param bookingSlot Slot of the booking with a start time and end time.
     */
    public Booking(Client client, BookingDate bookingDate, BookingSlot bookingSlot) {
        requireAllNonNull(client, bookingDate, bookingSlot);
        this.client = client;
        this.bookingDate = bookingDate;
        this.bookingSlot = bookingSlot;
    }

    private Client getClient() {
        return client;
    }

    public Phone getPhone() {
        return getClient().getPhone();
    }

    public BookingDate getBookingDate() {
        return this.bookingDate;
    }

    public BookingSlot getBookingSlot() {
        return this.bookingSlot;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return otherBooking.getClient().equals(getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(client);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Phone: ")
                .append(getPhone());

        return builder.toString();
    }
}
