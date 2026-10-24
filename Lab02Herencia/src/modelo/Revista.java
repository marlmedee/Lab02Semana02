
package modelo;


public class Revista extends MaterialBibliografico {
    private int edicion;
    
    public Revista(String codigo, String titulo, int anioPublicacion, String rutaImagen, int edicion){
       super(codigo, titulo, anioPublicacion, rutaImagen);
       this.edicion=edicion;
    }
    
      @Override
    public int getPlazoPrestamo() { 
        return 7; 
    }
    
    @Override
    public double getMultaDiaria() { 
        return 0.75; 
    }

    @Override
    public String getTipo() { 
        return "Revista"; 
    }
    
    @Override
    public String getDatoEspecifico() { 
        return "Edición: " + edicion; 
    }
    
}
