package Models;


import Utils.CoolLinkedList;

import java.time.LocalDate;

public class AuctionLot {
    private String title,description,type,imageURL,timeSold;
    private int year,askingPrice,finalPrice;
    private LocalDate dateSold;
    private boolean sold;


    public AuctionLot(String title, String description, String type, String imageURL, int year, int askingPrice) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.imageURL = imageURL;
        this.year = year;
        this.askingPrice = askingPrice;
        this.sold = false;
    }
    public void sell(int finalPrice,LocalDate dateSold,String timeSold){
        this.sold = true;
        this.finalPrice = finalPrice;
        this.dateSold = dateSold;
        this.timeSold = timeSold;
    }

    public boolean getSold(){return sold;}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTimeSold() {
        return timeSold;
    }

    public void setTimeSold(String timeSold) {
        this.timeSold = timeSold;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(int askingPrice) {
        this.askingPrice = askingPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }



    @Override
    public String toString() {
        return sold ?
                "Title: " + title +
                " Description: " + description +
                " Type: " + type +
                " ImageURL: " + imageURL +
                " Year: " + year +
                " Asking Price: " + askingPrice +
                " Final Price: " + finalPrice +
                " Date Sold: " + dateSold +
                " Time Sold: " + timeSold
                :
                "Title: " + title +
                " Description: " + description +
                " Type: " + type +
                " ImageURL: " + imageURL +
                " Year: " + year +
                " Asking Price: " + askingPrice;

    }
}
