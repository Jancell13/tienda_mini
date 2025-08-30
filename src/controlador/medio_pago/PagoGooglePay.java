package controlador.medio_pago;

public class PagoGooglePay implements Pagos {
    @Override
    public void crearPago(double valorTotal) {
        System.out.println("se ha procesado el pago con Google pay");
        //crear metodo de pago PayPal
    }
}
