package tienda.demo.dao;

import tienda.demo.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository <Rol,Long> {
    
}
