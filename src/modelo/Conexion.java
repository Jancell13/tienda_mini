package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    Connection cn;
    String url = "jdbc:mysql://10.6.234.85:3306/tienda";
    String user = "jd";
    String pass = "1234";

    public Connection getConection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Base de datos Apagada", JOptionPane.ERROR_MESSAGE);
        }
        return cn;
    }
}
