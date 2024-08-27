package laboratorio1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import laboratorio1.dao.SerializarObjeto;

public class Deporte implements Serializable {
    private String nombre;
    private String descripcion;
    private NivelDificultad nivelDificultad;
    private List<Entrenador> entrenadores;
    private Entrenador entrenador;

    public Deporte(String nombre, String descripcion, NivelDificultad nivelDificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelDificultad = nivelDificultad;
        this.entrenadores = new LinkedList<>();
    }

    public Deporte(String nombre, String descripcion, NivelDificultad nivelDificultad, Entrenador entrenador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelDificultad = nivelDificultad;
        this.entrenador = entrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNivelDificultad(NivelDificultad nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public void setEntrenadores(List<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public NivelDificultad getNivelDificultad() {
        return nivelDificultad;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    // Método para guardar todos los deportes
    public void guardar(List<Deporte> deportes) {

        // Serializar la lista completa de deportes
        SerializarObjeto.serializarLista(SerializarObjeto.rutaDao() + "deportes.txt", new ArrayList<>(deportes));
    }
}
