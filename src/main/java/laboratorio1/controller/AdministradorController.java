package laboratorio1.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import laboratorio1.App;
import laboratorio1.util.Utilidades;

public class AdministradorController implements Initializable {

    @FXML
    private Button btnDeporte;

    @FXML
    private Button btnEntrenador;

    @FXML
    private Button btnMiembro;

    @FXML
    private Button btnSesion;

    @FXML
    private Button btnSesionCompleta;

    @FXML
    private Button lbCerrarSesion;

    @FXML
    private Label lbDeporte;

    @FXML
    private Label lbEntrenador;

    @FXML
    private Label lbMiembro;

    @FXML
    private Label lbPanelGestion;

    @FXML
    private Label lbSesion;

    @FXML
    private Label lbSesionCompleta;

    @FXML
    void switchToGestionarDeportes(ActionEvent event) throws IOException {
        System.out.println("Cambiando escena a deporteView");
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
    void switchToGestionarMiembros(ActionEvent event) throws IOException {
        App.setRoot("miembroView");

    }

    @FXML
    void switchToVisualizarSesionesComp(ActionEvent event) throws IOException {
        App.setRoot("sesionesCompletasView");
    }

    @FXML
    void switchToLoginView(ActionEvent event) throws IOException {
        App.setRoot("LoginView");
    }

    public void cargarTextos() {
        Utilidades util = Utilidades.getInstance();
        lbPanelGestion.setText(util.getString("panelGestion"));
        lbDeporte.setText(util.getString("gestionaUnDeporte"));
        btnDeporte.setText(util.getString("gestionarDeporte"));
        lbEntrenador.setText(util.getString("gestionaUnEntrenador"));
        btnEntrenador.setText(util.getString("gestionarEntrenador"));
        lbSesion.setText(util.getString("gestionaUnaSesion"));
        btnSesion.setText(util.getString("gestionarSesion"));
        lbMiembro.setText(util.getString("gestionaUnMiembro"));
        btnMiembro.setText(util.getString("gestionarMiembro"));
        lbSesionCompleta.setText(util.getString("gestionaUnaSesionCompleta"));
        btnSesionCompleta.setText(util.getString("gestionarSesionCompleta"));
        lbCerrarSesion.setText(util.getString("cerrarSesion"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        cargarTextos();
    }
}
