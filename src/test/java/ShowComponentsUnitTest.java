import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.show.ShowInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                .allMatch(seat -> showInformation.hasSeat(seat));

        Assertions.assertEquals(true, result, "Show seats are not generated properly");
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
        final boolean result = cinema.available()
                .stream()
                .anyMatch(seatsToBook::contains);
        Assertions.assertEquals(false, result, "Not all seats have been booked");
    }

    @Test
    void bookingNonValidSeats(){
        final long showNumber = 1211;
        final int numberOfRows = 4;
        final int numberOfSeats = 4;
        final Cinema cinema = Cinema.getInstance();
        cinema.setup(showNumber, numberOfRows, numberOfSeats, 2);
        final List<String> seatsToBook = Arrays.asList("Z1", "Y4", "X2", "W3");
        final String ticketNumber = cinema.book(showNumber, "999", seatsToBook);
        Assertions.assertEquals(null, ticketNumber, "Ticket number is given despite invalid seats are chosen");
    }
}
