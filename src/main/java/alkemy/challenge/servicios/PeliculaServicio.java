/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.servicios;

import alkemy.challenge.entidades.Genero;
import alkemy.challenge.entidades.Pelicula;
import alkemy.challenge.entidades.Personaje;
import alkemy.challenge.repositorios.GeneroRepositorio;
import alkemy.challenge.repositorios.PeliculaRepositorio;
import alkemy.challenge.repositorios.PersonajeRepositorio;
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
public class PeliculaServicio {
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
   
    @Autowired 
    private GeneroRepositorio generoRepositorio;
    
    @Autowired 
    private PersonajeRepositorio personajeRepositorio;
    
public List <Pelicula> list(){
   return peliculaRepositorio.findAll();
}

public List <String> listaImagenTituloFecha(){
   return peliculaRepositorio.imagenTituloFecha();
}


public Optional <Pelicula> getOne (int id){
return peliculaRepositorio.findById(id);
}

public Optional <Pelicula>  getByTitulo (String titulo){
 return peliculaRepositorio.findByTitulo(titulo);
}

public void save (Pelicula pelicula){
peliculaRepositorio.save(pelicula);
}

public void deleteById (int id){
peliculaRepositorio.deleteById(id);
}

public boolean existById (int Id){
  return peliculaRepositorio.existsById(Id);
}

public boolean existByTitulo (String titulo){
    return peliculaRepositorio.existsByTitulo(titulo);
}

   public List<Pelicula> listarporGenero(int idGenero) {
        return peliculaRepositorio.listarPorGenero(idGenero);
    }

    public Pelicula buscarPorNombre (String titulo) {
        return peliculaRepositorio.buscarPorNombre(titulo);
    }
//    public List<Pelicula> listarCreacionASC() {
//        return peliculaRepositorio.listarCreacionASC();
//    }
//
//    public List<Pelicula> listarCreacionDESC() {
//        return peliculaRepositorio.listarCreacionDESC();
//    }
//    
     public Pelicula vincularPersonajesNuevoPers (Personaje personaje, int idPelicula) {
        
        Pelicula pelicula = peliculaRepositorio.getById(idPelicula);
        List <Personaje> peliculas = pelicula.getPersonajes();
        peliculas.add(personaje);
        peliculaRepositorio.save(pelicula);   
        return pelicula;
    }
  
    
    public Genero agregarGenero (int idPelicula, int idGenero){ 
        Pelicula pelicula = peliculaRepositorio.getById(idPelicula);
        Genero genero = generoRepositorio.getById(idGenero);
        pelicula.setGenero(genero);
        peliculaRepositorio.save(pelicula);
        return genero;
    }
    
        public List<Personaje> vincularExistentes (int id){
        List <Personaje> personajes = personajeRepositorio.buscarPorPelicula(id);
        Pelicula pelicula = peliculaRepositorio.getById(id); 
        for (Personaje personaje : personajes) {
            if (!personajeRepositorio.existsByNombre(personaje.getNombre()))
           personajes.add(personaje);
        }        
        pelicula.setPersonajes(personajes);
        peliculaRepositorio.save(pelicula);
        return personajes;
    }
}