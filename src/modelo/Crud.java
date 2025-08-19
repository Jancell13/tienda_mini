package modelo;

import java.util.List;

public interface Crud<T> {
    public List<T> listar();
    public int setAgregar(T tr);
    public int setActualizar(T tr);
    public int setEliminar(int id);
}