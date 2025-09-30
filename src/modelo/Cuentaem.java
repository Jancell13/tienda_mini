package modelo;

import java.util.List;

public interface Cuentaem<T> {
    public List<T> listar(int id);

    public int setActualizar(T tr);

}
