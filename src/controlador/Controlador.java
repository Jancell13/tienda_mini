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

public class Controlador implements ActionListener, MouseListener {
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
            view.totalPagarCampo.setText(String.valueOf(calcTotal()));
        }
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
                    break;

                case "tarjeta de débito":
                    pagos = PagoFactory.obtenerPago(TipoDePago.TARJETA_DEBITO);
                    pagos.crearPago(calcTotal());
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
            ControladorRegistrarProduc c = new ControladorRegistrarProduc(viewR);
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
