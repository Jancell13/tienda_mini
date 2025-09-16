package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoGooglePay implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        metodosDePagos viewP = new metodosDePagos();
        // viewP.listarMetodoGooglePay();
        viewP.setSize(270, 185);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);

    }

    @Override
    public boolean confirPago() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirPago'");
    }
}
