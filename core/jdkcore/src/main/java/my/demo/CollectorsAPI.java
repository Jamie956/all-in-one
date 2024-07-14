package my.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsAPI {

    @Test
    public void toList() {
        Stream<Integer> data = Stream.of(1, 4, 6, 8);
        Collector<Integer, ?, List<Integer>> c = Collectors.toList();
        List<Integer> result = data.collect(c);

        Assert.assertEquals(Integer.valueOf(1), result.get(0));
        Assert.assertEquals(Integer.valueOf(4), result.get(1));
        Assert.assertEquals(Integer.valueOf(6), result.get(2));
        Assert.assertEquals(Integer.valueOf(8), result.get(3));
    }

    @Test
    public void toMap() {
        Stream<Integer> data = Stream.of(1, 4, 6, 8);
        Collector<Integer, ?, Map<Integer, Integer>> c = Collectors.toMap(i -> i, i -> i * 2);
        Map<Integer, Integer> result = data.collect(c);

        Assert.assertEquals(Integer.valueOf(2), result.get(1));
        Assert.assertEquals(Integer.valueOf(8), result.get(4));
        Assert.assertEquals(Integer.valueOf(12), result.get(6));
        Assert.assertEquals(Integer.valueOf(16), result.get(8));
    }

    @Test
    public void groupingBy() {
        Stream<Integer> data = Stream.of(1, 4, 6, 8);
        Collector<Integer, ?, Map<String, List<Integer>>> c = Collectors.groupingBy(i -> i > 5 ? "大于5" : "小于5");
        Map<String, List<Integer>> result = data.collect(c);

        Assert.assertEquals(Integer.valueOf(1), result.get("小于5").get(0));
        Assert.assertEquals(Integer.valueOf(4), result.get("小于5").get(1));
        Assert.assertEquals(Integer.valueOf(6), result.get("大于5").get(0));
        Assert.assertEquals(Integer.valueOf(8), result.get("大于5").get(1));
    }

    @Test
    public void joining() {
        Stream<String> data = Stream.of("a", "b", "c");
        Collector<CharSequence, ?, String> j = Collectors.joining();
        String result = data.collect(j);
        Assert.assertEquals("abc", result);
    }

    @Test
    public void joining2() {
        Stream<String> data = Stream.of("a", "b", "c");
        Collector<CharSequence, ?, String> j = Collectors.joining(",");
        String result = data.collect(j);
        Assert.assertEquals("a,b,c", result);
    }

    @Test
    public void joining3() {
        Stream<String> data = Stream.of("a", "b", "c");
        Collector<CharSequence, ?, String> j = Collectors.joining(",", "{", "}");
        String result = data.collect(j);
        Assert.assertEquals("{a,b,c}", result);
    }
}