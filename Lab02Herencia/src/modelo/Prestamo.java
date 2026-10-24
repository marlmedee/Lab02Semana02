
package modelo;

import java.util.Calendar;
public class Prestamo {
     private MaterialBibliografico material;
     private Calendar fechaPrestamo;
     private Calendar fechaPrevista;
     private boolean activo;
     
     public Prestamo(MaterialBibliografico material, Calendar fechaPrestamo, Calendar fechaPrevista) {
        this.material = material;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevista = fechaPrevista;
        this.activo = true;
    }

    public MaterialBibliografico getMaterial() { 
        return material; 
    }
    public Calendar getFechaPrestamo() { 
        return fechaPrestamo; 
    }
    public Calendar getFechaPrevista() { 
        return fechaPrevista; 
    }
    public boolean isActivo() { 
        return activo; 
    }
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }
 
}
