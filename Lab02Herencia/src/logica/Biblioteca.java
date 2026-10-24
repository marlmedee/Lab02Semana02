/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import modelo.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Biblioteca {

    private List<MaterialBibliografico> inventario;
    private List<Prestamo> prestamosActivos;

    public Biblioteca() {
        this.inventario = new ArrayList<>();
        this.prestamosActivos = new ArrayList<>();
    }

    public MaterialBibliografico buscarMaterialRecursivo(String codigo, int indice) {
        if (indice >= inventario.size()) {
            return null;
        }
        if (inventario.get(indice).getCodigo().equals(codigo)) {
            return inventario.get(indice);
        }
        return buscarMaterialRecursivo(codigo, indice + 1);
    }

    public boolean registrarMaterial(MaterialBibliografico material) {
        if (buscarMaterialRecursivo(material.getCodigo(), 0) != null) {
            return false;
        }
        inventario.add(material);
        return true;
    }

}
