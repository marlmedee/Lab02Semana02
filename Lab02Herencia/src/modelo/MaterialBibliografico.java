
package modelo;

import java.util.Calendar;
public class MaterialBibliografico {
    private String codigo;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;
    private String rutaImagen;

    public MaterialBibliografico(String codigo, String titulo, int anioPublicacion, String rutaImagen) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.rutaImagen = rutaImagen;
        this.disponible = true;
    }
    
    public int getPlazoPrestamo() { 
        return 0; 
    }
    public double getMultaDiaria() { 
        return 0.0; 
    
    }
    public String getTipo() { 
        return "General";
        
    }
    public String getDatoEspecifico() { 
        return "N/A"; 
    
    }
    
    public String getCodigo() { 
        return codigo; 
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
  
}
