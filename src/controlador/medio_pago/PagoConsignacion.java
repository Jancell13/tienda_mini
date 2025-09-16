package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoConsignacion implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Consignacion");
        // crear metodo de pago PayPal
        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoConsignacion();
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.setSize(250, 150);
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirPago'");
    }
}
