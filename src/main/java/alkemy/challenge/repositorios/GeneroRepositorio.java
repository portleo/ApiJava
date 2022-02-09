/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.repositorios;

import alkemy.challenge.entidades.Genero;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 
 */
@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, Integer>{
      Optional <Genero> findByNombre (String nombre);
    boolean existsById(int id);
    boolean existsByNombre(String nombre);
     
    @Query ("SELECT c FROM Genero c")
    public List <Genero> listarGeneros ();
}

