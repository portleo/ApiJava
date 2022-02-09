/**
 *
 * @author Sebastian Vargas Zingaretti
 * mail vargaszingaretti@gmail.com
 * 
 */
package alkemy.challenge.seguridad.service;

import alkemy.challenge.seguridad.entity.Usuario;
import alkemy.challenge.seguridad.entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastian
 */
@Service
public class UsersDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UsuarioService usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
    Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
    return UsuarioPrincipal.build(usuario);
    }
    
}
