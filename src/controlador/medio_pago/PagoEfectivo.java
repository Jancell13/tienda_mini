package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoEfectivo implements Pagos {

    @Override
    public void crearPago(double valorTotal) {
        metodosDePagos viewP = new metodosDePagos();
        System.out.println("se ha procesado el pago con Efectivo  " + valorTotal);

        viewP.listarMetodoEfectivo();
        viewP.setSize(270, 150);
        viewP.totalPagarEfecty.setText(Double.toString(valorTotal));
        viewP.mostrarComoModal(viewP);

    }

    @Override
    public boolean confirPago() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirPago'");
    }
}
