package laboratorio1.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import laboratorio1.dao.SerializarObjeto;
import laboratorio1.model.Deporte;
import laboratorio1.model.Entrenador;
import laboratorio1.model.NivelDificultad;

public class DialogDeporteController implements Initializable {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextArea tfDescripcion;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox tfNivelDificutad;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox tfEntrenador;

    @FXML
    private TextField tfNombre;

    private Deporte deporte;
    private ObservableList<Deporte> deportes;

    private ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList();

    @FXML
    void aceptar(ActionEvent event) {

        String nombre = this.tfNombre.getText();
        String descripcion = this.tfDescripcion.getText();
        NivelDificultad nivelDificultad = (NivelDificultad)this.tfNivelDificutad.getSelectionModel().getSelectedItem();
        Entrenador entrenador = (Entrenador)this.tfEntrenador.getSelectionModel().getSelectedItem();

        Deporte d = new Deporte(nombre, descripcion, nivelDificultad, entrenador);

        if(!deportes.contains(d)) {

            //Modificar
            if(this.deporte != null) {
                this.deporte.setNombre(nombre);
                this.deporte.setDescripcion(descripcion);
                this.deporte.setNivelDificultad(nivelDificultad);
                this.deporte.setEntrenador(entrenador);

            //Insertar
            } else {
                this.deporte = d;
            }

            Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El deporte ya existe");
            alert.showAndWait();
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.deporte = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    //Con este método mando todos los deportes a esta clase para comprobar que no se repita
    public void initAtributos(ObservableList<Deporte> deportes) {
        this.deportes = deportes;
    }

    //Pone el deporte seleccionado en los campos de texto
    @SuppressWarnings("unchecked")
    public void initAtributos(ObservableList<Deporte> deportes, Deporte d) {
        this.deportes = deportes;
        this.deporte = d;
        this.tfNombre.setText(d.getNombre());
        this.tfDescripcion.setText(d.getDescripcion());
        this.tfNivelDificutad.setValue(d.getNivelDificultad());
    }

    public Deporte getDeporte() {
        return this.deporte;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    // Cargar los valores de NivelDificultad en el ComboBox
    ObservableList<NivelDificultad> list = FXCollections.observableArrayList(NivelDificultad.values());
    tfNivelDificutad.setItems(list);
        
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