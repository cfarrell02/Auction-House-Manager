package Main;


import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;

public class ModelController {
    @FXML
    private Text modelTitle;
    @FXML
    private ListView<String> mainList;
    @FXML
    private TextField biddName,biddTelephone,biddEmail,aucTitle,aucYear,
            aucAskingPrice,aucURL,bidAmount,bidTime;
    @FXML
    private DatePicker bidDate;
    @FXML
    private ChoiceBox<String> aucType;
    @FXML
    private TextArea biddAddress,aucDesc;
    @FXML
    private AnchorPane aucAdd,bidAdd,biddAdd;

}
