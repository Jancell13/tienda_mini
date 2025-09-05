package vista;

import java.util.regex.Pattern;

public class Validaciones {
    
    public static boolean validarCorreo(String correo) {
        String regex = "^[\\w._%+-]+@{1}[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(regex, correo);
    }
    //es para validar nombres, apellidos y campos que solo requieran letras
    public static boolean validarLetras(String texto) {
        return Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", texto);
    }
    
    public static boolean validarCedula(String cedula) {
        return Pattern.matches("^\\d{6,10}$", cedula);
    }
    
    public static boolean validarNumeros(String texto) {
        return Pattern.matches("^\\d+$", texto);
    }
    
    public static boolean validarContraseña(String contrasena) {
        // Al menos 8 caracteres, una mayúscula, una minúscula, un número
        String regex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        return Pattern.matches(regex, contrasena);
    }
}
