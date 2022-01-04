package Main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Models.AuctionLot;
import Models.Bidder;
import Utils.CoolLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class AuctionApplication extends Application {
    private static AuctionAPI auctionAPI;
    public static Stage mainWindow;
    @Override
    public void start(Stage stage) throws IOException {
        auctionAPI = new AuctionAPI();
        mainWindow = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }

    public static AuctionAPI getAuctionAPI() {
        return auctionAPI;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void save() throws IOException {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("AuctionSystem.xml"));
        out.writeObject(auctionAPI);
        out.close();
    }

    public static void load() throws IOException, ClassNotFoundException {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("AuctionSystem.xml"));
        auctionAPI = (AuctionAPI) is.readObject();
        is.close();
    }



}