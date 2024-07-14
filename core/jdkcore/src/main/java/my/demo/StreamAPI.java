package my.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamAPI {

    @Test
    public void filter() {
        Stream<Integer> s = Stream.of(1, 5, 6, 7);
        Stream<Integer> s1 = s.filter(i -> i > 5);
        Object[] arr = s1.toArray();

        Assert.assertSame(6, arr[0]);
        Assert.assertSame(7, arr[1]);
    }

    @Test
    public void map() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Stream<Integer> s1 = s.map(i -> i + 1);
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{2,2,7,8}, arr);
    }

    @Test
    public void mapToInt() {
        Stream<String> s = Stream.of("1", "1", "6", "7");
        IntStream s1 = s.mapToInt(i -> Integer.valueOf(i));
        int[] arr = s1.toArray();

        Assert.assertArrayEquals(new int[]{1,1,6,7}, arr);
    }

    @Test
    public void mapToLong() {
        Stream<String> s = Stream.of("1", "1", "6", "7");
        LongStream s1 = s.mapToLong(i -> Long.valueOf(i));
        long[] arr = s1.toArray();

        Assert.assertArrayEquals(new long[]{1,1,6,7}, arr);
    }

    @Test
    public void flatMap() {
        Stream<String> s = Stream.of("6", "7");

        Function<String, Stream<Integer>> f = e -> {
            int i = Integer.parseInt(e);
            return Stream.of(i, i * 2);
        };

        Stream<Integer> s1 = s.flatMap(f);
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{6,12,7,14}, arr);
    }

    @Test
    public void flatMapToInt() {
        Stream<String> s = Stream.of("6", "7");

        Function<String, IntStream> f = e -> {
            int i = Integer.parseInt(e);
            return IntStream.of(i, i * 2);
        };

        IntStream s1 = s.flatMapToInt(f);
        int[] arr = s1.toArray();

        Assert.assertArrayEquals(new int[]{6,12,7,14}, arr);
    }

    @Test
    public void distinct() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Stream<Integer> s1 = s.distinct();
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{1,6,7}, arr);
    }

    @Test
    public void sorted() {
        Stream<Integer> s = Stream.of(5, 9, 6, 7, 1);
        Stream<Integer> s1 = s.sorted();
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{1,5,6,7,9}, arr);
    }

    @Test
    public void sorted2() {
        Stream<Integer> s = Stream.of(5, 9, 6, 7, 1);
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        Stream<Integer> s1 = s.sorted(c);
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{1,5,6,7,9}, arr);
    }

    @Test
    public void peek() {
        Stream<Integer> s = Stream.of(5, 9, 6, 7, 1);
        Consumer<Integer> c = e -> System.out.println(e);
        Stream<Integer> s1 = s.peek(c);
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{5,9,6,7,1}, arr);
    }

    @Test
    public void limit() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Stream<Integer> s1 = s.limit(2);
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{1,1}, arr);
    }

    @Test
    public void skip() {
        Stream<Integer> s = Stream.of(6, 1, 7, 9, 3);
        Stream<Integer> s1 = s.skip(2);
        Object[] arr = s1.toArray();

        Assert.assertArrayEquals(new Integer[]{7,9,3}, arr);
    }

    @Test
    public void forEach() {
        Stream<Integer> s = Stream.of(6, 1, 7, 9, 3);
        Consumer<Integer> c = e -> System.out.println(e);
        s.forEach(c);
    }

    @Test
    public void forEachOrdered() {
        Stream<Integer> s = Stream.of(6, 1, 7, 9, 3);
        Consumer<Integer> c = e -> System.out.println(e);
        s.forEachOrdered(c);
    }

    @Test
    public void toArray() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Object[] arr = s.toArray();

        Assert.assertArrayEquals(new Integer[]{1,1,6,7}, arr);
    }

    @Test
    public void reduce() {
        Stream<Integer> s = Stream.of(8, 2);
        BinaryOperator<Integer> f = (a, b) -> a + b;
        Integer r = s.reduce(100, f);
        //100+8+2
        Assert.assertSame(110, r);
    }

    @Test
    public void reduce2() {
        Stream<Integer> s = Stream.of(8, 2);
        BinaryOperator<Integer> f = (a, b) -> a + b;
        Optional<Integer> r = s.reduce(f);
        Assert.assertSame(10, r.get());
    }

    @Test
    public void collect2() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        List<Integer> c = s.collect(Collectors.toList());

        Assert.assertSame(8, c.get(0));
        Assert.assertSame(2, c.get(1));
        Assert.assertSame(5, c.get(2));
    }

    @Test
    public void min() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        Comparator<Integer> c = Comparator.comparingInt(e -> e);
        Optional<Integer> o = s.min(c);
        Assert.assertSame(2, o.get());
    }

    @Test
    public void max() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        Comparator<Integer> c = Comparator.comparingInt(e -> e);
        Optional<Integer> o = s.max(c);
        Assert.assertSame(8, o.get());
    }

    @Test
    public void count() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        long c = s.count();
        Assert.assertSame(3L, c);
    }

    @Test
    public void anyMatch() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Predicate<Integer> f = e -> e > 5;
        boolean b = s.anyMatch(f);

        Assert.assertTrue(b);
    }

    @Test
    public void allMatch() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Predicate<Integer> f = e -> e > 5;
        boolean b = s.allMatch(f);

        Assert.assertFalse(b);
    }

    @Test
    public void noneMatch() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Predicate<Integer> f = e -> e > 5;
        boolean b = s.noneMatch(f);

        Assert.assertFalse(b);
    }

    @Test
    public void findFirst() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Optional<Integer> o = s.findFirst();

        Assert.assertSame(1, o.get());
    }

    @Test
    public void findAny() {
        Stream<Integer> s = Stream.of(6, 7);
        Optional<Integer> o = s.findAny();

        Assert.assertSame(6, o.get());
    }

    @Test
    public void iterate() {
        Stream<Integer> s = Stream.iterate(1, n -> n * 2).limit(5);
        Object[] arr = s.toArray();

        Assert.assertArrayEquals(new Integer[]{1,2,4,8,16}, arr);
    }


    @Test
    public void concat() {
        Stream<Integer> s = Stream.of(6, 7);
        Stream<Integer> s2 = Stream.of(3, 0);
        Stream<Integer> s3 = Stream.concat(s, s2);
        Object[] arr = s3.toArray();

        Assert.assertArrayEquals(new Integer[]{6,7,3,0}, arr);
    }
}