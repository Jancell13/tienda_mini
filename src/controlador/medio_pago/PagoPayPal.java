package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoPayPal implements Pagos {

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con PayPal");
        // crear metodo de pago PayPal

        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoPaypal();
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.setSize(380, 150);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);
    }

}
