package laboratorio1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import laboratorio1.dao.SerializarObjeto;

public class SesionEntrenamiento implements Serializable {
    private LocalDate fecha;
    private int duracion;
    private EstadoSesion estado;
    private Deporte deporte;
    private Entrenador entrenador;
    private List<Miembro> miembros;

    public SesionEntrenamiento(LocalDate fecha, int duracion, EstadoSesion estado, Deporte deporte,
                               Entrenador entrenador) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.estado = estado;
        this.deporte = deporte;
        this.entrenador = entrenador;
        this.miembros = new LinkedList<>();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public EstadoSesion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSesion estado) {
        this.estado = estado;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    // Método para guardar todas las sesiones
    public void guardar(List<SesionEntrenamiento> sesiones) {
        // Serializar la lista completa de deportes
        SerializarObjeto.serializarLista(SerializarObjeto.rutaDao() + "sesiones.txt", new ArrayList<>(sesiones));
    }

    public void agregarMiembro(Miembro miembro) {
        if (miembros != null && miembro != null) {
            miembros.add(miembro);
        }
}
}


