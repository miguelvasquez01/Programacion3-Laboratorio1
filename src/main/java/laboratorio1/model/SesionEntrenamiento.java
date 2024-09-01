package laboratorio1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import laboratorio1.dao.SerializarObjeto;

public class SesionEntrenamiento implements Serializable {
    private LocalDate fecha;
    private int duracion;
    private EstadoSesion estado;
    private Deporte deporte;
    private Entrenador entrenador;
    private String idSesion;
    private List<Miembro> miembros;

    public SesionEntrenamiento(LocalDate fecha, int duracion, EstadoSesion estado, Deporte deporte,
                               Entrenador entrenador , String idSesion) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.estado = estado;
        this.deporte = deporte;
        this.entrenador = entrenador;
        this.idSesion = idSesion;
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

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }


    

    // Método para guardar todas las sesiones
    public void guardar(List<SesionEntrenamiento> sesiones) {
        // Deserializar las sesiones completadas previamente guardadas
        List<SesionEntrenamiento> sesionesCompletadasPrevias = SerializarObjeto.deserializarLista(SerializarObjeto.rutaDao() + "sesionesCompletadas.txt", SesionEntrenamiento.class);

        if (sesionesCompletadasPrevias == null) {
            sesionesCompletadasPrevias = new ArrayList<>();
        }

        // Usar streams para separar las sesiones no completadas y las completadas
        List<SesionEntrenamiento> sesionesNoCompletadas = sesiones.stream()
            .filter(sesion -> !sesion.isCompletada())
            .collect(Collectors.toList());

        List<SesionEntrenamiento> nuevasSesionesCompletadas = sesiones.stream()
            .filter(SesionEntrenamiento::isCompletada)
            .collect(Collectors.toList());

        // Agregar las nuevas sesiones completadas a la lista de sesiones completadas previas
        sesionesCompletadasPrevias.addAll(nuevasSesionesCompletadas);

        // Serializar las listas en archivos separados
        SerializarObjeto.serializarLista(SerializarObjeto.rutaDao() + "sesionesProgramadas.txt", sesionesNoCompletadas);
        SerializarObjeto.serializarLista(SerializarObjeto.rutaDao() + "sesionesCompletadas.txt", sesionesCompletadasPrevias);
    }


    public void agregarMiembro(Miembro miembro) {
        if (miembros != null && miembro != null) {
            miembros.add(miembro);
        }
    }

    public boolean isCompletada() {
        if (this.estado.equals(EstadoSesion.COMPLETADA)) {
            return true;
        }
        return false;
    }

    public void cambiarEstado() {
        if(fecha.isAfter(LocalDate.now())) {
            setEstado(EstadoSesion.COMPLETADA);
        }
    }

    
}


