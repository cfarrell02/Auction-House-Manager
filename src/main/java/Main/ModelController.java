package Main;


import Models.AuctionLot;
import Models.Bid;
import Models.Bidder;
import Utils.AlertBox;
import Utils.CoolHashTable;
import Utils.CoolLinkedList;
import Utils.Utilities;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Locale;
import java.util.TimeZone;

public class ModelController {
    @FXML
    private Text modelTitle, finalBid;
    @FXML
    private ListView<String> mainList, bidList;

    @FXML
    private TextField biddName, biddTelephone, biddEmail, aucTitle, aucYear,
            aucAskingPrice, aucURL, bidAmount, bidTime, timeSold, searchBar;
    @FXML
    private DatePicker bidDate, dateSold;

    @FXML
    private ComboBox<String> bidLotChoice, aucType;
    @FXML
    private TextArea biddAddress, aucDesc;
    @FXML
    private AnchorPane aucAdd, bidAdd, biddAdd, completeAuc;
    @FXML
    private Button sellLot;

    private Bidder currentBidder;
    private AuctionLot currentLot;
    private Bid currentBid;

    //General Methods
    public void initialize() {
        aucType.getItems().addAll(
                "Baby",
                "Beauty",
                "Books",
                "Car & Motorbike",
                "CDs & Vinyl",
                "Clothing",
                "Computers & Accessories",
                "Digital Music",
                "DIY & Tools",
                "DVD & Blu-ray",
                "Electronics & Photo",
                "Fashion",
                "Garden & Outdoors",
                "Handmade",
                "Health & Personal Care",
                "Home & Business Services",
                "Home & Kitchen",
                "Industrial & Scientific",
                "Jewellery",
                "Large Appliances",
                "Lighting",
                "Luggage",
                "Musical Instruments & DJ Equipment",
                "PC & Video Games",
                "Pet Supplies",
                "Property",
                "Shoes & Bags",
                "Software",
                "Sports & Outdoors",
                "Stationery & Office Supplies",
                "Toys & Games",
                "Watches");
        modelTitle.setText(MainController.getCurrentTitle());
        bidLotChoice.valueProperty().addListener((ov, oldValue, newValue) -> populateAmount());
        if (MainController.getCurrentTitle().equals("Bidders")) {
            biddAdd.setVisible(true);
            for (Bidder bidder : AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList())
                mainList.getItems().add(bidder.toString());
        } else {
            aucAdd.setVisible(true);
            for (AuctionLot auctionLot : AuctionApplication.getAuctionAPI().getAuctionLots().toCoolLinkedList())
                mainList.getItems().add(auctionLot.toString());
        }


    }

    public void back() {
        if (biddAdd.isVisible() || aucAdd.isVisible()) {
            Stage stage = AuctionApplication.mainWindow;
            FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("main-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Main");
            stage.setScene(scene);
            stage.show();
        } else if (bidAdd.isVisible()) {
            mainList.getItems().clear();
            bidAdd.setVisible(false);
            biddAdd.setVisible(true);
            modelTitle.setText("Bidders");
            for (Bidder bidder : AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList())
                mainList.getItems().add(bidder.toString());
        } else {
            completeAuc.setVisible(false);
            aucAdd.setVisible(true);
            mainList.setDisable(false);
            modelTitle.setText("Auctions");
        }
    }

    public void home() {
        Stage stage = AuctionApplication.mainWindow;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("main-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }

    public void viewAttributes() {
        if (mainList.getSelectionModel().getSelectedIndex() >= 0) {
            if (biddAdd.isVisible()) {
                currentBidder = AuctionApplication.getAuctionAPI().getBidder(mainList.getSelectionModel().getSelectedIndex());
                biddName.setText(currentBidder.getName());
                biddAddress.setText(currentBidder.getAddress());
                biddTelephone.setText(currentBidder.getTelephone());
                biddEmail.setText(currentBidder.getTelephone());
            } else if (aucAdd.isVisible()) {
                bidList.getItems().clear();
                currentLot = AuctionApplication.getAuctionAPI().getAuctionLot(mainList.getSelectionModel().getSelectedIndex());
                aucType.setValue(currentLot.getType());
                aucAskingPrice.setText(String.valueOf(currentLot.getAskingPrice()));
                aucTitle.setText(currentLot.getTitle());
                aucDesc.setText(currentLot.getDescription());
                aucYear.setText(String.valueOf(currentLot.getYear()));
                aucURL.setText(currentLot.getImageURL());
                CoolLinkedList<Bid> toBeSorted = new CoolLinkedList<>();
                for (Bidder bidder : AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList()) {
                    for (Bid bid : bidder.getBids()) {
                        if (bid.getLot().equals(currentLot))
                            toBeSorted.add(bid);
                    }
                }
                toBeSorted.sort((a, b) -> b.getAmount() - a.getAmount());
                for (Bid bid : toBeSorted) {
                    bidList.getItems().add("Date: " + bid.getDate() + " Amount: " + bid.getAmount());
                }
                sellLot.setDisable(true);
                if (!currentLot.getSold()) sellLot.setDisable(false);

            } else if (bidAdd.isVisible()) {
                currentBid = currentBidder.getBid(mainList.getSelectionModel().getSelectedIndex());
                bidDate.setValue(currentBid.getDate());
                bidLotChoice.setValue(currentBid.getLot().getTitle());
                bidAmount.setText(String.valueOf(currentBid.getAmount()));
                bidTime.setText(String.valueOf(currentBid.getTime()));
            }
        }
    }

    public void delete(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            int index = mainList.getSelectionModel().getSelectedIndex();
            if (biddAdd.isVisible())
                AuctionApplication.getAuctionAPI().removeBidder(index);
            else if (aucAdd.isVisible())
                AuctionApplication.getAuctionAPI().removeAuctionLot(index);
            else if (bidAdd.isVisible()) {
                currentBidder.removeBid(index);
            }

            mainList.getItems().remove(index);


        }
    }


    /**
     * Bidder Methods
     **/

    public void addBidder() {
        try {
            if(AuctionApplication.getAuctionAPI().getBidders().get(biddName.getText())==null){
            Bidder newBidder = new Bidder(biddName.getText(), biddAddress.getText(), biddTelephone.getText(), biddEmail.getText());
            mainList.getItems().add(newBidder.toString());
            AuctionApplication.getAuctionAPI().addBidder(newBidder);

            biddName.clear();
            biddAddress.clear();
            biddTelephone.clear();
            biddEmail.clear();}else{
                AlertBox.display("Error","A bidder with this name \""+biddName+"\" already exists");
            }
        } catch (RuntimeException e) {
            AlertBox.display("Error!", "Enter in correct details \n" + e.getMessage());
        }
    }

    public void editBidder() {
        try {
            int index = mainList.getSelectionModel().getSelectedIndex();
            Bidder newBidder = new Bidder(biddName.getText(), biddAddress.getText(), biddTelephone.getText(), biddEmail.getText());
            AuctionApplication.getAuctionAPI().editBidder(index, newBidder);
            mainList.getItems().set(index, newBidder.toString());
        } catch (RuntimeException e) {
            AlertBox.display("Error!", "Enter in correct details \n" + e.getMessage());
        }
    }

    public void viewBids(MouseEvent mouseEvent) {
        if (biddAdd.isVisible()) {
            if (mouseEvent.getClickCount() >= 2) {
                int index = mainList.getSelectionModel().getSelectedIndex();
                if (index >= 0) {
                    currentBidder = AuctionApplication.getAuctionAPI().getBidder(index);
                    biddAdd.setVisible(false);
                    bidAdd.setVisible(true);
                    mainList.getItems().clear();
                    for (Bid bid : currentBidder.getBids())
                        mainList.getItems().add(bid.toString());
                    bidLotChoice.getItems().clear();
                    for (AuctionLot auctionLot : AuctionApplication.getAuctionAPI().getAuctionLots().toCoolLinkedList()) {
                        if (!auctionLot.getSold()) bidLotChoice.getItems().add(auctionLot.getTitle());
                    }
                    modelTitle.setText(currentBidder.getName() + "'s Bids");
                    AuctionApplication.mainWindow.setTitle(currentBidder.getName() + "'s Bids");
                }
            }
        }
    }

    /**
     * Auction Method
     **/

    public void addAuctionLot() {
        try {
            if (aucType != null) {
                AuctionLot newAuctionLot = new AuctionLot(aucTitle.getText(), aucDesc.getText(), aucType.getValue(), aucURL.getText(), Integer.parseInt(aucYear.getText()), Integer.parseInt(aucAskingPrice.getText()));
                if(AuctionApplication.getAuctionAPI().getAuctionLots().get(aucTitle.getText())==null) {
                    mainList.getItems().add(newAuctionLot.toString());
                    AuctionApplication.getAuctionAPI().addAuctionLot(newAuctionLot);
                    aucTitle.clear();
                    aucDesc.clear();
                    aucURL.clear();
                    aucYear.clear();
                    aucAskingPrice.clear();
                }else{
                    AlertBox.display("Error","A lot with this title \""+aucTitle.getText()+"\" already exists");
                }
            } else
                AlertBox.display("Error!", "Please select type");
        } catch (RuntimeException e) {
            AlertBox.display("Error!", "Enter in correct details \n" + e.getMessage());
        }
    }

    public void editAuctionLot() {
        try {
            int index = mainList.getSelectionModel().getSelectedIndex();
            AuctionLot newAuctionLot = new AuctionLot(aucTitle.getText(), aucDesc.getText(), aucType.getValue(), aucURL.getText(), Integer.parseInt(aucYear.getText()), Integer.parseInt(aucAskingPrice.getText()));
            AuctionApplication.getAuctionAPI().editAuctionLot(index, newAuctionLot);

            mainList.getItems().set(index, newAuctionLot.toString());
        } catch (RuntimeException e) {
            AlertBox.display("Error!", "Enter in correct details \n" + e.getMessage());
        }
    }

    public void viewSell() {
        aucAdd.setVisible(false);
        completeAuc.setVisible(true);
        dateSold.setValue(LocalDate.now());
        timeSold.setText(LocalTime.now().toString().substring(0,5));
        int finalPrice;
        if(highestBid(currentLot)!=null)
            finalPrice = highestBid(currentLot).getAmount();
        else
            finalPrice = 0;
        finalBid.setText(finalPrice + "");
        modelTitle.setText("Finalising Sale on " + currentLot.getTitle());
        currentLot = AuctionApplication.getAuctionAPI().getAuctionLot(mainList.getSelectionModel().getSelectedIndex());
        mainList.setDisable(true);
    }

    public void sellLot() {
        AuctionLot lot = AuctionApplication.getAuctionAPI().getAuctionLots().get(currentLot.getTitle());
        currentLot.sell(Integer.parseInt(finalBid.getText()), dateSold.getValue(), timeSold.getText());
        lot.sell(Integer.parseInt(finalBid.getText()), dateSold.getValue(), timeSold.getText());
        highestBid(currentLot).won();
        completeAuc.setVisible(false);
        aucAdd.setVisible(true);
        mainList.setDisable(false);
        mainList.getItems().clear();
        for (AuctionLot auctionLot : AuctionApplication.getAuctionAPI().getAuctionLots().toCoolLinkedList())
            mainList.getItems().add(auctionLot.toString());
        sellLot.setDisable(true);
        modelTitle.setText("Auction Lots");
    }

    public Bid highestBid(AuctionLot lot) {
        Bid highestBid = new Bid(0);
        for (Bidder bidder : AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList()) {
            for (Bid bid : bidder.getBids()) {
                if (bid.getLot().equals(lot) && bid.getAmount() > highestBid.getAmount())
                    highestBid = bid;
            }
        }
        return highestBid.getLot() == null? null:highestBid;
    }

    /**
     * bid methods
     **/
    public void addBid() {
        try {
            String lotTitle = bidLotChoice.getValue();
            AuctionLot lot = AuctionApplication.getAuctionAPI().findLotByName(lotTitle);
            int highestBid;
            if(highestBid(lot)!=null)
            highestBid = highestBid(lot).getAmount();
            else
                highestBid = 0;

            if(Utilities.validTime(bidTime.getText())){
            if (Integer.parseInt(bidAmount.getText()) > highestBid) {
                mainList.getItems().clear();
                Bid newBid = new Bid(bidTime.getText(), bidDate.getValue(), Integer.parseInt(bidAmount.getText()), lot);
                currentBidder.getBids().add(newBid);
                currentBidder.getBids().sort(Comparator.comparing(Bid::getDate));
                CoolLinkedList<Bid> sorted = new CoolLinkedList();
                for(Bid bid1:currentBidder.getBids()){
                    CoolLinkedList<Bid> timeSorted = new CoolLinkedList<>();
                    for(Bid bid2:currentBidder.getBids()){
                        if(bid1.getDate().equals(bid2.getDate())&&!sorted.contains(bid1)){
                            timeSorted.add(bid2);
                        }
                    }
                    timeSorted.sort(Comparator.comparing(Bid::getTime));        //This should be cleaned up, hopefully peter doesn't see this :(
                    for(Bid bid:timeSorted)
                        sorted.add(bid);
                }
                currentBidder.getBids().clear();
                for(Bid bid: sorted)
                    currentBidder.getBids().add(bid);
                for(Bid bid : currentBidder.getBids()){
                    mainList.getItems().add(bid.toString());
                }

                bidDate.getEditor().clear();
                bidTime.clear();
                bidAmount.clear();
            } else {
                AlertBox.display("Error", "New bid must be greater than " + highestBid);
            }}else{
                AlertBox.display("Error",bidTime.getText()+" is not valid \n Time must be in 24 hour format as HH:MM");
            }
        } catch (RuntimeException e) {
            AlertBox.display("Error", "Enter in correct details \n" + e.getMessage());
        }
    }


    public void editBid() {
        try {
            int index = mainList.getSelectionModel().getSelectedIndex();
            String lotTitle = bidLotChoice.getValue();
            AuctionLot lot = AuctionApplication.getAuctionAPI().findLotByName(lotTitle);

            Bid newBid = new Bid(bidTime.getText(), bidDate.getValue(), Integer.parseInt(bidAmount.getText()), lot);
            mainList.getItems().set(index, newBid.toString());
            currentBidder.getBids().set(index, newBid);
        } catch (RuntimeException e) {
            AlertBox.display("Error", "Enter in correct details \n" + e.getMessage());
        }
    }

    public void populateAmount() {
        AuctionLot auctionLot = AuctionApplication.getAuctionAPI().findLotByName(bidLotChoice.getValue());
        bidDate.setValue(LocalDate.now());
        bidTime.setText(LocalTime.now().toString().substring(0,5));
        if(highestBid(auctionLot)!=null)
        bidAmount.setText((highestBid(auctionLot).getAmount() + 1) + "");
    }


}
