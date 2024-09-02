package laboratorio1.controller;

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
import laboratorio1.model.Miembro;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MiembroController implements Initializable {

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colEmail;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colId;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colNombre;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colEdad;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colIdSesion;


    @FXML
    private TableView<Miembro> tblMiembros;

    private ObservableList<Miembro> miembros;

    @FXML
    void SwitchToAdmin(ActionEvent event) throws IOException {
        App.setRoot("AdministradorView");

    }



    @FXML
    void añadirMiembro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogMiembroView.fxml"));
            Parent root = loader.load();
            DialogMiembroController controlador = loader.getController();
            controlador.initAtributos(miembros);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Miembro m = controlador.getMiembro();

            if (m != null) {
                if (m.getNombre() == null || m.getNombre().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "El nombre no puede estar vacío").showAndWait();
                    return;
                }
                if (m.getEmail() == null || m.getEmail().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "El email no puede estar vacío").showAndWait();
                    return;
                }

                if (m.getId()== null || m.getId().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR , "El id no puede estar vacio").showAndWait();
                    return;
                }

                if (m.getEdad()<=0 ){
                    new  Alert(Alert.AlertType.ERROR,"La edad debe ser mayor que 0").showAndWait();
                    return;
                }

                if (m.getIdSesion()== null || m.getIdSesion().isEmpty() ){
                    new  Alert(Alert.AlertType.ERROR,"La id de la sesion no puede estar vacia").showAndWait();
                    return;
                }


                this.miembros.add(m);
                this.tblMiembros.setItems(miembros);
                tblMiembros.refresh();
                m.guardar(miembros);

                new Alert(Alert.AlertType.INFORMATION,"Recuerda verificar que exista una sesion con ese id en el panel de sesiones programadas").showAndWait();
            }

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            System.out.println(e);
        }
    }


    @FXML
    void editarMiembro(ActionEvent event) {
        Miembro m = this.tblMiembros.getSelectionModel().getSelectedItem();

        if (m == null) {
            new Alert(Alert.AlertType.ERROR, "Seleccione un miembro").showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/laboratorio1/DialogMiembroView.fxml"));
                Parent root = loader.load();

                DialogMiembroController controlador = loader.getController();
                controlador.initAtributos(miembros, m);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Miembro aux = controlador.getMiembro();

                if (aux != null) {
                    if (aux.getNombre() == null || aux.getNombre().isEmpty()) {
                        new Alert(Alert.AlertType.ERROR, "El nombre no puede estar vacío").showAndWait();
                        return;
                    }
                    if (aux.getEmail() == null || aux.getEmail().isEmpty()) {
                        new Alert(Alert.AlertType.ERROR, "El email no puede estar vacío").showAndWait();
                        return;
                    }

                    if (aux.getId() == null || aux.getId().isEmpty()) {
                        new  Alert(Alert.AlertType.ERROR,"El id no puede estar vacio").showAndWait();
                        return;
                    }
                    if (aux.getEdad() <= 0 ){
                        new Alert(Alert.AlertType.ERROR,"La edad debe ser mayor a 0").showAndWait();
                        return;
                    }

                    if (aux.getIdSesion() == null || aux.getIdSesion().isEmpty()) {
                        new Alert(Alert.AlertType.ERROR, "El id de la sesion no puede estar vacio").showAndWait();

                    }
                    tblMiembros.refresh();
                    m.guardar(miembros);


                }

            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        }
    }


    @FXML
    void eliminarMiembro(ActionEvent event) {

        Miembro m = this.tblMiembros.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione un miembro");
            alert.showAndWait();
        } else {
            this.miembros.remove(m);
            this.tblMiembros.refresh();
            m.guardar(miembros);

        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        miembros = FXCollections.observableArrayList();
        this.tblMiembros.setItems(miembros);

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        this.colIdSesion.setCellValueFactory(new PropertyValueFactory<>("IdSesion"));


        // Deserializar la lista de deportes desde el archivo
        List<Miembro> listaM = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao()+"miembros.txt", Miembro.class);

        // Verificar si la lista deserializada es nula o vacía
        if (listaM != null && !listaM.isEmpty()) {
            miembros.addAll(listaM); // Agregar los deportes a la lista observable
        }
    }
}

