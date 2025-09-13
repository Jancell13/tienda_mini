package controlador.medio_pago;

import vista.metodosDePagos;
import java.security.SecureRandom;

public class PagoEfectivo implements Pagos {

    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new java.security.SecureRandom();

    public static String generarCodigo(int longitud) {
        StringBuilder codigo = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(index));
        }
        return codigo.toString();
    }

    @Override
    public void crearPago(double valorTotal) {
        metodosDePagos viewP = new metodosDePagos();

        viewP.setCodigo(generarCodigo(15));
        viewP.montoCredito.setText(String.valueOf(valorTotal));
        viewP.listarMetodoEfectivo();
        viewP.setSize(270, 240);
        viewP.setVisible(true);
        viewP.setLocationRelativeTo(null);

        System.out.println("se ha procesado el pago con Efectivo  " + valorTotal);
        // crear metodo de pago PayPal

    }
}
