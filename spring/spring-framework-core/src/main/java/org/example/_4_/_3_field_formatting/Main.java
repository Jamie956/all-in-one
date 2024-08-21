package org.example._4_._3_field_formatting;

import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.number.PercentStyleFormatter;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Main {
    @Test
    public void dateFormatWithPattern() throws ParseException {
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM");
        System.out.println(dateFormatter.print(new Date(), Locale.CHINA));
        System.out.println(dateFormatter.parse("1993-09", Locale.CHINA));
    }

    @Test
    public void dateFormat() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DateFormatter dateFormatter = new DateFormatter();
        System.out.println(dateFormatter.print(date, Locale.CHINA));
        System.out.println(dateFormatter.print(timestamp, Locale.CHINA));

        dateFormatter.setIso(DateTimeFormat.ISO.DATE_TIME);
        System.out.println(dateFormatter.print(date, Locale.CHINA));
        System.out.println(dateFormatter.print(timestamp, Locale.CHINA));
    }

    @Test
    public void ccyFormat() throws ParseException {
        String curr = "1,234.56";
        CurrencyStyleFormatter formatter = new CurrencyStyleFormatter();
        formatter.setRoundingMode(RoundingMode.DOWN);
        formatter.setPattern("#,#00.0#");
        System.out.println(formatter.parse(curr, Locale.CHINA));
    }

    @Test
    public void percentFormat() throws ParseException {
        String curr = "12%";

        PercentStyleFormatter formatter = new PercentStyleFormatter();
        System.out.println(formatter.parse(curr, Locale.CHINA));
        System.out.println(formatter.print(0.12, Locale.CHINA));
    }

    @Test
    public void numFormat() throws ParseException {
        String curr = "12,000.1567";

        NumberStyleFormatter formatter = new NumberStyleFormatter();
        formatter.setPattern("#,#00.0#");
        System.out.println(formatter.parse(curr, Locale.CHINA));
        System.out.println(formatter.print(0.12, Locale.CHINA));
    }

}
