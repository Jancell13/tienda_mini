package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoApplePay implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Apple Pay");

        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoApplePay();
        viewP.setSize(520, 560);
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirPago'");
    }
}
