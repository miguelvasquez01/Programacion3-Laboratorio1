package laboratorio1.util;

import java.util.ResourceBundle;
import java.util.Locale;

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
}
