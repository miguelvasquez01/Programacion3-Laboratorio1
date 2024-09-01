package laboratorio1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import laboratorio1.App;

public class LoginController {

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtId;

    private  final String admin = "Juan David";
    private  final String idjuanda = "1091203215";
    private  final String admin2 = "Miguel Angel";
    private  final String idmiguel = "123";
    private  final String admingen = "123";
    private  final String admingenid= "123";


    @FXML
    void IniciarSesion(ActionEvent event) {
        String nombre = txtNombre.getText();
        String id = txtId.getText();

        if (nombre.equals(admin) && id.equals(idjuanda) || nombre.equals(admin2) && id.equals(idmiguel) || nombre.equals(admingen) && id.equals(admingenid)) {
            try {
                App.setRoot("administradorView");

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

        txtId.clear();
        txtNombre.clear();

    }

}