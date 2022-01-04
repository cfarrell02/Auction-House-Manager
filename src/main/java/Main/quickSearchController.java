package Main;

import Models.AuctionLot;
import Models.Bidder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class quickSearchController {
    @FXML
    ListView<String> aucList,bidderList;
    @FXML
    TextField aucSearchbar,bidderSearchbar;
    public void back(){
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

    public void searchLots(){
        aucList.getItems().clear();
        String query = aucSearchbar.getText();
        AuctionLot result = AuctionApplication.getAuctionAPI().getAuctionLots().get(query);
        if(result!=null){
            aucList.getItems().addAll("-Auction Lot-",result.getTitle(),"-Description-", result.getDescription(),"-Type-", result.getType(),"-Year-",String.valueOf(result.getYear()),"-Image Url-",result.getImageURL());
            if(result.getSold())
                aucList.getItems().addAll("-Final Price",String.valueOf(result.getFinalPrice()),"-Date Sold-",result.getDateSold().toString(),"-Time Sold-",result.getTimeSold());
        }
    }

    public void searchBidders(){
        bidderList.getItems().clear();
        String query = bidderSearchbar.getText();
        Bidder result = AuctionApplication.getAuctionAPI().getBidders().get(query);
        if(result!=null){
            bidderList.getItems().addAll("-Bidder-",result.getName(),
                    "-Telephone-", result.getTelephone(),
                    "-Address-", result.getAddress(),
                    "-Email-", result.getEmail());
        }
    }

}
