package laboratorio1.model;

public abstract class Miembro {
    private String nombre;
    private String email;
    private String id;

    public Miembro(String nombre, String email, String id) {
        this.nombre = nombre;
        this.email = email;
        this.id = id;
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

    // metodo abstracto que nos ayudara a comprobrar si un miembro puede inscribirse o no dependiendo si es juvenil o adulto
    public abstract boolean PuedeInscribirse(Deporte deporte);

}
