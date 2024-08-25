package demo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Address {
    //todo fail convert request date string
//    @DateTimeFormat(pattern="yyyyMMdd")
//    @DateTimeFormat
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
