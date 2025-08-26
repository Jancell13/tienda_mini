package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.MetodoPago;
import modelo.MetodoPagoDao;
import modelo.Producto;
import modelo.ProductoDao;
import vista.Vista;
import vista.VistaRegistrar;

public class Controlador implements ActionListener, MouseListener {
    public ProductoDao pdao = new ProductoDao();
    public MetodoPagoDao mtdao = new MetodoPagoDao();
    public Producto p = new Producto();
    public Vista view = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();
    Boolean flag = false;

    public Controlador(Vista v) {
        this.view = v;
        this.view.pagar.addActionListener(this);
        this.view.registrar.addActionListener(this);
        getListarProductos(view.tablaProducto);
        getMetodosPago();
        view.tablaProducto.addMouseListener(this);
        view.tablaProducto.setDefaultEditor(Object.class, null);

    }

    @Override
public void mouseClicked(MouseEvent me) {
    if (me.getClickCount() == 2) {
        int fila = view.tablaProducto.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "error en algo");
        } else {
            JOptionPane.showMessageDialog(view, "Funciona");
            String nombre = view.tablaProducto.getValueAt(fila, 0).toString();
            String precio = view.tablaProducto.getValueAt(fila, 1).toString();
            String cantidad = view.tablaProducto.getValueAt(fila, 2).toString();
            
            view.tabla.setValueAt(nombre,1,0);
            view.tabla.setValueAt(precio,1,1);
            view.tabla.setValueAt(cantidad,1,2);
        }
    }
}
    @Override
    public void actionPerformed(ActionEvent e) {



        /*
         * if (e.getSource() == view.limpiar) {
         * limpiarCampos();
         * }
         */
        /*
         * if (e.getSource() == view.enviar) {
         * view.txtId.setEditable(true);
         * flag = false;
         * view.enviar.setEnabled(flag);
         * updateUser(Integer.parseInt(view.txtId.getText()));
         * limpiarCampos();
         * limpiarTabla();
         * getListar(view.tabla);
         * }
         */
        /*
         * if (e.getSource() == view.actualizar) {
         * flag = true;
         * view.enviar.setEnabled(flag);
         * int fila = view.tabla.getSelectedRow();
         * 
         * if (fila == -1 && view.txtId.getText().isEmpty()) {
         * JOptionPane.showMessageDialog(view,
         * "debe seleccionar una fila o diligenciar todos los campos");
         * } else {
         * String id = view.tabla.getValueAt(fila, 0).toString();
         * String nombre = view.tabla.getValueAt(fila, 1).toString();
         * String apellido = view.tabla.getValueAt(fila, 2).toString();
         * String salario = view.tabla.getValueAt(fila, 3).toString();
         * String profesion = view.tabla.getValueAt(fila, 4).toString();
         * String entidad = view.tabla.getValueAt(fila, 5).toString();
         * view.txtId.setText(id);
         * view.txtId.setEditable(false);
         * view.txtNombre.setText(nombre);
         * view.txtApellido.setText(apellido);
         * view.txtSalario.setText(salario);
         * view.txtProfesion.setText(profesion);
         * view.listaE.setSelectedItem(entidad);
         * }
         * 
         * }
         */
        /*
         * if (e.getSource() == view.eliminar) {
         * int fila = view.tabla.getSelectedRow();
         * if (!view.txtId.getText().isEmpty()) {
         * int id = Integer.parseInt(view.txtId.getText());
         * deleteUser(id);
         * } else if (fila != -1) {
         * int id = Integer.parseInt(view.tabla.getValueAt(fila, 0).toString());
         * deleteUser(id);
         * } else {
         * JOptionPane.showMessageDialog(view,
         * "falta el id o debe seleccionar la fila que quiere eliminar");
         * }
         * limpiarCampos();
         * limpiarTabla();
         * getListar(view.tabla);
         * }
         */
        /*
         * if (e.getSource() == view.registrar) {
         * if (!view.txtId.getText().isEmpty() && !view.txtNombre.getText().isEmpty()
         * && !view.txtPrecio.getText().isEmpty() &&
         * !view.txtCantidad.getText().isEmpty()
         * && !view.txtId.getText().isEmpty() &&
         * !view.listaE.getSelectedItem().equals("Seleccionar")) {
         * 
         * setAdd();
         * } else {
         * JOptionPane.showMessageDialog(view, "Faltan campos por ingresar");
         * }
         * limpiarCampos();
         * limpiarTabla();
         * getListar(view.tabla);
         * }
         */

        if (e.getSource() == view.registrar) {

            VistaRegistrar viewR = new VistaRegistrar();
            viewR.setSize(520, 720);
            viewR.setVisible(true);
            viewR.setLocationRelativeTo(null);

        }

    }

    public void getListarProductos(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Producto> lista = pdao.listar();
        Object[] object = new Object[4];
        for (int indice = 0; indice < lista.size(); indice++) {
            object[0] = lista.get(indice).getId();
            object[1] = lista.get(indice).getNombre();
            object[2] = lista.get(indice).getPrecio();
            object[3] = lista.get(indice).getCantidad();
            modelo.addRow(object);
        }
        view.tablaProducto.setModel(modelo);
    }

    public void getMetodosPago() {
        List<MetodoPago> mp = mtdao.listar();
        for (int i = 0; i < mp.size(); i++) {
            view.modoDePago.addItem(mp.get(i).getNombre());
        }
    }

    /*
     * public void deleteUser(int id) {
     * int resultado = pdao.setEliminar(id);
     * if (resultado == 1) {
     * JOptionPane.showMessageDialog(view, "Se elimino correctamente");
     * } else {
     * JOptionPane.showMessageDialog(null, "Error de Eliminacion" +
     * JOptionPane.ERROR_MESSAGE);
     * }
     * }
     */

    /*
     * public void setAdd() {
     * int resultado;
     * int id = Integer.parseInt(view.txtId.getText());
     * String nombre = view.txtNombre.getText();
     * String apellido = view.txtApellido.getText();
     * Double salario = Double.parseDouble(view.txtSalario.getText());
     * String profesion = view.txtProfesion.getText();
     * String entidad = view.listaE.getSelectedItem().toString();
     * 
     * t.setId(id);
     * t.setNombre(nombre);
     * t.setApellido(apellido);
     * t.setSalario(salario);
     * t.setProfesion(profesion);
     * t.setEntidad(entidad);
     * resultado = dao.setAgregar(t);
     * System.out.println(entidad);
     * if (resultado == 1) {
     * JOptionPane.showMessageDialog(view,
     * "se inserto la informacion de forma correcta");
     * } else {
     * JOptionPane.showMessageDialog(null, "Error de insercion" +
     * JOptionPane.ERROR_MESSAGE);
     * }
     * }
     * 
     * public void updateUser(int id) {
     * int resultado;
     * String nombre = view.txtNombre.getText();
     * String apellido = view.txtApellido.getText();
     * Double salario = Double.parseDouble(view.txtSalario.getText());
     * String profesion = view.txtProfesion.getText();
     * String entidad = view.listaE.getSelectedItem().toString();
     * 
     * t.setId(id);
     * t.setNombre(nombre);
     * t.setApellido(apellido);
     * t.setSalario(salario);
     * t.setProfesion(profesion);
     * t.setEntidad(entidad);
     * 
     * resultado = dao.setActualizar(t);
     * 
     * if (resultado == 1) {
     * JOptionPane.showMessageDialog(view,
     * "se actualizo la informacion de forma correcta");
     * } else {
     * JOptionPane.showMessageDialog(null, "Error de actualizacion" +
     * JOptionPane.ERROR_MESSAGE);
     * }
     * }
     */
    /*
     * public void limpiarCampos() {
     * view.txtId.setText("");
     * view.txtNombre.setText("");
     * view.txtPrecio.setText("");
     * view.txtCantidad.setText("");
     * view.listaE.setSelectedIndex(0);
     * }
     */
    public void limpiarTabla() {
        for (int i = 0; i < view.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
