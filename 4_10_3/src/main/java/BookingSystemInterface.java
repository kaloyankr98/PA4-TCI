import java.time.LocalTime;

public interface BookingSystemInterface {
    void addBooking(LocalTime startBookingTime, int numberOfHoursToBook);
}
