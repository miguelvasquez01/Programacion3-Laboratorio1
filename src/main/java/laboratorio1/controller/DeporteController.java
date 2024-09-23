package laboratorio1.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

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
import laboratorio1.model.Deporte;

public class DeporteController implements Initializable {

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn<Deporte, String> colEntrenadores;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colNivelDificultad;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colNombre;

    @FXML
    private TableView<Deporte> tblDeportes;

    private ObservableList<Deporte> deportes;

    @FXML
    void agregarDeporte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogDeporteView.fxml"));
            Parent root = loader.load();

            DialogDeporteController controlador = loader.getController();
            controlador.initAtributos(deportes);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Deporte d = controlador.getDeporte();
            
            if(d != null) {
                this.deportes.add(d);
                this.tblDeportes.setItems(deportes);
                tblDeportes.refresh();
                d.guardar(this.deportes);
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void editarDeporte(ActionEvent event) {

        Deporte d = this.tblDeportes.getSelectionModel().getSelectedItem();
    
        if (d == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione un deporte");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogDeporteView.fxml"));
                Parent root = loader.load();

                DialogDeporteController controlador = loader.getController();
                controlador.initAtributos(deportes, d);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Deporte aux = controlador.getDeporte();
                
                if(aux != null) {
                    aux.guardar(this.deportes);
                    tblDeportes.refresh();
                }

            } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            }
        }
    }

    @FXML
    void eliminarDeporte(ActionEvent event) {

        Deporte d = this.tblDeportes.getSelectionModel().getSelectedItem();
    
        if (d == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione un deporte");
            alert.showAndWait();
        } else {
            this.deportes.remove(d);
            this.tblDeportes.refresh();
            d.guardar(this.deportes);
        }
    }

    @FXML
    void switchToAdmin(ActionEvent event) throws IOException {
        App.setRoot("administradorView");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        deportes = FXCollections.observableArrayList();
        this.tblDeportes.setItems(deportes);
        
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.colNivelDificultad.setCellValueFactory(new PropertyValueFactory<>("nivelDificultad"));
        // Configurar la columna para mostrar el nombre del entrenador
        this.colEntrenadores.setCellValueFactory(cellData -> {
            Deporte deporte = cellData.getValue();
            String nombreEntrenador = deporte.getEntrenador() != null ? deporte.getEntrenador().getNombre() : "Sin entrenador";
            return new SimpleStringProperty(nombreEntrenador);
        });

        // Deserializar la lista de deportes desde el archivo
        List<Deporte> listaD = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"deportes.txt", Deporte.class);

        // Verificar si la lista deserializada es nula o vacía
        if (listaD != null && !listaD.isEmpty()) {
            deportes.addAll(listaD); // Agregar los deportes a la lista observable
        }
    }
}