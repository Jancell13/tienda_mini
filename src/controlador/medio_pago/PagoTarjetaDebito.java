package controlador.medio_pago;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.FormatearD;
import vista.Validaciones;
import vista.metodosDePagos;

public class PagoTarjetaDebito implements Pagos, ActionListener {

    private boolean flag;
    private metodosDePagos viewP;

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Tarjeta de Debito");
        viewP = new metodosDePagos();
        viewP.listarMetodoDebito();
        flag = false;
        viewP.setSize(270, 210);
        viewP.confirDebito.addActionListener(this);
        viewP.montoCredito.setText(FormatearD.fDecimales(valorTotal));
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.confirDebito) {

            if (camposVacios() && Validaciones.validarNumeroCuenta(viewP.numTarjeDebito.getText())
                    && Validaciones.validarFecha(viewP.fechaTarjeDebito.getText())
                    && Validaciones.validarCVV(String.valueOf(viewP.numCvvDebito.getPassword()))) {
                flag = true;
                confirPago();
                viewP.dispose();
            } else {
                JOptionPane.showMessageDialog(viewP, "Debe llenar todos los campos");
            }

        }
    }

    private boolean camposVacios() {
        if (viewP.numTarjeDebito.getForeground() != Color.GRAY && viewP.fechaTarjeDebito.getForeground() != Color.GRAY
                && viewP.numCvvDebito.getForeground() != Color.GRAY) {
            return true;
        }
        return false;
    }

}
