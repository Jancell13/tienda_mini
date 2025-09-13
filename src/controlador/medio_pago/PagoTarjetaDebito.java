package controlador.medio_pago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import vista.metodosDePagos;

public class PagoTarjetaDebito implements Pagos {

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Tarjeta de Debito");
        metodosDePagos viewP = new metodosDePagos();
        viewP.mostrarComoModal(this);
        /* viewP.listarMetodoDebito(); */
        viewP.setSize(270, 210);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);
        viewP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewP.montoCredito.setText(String.valueOf(valorTotal));

    }

    /*
     * @Override
     * public void actionPerformed(ActionEvent e) {
     * if (e.getSource() == viewP.confirDebito) {
     * 
     * }
     * }
     */
}
