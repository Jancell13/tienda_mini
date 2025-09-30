package controlador;

public class FormatearD {

    public static String fDecimales(double valorD) {
        double valor = valorD; // tu valor
        String resultado;
        if (valor == Math.floor(valor)) {
            // Es un entero (por ejemplo, 10.00)
            resultado = String.format("%.0f", valor);
        } else {
            // Tiene decimales (por ejemplo, 10.50)
            resultado = String.format("%.2f", valor);
        }
        return resultado;
    }
}
