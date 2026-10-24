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

    public List<MaterialBibliografico> getInventario() {
        return inventario;
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

    public Prestamo registrarPrestamo(String codigo) {
        MaterialBibliografico material = buscarMaterialRecursivo(codigo, 0);

        if (material != null && material.isDisponible()) {
            Calendar fechaActual = Calendar.getInstance();
            Calendar fechaDevolucion = (Calendar) fechaActual.clone();

            fechaDevolucion.add(Calendar.DAY_OF_YEAR, material.getPlazoPrestamo());

            Prestamo nuevoPrestamo = new Prestamo(material, fechaActual, fechaDevolucion);
            prestamosActivos.add(nuevoPrestamo);
            material.setDisponible(false);

            return nuevoPrestamo;
        }
        return null;
    }

    public String procesarDevolucion(String codigo) {
        Prestamo prestamoEncontrado = null;
        for (int i = 0; i < prestamosActivos.size(); i++) {
            if (prestamosActivos.get(i).getMaterial().getCodigo().equals(codigo) && prestamosActivos.get(i).isActivo()) {
                prestamoEncontrado = prestamosActivos.get(i);
                break;
            }
        }

        if (prestamoEncontrado == null) {
            return "Error: No hay préstamo activo para ese código.";
        }

        Calendar fechaDevolucionActual = Calendar.getInstance();
        Calendar fechaPrevista = prestamoEncontrado.getFechaPrevista();

        long diasRetraso = 0;
        double multa = 0.0;

        if (fechaDevolucionActual.after(fechaPrevista)) {
            long milisegundosDiferencia = fechaDevolucionActual.getTimeInMillis() - fechaPrevista.getTimeInMillis();
            diasRetraso = milisegundosDiferencia / (1000 * 60 * 60 * 24);
            multa = diasRetraso * prestamoEncontrado.getMaterial().getMultaDiaria();
        }

        prestamoEncontrado.setActivo(false);
        prestamoEncontrado.getMaterial().setDisponible(true);
        prestamosActivos.remove(prestamoEncontrado);

        return "Devolución exitosa.\nDías de retraso: " + diasRetraso + "\nMulta total: " + multa + " €";
    }



}
