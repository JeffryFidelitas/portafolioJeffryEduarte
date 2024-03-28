package tienda.demo.service.impl;

import tienda.demo.service.UsuarioService;
import tienda.demo.dao.UsuarioDao;
import tienda.demo.dao.RolDao;
import tienda.demo.domain.Usuario;
import tienda.demo.domain.Rol;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;
    @Autowired
    private RolDao rolDao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) throw new UsernameNotFoundException(username);
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioByUsernameAndPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioByUsernameOrCorreo(String username, String correo) {
        return usuarioDao.findByUsernameOrCorreo(username, correo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean UsuarioExistsByUsernameOrCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    }
    
    @Override
    @Transactional
    public void save(Usuario usuario, boolean makeUserRole) {
        usuario=usuarioDao.save(usuario);
        if (makeUserRole) {
            Rol role = new Rol();
            role.setNombre("ROLE_USER");
            role.setIdUsuario(usuario.getIdUsuario());
            rolDao.save(role);
        }
    }
    
    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
