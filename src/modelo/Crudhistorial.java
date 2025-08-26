package modelo;

import java.util.List;

public interface Crudhistorial <T>{
    public List<T> listar();
    public int setAgregar(T tr);

}
