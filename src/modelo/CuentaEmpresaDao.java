package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CuentaEmpresaDao implements Cuentaem<Cuentaempresa> {

    @Override
    public List<Cuentaempresa> listar(int id) {
        List<Cuentaempresa> datos = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_entidad WHERE id=?";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuentaempresa c = new Cuentaempresa();
                    c.setId(rs.getInt(1));
                    c.setSaldo(rs.getDouble(2));
                    datos.add(c);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }

        return datos;
    }

    @Override
    public int setActualizar(Cuentaempresa c) {
        String sql = "UPDATE cuenta_entidad SET saldo=? WHERE id=?";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, c.getSaldo());
            ps.setInt(2, c.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Actualizacion", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

}
