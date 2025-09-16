package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDao implements Crud<Producto> {

    @Override
    public List<Producto> listar() {
        List<Producto> datos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCantidad(rs.getInt(4));
                datos.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }

        return datos;
    }

    @Override
    public int setAgregar(Producto p) {
        String sql = "INSERT INTO producto VALUES (?,?,?,?)";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getCantidad());
            return ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Inserción", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    @Override
    public int setActualizar(Producto p) {
        String sql = "UPDATE producto SET nombre=?, precio=?, cantidad_stock=? WHERE id=?";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Actualizacion", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    @Override
    public int setEliminar(int id) {
        String sql = "DELETE FROM producto WHERE id = ?";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    @Override
    public int getCantidad(int id) {
        String sql = "SELECT cantidad_stock FROM producto WHERE id = ?";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("cantidad_stock");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }

        return 0;
    }

    @Override
    public int setActualizarCantidad(int id, int cantidad) {
        String sql = "UPDATE producto SET cantidad_stock = cantidad_stock - ? WHERE id = ?";

        try (
                Connection con = Conexion.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, cantidad);
                    ps.setInt(2, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error al Actualizar Cantidad",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

     @Override
    public int getConsultar(int id) {
        String sql = "SELECT * FROM producto WHERE id = ?";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta por id", JOptionPane.ERROR_MESSAGE);
        }

        return 0;
    }

}