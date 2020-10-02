import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BookingSystemTest {
    private BookingSystem bookingSystem = new BookingSystem();

    @Test
    public void mustReturnNoBookedHoursIfNonBooked(){
        assertThat(bookingSystem.getBookedHours(), is(empty()));
    }

    @Test
    @Parameters(method = "acceptedBookedHours")
    public void mustReturnBookedHours(List<LocalTime> expectedBookedHours, LocalTime startBookingHour, int numberOfHours){
        bookingSystem.addBooking(startBookingHour, numberOfHours);
        //assertThat(expectedBookedHours, is(equals(bookingSystem.getBookedHours())));
        assertThat("reason", expectedBookedHours, is(equalTo(bookingSystem.getBookedHours())));
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "doubleBookedHours")
    public void mustNotDoDoubleBooking(LocalTime firstBookingHour, int firstNrOfBookedHours,
                                       LocalTime secondBookerHour, int secondNrOfBookedHours){
        bookingSystem.addBooking(firstBookingHour,firstNrOfBookedHours);
        bookingSystem.addBooking(secondBookerHour,secondNrOfBookedHours);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldOnlyPermitBookingOfRegularWholeHours(){
        bookingSystem.addBooking(LocalTime.of(14,30), 2);
    }

    //parameters for parametrized testing
    private static Object[] acceptedBookedHours(){
        return new Object[] {
                new Object[] {
                        Arrays.asList(
                                LocalTime.of(11, 0)),
                        LocalTime.of(11, 0), 1},
                new Object[] {
                        Arrays.asList(
                                LocalTime.of(12, 0),
                                LocalTime.of(13, 0)),
                        LocalTime.of(12, 0), 2},
                new Object[] {
                        Arrays.asList(
                                LocalTime.of(18, 0),
                                LocalTime.of(19, 0),
                                LocalTime.of(20, 0),
                                LocalTime.of(21, 0),
                                LocalTime.of(22, 0),
                                LocalTime.of(23, 0)),
                        LocalTime.of(18, 0), 6},
        };
    }

    private static Object[] doubleBookedHours(){
        return new Object[]{
                new Object[] {
                        LocalTime.of(11, 0), 1,
                        LocalTime.of(11, 0), 1
                },
                new Object[] {
                        LocalTime.of(14, 0), 2,
                        LocalTime.of(15, 0), 2
                },
                new Object[] {
                        LocalTime.of(15, 0), 2,
                        LocalTime.of(14, 0), 2
                }
        };
    }

}
