package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class HistorialDao implements Crudhistorial<Historial> {

    @Override
    public List<Historial> listar() {
        List<Historial> datos = new ArrayList<>();
        String sql = "SELECT * FROM historial";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Historial h = new Historial();
                h.setId(rs.getInt(1));
                h.setId_metodo_pago(rs.getInt(2));             
                h.setId_usuario (rs.getInt(3));
                h.setFecha(rs.getString(4));
                h.setCantidad_compra(rs.getInt(5));
                h.setPrecio_total(rs.getInt(6));
                h.setId_metodo_pago(rs.getInt(7));
                datos.add(h);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }

        return datos;
    }

   @Override
    public int setAgregar(Historial h){
        String sql = "INSERT INTO producto VALUES (?,?,?,?,?,?)";

        try(
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setInt(1, h.getId_usuario_producto());
            ps.setInt(2, h.getId_usuario());
            ps.setString(3, h.getFecha());
            ps.setInt(4, h.getCantidad_compra());
            ps.setInt(5, h.getPrecio_total());
            ps.setInt(6, h.getId_metodo_pago());
            return ps.executeUpdate();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Inserción", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    } 

}