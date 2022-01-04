package Models;

import java.time.LocalDate;

public class Bid {
    private String time;
    private LocalDate date;
    private int amount;
    private AuctionLot lot;
    private Boolean won;

    public Bid(String time, LocalDate date, int amount,AuctionLot auctionLot) {
        this.time = time;
        this.date = date;
        this.amount = amount;
        this.lot=auctionLot;
        this.won = false;
    }

    public Bid(int amount) {
        this.amount=amount;
    }

    public void won(){
        won=true;
    }

    public AuctionLot getLot() {
        return lot;
    }

    public void setLot(AuctionLot lot) {
        this.lot = lot;
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



    public String toString(){
        return won ? "(Won) "+"Time: "+time+
                "Date: "+date+
                "Amount: "+amount+
                "Lot: "+lot.getTitle()
                :
                "Time: "+time+
                        "Date: "+date+
                        "Amount: "+amount+
                        "Lot: "+lot.getTitle()
                ;
    }


}
