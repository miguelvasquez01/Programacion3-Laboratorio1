package laboratorio1.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import laboratorio1.App;

public class AdministradorController {

    @FXML
    void switchToGestionarDeportes(ActionEvent event) throws IOException {
        App.setRoot("deporteView");
    }

    @FXML
    void switchToGestionarEntrenadores(ActionEvent event) throws IOException {
        App.setRoot("entrenadorView");
    }

    @FXML
    void switchToGestionarSesionesActivas(ActionEvent event) throws IOException {
        App.setRoot("sesionView");
    }

    @FXML
    void switchToVisualizarSesionesComp(ActionEvent event) {

    }
}
