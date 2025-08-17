package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    Connection cn;
    String url = "jdbc:mysql://localhost:3306/tienda";
    String user = "root";
    String pass = "";

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

