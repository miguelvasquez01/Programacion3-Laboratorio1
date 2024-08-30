package laboratorio1.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SerializarObjeto {

    public static String rutaDao() {
        String rutaProyecto = System.getProperty("user.dir");
        String ruta = rutaProyecto + "/src/main/java/laboratorio1/dao/";
        return ruta;
    }
    
    public static boolean serializarObjeto(String direccionArchivo, Serializable objeto) {

        boolean sw = false;
        try (FileOutputStream fos = new FileOutputStream(direccionArchivo);
            ObjectOutputStream salida = new ObjectOutputStream(fos);) {
            salida.writeObject(objeto);
            sw = true;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return sw;
    }

    @SuppressWarnings("unchecked")
    public static <E> E deserializarObjeto(String direccionArchivo, Class<E> claseObjetivo) {
        E objeto = null;
        try (FileInputStream fis = new FileInputStream(direccionArchivo);
            ObjectInputStream entrada = new ObjectInputStream(fis);) {
            objeto = (E) entrada.readObject();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objeto;
    }

    // Método para serializar una lista de objetos
    public static <E extends Serializable> boolean serializarLista(String direccionArchivo, List<E> listaObjetos) {
        return serializarObjeto(direccionArchivo, (Serializable) listaObjetos);
    }

    // Método para deserializar una lista de objetos
    @SuppressWarnings("unchecked")
    public static <E> List<E> deserializarLista(String direccionArchivo, Class<E> claseObjetivo) {
        List<E> listaObjetos = null;
        try {
            listaObjetos = (List<E>) deserializarObjeto(direccionArchivo, List.class);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return listaObjetos;
    }
}
