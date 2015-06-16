# List Comprehension in Java
Build lists in mathematical [set-builder notation](https://en.wikipedia.org/wiki/Set-builder_notation) with Java, like { x * 2 | x E {1,2} ^ x is even }.

### Usage and motivation

In algebra we are able to define sets using set-builder notation, also known as set comprehension or list comprehension. For example, this computes the even numbers in a set of integers

```java
{ x | x E {1,2,3,4} ^ x is even }
// gives {2,4}
```

which is read as _give me the set of all x such that x belongs to {1,2,3,4} and x is even_.

But this notation jumped out of algebra and today we have ways to do this in [many](https://en.wikipedia.org/wiki/Comparison_of_programming_languages_(list_comprehension)) programming languages, such as Haskell or python.

Now you can achieve this in Java

```java
Predicate<Integer> even = x -> x % 2 == 0;

List<Integer> evens = new ListComprehension<Integer>()
        .suchThat(x -> {
            x.belongsTo(Arrays.asList(1, 2, 3, 4));
            x.is(even);
        });
// evens = {2,4};
```

And if we want to transform the output expression in some way like

```java
{ x * 2 | x E {1,2,3,4} ^ x is even }
// gives {2,4,6,8}
```

Our java code would look like

```java
List<Integer> duplicated = new ListComprehension<Integer>()
        .giveMeAll((Integer x) -> x * 2)
        .suchThat(x -> {
            x.belongsTo(Arrays.asList(1, 2, 3, 4));
            x.is(even);
        });
// duplicated = {2,4,6,8}
```

### Further reading

* [Set-builder notation.](https://en.wikipedia.org/wiki/Set-builder_notation)
* [How are programming languages supporting this today?](https://en.wikipedia.org/wiki/Comparison_of_programming_languages_(list_comprehension))
* [Haskell's list comprehension](http://learnyouahaskell.com/starting-out#im-a-list-comprehension)

### License

MIT
