package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoEfectivo implements Pagos {

    @Override
    public void crearPago(double valorTotal) {
        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoEfectivo();
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.setSize(270, 240);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);

        System.out.println("se ha procesado el pago con Efectivo  " + valorTotal);
        // crear metodo de pago PayPal

    }
}
