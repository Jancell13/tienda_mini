package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CuentaClienDao implements Cuentacli<Cuentacliente> {

    
    @Override
    public List<Cuentacliente> listar(int id_usuario) {
        List<Cuentacliente> datos = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_cliente WHERE id_usuario=?";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id_usuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuentacliente c = new Cuentacliente();
                    c.setId_usuario(rs.getInt(2)); // id_usuario está en la segunda columna
                    c.setSaldo(rs.getDouble(3));   // saldo está en la tercera columna
                    datos.add(c);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }

        return datos;
    }


    @Override
    public int setActualizar(Cuentacliente c) {
        String sql = "UPDATE cuenta_cliente SET saldo=? WHERE id_usuario=?";

        try(
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setDouble(1, c.getSaldo());
            ps.setInt(2, c.getId_usuario());
            return ps.executeUpdate();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Actualizacion", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    

}
