package controlador.medio_pago;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.FormatearD;
import vista.Validaciones;
import vista.metodosDePagos;

public class PagoPayPal implements Pagos, ActionListener {

    private boolean flag;
    private metodosDePagos viewP;
    private double valorTPagar;

    @Override
    public void crearPago(double valorTotal) {
        valorTPagar = valorTotal;
        viewP = new metodosDePagos();
        viewP.requestFocusInWindow();
        viewP.listarMetodoPaypal();
        flag = false;
        viewP.setSize(380, 150);
        viewP.confirPaypal.addActionListener(this);
        viewP.montoCredito.setText(FormatearD.fDecimales(valorTPagar));
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.confirPaypal) {
            if (camposVacios() && Validaciones.validarCorreo(viewP.correoPaypal.getText())) {
                flag = true;
                confirPago();
                viewP.dispose();
            } else {
                JOptionPane.showMessageDialog(viewP, "Debe llenar todos los campos");
            }
        }
    }

    private boolean camposVacios() {
        if (viewP.correoPaypal.getForeground() != Color.GRAY) {
            return true;
        }
        return false;
    }
}
