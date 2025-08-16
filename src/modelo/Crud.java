package modelo;

import java.util.List;

public interface Crud<T> {
    public List<T> listar();
}