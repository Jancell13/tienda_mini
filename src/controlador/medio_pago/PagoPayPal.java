package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoPayPal implements Pagos {

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con PayPal");
        // crear metodo de pago PayPal

        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoPaypal();
        viewP.setSize(380, 150);
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return false;
    }

}
