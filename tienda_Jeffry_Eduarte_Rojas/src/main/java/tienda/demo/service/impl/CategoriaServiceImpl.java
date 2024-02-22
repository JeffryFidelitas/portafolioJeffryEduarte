package tienda.demo.service.impl;
import tienda.demo.dao.CategoriaDao;
import tienda.demo.domain.Categoria;
import tienda.demo.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaDao categoriaDao;
    @Override
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista=categoriaDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }        
        return lista;
    }
}