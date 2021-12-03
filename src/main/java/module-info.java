module Main{
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires xstream;


    opens Main to javafx.fxml, xstream;
    exports Main;
    exports Utils to xstream;
    exports Models to xstream;
    opens Models to xstream;
    opens Utils to xstream, json;


}