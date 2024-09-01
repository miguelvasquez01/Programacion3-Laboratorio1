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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import laboratorio1.App;
import laboratorio1.dao.SerializarObjeto;
import laboratorio1.model.SesionEntrenamiento;

public class SesionController implements Initializable {

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

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colIdSesion;

    @FXML
    private TableView<SesionEntrenamiento> tblSesiones;

    @FXML
    private Button secondaryButton;

    private ObservableList<SesionEntrenamiento> sesiones;

    @FXML
    private void switchToAdmin(ActionEvent event) throws IOException {
        App.setRoot("administradorView");
    }

    @FXML
    void agregarSesion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogSesionView.fxml"));
            Parent root = loader.load();

            DialogSesionController controlador = loader.getController();
            controlador.initAtributos(sesiones);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            SesionEntrenamiento s = controlador.getSesion();

            
            
            if(s != null) {
                this.sesiones.add(s);
                this.tblSesiones.setItems(sesiones);
                s.cambiarEstado();//Verifica que este antes del día actual
                s.guardar(this.sesiones);
                tblSesiones.refresh();
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.out.println(e);
        }

        new Alert (AlertType.INFORMATION,"Recuerde verificar el id de la sesion al momento de registrar un miembro").showAndWait();
    }

    @FXML
    void editarSesion(ActionEvent event) {

        SesionEntrenamiento s = this.tblSesiones.getSelectionModel().getSelectedItem();
    
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una sesión");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogSesionView.fxml"));
                Parent root = loader.load();

                DialogSesionController controlador = loader.getController();
                controlador.initAtributos(sesiones, s);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                SesionEntrenamiento aux = controlador.getSesion();
                
                if(aux != null) {
                    aux.cambiarEstado();//Verifica que este antes del día actual
                    aux.guardar(this.sesiones);
                    tblSesiones.refresh();
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
    void eliminarSesion(ActionEvent event) {

        SesionEntrenamiento s = this.tblSesiones.getSelectionModel().getSelectedItem();
    
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una sesión");
            alert.showAndWait();
        } else {
            this.sesiones.remove(s);
            this.tblSesiones.refresh();
            s.cambiarEstado();//Verifica que este antes del día actual
            s.guardar(this.sesiones);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        sesiones = FXCollections.observableArrayList();
        this.tblSesiones.setItems(sesiones);

        // Deserializar la lista de deportes desde el archivo
        List<SesionEntrenamiento> listaS = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"sesionesProgramadas.txt", SesionEntrenamiento.class);

        // Verificar si la lista deserializada es nula o vacía
        if (listaS != null && !listaS.isEmpty()) {
            this.sesiones.addAll(listaS);
        }
        
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colIdSesion.setCellValueFactory(new PropertyValueFactory<>("idSesion"));
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