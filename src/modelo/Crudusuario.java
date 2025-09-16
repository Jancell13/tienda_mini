package modelo;

import java.util.List;

public interface Crudusuario <T>{
    public List<T> listar();
    public List<T> getUsuarioPorCorreo(String correo);
}
