package controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import modelo.Producto;
import modelo.ProductoDao;
import vista.VistaRegistrar;

public class ControladorRegistrarProduc implements ActionListener, MouseListener {

    public ProductoDao pdao = new ProductoDao();
    public Producto p = new Producto();
    public VistaRegistrar viewR = new VistaRegistrar();
    DefaultTableModel modelo = new DefaultTableModel();
    boolean flag = false;

    public ControladorRegistrarProduc(VistaRegistrar v) {
        this.viewR = v;
        this.viewR.buscar.addActionListener(this);
        this.viewR.eliminar.addActionListener(this);
        this.viewR.actualizar.addActionListener(this);
        this.viewR.actualizar.setEnabled(false);
        this.viewR.registrar.addActionListener(this);
        this.viewR.limpiar.addActionListener(this);
        getListar(viewR.tabla);
        viewR.tabla.addMouseListener(this);
        viewR.tabla.setDefaultEditor(Object.class, null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2) {
            flag = true;
            viewR.actualizar.setEnabled(flag);
            int fila = viewR.tabla.getSelectedRow();

            if (fila == -1 && viewR.tid.getText().isEmpty()) {
                // falta comparacion de todos los campos
                JOptionPane.showMessageDialog(viewR, "debe seleccionar la fila o dilgenciar todos los campos");
            } else {
                int id = Integer.parseInt(viewR.tabla.getValueAt(fila, 0).toString());
                String nombre = viewR.tabla.getValueAt(fila, 1).toString();
                String precio = viewR.tabla.getValueAt(fila, 2).toString();
                String cantidad = viewR.tabla.getValueAt(fila, 3).toString();
                viewR.tid.setText("" + id);
                viewR.tid.setEnabled(false);
                viewR.tnombre.setText(nombre);
                viewR.tprecio.setText(precio);
                viewR.tcantidad.setText(cantidad);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == viewR.buscar) {
            limpiarTabla();
            getListar(viewR.tabla);
            JOptionPane.showMessageDialog(viewR, "Consulta exitosa");
        }

        if (ae.getSource() == viewR.registrar) {
            if (!viewR.tid.getText().isEmpty() && !viewR.tnombre.getText().isEmpty()
                    && !viewR.tprecio.getText().isEmpty() && !viewR.tcantidad.getText().isEmpty()) {
                setAdd();
            } else {
                JOptionPane.showMessageDialog(viewR, "faltan campos por diligenciar");
            }
            limpiarTabla();
            limpiarCampos();
            getListar(viewR.tabla);

        }

        if (ae.getSource() == viewR.eliminar) {
            int fila = viewR.tabla.getSelectedRow();
            JOptionPane.showMessageDialog(viewR, "que valor contiene la fila " + fila);

            if (!viewR.tid.getText().isEmpty()) {
                int id = Integer.parseInt(viewR.tid.getText());
                setDelete(id);

            } else if (fila != -1) {
                int id = Integer.parseInt(viewR.tabla.getValueAt(fila, 0).toString());
                setDelete(id);
            } else {
                JOptionPane.showMessageDialog(viewR, "falta el id o debe seleccionar la fila que quiere eliminar");
            }

            limpiarTabla();
            limpiarCampos();
            getListar(viewR.tabla);
        }

        if (ae.getSource() == viewR.limpiar) {
            limpiarCampos();
        }

        if (ae.getSource() == viewR.actualizar) {
            int id = Integer.parseInt(viewR.tid.getText());
            setUpdate(id);
            flag = false;
            viewR.tid.setEnabled(true);
            limpiarTabla();
            limpiarCampos();
            getListar(viewR.tabla);
        }
        /* if (ae.getSource() == viewR.registrar && flag == true) {

            int id = Integer.parseInt(viewR.tid.getText());
            setUpdate(id);
            flag = false;
            viewR.tid.setEditable(true);
            limpiarTabla();
            limpiarCampos();
            getListar(viewR.tabla);
        } */
    }

    public void getListar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Producto> lista = pdao.listar();
        Object[] object = new Object[6];
        for (int indice = 0; indice < lista.size(); indice++) {
            object[0] = lista.get(indice).getId();
            object[1] = lista.get(indice).getNombre();
            object[2] = lista.get(indice).getPrecio();
            object[3] = lista.get(indice).getCantidad();
            modelo.addRow(object);
        }
        viewR.tabla.setModel(modelo);
    }

    public void setAdd() {
        int resultado;
        int id = Integer.parseInt(viewR.tid.getText().toString());
        String nombre = viewR.tnombre.getText();
        double precio = Double.parseDouble(viewR.tprecio.getText().toString());
        int cantidad = Integer.parseInt(viewR.tcantidad.getText().toString());

        p.setId(id);
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCantidad(cantidad);

        resultado = pdao.setAgregar(p);

        if (resultado == 1) {
            JOptionPane.showMessageDialog(viewR, "se inserto la informacion de forma correcta");
        } else {
            JOptionPane.showMessageDialog(viewR, "Error de inserccion " + JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setDelete(int id) {
        int resultado;
        resultado = pdao.setEliminar(id);

        if (resultado == 1) {
            JOptionPane.showMessageDialog(viewR, "se elimino correctamente");
        } else {
            JOptionPane.showMessageDialog(viewR, "Error de eliminacion");
        }
    }

    public void setUpdate(int id) {
        int resultado;

        String nombre = viewR.tnombre.getText();
        double precio = Double.parseDouble(viewR.tprecio.getText().toString());
        int cantidad = Integer.parseInt(viewR.tcantidad.getText().toString());

        p.setId(id);
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        resultado = pdao.setActualizar(p);

        if (resultado == 1) {
            JOptionPane.showMessageDialog(viewR, "se actualizo correctamente");
        } else {
            JOptionPane.showMessageDialog(viewR, "Error de actualizacion " + JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarTabla() {
        for (int i = 0; i < viewR.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiarCampos() {
        viewR.tid.setText("");
        viewR.tnombre.setText("");
        viewR.tprecio.setText("");
        viewR.tcantidad.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
