package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoTarjetaDebito implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Tarjeta de Debito");

        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoDebito();
        viewP.setSize(270, 185);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);
    }
}
