package controlador.medio_pago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.metodosDePagos;

public class PagoTarjetaCredito implements Pagos, ActionListener {
    boolean flag = false;
    metodosDePagos viewP = new metodosDePagos();

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Tarjeta de credito.");

        viewP.listarMetodoCredito();
        viewP.setSize(270, 240);
        viewP.montoCredito.setText(String.valueOf(valorTotal));
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
