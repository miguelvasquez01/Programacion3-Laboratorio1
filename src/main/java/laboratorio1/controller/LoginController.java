package laboratorio1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtId;

    private  final String admin = "Juan David";
    private  final String idjuanda = "1091203215";

    @FXML
    private TextField txtNombre;

    @FXML
    void IniciarSesion(ActionEvent event) {
        String nombre = txtNombre.getText();
        String id = txtId.getText();

        if (nombre.equals(admin) && id.equals(idjuanda)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/laboratorio1/administradorView.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = (Stage) txtNombre.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Nombre o id incorrectos");
            alert.showAndWait();
        }

    }

}