package laboratorio1.controller;

import java.io.File;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import laboratorio1.dao.SerializarObjeto;
import laboratorio1.model.Deporte;
import laboratorio1.model.Entrenador;
import laboratorio1.model.SesionEntrenamiento;

public class DialogEntrenadorController implements Initializable {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox tfEspecialidad;

    @FXML
    private TextField tfNombre;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox tfSesiones;

    private Entrenador entrenador;
    private ObservableList<Entrenador> entrenadores;

    private ObservableList<Deporte> deportes = FXCollections.observableArrayList();

    @FXML
    void aceptar(ActionEvent event) {

        String nombre = this.tfNombre.getText();
        Deporte deporte = (Deporte) this.tfEspecialidad.getSelectionModel().getSelectedItem();
        SesionEntrenamiento sesion = (SesionEntrenamiento) this.tfSesiones.getSelectionModel().getSelectedItem();

        Entrenador e = new Entrenador(nombre, deporte);

        if(!entrenadores.contains(e)) {

            //Modificar
            if(this.entrenador != null) {
                this.entrenador.setNombre(nombre);
                this.entrenador.setEspecialidad(deporte);
                this.entrenador.setSesion(sesion);

            //Insertar
            } else {
                this.entrenador = e;
            }

            Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El entrenador ya existe");
            alert.showAndWait();
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.entrenador = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    //Con este método mando todos los deportes a esta clase para comprobar que no se repita
    public void initAtributos(ObservableList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    //Pone el entrenador seleccionado en los campos de texto
    public void initAtributos(ObservableList<Entrenador> entrenadores, Entrenador e) {
        this.entrenadores = entrenadores;
        this.entrenador = e;
        this.tfNombre.setText(e.getNombre());
        // this.tfEspecialidad.setValue(e.getEspecialidad().getNombre());
        // this.tfSesiones.setValue(e.getSesion());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Deserializar la lista de deportes
        File archivo = new File(SerializarObjeto.rutaDao() + "deportes.txt");
        if(archivo.exists()) {
            List<Deporte> listaD = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"deportes.txt", Deporte.class);
            deportes.setAll(listaD); 
        } else {
            System.out.println("Archivo no creado, hacer transient y quitar");
        }

        // Configurar el ComboBox con la lista observable
        tfEspecialidad.setItems(deportes);

        // Configurar un StringConverter para mostrar los nombres de los deportes en el ComboBox
        tfEspecialidad.setConverter(new StringConverter<Deporte>() {
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
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }
}