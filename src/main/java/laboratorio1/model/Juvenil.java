package laboratorio1.model;

public class Juvenil extends Miembro {

    public Juvenil(String nombre, String email, String id) {
        super(nombre, email, id);

    }
//metodo para verificar que un juvenil solo se pueda inscribir a deportes con nivel de dificultad bajo o medio
    @Override
    public boolean PuedeInscribirse(Deporte deporte) {
        if (deporte.getNivelDificultad() == NivelDificultad.BAJO
                || deporte.getNivelDificultad() == NivelDificultad.MEDIO) {
                    return true;

        }

        return false;

    }

}
