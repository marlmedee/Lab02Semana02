
package modelo;


public class Tesis extends MaterialBibliografico {
    private String tesista;
    
    public Tesis (String codigo, String titulo, int anioPublicacion, String rutaImagen, String tesista){
      super(codigo,titulo,anioPublicacion,rutaImagen);
      this.tesista=tesista;
    
    }
    
    @Override
    public int getPlazoPrestamo() { 
        return 3; 
    }
    
    @Override
    public double getMultaDiaria() { 
        return 3.00; 
    
    }

    @Override
    public String getTipo() { 
        return "Tesis"; 
    
    }
    
    @Override
    public String getDatoEspecifico() { 
        return "Tesista: " + tesista; 
    }

    
}
