/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.seguridad.repository;

import alkemy.challenge.seguridad.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
Optional <Usuario> findByNombreUsuario  (String nombreUsuario);
boolean existsByNombreUsuario (String nombreUsuario);
boolean existsByEmail (String email);

        }
