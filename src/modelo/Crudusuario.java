package modelo;

import java.util.List;

public interface Crudusuario<T> {

    public List<T> getUsuarioPorCorreo(String correo);

    public List<T> getUsuarioPorId(int id);

    public int setAgregar(T tr);

}
