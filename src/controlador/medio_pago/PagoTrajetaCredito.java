package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoTrajetaCredito implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Tarjeta de credito.");
        metodosDePagos viewP = new metodosDePagos();
                viewP.listarMetodoCredito();
                viewP.montoCredito.setText(String.valueOf(valorTotal));
                viewP.setSize(270, 240);
                viewP.setVisible(true);
                viewP.setLocationRelativeTo(null);
    }
}
