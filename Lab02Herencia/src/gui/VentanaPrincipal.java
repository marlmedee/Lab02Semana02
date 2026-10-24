
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
                    if (tipo.equals("Libro")){
                        nuevoMaterial = new Libro(cod,tit,anio,img,esp);
                    } 
                    else if (tipo.equals("Revista")){
                        nuevoMaterial = new Revista(cod,tit,anio,img,Integer.parseInt(esp));
                    } 
                    else if (tipo.equals("Tesis")){
                        nuevoMaterial = new Tesis(cod, tit, anio, img, esp);
                    
                    }
                    if (nuevoMaterial == null){
                    JOptionPane.showMessageDialog(null, "Error: El tipo de material no coincide. Revisa el ComboBox. Valor recibido: '" + tipo + "'");
                    return; 
}                   
                    if (biblioteca.registrarMaterial(nuevoMaterial)){
                        JOptionPane.showMessageDialog(null, "material registrado con exito!");
                        actualizarTabla();
                        txtCodigo.setText(""); txtTitulo.setText(""); txtAnio.setText(""); txtImagen.setText(""); txtDatoEspecifico.setText("");
                    } 
                    else{
                        JOptionPane.showMessageDialog(null, "error, codigo duplicado");
                    }
                } 
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "error numerico en anio o edicion");
                }
            }
        });
    
        panelRegistro.add(new JLabel("Tipo de material:")); panelRegistro.add(comboTipo);
        panelRegistro.add(new JLabel("Codigo unico:")); panelRegistro.add(txtCodigo);
        panelRegistro.add(new JLabel("Titulo:")); panelRegistro.add(txtTitulo);
        panelRegistro.add(new JLabel("Anio de publicacion:")); panelRegistro.add(txtAnio);
        panelRegistro.add(new JLabel("Nombre de imagen (opcional):")); panelRegistro.add(txtImagen);
        panelRegistro.add(lblDatoEspecifico); panelRegistro.add(txtDatoEspecifico);
        panelRegistro.add(new JLabel("")); panelRegistro.add(btnRegistrar);
        JPanel panelOperaciones = new JPanel(new GridLayout(4,1,10,10));
        panelOperaciones.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        JTextField txtBuscar = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar ");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MaterialBibliografico mat = biblioteca.buscarMaterialRecursivo(txtBuscar.getText().trim(), 0);
                if (mat!=null){
                    JOptionPane.showMessageDialog(null, "ENCONTRADO:\nTítulo: " + mat.getTitulo() + "\nTipo: " + mat.getTipo() + "\nDisponible: " + (mat.isDisponible() ? "Si" : "No"));
                } 
                else{
                    JOptionPane.showMessageDialog(null, "Material no encontrado");
                }
            }
        });
        panelBusqueda.add(new JLabel("Codigo:")); panelBusqueda.add(txtBuscar); panelBusqueda.add(btnBuscar);
        JPanel panelAcciones=new JPanel(new FlowLayout());
        JButton btnPrestar=new JButton("Realizar prestamo");
        JButton btnDevolver=new JButton("Procesar devolucion");
        btnPrestar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String cod=txtBuscar.getText().trim();
                Prestamo p=biblioteca.registrarPrestamo(cod);
                if (p != null){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    JOptionPane.showMessageDialog(null, "prestamo exitoso!\nDevolucion para: " + sdf.format(p.getFechaPrevista().getTime()));
                    actualizarTabla();
                } 
                else{
                    JOptionPane.showMessageDialog(null, "error, el material no existe o ya esta prestado");
                }
            }
        });

        btnDevolver.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String cod=txtBuscar.getText().trim();
                String resultado=biblioteca.procesarDevolucion(cod);
                JOptionPane.showMessageDialog(null,resultado);
                actualizarTabla();
            }
        });
        panelAcciones.add(btnPrestar); panelAcciones.add(btnDevolver);
        panelOperaciones.add(new JLabel("Operaciones de biblioteca (Ingrese codigo)"));
        panelOperaciones.add(panelBusqueda);
        panelOperaciones.add(panelAcciones);
        String[] columnas = {"Codigo", "Título", "Tipo", "Anio", "Disponible", "Especifico"};
        modelotabla=new DefaultTableModel(columnas, 0);
        tablaMateriales=new JTable(modelotabla);
        JScrollPane panelScroll = new JScrollPane(tablaMateriales);
        panelPestanias.addTab("1.Registro", panelRegistro);
        panelPestanias.addTab("2.Operaciones", panelOperaciones);
        panelPestanias.addTab("3.Inventario", panelScroll);
        add(panelPestanias);
    }
    private void actualizarTabla() {
        modelotabla.setRowCount(0);
        for (int i = 0; i < biblioteca.getInventario().size(); i++){
            
            MaterialBibliografico m = biblioteca.getInventario().get(i);
            Object[] fila = {
                m.getCodigo(), 
                m.getTitulo(), 
                m.getTipo(), 
                m.getAnioPublicacion(), 
                m.isDisponible() ? "Si" : "No",
                m.getDatoEspecifico()
            };
            modelotabla.addRow(fila);
        }
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
 
}
