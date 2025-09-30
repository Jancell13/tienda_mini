package controlador.medio_pago;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.FormatearD;
import vista.Validaciones;
import vista.metodosDePagos;

public class PagoTranferencia implements Pagos, ActionListener {
    private boolean flag;
    private metodosDePagos viewP;

    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Transferencias");
        viewP = new metodosDePagos();
        flag = false;
        viewP.listarMetodoTransferencia();
        viewP.confirTransfer.addActionListener(this);
        viewP.montoTrans.setText(FormatearD.fDecimales(valorTotal));
        viewP.montoTrans.setForeground(Color.black);
        viewP.setSize(320, 240);
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.confirTransfer) {

            if (camposVacios()
                    && Validaciones.validarLetras(viewP.nomTrans.getText())
                    && Validaciones.validarNumeroCuenta(viewP.numCuentaTra.getText())
                    && Validaciones.validarLetras(viewP.nomBanTra.getText())
                    && Validaciones.validarTelefonos(viewP.numContaTra.getText())
                    && Validaciones.validarNumeros(viewP.montoTrans.getText())) {
                flag = true;
                confirPago();
                viewP.dispose();
            } else {
                JOptionPane.showMessageDialog(viewP, "Debe llenar todos los campos");
            }

        }
    }

    private boolean camposVacios() {
        if (viewP.nomTrans.getForeground() != Color.GRAY
                && viewP.numCuentaTra.getForeground() != Color.GRAY
                && viewP.nomBanTra.getForeground() != Color.GRAY
                && viewP.numContaTra.getForeground() != Color.GRAY
                && viewP.montoTrans.getForeground() != Color.GRAY) {
            return true;
        }
        return false;
    }

}
