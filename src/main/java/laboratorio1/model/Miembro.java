package laboratorio1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import laboratorio1.dao.SerializarObjeto;

public  class Miembro implements Serializable {
    private  int edad;
    private String nombre;
    private String email;
    private String id;


    public Miembro(String nombre, String email, String id, int edad) {
        this.nombre = nombre;
        this.email = email;
        this.id = id;
        this.edad = edad;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Método para guardar todos los deportes
    public void guardar(List<Miembro> miembros) {

        // Serializar la lista completa de deportes
        SerializarObjeto.serializarLista(SerializarObjeto.rutaDao() + "miembros.txt", new ArrayList<>(miembros));
    }
}
