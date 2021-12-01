package Main;

import Models.AuctionLot;
import Models.Bidder;
import Utils.CoolLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AuctionApplication extends Application {
    private static AuctionAPI auctionAPI;
    public static Stage mainWindow;
    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
        auctionAPI = new AuctionAPI();
    }

    public static AuctionAPI getAuctionAPI() {
        return auctionAPI;
    }

    public static void main(String[] args) {
        launch();
    }




}