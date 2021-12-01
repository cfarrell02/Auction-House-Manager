package Models;

import java.time.LocalDate;

public class Bid {
    private String time;
    private LocalDate date;
    private int amount;

    public Bid(String time, LocalDate date, int amount) {
        this.time = time;
        this.date = date;
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
