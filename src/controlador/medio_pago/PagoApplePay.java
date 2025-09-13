package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoApplePay implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Apple Pay");

        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoApplePay();
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.setSize(520, 560);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);
    }
}
