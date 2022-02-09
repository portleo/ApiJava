/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.repositorios;

import alkemy.challenge.entidades.Personaje;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Integer> {

    Optional<Personaje> findByNombre(String nombre);

    boolean existsById(int id);

    boolean existsByNombre(String nombre);

    @Query("SELECT c.nombre, c.imagen FROM Personaje c")
    public List<String> listarNombreImagen();

    @Query("SELECT c FROM Personaje c WHERE c.nombre = :nombre")
    public List<Personaje> buscarPorNombre(String nombre);

    @Query("SELECT c FROM Personaje c WHERE c.edad = :edad")
    public List<Personaje> buscarPorEdad(int edad);

    @Query(value = "SELECT c.nombre, c.edad, c.peso, c.historia, c.imagen FROM Personaje c WHERE pelicula_id = :id", nativeQuery = true)
    public List<Personaje> buscarPorPelicula(int id);
}
