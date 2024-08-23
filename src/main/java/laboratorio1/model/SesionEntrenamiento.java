package laboratorio1.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SesionEntrenamiento {
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


    //metodo para inscribir un miembro si cumple con la condicion dependiendo del deporte

    public void inscribirMiembro(Miembro miembro) {

        if (miembro != null && deporte != null) {

            if (miembro.PuedeInscribirse(deporte) == true) {
                miembros.add(miembro);

            }
        }

    }
    //metodo para cancelar la inscripcion de un miembro a una sesion de entrenamiento
    public void CancelarInscripcion(Miembro miembro){
        boolean existe = false;
        for(Miembro miembro1 : miembros){
            if (miembro.equals(miembro1)) {
                existe = true;
                
            }
        }

        if (existe == true) {
            miembros.remove(miembro);
            
        }
    }
}
