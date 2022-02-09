/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.repositorios;

import alkemy.challenge.entidades.Pelicula;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 
 */
@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer> {

    Optional<Pelicula> findByTitulo(String titulo);

    boolean existsByTitulo(String titulo);

    @Query("SELECT c.titulo, c.imagen, c.fechaCreacion FROM Pelicula c")
    public List<String> imagenTituloFecha();

    @Query("Select c FROM Pelicula c WHERE genero_id = :id")
    public List<Pelicula> listarPorGenero(int id);

    @Query("SELECT c FROM Pelicula c WHERE c.titulo = :titulo")
    public Pelicula buscarPorNombre(@Param("titulo") String titulo);
}
