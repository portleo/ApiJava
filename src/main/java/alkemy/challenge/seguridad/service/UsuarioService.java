/**
 *
 * @author Sebastian Vargas Zingaretti
 * mail vargaszingaretti@gmail.com
 * 
 */
package alkemy.challenge.seguridad.service;

import alkemy.challenge.seguridad.entity.Usuario;
import alkemy.challenge.seguridad.repository.UsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastian
 */
@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existByNombreUsuario(String nombreusuario) {
        return usuarioRepository.existsByNombreUsuario(nombreusuario);
    }

    public boolean existByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

}
