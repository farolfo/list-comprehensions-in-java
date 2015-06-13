import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SetBuilder {

    public static <T> Set<T> where(Consumer<Variable> predicate){
        Variable x = new Variable<T>();
        predicate.accept(x);
        return x.value();
    }

    public static class Variable<T> {
        private Set<T> belongsTo = new HashSet<>();
        private Set<Predicate<T>> conditions = new HashSet<>();

        public Variable belongsTo(Set<T> c){
            this.belongsTo.addAll(c);
            return this;
        }

        public Variable is(Predicate<T> p) {
            this.conditions.add(p);
            return this;
        }

        public Set<T> value() {
            return intersect(conditions.stream().map(condition ->
                            belongsTo.stream().filter(condition).collect(Collectors.toSet())
            ).collect(Collectors.toSet()));
        }

        private Set<T> intersect(Set<Set<T>> sets) {
            Map<T,Integer> elemCounts = new HashMap<>();
            for (Set<T> set : sets) {
                for (T elem : set) {
                    elemCounts.put(elem, elemCounts.get(elem) == null ? 1 : elemCounts.get(elem) + 1);
                }
            }

            return elemCounts.entrySet().stream()
                    .filter(entry ->
                            entry.getValue() == sets.size())
                    .map(entry ->
                            entry.getKey())
                    .collect(Collectors.toSet());
        };

    }
}
