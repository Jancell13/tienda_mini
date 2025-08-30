package controlador.medio_pago;

import vista.metodosDePagos;

public class PagoTranferencia implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Transferencias");
        
        metodosDePagos viewP = new metodosDePagos();
        viewP.listarMetodoTransferencia();
        viewP.setSize(320, 240);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);
    }
}
