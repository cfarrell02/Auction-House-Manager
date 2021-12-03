package Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
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

    public static String getCurrentTitle() {
        return currentTitle;
    }

    public void save() throws IOException {
        AuctionApplication.save();
    }

    public void load() throws IOException, ClassNotFoundException {
        AuctionApplication.load();
    }


}