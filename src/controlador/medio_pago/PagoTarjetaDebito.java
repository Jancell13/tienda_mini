package controlador.medio_pago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.metodosDePagos;

public class PagoTarjetaDebito implements Pagos, ActionListener {

    public boolean flag = false;
    metodosDePagos viewP = new metodosDePagos();

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Tarjeta de Debito");
        viewP.listarMetodoDebito();
        viewP.setSize(270, 210);
        viewP.confirDebito.addActionListener(this);
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.mostrarComoModal(viewP);

    }

    @Override
    public boolean confirPago() {
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.confirDebito) {
            flag = true;
            confirPago();
            viewP.dispose();
        }
    }

}
