package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class UsuarioDao implements Crudusuario<Usuario> {

    @Override
    public List<Usuario> getUsuarioPorCorreo(String correo) {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT u.id, u.email, u.password, ur.id_rol " +
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

                    u.setId(rs.getInt(1));
                    u.setEmail(rs.getString(2));
                    u.setPassword(rs.getString(3));
                    u.setRol(rs.getInt(4));
                    datos.add(u);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

    @Override
    public List<Usuario> getUsuarioPorId(int id) {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT u.id, u.email, u.password, ur.id_rol " +
                "FROM usuario u " +
                "JOIN usuario_rol ur ON id_usuario = u.id " +
                "WHERE u.id = ?;";
        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
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

      @Override
    public int setAgregar(Usuario u) {
        String sql = "INSERT INTO `usuario`(`documento`, `nombre`, `apellido`, `email`, `password`) VALUES (?,?,?,?,?);";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, u.getDocumento());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPassword());
            return ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Inserción", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

}
