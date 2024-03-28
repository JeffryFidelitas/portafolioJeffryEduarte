package tienda.demo.service;

import org.springframework.security.core.userdetails.*;
import tienda.demo.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    
    public List<Usuario> getUsuarios();
    
    public Usuario getUsuario(Usuario usuario);
    
    public Usuario getUsuarioByUsername(String username);
    
    public Usuario getUsuarioByUsernameAndPassword(String username, String password);
    
    public Usuario getUsuarioByUsernameOrCorreo(String username, String correo);
    
    public boolean UsuarioExistsByUsernameOrCorreo(String username, String correo);
    
    public void save(Usuario usuario, boolean makeUserRol);
    
    public void delete(Usuario usuario);
}
