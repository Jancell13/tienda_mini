package controlador.medio_pago;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.FormatearD;
import vista.metodosDePagos;

public class PagoEfectivo implements Pagos, ActionListener {
    private boolean flag;
    private metodosDePagos viewP;
    private double valorTPagar, valorRPago, valorADevolver;

    @Override
    public void crearPago(double valorTotal) {
        valorTPagar = valorTotal;
        flag = false;
        viewP = new metodosDePagos();
        viewP.listarMetodoEfectivo();
        viewP.setSize(270, 150);
        viewP.pagarEfecty.addActionListener(this);
        viewP.totalPagarEfecty.setText(FormatearD.fDecimales(valorTPagar));
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return this.flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.pagarEfecty) {
            if (camposVacios()) {
                valorRPago = Double.parseDouble(viewP.montoRecibirEfecty.getText().toString());
                if (valorRPago > valorTPagar) {
                    valorADevolver = valorRPago - valorTPagar;
                    flag = true;
                    confirPago();
                    JOptionPane.showMessageDialog(viewP,
                            "Pago con Efectivo recibido, su devuelta sera visible en la factura");
                    viewP.dispose();
                } else if (valorRPago == valorTPagar) {
                    valorADevolver = 0.0;
                    flag = true;
                    confirPago();
                    JOptionPane.showMessageDialog(viewP, "Pago exacto con Efectivo recibido");
                    viewP.dispose();
                } else {
                    flag = false;
                    JOptionPane.showMessageDialog(viewP, "Valor insuficiente");
                }
            } else {
                JOptionPane.showMessageDialog(viewP, "Debe llenar todos los campos");
            }
        }
    }

    private boolean camposVacios() {
        if (viewP.montoRecibirEfecty.getForeground() != Color.GRAY) {
            return true;
        }
        return false;
    }

    public double getValorADevolver() {
        return valorADevolver;
    }

}
