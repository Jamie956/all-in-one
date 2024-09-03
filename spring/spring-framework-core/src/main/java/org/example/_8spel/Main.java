package org.example._8spel;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.GregorianCalendar;

public class Main {

    @Test
    public void test1() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        System.out.println(message);
    }

    //Parse String method concat
    @Test
    public void test2() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println(message);
    }

    //Parse string bytes
    @Test
    public void test3() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        System.out.println(bytes);
    }

    @Test
    public void test4() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        int length = (Integer) exp.getValue();
        System.out.println(length);
    }

    //Parse String method toUpperCase
    @Test
    public void test5() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }

    @Test
    public void test6() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();

        // Parse name as an expression
        Expression exp = parser.parseExpression("name");
        // Get expression value from object tesla
        String name = (String) exp.getValue(tesla);
        System.out.println(name);
        // name == "Nikola Tesla"

        // Unknown name this moment
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        // Get expression value from object tesla
        boolean result = exp.getValue(tesla, Boolean.class);
        System.out.println(result);
        // result == true
    }

    @Test
    public void test7() {
        Simple simple = new Simple();
        simple.booleanList.add(true);

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        ExpressionParser parser = new SpelExpressionParser();
        // "false" is passed in here as a String. SpEL and the conversion service
        // will recognize that it needs to be a Boolean and convert it accordingly.
        parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

        // b is false
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);
    }

    @Test
    public void test8() {
        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);

        ExpressionParser parser = new SpelExpressionParser(config);

        // 自动创建数组和元素
        Expression expression = parser.parseExpression("list[3]");

        Demo demo = new Demo();

        Object o = expression.getValue(demo);

        // demo.list will now be a real collection of 4 entries
        // Each entry is a new empty String
        System.out.println(demo.list.size());
    }
}
