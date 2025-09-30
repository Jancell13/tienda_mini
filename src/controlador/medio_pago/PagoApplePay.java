package controlador.medio_pago;

import vista.metodosDePagos;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PagoApplePay implements Pagos {

    private boolean flag;
    private metodosDePagos viewP;
    private double valorTPagar;
    private Timer timer;

    @Override
    public void crearPago(double valorTotal) {
        valorTPagar = valorTotal;
        flag = false;
        viewP = new metodosDePagos();
        viewP.listarMetodoApplePay();
        viewP.setSize(520, 560);

        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = true;
                viewP.dispose();
                confirPago();
                JOptionPane.showMessageDialog(viewP,
                        "Pago con Apple Pay confirmado." + "\nTotal pagado: " + valorTPagar);
            }
        });
        timer.setRepeats(false);
        timer.start();

        viewP.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                }
            }
        });

        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return this.flag;
    }
}
