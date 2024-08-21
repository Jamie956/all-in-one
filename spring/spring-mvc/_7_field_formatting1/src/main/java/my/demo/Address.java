package my.demo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

public class Address {
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    @NumberFormat(pattern="#,###,###.#")
    private Float ind;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getInd() {
        return ind;
    }

    public void setInd(Float ind) {
        this.ind = ind;
    }
}
