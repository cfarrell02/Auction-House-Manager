package Main;


import Models.AuctionLot;
import Models.Bid;
import Models.Bidder;
import Utils.CoolLinkedList;
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
import java.time.LocalDate;

public class ModelController {
    @FXML
    private Text modelTitle;
    @FXML
    private ListView<String> mainList;

    @FXML
    private TextField biddName, biddTelephone, biddEmail, aucTitle, aucYear,
            aucAskingPrice, aucURL, bidAmount, bidTime;
    @FXML
    private DatePicker bidDate;
    @FXML
    private ChoiceBox<String> aucType;
    @FXML
    private ComboBox<String> bidLotChoice;
    @FXML
    private TextArea biddAddress, aucDesc;
    @FXML
    private AnchorPane aucAdd, bidAdd, biddAdd;

    private Bidder currentBidder;
    private AuctionLot currentLot;
    private Bid currentBid;

    //General Methods
    public void initialize() {
        modelTitle.setText(MainController.getCurrentTitle());
        if (MainController.getCurrentTitle().equals("Bidders")) {
            biddAdd.setVisible(true);
            for (Bidder bidder : AuctionApplication.getAuctionAPI().getBidders())
                mainList.getItems().add(bidder.toString());
        }else {
            aucAdd.setVisible(true);
            for(AuctionLot auctionLot: AuctionApplication.getAuctionAPI().getAuctionLots())
                mainList.getItems().add(auctionLot.toString());
        }

        for(AuctionLot auctionLot:AuctionApplication.getAuctionAPI().getAuctionLots()){
            bidLotChoice.getItems().add(auctionLot.getTitle());
        }



    }

    public void back() {
        if (MainController.getCurrentTitle().equals("Bidders")||MainController.getCurrentTitle().equals("Auction Lots")) {
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
    }

    public void viewAttributes(){
        if(mainList.getSelectionModel().getSelectedIndex()>=0){
        if(biddAdd.isVisible()){
            currentBidder = AuctionApplication.getAuctionAPI().getBidder(mainList.getSelectionModel().getSelectedIndex());
            biddName.setText(currentBidder.getName());
            biddAddress.setText(currentBidder.getAddress());
            biddTelephone.setText(currentBidder.getTelephone());
            biddEmail.setText(currentBidder.getTelephone());
        }else if (aucAdd.isVisible()){
            currentLot = AuctionApplication.getAuctionAPI().getAuctionLot(mainList.getSelectionModel().getSelectedIndex());
            aucType.setValue(currentLot.getType());
            aucAskingPrice.setText(String.valueOf(currentLot.getAskingPrice()));
            aucTitle.setText(currentLot.getTitle());
            aucDesc.setText(currentLot.getDescription());
            aucYear.setText(String.valueOf(currentLot.getYear()));
            aucURL.setText(currentLot.getImageURL());
        }}else if(bidAdd.isVisible()){
            currentBid = currentBidder.getBid(mainList.getSelectionModel().getSelectedIndex());
            bidDate.setValue(currentBid.getDate());
            bidLotChoice.setValue(currentBid.getLot().getTitle());
            bidAmount.setText(String.valueOf(currentBid.getAmount()));
            bidTime.setText(String.valueOf(currentBid.getTime()));
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
     *
     * Bidder Methods
     **/
    public void addBidder() {
        Bidder newBidder = new Bidder(biddName.getText(), biddAddress.getText(), biddTelephone.getText(), biddEmail.getText());
        mainList.getItems().add(newBidder.toString());
        AuctionApplication.getAuctionAPI().addBidder(newBidder);

        biddName.clear();
        biddAddress.clear();
        biddTelephone.clear();
        biddEmail.clear();
    }

    public void editBidder(){
        int index = mainList.getSelectionModel().getSelectedIndex();
        Bidder newBidder = new Bidder(biddName.getText(),biddAddress.getText(),biddTelephone.getText(),biddEmail.getText());
        AuctionApplication.getAuctionAPI().editBidder(index,newBidder);
       mainList.getItems().set(index,newBidder.toString());
    }

    public void viewBids(MouseEvent mouseEvent){
        if(biddAdd.isVisible()){
        if(mouseEvent.getClickCount()>=2){
            int index = mainList.getSelectionModel().getSelectedIndex();
            if(index>=0){
                currentBidder = AuctionApplication.getAuctionAPI().getBidder(index);
                biddAdd.setVisible(false);
                bidAdd.setVisible(true);
                mainList.getItems().clear();
                for(Bid bid:currentBidder.getBids())
                    mainList.getItems().add(bid.toString());
                modelTitle.setText(currentBidder.getName()+"'s Bids");
                AuctionApplication.mainWindow.setTitle(currentBidder.getName()+"'s Bids");
            }
        }
        }
    }

    /**Auction Method**/

    public void addAuctionLot(){
        AuctionLot newAuctionLot = new AuctionLot(aucTitle.getText(), aucDesc.getText(), aucType.getValue(), aucURL.getText(), Integer.parseInt(aucYear.getText()), Integer.parseInt(aucAskingPrice.getText()));
        mainList.getItems().add(newAuctionLot.toString());
        AuctionApplication.getAuctionAPI().addAuctionLot(newAuctionLot);

        aucTitle.clear();
        aucDesc.clear();
        aucURL.clear();
        aucYear.clear();
        aucAskingPrice.clear();
    }

    public void editAuctionLot(){
        int index = mainList.getSelectionModel().getSelectedIndex();
        AuctionLot newAuctionLot = new AuctionLot(aucTitle.getText(), aucDesc.getText(), aucType.getValue(), aucURL.getText(), Integer.parseInt(aucYear.getText()), Integer.parseInt(aucAskingPrice.getText()));
        AuctionApplication.getAuctionAPI().editAuctionLot(index, newAuctionLot);

        mainList.getItems().set(index, newAuctionLot.toString());
    }

    /**bid methods**/
    public void addBid(){
        String lotTitle = bidLotChoice.getValue();
        AuctionLot lot = AuctionApplication.getAuctionAPI().findLotByName(lotTitle);

        Bid newBid = new Bid(bidTime.getText(), bidDate.getValue(), Integer.parseInt(bidAmount.getText()),lot);
        mainList.getItems().add(newBid.toString());
        currentBidder.getBids().add(newBid);
        bidDate.getEditor().clear();
        bidTime.clear();
        bidAmount.clear();
    }

    public void editBid(){
        int index = mainList.getSelectionModel().getSelectedIndex();
        String lotTitle = bidLotChoice.getValue();
        AuctionLot lot = AuctionApplication.getAuctionAPI().findLotByName(lotTitle);

        Bid newBid = new Bid(bidTime.getText(), bidDate.getValue(), Integer.parseInt(bidAmount.getText()),lot);
        mainList.getItems().set(index,newBid.toString());
        currentBidder.getBids().set(index,newBid);
    }




}