package controlador.medio_pago;

public interface Pagos {
    void crearPago(double valorTotal);

    boolean confirPago();
}
