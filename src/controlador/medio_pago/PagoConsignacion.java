package controlador.medio_pago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import controlador.FormatearD;
import vista.metodosDePagos;

public class PagoConsignacion implements Pagos, ActionListener {
    private static final String caract = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();

    private boolean flag;
    private metodosDePagos viewP;
    private double valorTPagar;

    @Override
    public void crearPago(double valorTotal) {
        valorTPagar = valorTotal;
        flag = false;
        viewP = new metodosDePagos();
        viewP.listarMetodoConsignacion();
        viewP.setSize(250, 170);
        viewP.confirConsig.addActionListener(this);
        viewP.codigoConsig.setText(generarCodigos(15));
        viewP.montoCredito.setText(FormatearD.fDecimales(valorTPagar));
        viewP.mostrarComoModal(viewP);
    }

    @Override
    public boolean confirPago() {
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewP.confirConsig) {
            flag = true;
            confirPago();
            viewP.dispose();
        }
    }

    public static String generarCodigos(int longitud) {
        if (longitud <= 0) {
            return "";
        }

        StringBuilder codigo = new StringBuilder(longitud);
        int fuenteLength = caract.length();

        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = RANDOM.nextInt(fuenteLength);
            codigo.append(caract.charAt(indiceAleatorio));
        }

        return codigo.toString();
    }

}
