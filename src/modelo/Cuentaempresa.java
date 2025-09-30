package modelo;


public class Cuentaempresa {
    private int id;
    private double saldo;

    public Cuentaempresa(int id, int saldo) {
        this.id = id;
        this.saldo = saldo;
    }
    public Cuentaempresa() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
