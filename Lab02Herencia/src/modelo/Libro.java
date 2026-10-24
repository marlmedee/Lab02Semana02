package modelo;

public class Libro extends MaterialBibliografico{
    private String autor;
    
    public Libro (String codigo, String titulo, int anioPublicacion, String rutaImagen, String autor){
       super(codigo,titulo,anioPublicacion,rutaImagen);
       this.autor=autor;
    }
    @Override
    public int getPlazoPrestamo() { 
        return 14; 
    } 
    
    @Override
    public double getMultaDiaria() { 
        return 1.50; 
    }
    
       
    @Override
    public String getTipo() { 
        return "Libro"; 
    }
    
    @Override
    public String getDatoEspecifico() {
        return "Autor: " + autor; 
    }
}
