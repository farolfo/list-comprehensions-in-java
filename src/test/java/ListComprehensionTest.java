import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@SuppressWarnings("unchecked")
public class ListComprehensionTest {

    /**
     * Test for { x | x E {1,2,3,4} ^ x is even }
     */
    @Test
    public void filterTest() {
        List<Integer> someIntegers = Arrays.asList(1, 2, 3, 4);
        List<Integer> actualEvens = Arrays.asList(2, 4);

        List<Integer> evens = filter(someIntegers, (Integer x) -> x % 2 == 0);

        assertThat(evens, is(actualEvens));
    }

    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        return new ListComprehension<T>()
                .suchThat(s -> {
                    s.belongsTo(list);
                    s.holds(p);
                });
    }

    /**
     * Test for { x * 2 | x E {1,2,3,4} }
     */
    @Test
    public void mapTest() {
        List<Integer> someIntegers = Arrays.asList(1, 2, 3, 4);
        List<Integer> actualDuplicated = Arrays.asList(2, 4, 6, 8);

        List<Integer> duplicated = map(someIntegers, (Integer x) -> x * 2);

        assertThat(duplicated, is(actualDuplicated));
    }

    public <T> List<T> map(List<T> list, Function<T,T> f) {
        return new ListComprehension<T>()
                .giveMeAll(t -> f.apply(t))
                .suchThat(s -> {
                    s.belongsTo(list);
                });
    }
}
