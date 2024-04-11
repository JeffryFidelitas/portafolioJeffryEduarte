package tienda.demo.dao;

import tienda.demo.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository <Factura,Long> {
    
}