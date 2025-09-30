package controlador;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import modelo.CuentaClienDao;
import modelo.CuentaEmpresaDao;
import modelo.Cuentacliente;
import modelo.Cuentaempresa;
import modelo.Historial;
import modelo.HistorialDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controlador implements ActionListener {
    public ProductoDao pdao = new ProductoDao();
    public MetodoPagoDao mtdao = new MetodoPagoDao();
    public Producto p = new Producto();
    public Vista view = new Vista();
    private double valorTotal = 0;
    private double valorADevolverFactura = 0;
    DefaultTableModel modelo = new DefaultTableModel();

    private final int idEmpresa = 1;
    private CuentaClienDao cuentaDao = new CuentaClienDao();
    private CuentaEmpresaDao cuentaEDao = new CuentaEmpresaDao();
    private HistorialDao historialDao = new HistorialDao();

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
            double valorTP = calcTotal();

            if (valorTP > 0) {
                int idCliente = UsuarioSesion.idUsuario;
                List<Cuentacliente> cuentasCliente = cuentaDao.listar(idCliente);
                if (!cuentasCliente.isEmpty()) {
                    Cuentacliente cuentaCliente = cuentasCliente.get(0);
                    double saldoCliente = cuentaCliente.getSaldo();
                    if (saldoCliente < valorTP) {
                        JOptionPane.showMessageDialog(view, "Saldo insuficiente para realizar el pago.");
                        return;
                    }
                }

                String metodo = view.modoDePago.getSelectedItem().toString().toLowerCase();
                Pagos pagos;

                switch (metodo) {

                    case "efectivo":
                        pagos = PagoFactory.obtenerPago(TipoDePago.EFECTIVO);
                        pagos.crearPago(valorTP);
                        // Obtener valor a devolver si es PagoEfectivo
                        if (pagos instanceof controlador.medio_pago.PagoEfectivo) {
                            controlador.medio_pago.PagoEfectivo pagoEfectivo = (controlador.medio_pago.PagoEfectivo) pagos;
                            valorADevolverFactura = pagoEfectivo.getValorADevolver();
                        } else {
                            valorADevolverFactura = 0;
                        }
                        pagoConfirmado(pagos.confirPago());
                        break;

                    case "paypal":
                        pagos = PagoFactory.obtenerPago(TipoDePago.PAYPAL);
                        pagos.crearPago(valorTP);
                        pagoConfirmado(pagos.confirPago());
                        break;

                    case "consignación":
                        pagos = PagoFactory.obtenerPago(TipoDePago.CONSIGNACION);
                        pagos.crearPago(valorTP);
                        pagoConfirmado(pagos.confirPago());
                        break;

                    case "bitcoin":
                        pagos = PagoFactory.obtenerPago(TipoDePago.BITCOIN);
                        pagos.crearPago(valorTP);
                        pagoConfirmado(pagos.confirPago());
                        break;
                    case "tarjeta de credito":
                        pagos = PagoFactory.obtenerPago(TipoDePago.TARJETA_CREDITO);
                        pagos.crearPago(valorTP);
                        pagoConfirmado(pagos.confirPago());
                        break;

                    case "tarjeta de débito":
                        pagos = PagoFactory.obtenerPago(TipoDePago.TARJETA_DEBITO);
                        pagos.crearPago(valorTP);
                        pagoConfirmado(pagos.confirPago());
                        break;

                    case "apple pay":
                        pagos = PagoFactory.obtenerPago(TipoDePago.APPLE_PAY);
                        pagos.crearPago(valorTP);
                        pagoConfirmado(pagos.confirPago());
                        break;
                    case "google pay":
                        /*
                         * pagos = PagoFactory.obtenerPago(TipoDePago.GOOGLE_PAY);
                         * pagos.crearPago(valorTP);
                         * pagoConfirmado(pagos.confirPago());
                         */
                        JOptionPane.showMessageDialog(view, "Metodo de pago no disponible");
                        break;

                    case "transferencia":
                        pagos = PagoFactory.obtenerPago(TipoDePago.TRANSFERENCIAS);
                        pagos.crearPago(valorTP);
                        pagoConfirmado(pagos.confirPago());
                        break;

                    default:
                        System.out.println("Método de pago no reconocido: " + metodo);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(view, "Seleccione minimo un producto antes de proceder al pago");
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
            valorTotal += Double.parseDouble(view.tabla.getModel().getValueAt(i, 4).toString());
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
                int id = Integer.parseInt(view.tablaProducto.getValueAt(fila, 0).toString()); // ID real
                String nombre = view.tablaProducto.getValueAt(fila, 1).toString();
                double precioUnitario = Double.parseDouble(view.tablaProducto.getValueAt(fila, 2).toString());
                int stockDisponible = Integer.parseInt(view.tablaProducto.getValueAt(fila, 3).toString());
                DefaultTableModel modeloDestino = (DefaultTableModel) view.tabla.getModel();
                boolean encontrado = false;

                for (int i = 0; i < modeloDestino.getRowCount(); i++) {
                    int idEnTabla = Integer.parseInt(modeloDestino.getValueAt(i, 0).toString());
                    if (idEnTabla == id) {
                        int cantidadActual = Integer.parseInt(modeloDestino.getValueAt(i, 3).toString());
                        if (cantidadActual < stockDisponible) {
                            int nuevaCantidad = cantidadActual + 1;
                            modeloDestino.setValueAt(nuevaCantidad, i, 3);
                            double nuevoTotal = precioUnitario * nuevaCantidad;
                            modeloDestino.setValueAt(nuevoTotal, i, 4);
                            encontrado = true;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(view, "No hay más stock del producto seleccionado");
                            return;
                        }
                    }
                }
                if (!encontrado) {
                    if (stockDisponible > 0) {
                        modeloDestino.addRow(new Object[] { id, nombre, precioUnitario, 1, precioUnitario });
                    } else {
                        JOptionPane.showMessageDialog(view, "No hay stock disponible para este producto");
                    }
                }
            }
            view.totalPagarCampo.setText(String.valueOf(calcTotal()));
        }
    }

    public void pagoConfirmado(Boolean f) {
        if (f) {
            view.totalPagarCampo.setText("");
            double totalFactura = 0;
            String metodoPagoNombre = view.modoDePago.getSelectedItem().toString();
            int idMetodoPago = -1;
            for (MetodoPago mp : mtdao.listar()) {
                if (mp.getNombre().equalsIgnoreCase(metodoPagoNombre)) {
                    idMetodoPago = mp.getId();
                    break;
                }
            }
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (int i = 0; i < view.tabla.getRowCount(); i++) {
                int idProducto = Integer.parseInt(view.tabla.getValueAt(i, 0).toString());
                int cantidadVendida = Integer.parseInt(view.tabla.getValueAt(i, 3).toString());
                int idP = pdao.getConsultar(idProducto);
                if (idP != 0) {
                    if (cantidadVendida >= 0) {
                        pdao.setActualizarCantidad(idP, cantidadVendida);
                    } else {
                        JOptionPane.showMessageDialog(view,
                                "Stock insuficiente para: " + pdao.getConsultar(idP));
                    }
                }
                double subtotal = Double.parseDouble(view.tabla.getValueAt(i, 4).toString());
                totalFactura += subtotal;
                try {

                    // Insertar en historial
                    Historial h = new Historial();
                    h.setId_producto(idProducto);
                    h.setId_usuario(UsuarioSesion.idUsuario);
                    h.setFecha(fechaHora);
                    h.setCantidad_compra(cantidadVendida);
                    h.setPrecio_total(subtotal);
                    h.setId_metodo_pago(idMetodoPago);
                    historialDao.setAgregar(h);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // Actualizar saldo cliente
            List<Cuentacliente> cuentasCliente = cuentaDao.listar(UsuarioSesion.idUsuario);
            if (!cuentasCliente.isEmpty()) {
                Cuentacliente cuentaCliente = cuentasCliente.get(0);
                cuentaCliente.setSaldo(cuentaCliente.getSaldo() - totalFactura);
                cuentaDao.setActualizar(cuentaCliente);
            }

            // Actualizar saldo empresa
            List<Cuentaempresa> cuentasEmpresa = cuentaEDao.listar(idEmpresa);
            if (!cuentasEmpresa.isEmpty()) {
                Cuentaempresa cuentaEmpresa = cuentasEmpresa.get(0);
                cuentaEmpresa.setSaldo(cuentaEmpresa.getSaldo() + totalFactura);
                cuentaEDao.setActualizar(cuentaEmpresa);
            }

            generarFactura();
            JOptionPane.showMessageDialog(view, "Pago confirmado y stock actualizado.");
            limpiarTablaCarrito();
            limpiarTablaProductos();
            modelo.setRowCount(0);
            getListarProductos(view.tablaProducto);
        } else {
            JOptionPane.showMessageDialog(view, "Pago cancelado.");
            valorTotal = 0;
        }
    }

    private void generarFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("\n********************** FACTURA **********************\n");
        String metodoPago = view.modoDePago.getSelectedItem().toString();
        factura.append(String.format("\nMétodo de pago: %s\n\n", metodoPago));
        factura.append(String.format("%-20s %-10s %-10s %-10s\n", "Producto", "Precio", "Cantidad", "Subtotal"));
        double total = 0;
        for (int i = 0; i < view.tabla.getRowCount(); i++) {
            String producto = view.tabla.getValueAt(i, 1).toString();
            if (producto.length() > 20) {
                producto = producto.substring(0, 17) + "...";
            }
            String precio = view.tabla.getValueAt(i, 2).toString();
            String cantidad = view.tabla.getValueAt(i, 3).toString();
            String subtotal = view.tabla.getValueAt(i, 4).toString();
            factura.append(String.format("%-20s %-10s %-10s %-10s\n", producto, precio, cantidad, subtotal));
            total += Double.parseDouble(subtotal);
        }
        factura.append("-".repeat(52) + "\n");
        String totalStr = String.format("%.2f", total);
        String totalLine = String.format("%-10s %39s\n\n", "TOTAL:", totalStr);
        factura.append(totalLine);
        // Mostrar valor a devolver solo si es efectivo y mayor a 0
        if (metodoPago.equalsIgnoreCase("efectivo") && valorADevolverFactura > 0) {
            String devultaStr = String.format("%.2f", valorADevolverFactura);
            String devueltaLine = String.format("%-10s %39s\n", "DEVUELTA:", devultaStr);
            factura.append(String.format(devueltaLine));
        }
        factura.append("\n" + "*".repeat(52) + "\n");

        javax.swing.JTextArea area = new javax.swing.JTextArea(factura.toString());
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setEditable(false);
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(area);
        scroll.setPreferredSize(new Dimension(390, 220));
        JOptionPane.showMessageDialog(view, scroll, "Factura", JOptionPane.INFORMATION_MESSAGE);
    }
}
