package laboratorio1.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import laboratorio1.App;
import laboratorio1.dao.SerializarObjeto;
import laboratorio1.model.Entrenador;

public class EntrenadorController implements Initializable {

    @FXML
    private TableColumn<Entrenador, String> colEspecialidad;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colNombre;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colSesiones;

    @FXML
    private TableView<Entrenador> tblEntrenadores;

    private ObservableList<Entrenador> entrenadores;

    @FXML
    void switchToAdmin(ActionEvent event) throws IOException {
        App.setRoot("administradorView");
    }

    @FXML
    void agregarEntrenador(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogEntrenadorView.fxml"));
            Parent root = loader.load();

            DialogEntrenadorController controlador = loader.getController();
            controlador.initAtributos(entrenadores);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Entrenador e = controlador.getEntrenador();
            
            if(e != null) {
                this.entrenadores.add(e);
                this.tblEntrenadores.setItems(entrenadores);
                tblEntrenadores.refresh();
                e.guardar(this.entrenadores);
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.out.println(e);
        }
    }

    @FXML
    void editarEntrenador(ActionEvent event) {

        Entrenador e = this.tblEntrenadores.getSelectionModel().getSelectedItem();
    
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione un entrenador");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogEntrenadorView.fxml"));
                Parent root = loader.load();

                DialogEntrenadorController controlador = loader.getController();
                controlador.initAtributos(entrenadores, e);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Entrenador aux = controlador.getEntrenador();
                
                if(aux != null) {
                    tblEntrenadores.refresh();
                    e.guardar(entrenadores);
                }

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void eliminarEntrenador(ActionEvent event) {

        Entrenador e = this.tblEntrenadores.getSelectionModel().getSelectedItem();
    
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione un entrenador");
            alert.showAndWait();
        } else {
            this.entrenadores.remove(e);
            this.tblEntrenadores.refresh();
            e.guardar(entrenadores);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        entrenadores = FXCollections.observableArrayList();
        this.tblEntrenadores.setItems(entrenadores);
        
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        // Configurar la columna para mostrar el nombre de la especialidad
        this.colEspecialidad.setCellValueFactory(cellData -> {
            Entrenador entrenador = cellData.getValue();
            String nombreEspecialidad = entrenador.getEspecialidad() != null ? entrenador.getEspecialidad().getNombre() : "Sin especialidad";
            return new SimpleStringProperty(nombreEspecialidad);
        });
        this.colSesiones.setCellValueFactory(new PropertyValueFactory<>("sesion"));

        // Deserializar la lista de deportes desde el archivo
        List<Entrenador> listaE = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"entrenadores.txt", Entrenador.class);

        // Verificar si la lista deserializada es nula o vacía
        if (listaE != null && !listaE.isEmpty()) {
            entrenadores.addAll(listaE); // Agregar los deportes a la lista observable
        }
    }
}
