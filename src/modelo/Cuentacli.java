package modelo;

import java.util.List;


public interface Cuentacli<T> {
    public List<T> listar(int id); 
    public int setActualizar(T tr);
    
}
