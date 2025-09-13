package modelo;

public class Cuentacliente {

    private int id_usuario;
    private double saldo;

    public Cuentacliente(int id_usuario, int saldo) {
        this.id_usuario = id_usuario;
        this.saldo = saldo;
    }
    public Cuentacliente() {

    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}
