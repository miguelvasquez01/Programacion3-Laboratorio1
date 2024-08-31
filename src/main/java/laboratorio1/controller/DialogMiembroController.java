package laboratorio1.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import laboratorio1.model.Miembro;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogMiembroController implements Initializable {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfEdad;


    @FXML
    private Miembro miembro;
    private ObservableList<Miembro> miembros;

    @FXML
    void aceptar(ActionEvent event) {
        String nombre = tfNombre.getText();
        String email = tfEmail.getText();
        String id = tfId.getText();
        int edad = Integer.parseInt(tfEdad.getText());

        Miembro m = new Miembro(nombre,email,id,edad);

        if (!miembros.contains(m)) {

            if (this.miembro != null) {
                this.miembro.setNombre(m.getNombre());
                this.miembro.setEmail(m.getEmail());
                this.miembro.setId(m.getId());
                this.miembro.setEdad(m.getEdad());

            }else {
            this.miembro = m;
        }

        Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
        stage.close();
    } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El miembro ya existe ");
            alert.showAndWait();

        }
    }




    @FXML
    void cancelar(ActionEvent event) {
        this.miembro = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();

    }

    public void initAtributos(ObservableList<Miembro> miembros) {
        this.miembros = miembros;
    }

    public void initAtributos(ObservableList<Miembro> miembros, Miembro m) {
        this.miembros = miembros;
        this.miembro = m;
        this.tfNombre.setText(m.getNombre());
        this.tfEmail.setText(m.getEmail());
        this.tfId.setText(m.getId());
        this.tfEdad.setText(String.valueOf(m.getEdad()));
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public  Miembro getMiembro() {return miembro;}
}

