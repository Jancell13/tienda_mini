package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import controlador.medio_pago.PagoFactory;
import controlador.medio_pago.Pagos;
import controlador.medio_pago.TipoDePago;
import modelo.MetodoPago;
import modelo.MetodoPagoDao;
import modelo.Producto;
import modelo.ProductoDao;
import vista.Vista;
import vista.VistaRegistrar;
import vista.metodosDePagos;

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
                JOptionPane.showMessageDialog(view, "Debe seleccionar un producto");
            } else {
                String nombre = view.tablaProducto.getValueAt(fila, 1).toString();
                
                double precioUnitario = Double.parseDouble(view.tablaProducto.getValueAt(fila, 2).toString());
                double precioU = Double.parseDouble(view.tablaProducto.getValueAt(fila, 2).toString());
                DefaultTableModel modeloDestino = (DefaultTableModel) view.tabla.getModel();
                boolean encontrado = false;

                for (int i = 0; i < modeloDestino.getRowCount(); i++) {
                    String nombreEnTabla = modeloDestino.getValueAt(i, 0).toString();

                    if (nombreEnTabla.equals(nombre)) {
                        int cantidadActual = Integer.parseInt(modeloDestino.getValueAt(i, 2).toString());
                        int nuevaCantidad = cantidadActual + 1;
                        modeloDestino.setValueAt(nuevaCantidad, i, 2);
                        double nuevoTotal = precioUnitario * nuevaCantidad;
                        modeloDestino.setValueAt(nuevoTotal, i, 3);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    modeloDestino.addRow(new Object[] { nombre, precioU, 1, precioUnitario });
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.pagar) {

            PagoFactory pagoFactory = new PagoFactory();
            double valorTotal = 0;

            for (int i = 0; i < view.tabla.getModel().getRowCount(); i++) {
                valorTotal += Double.parseDouble(view.tabla.getModel().getValueAt(i, 2).toString());
            }

            if (view.modoDePago.getSelectedItem().equals("Tarjeta de Credito")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.TARJETA_CREDITO);
                pagos.crearPago(valorTotal);
                
            }
            if (view.modoDePago.getSelectedItem().equals("efectivo")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.EFECTIVO);
                pagos.crearPago(valorTotal);                
            }
            if (view.modoDePago.getSelectedItem().equals("paypal")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.PAYPAL);
                pagos.crearPago(valorTotal);

            }
            if (view.modoDePago.getSelectedItem().equals("consignacion")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.CONSIGNACION);
                pagos.crearPago(valorTotal);
            }
            if (view.modoDePago.getSelectedItem().equals("bitcoin")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.BITCOIN);
                pagos.crearPago(valorTotal);
            }
            if (view.modoDePago.getSelectedItem().equals("Tarjeta de Debito")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.TARJETA_DEBITO);
                pagos.crearPago(valorTotal);
            }
            if (view.modoDePago.getSelectedItem().equals("apple pay")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.APPLE_PAY);
                pagos.crearPago(valorTotal);
            }
            
             /*  if (view.modoDePago.getSelectedItem().equals("google pay")) {
              
              metodosDePagos viewP = new metodosDePagos();
              viewP.listarMetodoGooglePay();
              viewP.setSize(270, 185);
              viewP.setVisible(true);
              viewP.setLocationRelativeTo(null);
              } */
            
            if (view.modoDePago.getSelectedItem().equals("transferencia")) {
                Pagos pagos = pagoFactory.obtenerPago(TipoDePago.TRANSFERENCIAS);
                pagos.crearPago(valorTotal);

            }
        }

        if (e.getSource() == view.registrar) {

            VistaRegistrar viewR = new VistaRegistrar();
            ControladorRegistrarProduc c = new ControladorRegistrarProduc(viewR);
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
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
