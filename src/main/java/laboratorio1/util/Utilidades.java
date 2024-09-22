package laboratorio1.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import laboratorio1.model.Entrenador;
import laboratorio1.model.Miembro;

public class Utilidades {

    private static Utilidades utilidades;

    private ResourceBundle r;
    private static String idioma;
    private static String pais;

    // Constructor privado para el patrón Singleton
    private Utilidades() {
        setLocale("es", "ES");  // Valor por defecto
    }
    
    public static Utilidades getInstance() {
        if (utilidades == null) {
            utilidades = new Utilidades();
        }
        return utilidades;
    }

    @SuppressWarnings({ "static-access", "deprecation" })
    public void setLocale(String idioma, String pais) {

        this.idioma = idioma;
        this.pais = pais;
        Locale locale = new Locale(idioma, pais);
        r = ResourceBundle.getBundle("MiRecurso", locale); //Aquí instancio el ResourceBundle
    }

    // Método para obtener el texto desde el archivo .properties
    public String getString(String key) {
        return r.getString(key);
    }

    public ResourceBundle getR() {
        return r;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getPais() {
        return pais;
    }
    //-----------------------------------------------------------------------------------------------
    //Logger

    private static final Logger LOGGER = Logger.getLogger(Utilidades.class.getName());

    static {//static se ejecuta cuando la clase Utilidades sea cargada
        try {
            // Inicializar el FileHandler
            FileHandler archivo = new FileHandler("src/main/java/laboratorio1/util/LOG-personalizado.txt", true);
            archivo.setFormatter(new SimpleFormatter());

            // Añadir el manejador al LOGGER
            LOGGER.addHandler(archivo);

            LOGGER.log(Level.INFO, "LOG de info");
            LOGGER.log(Level.CONFIG, "Mensaje  de configuración");
            LOGGER.log(Level.WARNING, "Mensaje de warning");
            LOGGER.log(Level.SEVERE, "Mensaje severo");
            LOGGER.log(Level.FINE, "Info de alto nivel");
            LOGGER.log(Level.FINER, "Info con más detaller");
            LOGGER.log(Level.FINEST, "Info con máximo nivel de detalle");

        } catch (IOException e) {
            LOGGER.severe("Error al inicializar el FileHandler: " + e.getMessage());
        }
    }
    //-------------------------------------------------------------------------------------------------
    //Manipulación de archivos

    public void escribirArchivos(ArrayList<Entrenador> listaEntrenadores, ArrayList<Miembro> listaMiembros) throws IOException {

        String ruta = null;

        //Tambien verifica si el directorio existe
        ruta = crearDirectorio("C://Reportes_Java");
    
        //Ahora que tenemos la ruta, podemos escribir en el archivo
        try {
            FileWriter archivoSalida = new FileWriter(ruta + "/reporte.txt", true);
            BufferedWriter bufferSalida = new BufferedWriter(archivoSalida);

            int contador = 0; // Contador para llevar la cuenta de los elementos

            for(Entrenador entrenador: listaEntrenadores) {
                if(contador % 10 == 0) {//Escribe cada 10 elementos
                    String linea = entrenador.toString();
                    bufferSalida.write(linea + "\n");
                }
                contador += 1;
            }
            System.out.println("Cerrando buffer");

            bufferSalida.close();
            archivoSalida.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
        }
    }

    public String crearDirectorio(String rutaDirectorio) {
        File nuevoDirectorio = new File(rutaDirectorio);
    
        if (nuevoDirectorio.exists()) {//Tambien se puede hacer un for para verificar si existe
            System.out.println("Escribiendo en: " + rutaDirectorio);
            return nuevoDirectorio.getAbsolutePath();
        }
    
        if (nuevoDirectorio.mkdir()) {
            System.out.println("Directorio " + rutaDirectorio + " creado.");
            return nuevoDirectorio.getAbsolutePath();
        } else {
            System.out.println("No se pudo crear el directorio: " + rutaDirectorio);
            return ""; // Salimos del método si no se pudo crear
        }
    }    
}
