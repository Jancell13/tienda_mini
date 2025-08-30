package controlador.medio_pago;

import java.util.HashMap;
import java.util.Map;

public class PagoFactory {
    private final static Map<TipoDePago, Pagos> pagos = new HashMap<>(){{
        put(TipoDePago.PAYPAL,new PagoPayPal());
        put(TipoDePago.EFECTIVO,new PagoEfectivo());
        put(TipoDePago.TARJETA_CREDITO,new PagoTrajetaCredito());
        put(TipoDePago.TARJETA_DEBITO,new PagoTrajetaCredito());
        put(TipoDePago.GOOGLE_PAY,new PagoGooglePay());
        put(TipoDePago.APPLE_PAY,new PagoApplePay());
        put(TipoDePago.TRANSFERENCIAS,new PagoTranferencia());
        put(TipoDePago.BITCOIN,new PagoBitcoin());
        put(TipoDePago.CONSIGNACION,new PagoConsignacion());
    }};

    public static Pagos obtenerPago(TipoDePago tipoDePago){
        return pagos.get(tipoDePago);
    }

    
}
