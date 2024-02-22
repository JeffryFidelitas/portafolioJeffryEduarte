package tienda.demo.service;
import tienda.demo.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    public List<Categoria> getCategorias(boolean activos);
}