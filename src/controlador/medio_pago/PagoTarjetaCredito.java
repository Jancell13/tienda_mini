package controlador.medio_pago;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.FormatearD;
import vista.Validaciones;
import vista.metodosDePagos;

public class PagoTarjetaCredito implements Pagos, ActionListener {

    private boolean flag;
    private metodosDePagos viewP;

    @Override
    public void crearPago(double valorTotal) {
        viewP = new metodosDePagos();
        viewP.listarMetodoCredito();
        flag = false;
        viewP.setSize(270, 240);
        viewP.confirCredi.addActionListener(this);
        viewP.montoCredito.setText(FormatearD.fDecimales(valorTotal));
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.confirCredi) {

            if (camposVacios() && Validaciones.validarLetras(viewP.nombreCredito.getText())
                    && Validaciones.validarNumeroCuenta(viewP.numTarjeCredito.getText())
                    && Validaciones.validarFecha(viewP.fechaTarjeCredito.getText())
                    && Validaciones.validarCVV(String.valueOf(viewP.numCvvCredito.getPassword()))) {
                flag = true;
                confirPago();
                viewP.dispose();
            } else {
                JOptionPane.showMessageDialog(viewP, "Debe llenar todos los campos");
            }
        }
    }

    private boolean camposVacios() {
        if (viewP.nombreCredito.getForeground() != Color.GRAY && viewP.numTarjeCredito.getForeground() != Color.GRAY
                && viewP.fechaTarjeCredito.getForeground() != Color.GRAY
                && viewP.numCvvCredito.getForeground() != Color.GRAY) {
            return true;
        }
        return false;
    }
}
