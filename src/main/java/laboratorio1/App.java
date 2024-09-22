package laboratorio1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import laboratorio1.model.Entrenador;
import laboratorio1.util.Utilidades;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginView"), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) throws IOException {
        launch();

        Entrenador entrenador1 = new Entrenador("Juan Pérez", null, null);
        Entrenador entrenador2 = new Entrenador("Ana Gómez", null, null);
        Entrenador entrenador3 = new Entrenador("Carlos Sánchez", null, null);
        Entrenador entrenador4 = new Entrenador("María Fernández", null, null);
        Entrenador entrenador5 = new Entrenador("Luis Martínez", null, null);
        Entrenador entrenador6 = new Entrenador("José Ramírez", null, null);
        Entrenador entrenador7 = new Entrenador("Laura Morales", null, null);
        Entrenador entrenador8 = new Entrenador("Pedro Gutiérrez", null, null);
        Entrenador entrenador9 = new Entrenador("Sofía González", null, null);
        Entrenador entrenador10 = new Entrenador("Miguel Torres", null, null);
        Entrenador entrenador11 = new Entrenador("Pepito", null, null);
    
        ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();
    
        listaEntrenadores.add(entrenador1);
        listaEntrenadores.add(entrenador2);
        listaEntrenadores.add(entrenador3);
        listaEntrenadores.add(entrenador4);
        listaEntrenadores.add(entrenador5);
        listaEntrenadores.add(entrenador6);
        listaEntrenadores.add(entrenador7);
        listaEntrenadores.add(entrenador8);
        listaEntrenadores.add(entrenador9);
        listaEntrenadores.add(entrenador10);
        listaEntrenadores.add(entrenador11);

        Utilidades.getInstance().escribirArchivos(listaEntrenadores, null);
    }

}