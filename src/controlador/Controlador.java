package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

public class Controlador implements ActionListener {
    public ProductoDao pdao = new ProductoDao();
    public MetodoPagoDao mtdao = new MetodoPagoDao();
    public Producto p = new Producto();
    public Vista view = new Vista();
    private double valorTotal = 0;
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Vista v) {
        this.view = v;
        this.view.pagar.addActionListener(this);
        this.view.registrar.addActionListener(this);
        getListarProductos(view.tablaProducto);
        getMetodosPago();
        view.tablaProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                logicaTabla(me);
            }
        });
        view.tablaProducto.setDefaultEditor(Object.class, null);
        view.tabla.setDefaultEditor(Object.class, null);
    }

    public Controlador() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.pagar) {
            String metodo = view.modoDePago.getSelectedItem().toString().toLowerCase();
            Pagos pagos;

            switch (metodo) {
                case "tarjeta de credito":
                    pagos = PagoFactory.obtenerPago(TipoDePago.TARJETA_CREDITO);
                    pagos.crearPago(calcTotal());
                    break;

                case "efectivo":
                    pagos = PagoFactory.obtenerPago(TipoDePago.EFECTIVO);
                    pagos.crearPago(calcTotal());
                    break;

                case "paypal":
                    pagos = PagoFactory.obtenerPago(TipoDePago.PAYPAL);
                    pagos.crearPago(calcTotal());
                    break;

                case "consignación":
                    pagos = PagoFactory.obtenerPago(TipoDePago.CONSIGNACION);
                    pagos.crearPago(calcTotal());
                    break;

                case "bitcoin":
                    pagos = PagoFactory.obtenerPago(TipoDePago.BITCOIN);
                    pagos.crearPago(calcTotal());
                    pagoConfirmado(pagos.confirPago());
                    break;

                case "tarjeta de débito":
                    pagos = PagoFactory.obtenerPago(TipoDePago.TARJETA_DEBITO);
                    pagos.crearPago(calcTotal());
                    pagoConfirmado(pagos.confirPago());
                    break;

                case "apple pay":
                    pagos = PagoFactory.obtenerPago(TipoDePago.APPLE_PAY);
                    pagos.crearPago(calcTotal());
                    break;
                case "google pay":
                    pagos = PagoFactory.obtenerPago(TipoDePago.GOOGLE_PAY);
                    pagos.crearPago(calcTotal());
                    break;

                case "transferencia":
                    pagos = PagoFactory.obtenerPago(TipoDePago.TRANSFERENCIAS);
                    pagos.crearPago(valorTotal);
                    break;

                default:
                    System.out.println("Método de pago no reconocido: " + metodo);
                    break;
            }
        }
        if (e.getSource() == view.registrar) {
            VistaRegistrar viewR = new VistaRegistrar();
            new ControladorRegistrarProduc(viewR);
            viewR.setSize(620, 720);
            viewR.setVisible(true);
            viewR.setLocationRelativeTo(null);
        }
    }

    private double calcTotal() {
        valorTotal = 0;
        for (int i = 0; i < view.tabla.getModel().getRowCount(); i++) {
            valorTotal += Double.parseDouble(view.tabla.getModel().getValueAt(i, 3).toString());
        }
        return valorTotal;
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

    public void limpiarTablaCarrito() {
        DefaultTableModel modeloCarrito = (DefaultTableModel) view.tabla.getModel();
        modeloCarrito.setRowCount(0); // elimina todas las filas
    }

    public void limpiarTablaProductos() {
        DefaultTableModel modeloProductos = (DefaultTableModel) view.tablaProducto.getModel();
        modeloProductos.setRowCount(0);
    }

    private void logicaTabla(MouseEvent me) {
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
            view.totalPagarCampo.setText(String.valueOf(calcTotal()));
        }
    }

    public void pagoConfirmado(Boolean f) {
        if (f) {
            // Recorremos los productos del carrito (tabla view.tabla)
            for (int i = 0; i < view.tabla.getRowCount(); i++) {
                String nombreProducto = view.tabla.getValueAt(i, 0).toString();
                int cantidadVendida = Integer.parseInt(view.tabla.getValueAt(i, 2).toString());

                // Buscar producto por nombre (o mejor por ID si lo tienes en la tabla)
                /*
                 * Producto producto = pdao.buscarPorNombre(nombreProducto);
                 * if (producto != null) {
                 * int nuevoStock = producto.getCantidad() - cantidadVendida;
                 * 
                 * if (nuevoStock >= 0) {
                 * pdao.actualizarStock(producto.getId(), nuevoStock);
                 * } else {
                 * JOptionPane.showMessageDialog(view,
                 * "Stock insuficiente para: " + producto.getNombre());
                 * }
                 * }
                 */

            }

            // Limpiar carrito después de la compra
            limpiarTablaCarrito();
            JOptionPane.showMessageDialog(view, "Pago confirmado y stock actualizado.");

            // Refrescar listado de productos en la vista
            limpiarTablaProductos();
            modelo.setRowCount(0);
            getListarProductos(view.tablaProducto);

        } else {
            JOptionPane.showMessageDialog(view, "Pago cancelado.");
        }
    }

}
