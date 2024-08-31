package laboratorio1.model;

public  class Miembro {
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


}
