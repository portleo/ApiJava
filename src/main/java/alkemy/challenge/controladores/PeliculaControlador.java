/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.controladores;

import alkemy.challenge.dto.Mensaje;
import alkemy.challenge.dto.PeliculaDto;
import alkemy.challenge.entidades.Pelicula;
import alkemy.challenge.servicios.GeneroServicio;
import alkemy.challenge.servicios.PeliculaServicio;
import java.time.LocalDateTime;
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

@RestController
@RequestMapping("/pelicula")
@CrossOrigin
public class PeliculaControlador {

    @Autowired
    PeliculaServicio peliculaServicio;

    @Autowired
    GeneroServicio generoServicio;

    @GetMapping("/lista")
    public ResponseEntity<List<Pelicula>> list() {
        List<Pelicula> list = peliculaServicio.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<String>> listaImagenTituloFecha() {
        List<String> listaImgTitFec = peliculaServicio.listaImagenTituloFecha();
        return new ResponseEntity(listaImgTitFec, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Pelicula> getPorId(@PathVariable("id") int id) {
        if (!peliculaServicio.existById(id)) {
            return new ResponseEntity(new Mensaje("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        }
        Pelicula pelicula;
        pelicula = peliculaServicio.getOne(id).get();
        return new ResponseEntity(pelicula, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PeliculaDto peliculaDto) {
        if (peliculaDto.getTitulo() == null || peliculaDto.getTitulo().isEmpty()) {
            return new ResponseEntity(new Mensaje("El titulo no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getIdGenero() == 0) {
            return new ResponseEntity(new Mensaje("El Genero no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getCalificacion() < 1 || peliculaDto.getCalificacion() > 5) {
            return new ResponseEntity(new Mensaje("La calificacion debe ser entre 1 y 5"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getDia() < 1 || peliculaDto.getDia() > 31) {
            return new ResponseEntity(new Mensaje("El dia es incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getMes() < 1 || peliculaDto.getMes() > 12) {
            return new ResponseEntity(new Mensaje("El mes es incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaServicio.existByTitulo(peliculaDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("Esa pelicula ya existe"), HttpStatus.BAD_REQUEST);
        }
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(peliculaDto.getTitulo());
        pelicula.setCalificacion(peliculaDto.getCalificacion());
        pelicula.setDia(peliculaDto.getDia());
        pelicula.setMes(peliculaDto.getMes());
        pelicula.setAnio(peliculaDto.getAnio());
        pelicula.setFechaCreacion(LocalDateTime.of(peliculaDto.getAnio(), peliculaDto.getMes(), peliculaDto.getDia(), 0, 0));
        pelicula.setImagen(peliculaDto.getFoto());
        peliculaServicio.save(pelicula);
        pelicula.setGenero(peliculaServicio.agregarGenero(pelicula.getId(), peliculaDto.getIdGenero()));
        // generoServicio.setearPeliculas(pelicula.getId(),pelicula.getGenero().getId());
        peliculaServicio.save(pelicula);

        return new ResponseEntity(new Mensaje("Pelicula creada exitosamente."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> upadte(@PathVariable("id") int id, @RequestBody PeliculaDto peliculaDto) {
        if (!peliculaServicio.existById(id)) {
            return new ResponseEntity(new Mensaje("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        }
        if (peliculaServicio.existByTitulo(peliculaDto.getTitulo()) && peliculaServicio.getByTitulo(peliculaDto.getTitulo()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa pelicula ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getTitulo() == null || peliculaDto.getTitulo().isEmpty()) {
            return new ResponseEntity(new Mensaje("El titulo no puede ser nulo o estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getCalificacion() < 1 || peliculaDto.getCalificacion() > 5) {
            return new ResponseEntity(new Mensaje("La calificacion debe ser entre 1 y 5"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getDia() < 1 || peliculaDto.getDia() > 31) {
            return new ResponseEntity(new Mensaje("El dia es incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getMes() < 1 || peliculaDto.getMes() > 12) {
            return new ResponseEntity(new Mensaje("El mes es incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (peliculaDto.getIdGenero() == 0) {
            return new ResponseEntity(new Mensaje("Debe ingresar un id de Genero y este no puede ser el numero 0"), HttpStatus.BAD_REQUEST);
        }

        Pelicula pelicula = peliculaServicio.getOne(id).get();
        pelicula.setTitulo(peliculaDto.getTitulo());
        pelicula.setCalificacion(peliculaDto.getCalificacion());
        pelicula.setDia(peliculaDto.getDia());
        pelicula.setMes(peliculaDto.getMes());
        pelicula.setAnio(peliculaDto.getAnio());
        pelicula.setFechaCreacion(LocalDateTime.of(peliculaDto.getAnio(), peliculaDto.getMes(), peliculaDto.getDia(), 0, 0));
        pelicula.setImagen(peliculaDto.getFoto());
        pelicula.setPersonajes(peliculaServicio.vincularExistentes(pelicula.getId()));
        pelicula.setGenero(peliculaServicio.agregarGenero(id, peliculaDto.getIdGenero()));
        generoServicio.agregarPeliculas(id, peliculaDto.getIdGenero());
        peliculaServicio.save(pelicula);
        return new ResponseEntity(new Mensaje("Pelicula Modificada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!peliculaServicio.existById(id)) {
            return new ResponseEntity(new Mensaje("Ese id de pelicula es inexistente"), HttpStatus.NOT_FOUND);
        }
        peliculaServicio.deleteById(id);
        return new ResponseEntity(new Mensaje("Pelicula Eliminada"), HttpStatus.OK);
    }
}
