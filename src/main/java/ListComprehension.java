import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ListComprehension<T> {

    private Function<T,T> outputExpression = x -> x;

    ListComprehension() {
    }

    public void setOutputExpression(Function<T, T> outputExpression) {
        this.outputExpression = outputExpression;
    }

    public List<T> suchThat(Consumer<Var> predicates){
        Var x = new Var<T>();
        predicates.accept(x);
        return (List<T>) x.value().stream().map(outputExpression).collect(Collectors.toList());
    }

    public ListComprehension<T> giveMeAll(Function<T,T> resultTransformer) {
        this.setOutputExpression(resultTransformer);
        return this;
    }

    public class Var<T> {
        private Set<T> belongsTo = new HashSet<>();
        private Set<Predicate<T>> conditions = new HashSet<>();

        Var() {
            this.conditions.add(x -> true);
        }

        public Var belongsTo(List<T> c) {
            this.belongsTo.addAll(c);
            return this;
        }

        public Var is(Predicate<T> p) {
            this.conditions.add(p);
            return this;
        }

        public Var holds(Predicate<T> condition) {
            return is(condition);
        }

        public List<T> value() {
            return intersect(conditions.stream().map(
                    condition -> belongsTo.stream().filter(condition)
                            .collect(Collectors.toList())).collect(Collectors.toList()));
        }

        private List<T> intersect(List<List<T>> lists) {
            return belongsTo.stream().filter(
                    x -> lists.stream().filter(
                            list -> list.contains(x)).count() == lists.size())
                    .collect(Collectors.toList());
        }
    }
}
