# Set Builder
Build sets in mathematical [set-builder notation](https://en.wikipedia.org/wiki/Set-builder_notation) with Java.

### Usage

In algebra we are able to define sets using set-builder notation, also known as set comprehension. For example, this computes the even numbers in a set of integers

```java
{ x | x E {1,2,3,4} ^ x is even }
// gives {2,4}
```

And this is how you achieve this in Java with SetBuilder

```java
Predicate<Integer> even = x -> x % 2 == 0;

Set<Integer> someIntegers = new HashSet(Arrays.asList(1, 2, 3, 4));

Set<Integer> evens = SetBuilder.where((x) -> {
    x.belongsTo(someIntegers);
    x.is(even);
});
// evens -> [2, 4];
```
