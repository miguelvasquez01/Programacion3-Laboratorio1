package laboratorio1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import laboratorio1.dao.SerializarObjeto;

public class Entrenador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private Deporte especialidad;
    private List<SesionEntrenamiento> sesiones;

    private SesionEntrenamiento sesion;

    //constructor
    public Entrenador(String nombre, Deporte especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.sesiones = new LinkedList<>();
    }

    public Entrenador(String nombre, Deporte especialidad, SesionEntrenamiento s) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.sesion = s;
    }

    public Entrenador() {
        //Constructor vacío para serializar XML
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Deporte getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Deporte especialidad) {
        this.especialidad = especialidad;
    }

    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionEntrenamiento> sesiones) {
        this.sesiones = sesiones;
    }

    public SesionEntrenamiento getSesion() {
        return sesion;
    }

    public void setSesion(SesionEntrenamiento sesion) {
        this.sesion = sesion;
    }

    // Método para guardar todos los entrenadores
    public void guardar(List<Entrenador> entrenadores) {
        // Serializar la lista completa de deportes
        SerializarObjeto.serializarLista(SerializarObjeto.rutaDao()+"entrenadores.txt", new ArrayList<>(entrenadores));
    }

    @Override
    public String toString() {
        return "Entrenador{" +
               "Nombre='" + nombre + '\'' +
               ", Especialidad=" + (especialidad != null ? especialidad.getNombre() : "N/A") +
               ", Sesion=" + (sesion != null ? sesion.getFecha() : "N/A") +
               '}';
    }
}
