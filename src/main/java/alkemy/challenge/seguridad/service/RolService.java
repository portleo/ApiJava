/**
 *
 * @author Sebastian Vargas Zingaretti
 * mail vargaszingaretti@gmail.com
 * 
 */
package alkemy.challenge.seguridad.service;

import alkemy.challenge.seguridad.entity.Rol;
import alkemy.challenge.seguridad.enums.RolNombre;
import alkemy.challenge.seguridad.repository.RolRepository;
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
public class RolService {
    
    @Autowired
    RolRepository rolRepository;
     
    public Optional <Rol> getByNombre(RolNombre rolNombre){
    return rolRepository.findByRolNombre(rolNombre);
    }  
    
    public void save (Rol rol){
        rolRepository.save(rol);
    }
}
