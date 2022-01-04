package Main;

import Models.AuctionLot;
import Models.Bidder;
import Utils.AlertBox;
import Utils.CoolLinkedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Locale;


public class MainController {

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> frontList;

    private static String currentTitle;

    public void showBidder() throws IOException {
        currentTitle = "Bidders";
        Stage stage = AuctionApplication.mainWindow;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("model-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(currentTitle);
        stage.setScene(scene);
        stage.show();
    }

    public void showAuctionLot() throws IOException {
        currentTitle = "Auction Lots";
        Stage stage = AuctionApplication.mainWindow;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("model-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(currentTitle);
        stage.setScene(scene);
        stage.show();
    }

    public void showQuickSearch() throws IOException {
        Stage stage = AuctionApplication.mainWindow;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("quick-search.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Quick Search");
        stage.setScene(scene);
        stage.show();
    }

    public static String getCurrentTitle() {
        return currentTitle;
    }

    public void initialize(){
        search();
    }

    public void goTo(MouseEvent m){
        if(m.getClickCount()>=2){
        int index = frontList.getSelectionModel().getSelectedIndex();
        AuctionLot auctionLot= AuctionApplication.getAuctionAPI().findLotByName(frontList.getItems().get(index));
        Bidder bidder = AuctionApplication.getAuctionAPI().findBidderByName(frontList.getItems().get(index));
        if(auctionLot!=null){
            AlertBox.display("Details of this Lot",auctionLot.toString());
        }else if(bidder!=null){
            AlertBox.display("Details of this Bidder",bidder.toString());
        }}

    }

    public void search() {
    //    try {

            CoolLinkedList<Bidder> sortedBidder = new CoolLinkedList<>();
            CoolLinkedList<AuctionLot> sortedAuctionLot = new CoolLinkedList<>();
            String searchValue = searchBar.getText().toLowerCase().trim();
            frontList.getItems().clear();


            if(!AuctionApplication.getAuctionAPI().getBidders().isEmpty()) {
                for (Bidder bidder : AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList())
                    if (bidder.getName().toLowerCase().contains(searchValue) || bidder.getTelephone().toLowerCase().contains(searchValue) ||
                            bidder.getAddress().toLowerCase().contains(searchValue) || bidder.getEmail().toLowerCase().contains(searchValue))
                        sortedBidder.add(bidder);

               sortedBidder.sort(Comparator.comparing(Bidder::getName));
                    frontList.getItems().add("---Bidders---");
                for (Bidder bidder1 : sortedBidder) {

                    frontList.getItems().add(bidder1.getName());

                }
            }

            if (!AuctionApplication.getAuctionAPI().getAuctionLots().isEmpty()) {
                for (AuctionLot auctionLot : AuctionApplication.getAuctionAPI().getAuctionLots().toCoolLinkedList())
                    if (auctionLot.getTitle().toLowerCase().contains(searchValue) || auctionLot.getDescription().toLowerCase().contains(searchValue)
                            || auctionLot.getType().toLowerCase().contains(searchValue) || (auctionLot.getYear()+"").contains(searchValue))
                        sortedAuctionLot.add(auctionLot);

                    sortedAuctionLot.sort(Comparator.comparing(AuctionLot::getTitle));
                    frontList.getItems().add("---Auction Lots---");
                    for (AuctionLot auctionLot1 : sortedAuctionLot) {
                        frontList.getItems().add(auctionLot1.getTitle());
                    }
                }
            }

    //    } catch (RuntimeException e) {
     //       AlertBox.display("Error!", e.getMessage());

      //  }



    public void save() throws IOException {
        AuctionApplication.save();
    }

    public void load() throws IOException, ClassNotFoundException {
        AuctionApplication.load();
        initialize();
    }


}