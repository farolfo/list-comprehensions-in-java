import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ComprehensiveSetTest {

    @Test
    public void evenTest() {
        Predicate<Integer> even = x -> x % 2 == 0;

        Set<Integer> someIntegers = new HashSet(Arrays.asList(1, 2, 3, 4));
        Set<Integer> actualEvens = new HashSet(Arrays.asList(2,4));

        Set<Integer> evens = ComprehensiveSet.suchThat((x) -> {
            x.belongsTo(someIntegers);
            x.is(even);
        });

        assertThat(evens, is(actualEvens));
    }
}
