package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDao implements Crudusuario<Usuario> {

    @Override
    public List<Usuario> listar() {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT * FROM historial";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setDocumento(rs.getInt(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(6));
                datos.add(u);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }

        return datos;
    }

    @Override
    public List<Usuario> getUsuarioPorCorreo(String correo) {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT u.email, u.password, ur.id_rol " +
                "FROM usuario u " +
                "JOIN usuario_rol ur ON id_usuario = u.id " +
                "WHERE u.email = ?;";
        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setEmail(rs.getString(1));
                    u.setPassword(rs.getString(2));
                    u.setRol(rs.getInt(3));
                    datos.add(u);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

}
