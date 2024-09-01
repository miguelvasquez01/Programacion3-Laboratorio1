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
    private TextField tfidSesion;

    private Miembro miembro;
    private ObservableList<Miembro> miembros;

    @FXML
    void aceptar(ActionEvent event) {
        // Validaciones
        if (tfNombre.getText() == null || tfNombre.getText().trim().isEmpty()) {
            showError("El nombre no puede estar vacío.");
            return;
        }

        if (tfEmail.getText() == null || tfEmail.getText().trim().isEmpty()) {
            showError("El email no puede estar vacío.");
            return;
        }

        if (tfId.getText() == null || tfId.getText().trim().isEmpty()) {
            showError("El ID no puede estar vacío.");
            return;
        }

        if (tfidSesion.getText() == null || tfidSesion.getText().trim().isEmpty()) {
            showError("El ID de la sesión no puede estar vacío.");
            return;
        }

        String edadStr = tfEdad.getText();
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
            if (edad <= 0) {
                showError("La edad debe ser un número positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("La edad debe ser un número válido.");
            return;
        }

        String nombre = tfNombre.getText();
        String email = tfEmail.getText();
        String id = tfId.getText();
        String idSesion = tfidSesion.getText();

        Miembro m = new Miembro(nombre, email, id, edad, idSesion);

        if (!miembros.contains(m)) {
            if (this.miembro != null) {
                this.miembro.setNombre(m.getNombre());
                this.miembro.setEmail(m.getEmail());
                this.miembro.setId(m.getId());
                this.miembro.setIdSesion(m.getIdSesion());
                this.miembro.setEdad(m.getEdad());

            } else {
                this.miembro = m;
            }

            Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
            stage.close();
        } else {
            showError("El miembro ya existe.");
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.miembro = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    // Método para mostrar mensajes de error
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
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
        this.tfidSesion.setText(m.getIdSesion());
        this.tfEdad.setText(String.valueOf(m.getEdad()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicialización si es necesario
    }

    public Miembro getMiembro() {
        return miembro;
    }
}
