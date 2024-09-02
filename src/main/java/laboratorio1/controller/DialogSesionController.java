package laboratorio1.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import laboratorio1.dao.SerializarObjeto;
import laboratorio1.model.Deporte;
import laboratorio1.model.Entrenador;
import laboratorio1.model.EstadoSesion;
import laboratorio1.model.SesionEntrenamiento;

public class DialogSesionController implements Initializable {

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

    @FXML
    private TextField tfIdSesion;

    private SesionEntrenamiento sesion;
    private ObservableList<SesionEntrenamiento> sesiones;

    private ObservableList<Deporte> deportes = FXCollections.observableArrayList();
    private ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList();

    @FXML
    void aceptar(ActionEvent event) {
        try {
            // Validaciones
            if (tfIdSesion.getText() == null || tfIdSesion.getText().trim().isEmpty()) {
                showError("El ID de la sesión no puede estar vacío.");
                return;
            }

            LocalDate fecha = this.tfFecha.getValue();
            if (fecha == null) {
                showError("La fecha de la sesión no puede estar vacía.");
                return;
            }

            if (fecha.isBefore(LocalDate.now())) {
                showError("La fecha de la sesión no puede ser anterior a la fecha actual.");
                return;
            }

            String duracionStr = tfDuracion.getText();
            int duracion;
            try {
                duracion = Integer.parseInt(duracionStr);
                if (duracion <= 0) {
                    showError("La duración debe ser un número positivo.");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("La duración debe ser un número válido.");
                return;
            }

            Deporte deporte = (Deporte) this.tfDeporte.getSelectionModel().getSelectedItem();
            if (deporte == null) {
                showError("Debe seleccionar un deporte.");
                return;
            }

            Entrenador entrenador = (Entrenador) this.tfEntrenador.getSelectionModel().getSelectedItem();
            if (entrenador == null) {
                showError("Debe seleccionar un entrenador.");
                return;
            }

            String idSesion = this.tfIdSesion.getText();
            EstadoSesion estado = EstadoSesion.PROGRAMADA;
            SesionEntrenamiento s = new SesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, idSesion);

            if (!sesiones.contains(s)) {

                // Modificar
                if (this.sesion != null) {
                    this.sesion.setFecha(fecha);
                    this.sesion.setDuracion(duracion);
                    this.sesion.setDeporte(deporte);
                    this.sesion.setEntrenador(entrenador);
                    this.sesion.setIdSesion(idSesion);

                } else {
                    // Insertar
                    this.sesion = s;
                }

                Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                stage.close();

            } else {
                showError("La sesión ya existe.");
            }
        } catch (Exception e) {
            showError("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.sesion = null;
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

    public void initAtributos(ObservableList<SesionEntrenamiento> sesiones) {
        this.sesiones = sesiones;
    }

    public void initAtributos(ObservableList<SesionEntrenamiento> sesiones, SesionEntrenamiento s) {
        this.sesiones = sesiones;
        this.sesion = s;
        this.tfIdSesion.setText(s.getIdSesion());
        this.tfFecha.setValue(s.getFecha());
        this.tfDuracion.setText(String.valueOf(s.getDuracion()));
    }

    public SesionEntrenamiento getSesion() {
        return this.sesion;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Deserializar la lista de deportes
        List<Deporte> listaD = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao() + "deportes.txt",
                Deporte.class);
        deportes.setAll(listaD);
        tfDeporte.setItems(deportes);

        tfDeporte.setConverter(new StringConverter<Deporte>() {
            @Override
            public String toString(Deporte deporte) {
                return deporte != null ? deporte.getNombre() : "";
            }

            @Override
            public Deporte fromString(String nombre) {
                return null;
            }
        });

        // Deserializar la lista de entrenadores desde el archivo
        List<Entrenador> listaE = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao() + "entrenadores.txt",
                Entrenador.class);
        entrenadores.setAll(listaE);
        tfEntrenador.setItems(entrenadores);

        tfEntrenador.setConverter(new StringConverter<Entrenador>() {
            @Override
            public String toString(Entrenador entrenador) {
                return entrenador != null ? entrenador.getNombre() : "";
            }

            @Override
            public Entrenador fromString(String nombre) {
                return null;
            }
        });
    }
}
