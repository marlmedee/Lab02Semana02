
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
    }
    public static void main(String args[]){
        
    }
}
