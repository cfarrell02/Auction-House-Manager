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
    private TextArea biddAddress, aucDesc;
    @FXML
    private AnchorPane aucAdd, bidAdd, biddAdd;

    private Bidder currentBidder;
    private AuctionLot currentLot;

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
                currentLot.removeBid(index);
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
        Bid newBid = new Bid(bidTime.getText(), bidDate.getValue(), Integer.parseInt(bidAmount.getText()));
        mainList.getItems().add(newBid.toString());
        currentBidder.getBids().add(newBid);
        currentLot.getBids().add(newBid);

        bidTime.clear();
        bidAmount.clear();
    }

    public void editBid(){
        int index = mainList.getSelectionModel().getSelectedIndex();
        Bid newBid = new Bid(bidTime.getText(), bidDate.getValue(), Integer.parseInt(bidAmount.getText()));
        currentBidder.editBid(index, newBid);
        currentLot.getBids().add(newBid);

        mainList.getItems().set(index, newBid.toString());

    }




}