/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genero {
      @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)   
    private int id;
    private String nombre;
    private String imagen;
    @JsonIgnore
    @OneToMany (mappedBy = "genero")
    private List <Pelicula> peliculas;
   // porque one to many si en realidad un genero tiene una lista ??

    public Genero() {
    }

    public Genero(int id, String nombre, String imagen, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.peliculas = peliculas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Pelicula>  getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    
    
    
}


