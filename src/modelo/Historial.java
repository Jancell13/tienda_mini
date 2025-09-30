package modelo;

public class Historial {
    private int id;
    private int id_producto;
    private int id_usuario;
    private String fecha;
    private int cantidad_compra;
    private double precio_total;
    private int id_metodo_pago;

    public Historial(int id, int id_producto, int id_usuario, String fecha, int cantidad_compra, double precio_total,
            int id_metodo_pago) {
        this.id = id;
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.cantidad_compra = cantidad_compra;
        this.precio_total = precio_total;
        this.id_metodo_pago = id_metodo_pago;
    }

    public Historial() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
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

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public int getId_metodo_pago() {
        return id_metodo_pago;
    }

    public void setId_metodo_pago(int id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    }

}
