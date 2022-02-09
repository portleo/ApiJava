/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.seguridad.repository;

import alkemy.challenge.seguridad.entity.Rol;
import alkemy.challenge.seguridad.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository <Rol, Integer> {
   Optional <Rol> findByRolNombre (RolNombre rolNombre);
}
