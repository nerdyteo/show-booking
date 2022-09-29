import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.show.ParsedTicketNumber;
import com.nerdyteo.show_booking.show.Seat;
import com.nerdyteo.show_booking.show.ShowInformation;
import com.nerdyteo.show_booking.util.TicketUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShowComponentsUnitTest {

    @Test
    void setUpShowClass() {
        final int numberOfRows = 20;
        final int numberOfSeats = 9;
        final int alphabetA = 'A';
        final ShowInformation showInformation = new ShowInformation(1, numberOfRows, numberOfSeats, 1);
        final boolean result = IntStream.range(0, numberOfRows)
                .map(value -> (char) (alphabetA + value))
                .mapToObj(row -> {
                    final StringBuilder builder = new StringBuilder();
                    builder.append((char) row);
                    return IntStream.rangeClosed(1, numberOfSeats)
                            .mapToObj(seat -> {
                                builder.append(seat);
                                final String seatNumber = builder.toString();
                                builder.delete(1, builder.length());
                                return seatNumber;
                            })
                            .collect(Collectors.toList());
                })
                .reduce(new ArrayList<>(), (identity, given) -> {
                    identity.addAll(given);
                    return identity;
                })
                .stream()
                .allMatch(showInformation::hasSeat);

        Assertions.assertTrue(result, "Show seats are not generated properly");
    }

    @Test
    void bookingPositiveTest() {
        final long showNumber = 1211;
        final int numberOfRows = 4;
        final int numberOfSeats = 4;
        final Cinema cinema = Cinema.getInstance();
        cinema.setup(showNumber, numberOfRows, numberOfSeats, 2);
        final List<String> seatsToBook = Arrays.asList("A1", "B4", "C2", "D3");
        cinema.book(showNumber, "9999", seatsToBook);
        final boolean result = cinema.available(showNumber)
                .stream()
                .map(Seat::getSeatNumber)
                .noneMatch(seatsToBook::contains);
        Assertions.assertTrue(result, "Not all seats have been booked");
    }

    @Test
    void bookingNonValidSeats() {
        final long showNumber = 1211;
        final int numberOfRows = 4;
        final int numberOfSeats = 4;
        final Cinema cinema = Cinema.getInstance();
        cinema.setup(showNumber, numberOfRows, numberOfSeats, 2);
        final List<String> seatsToBook = Arrays.asList("Z1", "Y4", "X2", "W3");
        final String ticketNumber = cinema.book(showNumber, "999", seatsToBook);
        Assertions.assertNull(ticketNumber, "Ticket number is given despite invalid seats are chosen");
    }

    @Test
    void bookingUnavailableSeats() {
        final long showNumber = 1211;
        final int numberOfRows = 4;
        final int numberOfSeats = 4;
        final Cinema cinema = Cinema.getInstance();
        cinema.setup(showNumber, numberOfRows, numberOfSeats, 2);
        final List<String> seatsToBook = Arrays.asList("A1", "B4", "C2", "D3");
        cinema.book(showNumber, "9999", seatsToBook);
        final String ticketNumber = cinema.book(showNumber, "9999", seatsToBook);
        Assertions.assertNull(ticketNumber, "Ticket number is given despite unavailable seats are chosen");
    }

    @Test
    void cancelTicket() {
        final Long showNumber = 1211L;
        final int numberOfRows = 4;
        final int numberOfSeats = 4;
        final String phone = "999";
        final Cinema cinema = Cinema.getInstance();
        cinema.setup(showNumber, numberOfRows, numberOfSeats, 2);
        final List<String> seatsToBook = Arrays.asList("A1", "B4", "C2", "D3");
        final String ticketNumber = cinema.book(showNumber, phone, seatsToBook);
        cinema.cancel(ticketNumber, String.valueOf(showNumber.hashCode()));
        final List<String> availableSeats = cinema.available(showNumber)
                .stream()
                .map(Seat::getSeatNumber)
                .collect(Collectors.toList());
        final boolean result = seatsToBook.stream()
                .allMatch(availableSeats::contains);
        Assertions.assertTrue(result, "Seats from ticket are not available after cancellation");
    }

    @Test
    void cancellationWindowFunctioning() throws InterruptedException {
        final Long showNumber = 1211L;
        final int numberOfRows = 4;
        final int numberOfSeats = 4;
        final int cancellationWindowMinutes = 1;
        final long cancellationWindowMillis = TimeUnit.MINUTES.toMillis(cancellationWindowMinutes + 1);

        final List<String> seatsToBook = Arrays.asList("A1", "B4", "C2", "D3");

        final Cinema cinema = Cinema.getInstance();
        cinema.setup(showNumber, numberOfRows, numberOfSeats, cancellationWindowMinutes);
        final String ticketNumber = cinema.book(showNumber, "9999", seatsToBook);
        Thread.sleep(cancellationWindowMillis);
        cinema.cancel(ticketNumber, String.valueOf(showNumber.hashCode()));
        final List<String> availableSeats = cinema.available(showNumber)
                .stream()
                .map(Seat::getSeatNumber)
                .collect(Collectors.toList());
        final boolean result = seatsToBook.stream()
                .noneMatch(availableSeats::contains);
        Assertions.assertTrue(result, "Seats from ticket are available when ticket is cancelled after the cancellation window");
    }

}
