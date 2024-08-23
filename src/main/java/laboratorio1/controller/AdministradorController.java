package laboratorio1.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import laboratorio1.App;

public class AdministradorController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
