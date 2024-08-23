package laboratorio1.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import laboratorio1.App;

public class SesionController {

    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("administradorView");
    }
}