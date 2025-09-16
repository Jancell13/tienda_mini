package controlador.medio_pago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.metodosDePagos;

public class PagoTranferencia implements Pagos, ActionListener {
    Boolean flag = false;
    metodosDePagos viewP = new metodosDePagos();

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Transferencias");

        viewP.listarMetodoTransferencia();
        viewP.setSize(320, 240);
        viewP.mostrarComoModal(viewP);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.confirDebito) {
            flag = true;
            viewP.dispose();
        }
    }

    @Override
    public boolean confirPago() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirPago'");
    }
}
