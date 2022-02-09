/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.servicios;

import alkemy.challenge.entidades.Genero;
import alkemy.challenge.entidades.Pelicula;
import alkemy.challenge.repositorios.GeneroRepositorio;
import alkemy.challenge.repositorios.PeliculaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 
 */
@Service
@Transactional
   public class GeneroServicio {
   
    @Autowired
    private GeneroRepositorio generoRepositorio;
  
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    
    public List<Genero> listarGeneros() {
     return generoRepositorio.listarGeneros();
    }

    public Optional<Genero> getOne(int id) {
        return generoRepositorio.findById(id);
    }

    public Optional<Genero> getByNombre(String nombre) {
        return generoRepositorio.findByNombre(nombre);
    }

    public void crearGenero(Genero genero) {      
        generoRepositorio.save(genero);
    }

    public void eliminarGeneroPorId(int id) {
        generoRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return generoRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return generoRepositorio.existsByNombre(nombre);
    }
    
    public void agregarPeliculas (int idPelicula, int idGenero) {
        List <Pelicula> peliculas = peliculaRepositorio.listarPorGenero(idGenero);
        Genero genero = generoRepositorio.getById(idGenero);
        genero.setPeliculas(peliculas);
        generoRepositorio.save(genero);
        
    }
} 