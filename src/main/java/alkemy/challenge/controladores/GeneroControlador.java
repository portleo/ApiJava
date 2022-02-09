/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.controladores;

import alkemy.challenge.dto.GeneroDto;
import alkemy.challenge.dto.Mensaje;
import alkemy.challenge.entidades.Genero;
import alkemy.challenge.repositorios.PeliculaRepositorio;
import alkemy.challenge.servicios.GeneroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sebastian
 */
@RestController
@RequestMapping("/genero")
@CrossOrigin
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    @GetMapping("/lista")
    public ResponseEntity<List<Genero>> listarTodos() {
        List<Genero> lista = generoServicio.listarGeneros();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody GeneroDto generoDto) {
        if (generoDto.getNombre() == null || generoDto.getNombre().isEmpty()) {
            return new ResponseEntity(new Mensaje("El nombre no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        }
         if (generoServicio.existsByNombre(generoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese genero ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (generoDto.getPeliculas() == null) {
            return new ResponseEntity(new Mensaje("Ingrese una pelicula para el Genero"), HttpStatus.BAD_REQUEST);
        }
       
        Genero genero = new Genero();
        genero.setNombre(generoDto.getNombre());
        genero.setImagen(generoDto.getImagen());
        genero.setPeliculas(generoDto.getPeliculas());
        generoServicio.crearGenero(genero);

        return new ResponseEntity(new Mensaje("Genero creado exitosamente."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> upadte(@PathVariable("id") int id, @RequestBody GeneroDto generoDto) {
        if (!generoServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("Ese id de Genero es inexistente"), HttpStatus.NOT_FOUND);
        }
        if (generoServicio.existsByNombre(generoDto.getNombre()) && generoServicio.getByNombre(generoDto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese genero ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (generoDto.getNombre() == null || generoDto.getNombre().isEmpty()) {
            return new ResponseEntity(new Mensaje("El nombre del Genero no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Genero genero = generoServicio.getOne(id).get();
        genero.setNombre(generoDto.getNombre());
        genero.setImagen(generoDto.getImagen());
        genero.setPeliculas(generoDto.getPeliculas());
        genero.setPeliculas(peliculaRepositorio.listarPorGenero(genero.getId()));

        return new ResponseEntity(new Mensaje("Genero Modificado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!generoServicio.existsById(id)) {
            return new ResponseEntity(new Mensaje("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        }
        generoServicio.eliminarGeneroPorId(id);
        return new ResponseEntity(new Mensaje("Genero Eliminado"), HttpStatus.OK);
    }
}
