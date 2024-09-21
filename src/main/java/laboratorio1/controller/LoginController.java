package laboratorio1.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import laboratorio1.App;
import laboratorio1.util.Utilidades;

public class LoginController implements Initializable {
    
    @FXML
    private Button lbEspañol;

    @FXML
    private Label lbId;

    @FXML
    private Button lbIngles;

    @FXML
    private Button lbIngresar;

    @FXML
    private Label lbIniciarSesion;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbSeleccione;

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

    private void cargarTextos() {
        Utilidades utilidades = Utilidades.getInstance();
        lbIniciarSesion.setText(utilidades.getString("iniciarSesion"));
        lbNombre.setText(utilidades.getString("nombre"));
        lbId.setText(utilidades.getString("id"));
        lbIngresar.setText(utilidades.getString("ingresar"));
        lbSeleccione.setText(utilidades.getString("seleccione"));
        lbEspañol.setText(utilidades.getString("espanol"));
        lbIngles.setText(utilidades.getString("ingles"));
    }

    @FXML
    void cambiarIdiomaEspanol(ActionEvent event) {
        Utilidades.getInstance().setLocale("es", "ES");
        cargarTextos();
    }

    @FXML
    void cambiarIdiomaIngles(ActionEvent event) {
        Utilidades.getInstance().setLocale("en", "US");
        cargarTextos();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Utilidades utilidades = Utilidades.getInstance();

        String idioma = utilidades.getIdioma();
        String pais = utilidades.getPais();
        utilidades.setLocale(idioma, pais);

        cargarTextos();
    }
}