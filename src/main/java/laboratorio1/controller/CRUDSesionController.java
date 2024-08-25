package laboratorio1.controller;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import laboratorio1.model.Deporte;
import laboratorio1.model.Entrenador;
import laboratorio1.model.EstadoSesion;
import laboratorio1.model.SesionEntrenamiento;

public class CRUDSesionController {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox tfDeporte;

    @FXML
    private TextField tfDuracion;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox tfEntrenador;

    @FXML
    private DatePicker tfFecha;

    private SesionEntrenamiento sesion;
    private ObservableList<SesionEntrenamiento> sesiones;

    @FXML
    void aceptar(ActionEvent event) {

        LocalDate fecha = this.tfFecha.getValue();
        int duracion = Integer.parseInt(tfDuracion.getText());
        EstadoSesion estado = EstadoSesion.PROGRAMADA;
        Deporte deporte = (Deporte) this.tfDeporte.getSelectionModel().getSelectedItem();
        Entrenador entrenador = (Entrenador) this.tfEntrenador.getSelectionModel().getSelectedItem();

        SesionEntrenamiento s = new SesionEntrenamiento(fecha, duracion, estado, deporte, entrenador);

        if(!sesiones.contains(s)) {
            this.sesion = s;

            Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La sesion ya existe");
            alert.showAndWait();
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.sesion = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    //Con este método mando todas las sesiones a esta clase para comprobar que no se repita
    public void initAtributos(ObservableList<SesionEntrenamiento> sesiones) {
        this.sesiones = sesiones;
    }

    public SesionEntrenamiento getSesion() {
        return sesion;
    }
}