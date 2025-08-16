package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.ProductoDao;
import vista.Vista;

public class Controlador implements ActionListener {
    public ProductoDao pdao = new ProductoDao();
    public Producto p = new Producto();
    public Vista view = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();
    Boolean flag = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.buscar) {
            limpiarTabla();
            getListar(view.tabla);
            JOptionPane.showMessageDialog(view, "esta funcionando el boton");
        }
        /* if (e.getSource() == view.limpiar) {
            limpiarCampos();
        } */
       /*  if (e.getSource() == view.enviar) {
            view.txtId.setEditable(true);
            flag = false;
            view.enviar.setEnabled(flag);
            updateUser(Integer.parseInt(view.txtId.getText()));
            limpiarCampos();
            limpiarTabla();
            getListar(view.tabla);
        } */
        /* if (e.getSource() == view.actualizar) {
            flag = true;
            view.enviar.setEnabled(flag);
            int fila = view.tabla.getSelectedRow();

            if (fila == -1 && view.txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "debe seleccionar una fila o diligenciar todos los campos");
            } else {
                String id = view.tabla.getValueAt(fila, 0).toString();
                String nombre = view.tabla.getValueAt(fila, 1).toString();
                String apellido = view.tabla.getValueAt(fila, 2).toString();
                String salario = view.tabla.getValueAt(fila, 3).toString();
                String profesion = view.tabla.getValueAt(fila, 4).toString();
                String entidad = view.tabla.getValueAt(fila, 5).toString();
                view.txtId.setText(id);
                view.txtId.setEditable(false);
                view.txtNombre.setText(nombre);
                view.txtApellido.setText(apellido);
                view.txtSalario.setText(salario);
                view.txtProfesion.setText(profesion);
                view.listaE.setSelectedItem(entidad);
            }

        } */
        /* if (e.getSource() == view.eliminar) {
            int fila = view.tabla.getSelectedRow();
            if (!view.txtId.getText().isEmpty()) {
                int id = Integer.parseInt(view.txtId.getText());
                deleteUser(id);
            } else if (fila != -1) {
                int id = Integer.parseInt(view.tabla.getValueAt(fila, 0).toString());
                deleteUser(id);
            } else {
                JOptionPane.showMessageDialog(view, "falta el id o debe seleccionar la fila que quiere eliminar");
            }
            limpiarCampos();
            limpiarTabla();
            getListar(view.tabla);
        } */
        /* if (e.getSource() == view.registrar) {
            if (!view.txtId.getText().isEmpty() && !view.txtNombre.getText().isEmpty()
                    && !view.txtPrecio.getText().isEmpty() && !view.txtCantidad.getText().isEmpty()
                    && !view.txtId.getText().isEmpty() && !view.listaE.getSelectedItem().equals("Seleccionar")) {

                setAdd();
            } else {
                JOptionPane.showMessageDialog(view, "Faltan campos por ingresar");
            }
            limpiarCampos();
            limpiarTabla();
            getListar(view.tabla);
        } */

    }

    public void getListar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Producto> lista = pdao.listar();
        Object[] object = new Object[6];

        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getPrecio();
            object[3] = lista.get(i).getCantidad();
            modelo.addRow(object);
        }
        view.tabla.setModel(modelo);
    }

   /*  public void deleteUser(int id) {
        int resultado = pdao.setEliminar(id);
        if (resultado == 1) {
            JOptionPane.showMessageDialog(view, "Se elimino correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error de Eliminacion" + JOptionPane.ERROR_MESSAGE);
        }
    } */

    /* public void setAdd() {
        int resultado;
        int id = Integer.parseInt(view.txtId.getText());
        String nombre = view.txtNombre.getText();
        String apellido = view.txtApellido.getText();
        Double salario = Double.parseDouble(view.txtSalario.getText());
        String profesion = view.txtProfesion.getText();
        String entidad = view.listaE.getSelectedItem().toString();

        t.setId(id);
        t.setNombre(nombre);
        t.setApellido(apellido);
        t.setSalario(salario);
        t.setProfesion(profesion);
        t.setEntidad(entidad);
        resultado = dao.setAgregar(t);
        System.out.println(entidad);
        if (resultado == 1) {
            JOptionPane.showMessageDialog(view, "se inserto la informacion de forma correcta");
        } else {
            JOptionPane.showMessageDialog(null, "Error de insercion" + JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateUser(int id) {
        int resultado;
        String nombre = view.txtNombre.getText();
        String apellido = view.txtApellido.getText();
        Double salario = Double.parseDouble(view.txtSalario.getText());
        String profesion = view.txtProfesion.getText();
        String entidad = view.listaE.getSelectedItem().toString();

        t.setId(id);
        t.setNombre(nombre);
        t.setApellido(apellido);
        t.setSalario(salario);
        t.setProfesion(profesion);
        t.setEntidad(entidad);

        resultado = dao.setActualizar(t);

        if (resultado == 1) {
            JOptionPane.showMessageDialog(view, "se actualizo la informacion de forma correcta");
        } else {
            JOptionPane.showMessageDialog(null, "Error de actualizacion" + JOptionPane.ERROR_MESSAGE);
        }
    }
 */
    public void limpiarCampos() {
        view.txtId.setText("");
        view.txtNombre.setText("");
        view.txtPrecio.setText("");
        view.txtCantidad.setText("");
        view.listaE.setSelectedIndex(0);
    }

    public void limpiarTabla() {
        for (int i = 0; i < view.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
