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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import laboratorio1.App;
import laboratorio1.dao.SerializarObjeto;
import laboratorio1.model.SesionEntrenamiento;

public class SesionesCompletasController implements Initializable {

    @FXML
    private TableColumn<SesionEntrenamiento, String> colDeporte;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colDuracion;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colEntrenador;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colEstado;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colFecha;

    @FXML
    private TableView<SesionEntrenamiento> tblSesiones;

    private ObservableList<SesionEntrenamiento> sesiones;

    @FXML
    void switchToAdmin(ActionEvent event) throws IOException {
        App.setRoot("administradorView");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sesiones = FXCollections.observableArrayList();
        this.tblSesiones.setItems(sesiones);

        // Deserializar la lista de deportes desde el archivo
        List<SesionEntrenamiento> listaS = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"sesionesCompletadas.txt", SesionEntrenamiento.class);

        // Verificar si la lista deserializada es nula o vacía
        if (listaS != null && !listaS.isEmpty()) {
            this.sesiones.addAll(listaS);
        }
        
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        // Configurar las columnas
        this.colDeporte.setCellValueFactory(cellData -> {
            SesionEntrenamiento sesion = cellData.getValue();
            if (sesion.getDeporte() != null) {
                return new SimpleStringProperty(sesion.getDeporte().getNombre());
            } else {
                return new SimpleStringProperty("Sin deporte");
            }
        });
        this.colEntrenador.setCellValueFactory(cellData -> {
            SesionEntrenamiento sesion = cellData.getValue();
            if (sesion.getEntrenador() != null) {
                return new SimpleStringProperty(sesion.getEntrenador().getNombre());
            } else {
                return new SimpleStringProperty("Sin entrenador");
            }
        });
    }

}