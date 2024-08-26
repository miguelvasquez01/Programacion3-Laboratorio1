package laboratorio1.model;

import java.util.LinkedList;
import java.util.List;

public class Deporte {
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
}
