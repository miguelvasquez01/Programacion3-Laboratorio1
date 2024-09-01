package laboratorio1.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Administrador {
    private String nombre;
    private String id;
    private List<SesionEntrenamiento> sesionesProgramadas;

    public Administrador(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.sesionesProgramadas = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SesionEntrenamiento> getSesionesProgramadas() {
        return sesionesProgramadas;
    }

    public void setSesionesProgramadas(List<SesionEntrenamiento> sesionesProgramadas) {
        this.sesionesProgramadas = sesionesProgramadas;
    }

    // metodo para programar sesiones
    public void ProgramarSesion(LocalDate fecha, int duracion, Deporte deporte, Entrenador entrenador , String idSesion)
            throws Exception {

        // Verifica que los parametros no sean invalidos o nulos
        if (fecha == null || duracion < 0 || deporte == null || entrenador == null ) {
            throw new Exception("Los parametros son invalidos");

        }

        // crea una sesion con los parametros suministrados
        SesionEntrenamiento sesion = new SesionEntrenamiento(fecha, duracion, EstadoSesion.PROGRAMADA, deporte,
                entrenador, idSesion);

        // comprobar si se desea programar una sesion ya programada
        for (SesionEntrenamiento s : sesionesProgramadas) {
            if (s.getEntrenador().equals(entrenador) && s.getDeporte().equals(deporte)
                    && s.getDuracion() == duracion && s.getFecha().equals(fecha)) {
                throw new Exception("No se puede programar una sesion ya programada ");

            }
        }

        // si no es una sesion ya programada se añade la sesion a la lista de sesiones
        // programadas

        sesionesProgramadas.add(sesion);

    }

    // metodo para cancelar sesiones
    public void CancelarSesion(SesionEntrenamiento sesionAcancelar) throws Exception {
        // comprueba que la sesion que se desea cancelar no este ya completada
        if (sesionAcancelar.getEstado().equals(EstadoSesion.COMPLETADA)) {
            throw new Exception("No se puede cancelar una sesion ya completada");

        }

        // comprueba que la sesion este en la lista de sesiones programadas, y si esta
        // la remueve de esta misma
        for (SesionEntrenamiento sesionEntrenamiento : sesionesProgramadas) {
            if (sesionEntrenamiento.equals(sesionAcancelar)) {
                sesionesProgramadas.remove(sesionAcancelar);
                break;

            }

        }

    }
}