package modelo;

public class Historial {
    private int id;
    private int id_usuario_producto ;
    private int id_usuario;
    private String fecha;
    private int cantidad_compra;
    private int precio_total;
    private int id_metodo_pago ;

    public Historial(int id, int id_usuario_producto, int id_usuario, String fecha, int cantidad_compra, int precio_total, int id_metodo_pago) {
        this.id = id;
        this.id_usuario_producto = id_usuario_producto;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.cantidad_compra = cantidad_compra;
        this.precio_total = precio_total;
        this.id_metodo_pago = id_metodo_pago;
    }

    public Historial(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario_producto() {
        return id_usuario_producto;
    }

    public void setId_usuario_producto(int id_usuario_producto) {
        this.id_usuario_producto = id_usuario_producto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad_compra() {
        return cantidad_compra;
    }

    public void setCantidad_compra(int cantidad_compra) {
        this.cantidad_compra = cantidad_compra;
    }

    public int getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(int precio_total) {
        this.precio_total = precio_total;
    }

    public int getId_metodo_pago() {
        return id_metodo_pago;
    }

    public void setId_metodo_pago(int id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    }

    
}
