package laboratorio1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import laboratorio1.dao.SerializarObjeto;

public class Entrenador implements Serializable {
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
        SerializarObjeto.serializarLista("entrenadores.txt", new ArrayList<>(entrenadores));
    }


    //metodo para agregar sesiones , verifica si existe o no y si no existe agrega la sesion
    public void agregarSesion(SesionEntrenamiento sesionEntrenamiento){
        boolean existe = false;
        for (SesionEntrenamiento sesion : sesiones) {
            if (sesion.equals(sesionEntrenamiento)) {
                existe = true;
                break;
                
            }
            
        }

        if (existe == false) {
            sesiones.add(sesionEntrenamiento);
            
        }
        
    }
    //metodo para remover sesiones, verifica que la lista sesiones no esta vacia y luego si la sesion existe, si esto pasa remueve la sesion de la lista de sesiones
    public void removerSesion(SesionEntrenamiento sesionEntrenamiento) {
        if (sesiones != null) {

            boolean existe = false;
            for (SesionEntrenamiento sesion : sesiones) {
                if (sesion.equals(sesionEntrenamiento)) {
                    existe = true;

                }

            }

            if (existe == true) {
                sesiones.remove(sesionEntrenamiento);

            }

        }

    }

}
