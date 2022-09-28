import com.nerdyteo.show_booking.util.ConversionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class ConversionUnitTest {
    @Test
    void toInt() {
        final int value = 123;
        final String stringValue = Integer.toString(value);
        final int converted = ConversionUtil.toInt(stringValue);
        Assertions.assertEquals(value, converted, "Conversion from String to INT is invalid");
    }

    @Test
    void toLong() {
        final long value = 1234L;
        final String stringValue = Long.toString(value);
        final long converted = ConversionUtil.toLong(stringValue);
        Assertions.assertEquals(value, converted, "Conversion from String to LONG is invalid");
    }


    @Test
    void toList() {
        final String delimiter = ",";
        final List<String> value = Arrays.asList("1", "2", "3", "4");
        final StringJoiner joiner = new StringJoiner(delimiter);
        value.forEach(joiner::add);
        final List<String> converted = ConversionUtil.toList(joiner.toString(), delimiter, string -> string);
        final boolean result = value.stream().allMatch(converted::contains);
        Assertions.assertEquals(true, result, "Conversion from String to LIST is invalid");
    }

}