/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.controladores;

import alkemy.challenge.dto.Mensaje;
import alkemy.challenge.servicios.PeliculaServicio;
import alkemy.challenge.dto.PersonajeDto;
import alkemy.challenge.entidades.Personaje;
import alkemy.challenge.repositorios.PersonajeRepositorio;
import alkemy.challenge.servicios.PersonajeServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personaje")
@CrossOrigin()
public class PersonajeControlador {
    @Autowired
    PersonajeServicio personajeServicio;
    @Autowired
    PersonajeRepositorio personajeRepositorio;
    @GetMapping()
    public ArrayList <Personaje> obtenerPersonajes(){
        return personajeServicio.obtenerPersonajes();
    } 
    
    @PostMapping ()
    public Personaje guardarPersonaje (@RequestBody  Personaje personaje ){
    return this.personajeServicio.creaDevuelvePersonaje(personaje);
}
     @GetMapping ("/characters")
    public ResponseEntity<Optional<String>> listarFotoNombre (){
        List<String> lista = personajeServicio.listarNombreImagen();        
        return new ResponseEntity (lista, HttpStatus.OK);
    }
    
        
    @GetMapping ("/detail/{id}")
    public ResponseEntity <Personaje> getPorId (@PathVariable ("id") int id) {
        if (!personajeServicio.existsById(id))
        return new ResponseEntity (new Mensaje ("Ese id de personaje es inexistente"), HttpStatus.NOT_FOUND);
        Personaje personaje;      
        personaje = personajeServicio.getOne(id).get();
        return new ResponseEntity (personaje, HttpStatus.OK);
        
    } 
    
    @PreAuthorize("hasRole ('ADMIN')")
        @PostMapping ("/create")    
    public ResponseEntity<?> create (@RequestBody PersonajeDto personajeDto) {
        if (personajeDto.getNombre()== null || personajeDto.getNombre().isEmpty())
            return new ResponseEntity (new Mensaje("El nombre no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getEdad() <= 0)
            return new ResponseEntity (new Mensaje("La edad no puede ser 0 o menor a 0"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getHistoria()== null || personajeDto.getHistoria().isEmpty())
            return new ResponseEntity (new Mensaje("La historia no puede ser nula o estar vacia"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getPeso() <= 0)
            return new ResponseEntity (new Mensaje("El peso no puede ser 0 o menor a 0"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getIdPelicula() == 0)
            return new ResponseEntity (new Mensaje("La pelicula no puede ser nula"),HttpStatus.BAD_REQUEST);
        if (personajeServicio.existsByNombre(personajeDto.getNombre()))
            return new ResponseEntity (new Mensaje ("Ese personaje ya existe"), HttpStatus.BAD_REQUEST);
        Personaje personaje = new Personaje ();
        personaje.setNombre(personajeDto.getNombre());
        personaje.setEdad(personajeDto.getEdad());
        personaje.setImagen(personaje.getImagen());
        personaje.setHistoria(personajeDto.getHistoria());
       // personaje.setPelicula(peliculaServicio.vincularPersonajesNuevoPers(personaje, personajeDto.getIdPelicula()));
        personaje.setPeso(personajeDto.getPeso()); 
        personajeServicio.crearPersonaje(personaje);
        return new ResponseEntity ( new Mensaje ("Personaje creado exitosamente."), HttpStatus.OK);
    } 

     
       @PutMapping ("/update/{id}")
    public ResponseEntity<?> upadte (@PathVariable ("id") int id, @RequestBody PersonajeDto personajeDto) {
        if (!personajeServicio.existsById(id))
            return new ResponseEntity (new Mensaje ("Ese id de personaje es inexistente"), HttpStatus.NOT_FOUND);
        if (personajeServicio.existsByNombre(personajeDto.getNombre()) && personajeServicio.getByNombre(personajeDto.getNombre()).get().getId() != id)
            return new ResponseEntity (new Mensaje ("Ese personaje ya existe"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getNombre()== null || personajeDto.getNombre().isEmpty())
            return new ResponseEntity (new Mensaje("El nombre no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getEdad() <= 0)
            return new ResponseEntity (new Mensaje("La edad no puede ser 0 o menor a 0"), HttpStatus.BAD_REQUEST);
        if (personajeDto.getHistoria()== null || personajeDto.getHistoria().isEmpty())
            return new ResponseEntity (new Mensaje("La historia no puede ser nula o estar vacia"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getPeso() <= 0)
            return new ResponseEntity (new Mensaje("El peso no puede ser 0 o menor a 0"),HttpStatus.BAD_REQUEST);
        if (personajeDto.getIdPelicula()== 0)
            return new ResponseEntity (new Mensaje("La id de pelicula no puede ser nula o 0"),HttpStatus.BAD_REQUEST);
        
        Personaje personaje = personajeServicio.getOne(id).get();         
        personaje.setNombre(personajeDto.getNombre());
        personaje.setEdad(personajeDto.getEdad());
        personaje.setImagen(personaje.getImagen());
        personaje.setHistoria(personajeDto.getHistoria());
        personaje.setPelicula(personajeDto.getPelicula());
        personaje.setPeso(personajeDto.getPeso());
        personajeRepositorio.save(personaje);
        return new ResponseEntity ( new Mensaje ("Personaje Modificado"), HttpStatus.OK);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable ("id") int id) {
        if (!personajeServicio.existsById(id))
            return new ResponseEntity (new Mensaje ("Ese id de personaje es inexistente"), HttpStatus.NOT_FOUND);
        personajeServicio.eliminarPersonajePorId(id);
        return new ResponseEntity ( new Mensaje ("Personaje Eliminado"), HttpStatus.OK);
    }
     @GetMapping ("/characters?name={nombre}")
    public ResponseEntity <Personaje> busquedaPorNombre (@PathVariable ("nombre") String nombre) {
//        if (!personajeServicio.existsByNombre(nombre))
//        return new ResponseEntity (new Mensaje ("El nombre del personaje es inexistente"), HttpStatus.BAD_REQUEST);
          Personaje personaje = personajeServicio.getByNombre(nombre).get();
        return new ResponseEntity (personaje, HttpStatus.OK);
    }
 
    @GetMapping ("/characters?age={edad}")
    public ResponseEntity <List<Personaje>> buscarPorEdad (@PathVariable ("edad") int edad) {    
        List<Personaje> lista = personajeServicio.buscarPorEdad(edad);
        return new ResponseEntity (lista, HttpStatus.OK); 
    }
   
//    @GetMapping ("/characters?movies={idPelicula})
//    public ResponseEntity <List<Personaje>> buscarPorPelicula (@PathVariable ("idPelicula") int idPelicula){    
//        List<Personaje> lista = personajeServicio.listarPorPelicula(idPelicula);
//        return new ResponseEntity (lista, HttpStatus.OK);
//    }  
}
    
    
