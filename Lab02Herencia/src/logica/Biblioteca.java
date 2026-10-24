/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import modelo.*; //Importar las clases del paquete modelo
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



}
