package laboratorio1.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import laboratorio1.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}