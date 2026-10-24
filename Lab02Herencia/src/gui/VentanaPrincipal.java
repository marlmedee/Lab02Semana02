
package gui;
import modelo.*;
import logica.Biblioteca;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class VentanaPrincipal extends JFrame{
    private Biblioteca biblioteca;
    private DefaultTableModel modelotabla;
    private JTable tablaMateriales;
    
    public VentanaPrincipal(){
        biblioteca = new Biblioteca();
        setTitle("-- Sistema de Gestion de Biblioteca --");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane panelPestanias = new JTabbedPane();
        JPanel panelRegistro = new JPanel(new GridLayout(8, 2, 10, 10));
        panelRegistro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Libro","Revista","Tesis"});
        JTextField txtCodigo = new JTextField();
        JTextField txtTitulo = new JTextField();
        JTextField txtAnio = new JTextField();
        JTextField txtImagen = new JTextField();
        JTextField txtDatoEspecifico = new JTextField();
        JLabel lblDatoEspecifico = new JLabel("Autor: ");
        comboTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccionado=(String)comboTipo.getSelectedItem();
                if (seleccionado.equals("Libro")){
                   lblDatoEspecifico.setText("Autor:");
                }
                else if (seleccionado.equals("Revista")){
                   lblDatoEspecifico.setText("Edicion(Numero):");
                }
                else if (seleccionado.equals("Tesis")){
                   lblDatoEspecifico.setText("Tesista: ");
                }
            }
        });
    JButton btnRegistrar=new JButton("Registrar Material");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String cod=txtCodigo.getText().trim();
                    String tit=txtTitulo.getText().trim();
                    String anioStr=txtAnio.getText().trim();
                    String img=txtImagen.getText().trim();
                    String esp=txtDatoEspecifico.getText().trim();
                    int anio=Integer.parseInt(anioStr);
                    int anioActual=Calendar.getInstance().get(Calendar.YEAR);
                    if (cod.isEmpty()||tit.isEmpty()||anioStr.isEmpty()||esp.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "no se permiten campos vacios.");
                        return;
                    }
                    if (anio>anioActual ||anio< 1000) {
                        JOptionPane.showMessageDialog(null, "anio invalido");
                        return;
                    }
                    MaterialBibliografico nuevoMaterial = null;
                    String tipo=(String)comboTipo.getSelectedItem();
                    if (tipo.equals("libro")){
                        nuevoMaterial = new Libro(cod,tit,anio,img,esp);
                    } 
                    else if (tipo.equals("revista")){
                        nuevoMaterial = new Revista(cod,tit,anio,img,Integer.parseInt(esp));
                    } 
                    else if (tipo.equals("tesis")){
                        nuevoMaterial = new Tesis(cod, tit, anio, img, esp);
                    }
                    if (biblioteca.registrarMaterial(nuevoMaterial)){
                        JOptionPane.showMessageDialog(null, "material registrado con exito!");
                        actualizarTabla();
                        txtCodigo.setText(""); txtTitulo.setText(""); txtAnio.setText(""); txtImagen.setText(""); txtDatoEspecifico.setText("");
                    } 
                    else{
                        JOptionPane.showMessageDialog(null, "error, codigo duplicado.");
                    }
                } 
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "error numerico en anio o edicion.");
                }
            }
        });
    }
    private void actualizarTabla() {

        for (int i = 0; i < biblioteca.getInventario().size(); i++) {
            MaterialBibliografico m = biblioteca.getInventario().get(i);
            Object[] fila = {
                m.getCodigo(), 
                m.getTitulo(), 
                m.getTipo(), 
                m.getAnioPublicacion(), 
                m.isDisponible() ? "Si" : "No",
                m.getDatoEspecifico()
            };
            
        }
    }
    public static void main(String args[]){
        
    }
 
}
