/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.servicios;

import alkemy.challenge.entidades.Personaje;
import alkemy.challenge.repositorios.PersonajeRepositorio;
import java.util.ArrayList;
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
public class PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    
    //CREACION PERSONAJE
    public void crearPersonaje(Personaje personaje) {
        personajeRepositorio.save(personaje);
    }
// CREA Y DEVUELVE PERSONAJE CON EL ID
    public Personaje creaDevuelvePersonaje(Personaje personaje) {
        return personajeRepositorio.save(personaje);
    }
// DEVUELVE LISTA DE PERSONAJES
    public ArrayList<Personaje> obtenerPersonajes() {
        return (ArrayList<Personaje>) personajeRepositorio.findAll();
    }
// DEVUELVE LISTA DE NOMBRE E IMAGEN DE PERSONAJE
    public List<String> listarNombreImagen() {
        return (List<String>) personajeRepositorio.listarNombreImagen();
    }
// MODIFICAR PERSONAJE
    public void modificarPersonajePorId(int id) {

    }
// ELIMINA PERSONAJE
    public void eliminarPersonajePorId(int id) {
        personajeRepositorio.deleteById(id);
    }

    public Optional<Personaje> getOne(int id) {
        return personajeRepositorio.findById(id);
    }

    public Optional<Personaje> getByNombre(String nombre) {
        return personajeRepositorio.findByNombre(nombre);
    }

    public boolean existsById(int id) {
        return personajeRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return personajeRepositorio.existsByNombre(nombre);
    }

    public List<Personaje> buscarPorNombre(String nombre) {
        return personajeRepositorio.buscarPorNombre(nombre);
    }

    public List<Personaje> buscarPorEdad(int edad) {
        return personajeRepositorio.buscarPorEdad(edad);
    }
}
