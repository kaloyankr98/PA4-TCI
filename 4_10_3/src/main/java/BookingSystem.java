import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingSystem implements BookingSystemInterface{
    private List<LocalTime> bookedHours;

    public BookingSystem(){
        this.bookedHours = new ArrayList<>();
    }

    public List<LocalTime> getBookedHours() {
        return bookedHours;
    }


    @Override
    public void addBooking(LocalTime startBookingTime, int numberOfHoursToBook) {
        if(bookedHours.contains(startBookingTime)){
            throw new IllegalArgumentException();
        }
        else if(startBookingTime.getMinute() != 0){
            throw new IllegalArgumentException();
        }
        else {
            bookedHours.add(startBookingTime);
            int hourNrToAdd = 1;
            while (numberOfHoursToBook > 1) {
                numberOfHoursToBook--;
                LocalTime timeToAddToBooking = startBookingTime.plusHours(hourNrToAdd);
                if(bookedHours.contains(timeToAddToBooking)){
                    throw new IllegalArgumentException();
                }
                bookedHours.add(timeToAddToBooking);
                hourNrToAdd++;
            }
        }
    }
}
