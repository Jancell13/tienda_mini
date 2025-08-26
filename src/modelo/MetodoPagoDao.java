package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class MetodoPagoDao implements MTP<MetodoPago> {
    @Override
    public List<MetodoPago> listar() {
        List<MetodoPago> datos = new ArrayList<>();
        String sql = "SELECT * FROM metodo_pago";
        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MetodoPago p = new MetodoPago();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                datos.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }
}
