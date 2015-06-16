import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@SuppressWarnings("unchecked")
public class ListComprehensionTest {

    /**
     *
     */
    @Test
    public void evenTest() {
        Predicate<Integer> even = x -> x % 2 == 0;

        List<Integer> someIntegers = Arrays.asList(1, 2, 3, 4);
        List<Integer> actualEvens = Arrays.asList(2,4);

        List<Integer> evens = new ListComprehension<Integer>()
                .suchThat(x -> {
                    x.belongsTo(someIntegers);
                    x.is(even);
                });

        assertThat(evens, is(actualEvens));
    }

    @Test
    public void mapTest() {
        List<Integer> someIntegers = Arrays.asList(1, 2, 3, 4);
        List<Integer> actualDuplicated = Arrays.asList(2, 4, 6, 8);

        List<Integer> duplicated = new ListComprehension<Integer>()
                .giveMeAll((Integer x) -> x * 2)
                .suchThat(x -> {
                    x.belongsTo(someIntegers);
                });

        assertThat(duplicated, is(actualDuplicated));
    }

    @Test
    @Ignore
    public void forAllTest() {
        assert false;
    }

    @Test
    @Ignore
    public void existsTest() {
        assert false;
    }

}
