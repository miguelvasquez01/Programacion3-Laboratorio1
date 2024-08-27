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

    private SesionEntrenamiento sesion;
    private ObservableList<SesionEntrenamiento> sesiones;

    private ObservableList<Deporte> deportes = FXCollections.observableArrayList();
    private ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList();

    @FXML
    void aceptar(ActionEvent event) {

        LocalDate fecha = this.tfFecha.getValue();
        int duracion = Integer.parseInt(tfDuracion.getText());
        EstadoSesion estado = EstadoSesion.PROGRAMADA;
        Deporte deporte = (Deporte) this.tfDeporte.getSelectionModel().getSelectedItem();
        Entrenador entrenador = (Entrenador) this.tfEntrenador.getSelectionModel().getSelectedItem();

        SesionEntrenamiento s = new SesionEntrenamiento(fecha, duracion, estado, deporte, entrenador);

        if(!sesiones.contains(s)) {

            //Modificar
            if(this.sesion != null) {
                this.sesion.setFecha(fecha);
                this.sesion.setDuracion(duracion);
                this.sesion.setDeporte(deporte);
                this.sesion.setEntrenador(entrenador);

            //Insertar
            } else {
                this.sesion = s;
            }

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

    //Pone la sesión seleccionada en los campos de texto
    public void initAtributos(ObservableList<SesionEntrenamiento> sesiones, SesionEntrenamiento s) {
        this.sesiones = sesiones;
        this.sesion = s;
        this.tfFecha.setValue(s.getFecha());
        this.tfDuracion.setText(s.getDuracion() + "");
    }

    public SesionEntrenamiento getSesion() {
        return this.sesion;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        // Deserializar la lista de deportes
        List<Deporte> listaD = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"deportes.txt", Deporte.class);
        deportes.setAll(listaD); 
        // Configurar el TableView con la lista observable
        tfDeporte.setItems(deportes);
        
        // Configurar un StringConverter para mostrar los nombres de los deportes en el ComboBox
        tfDeporte.setConverter(new StringConverter<Deporte>() {
            @Override
            public String toString(Deporte deporte) {
                // Verifica si el deporte es null antes de acceder a su nombre
                return deporte != null ? deporte.getNombre() : "";//Convierte el entrenador en string
            }
            @Override
            public Deporte fromString(String nombre) {
                // el ComboBox ya sabe internamente qué objeto fue seleccionado, por lo que no se requiere una conversión desde texto (String) a objeto (Deporte).
                return null;
            }
        });

        // Deserializar la lista de entrenadores desde el archivo
        List<Entrenador> listaE = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"entrenadores.txt", Entrenador.class);
        entrenadores.setAll(listaE);

            // Configurar el ComboBox tfEntrenador con la lista observable de entrenadores
        tfEntrenador.setItems(entrenadores);

        // Configurar un StringConverter para mostrar los nombres de los entrenadores en el ComboBox
        tfEntrenador.setConverter(new StringConverter<Entrenador>() {
            @Override
            public String toString(Entrenador entrenador) {
                // Verifica si el entrenador es null antes de acceder a su nombre
                return entrenador != null ? entrenador.getNombre() : "";//Convierte el entrenador en string
            }

            @Override
            public Entrenador fromString(String nombre) {
                // el ComboBox ya sabe internamente qué objeto fue seleccionado, por lo que no se requiere una conversión desde texto (String) a objeto (Entrenador).
                return null;
            }
        });
    }
}