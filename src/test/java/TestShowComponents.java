import com.nerdyteo.show_booking.show.Seat;
import com.nerdyteo.show_booking.show.ShowInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestShowComponents {

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
}
