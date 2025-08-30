package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoBitcoin implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Bitcoin");
        // crear metodo de pago PayPal
        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoBitcoin();
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.setSize(350, 420);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);
    }
}
