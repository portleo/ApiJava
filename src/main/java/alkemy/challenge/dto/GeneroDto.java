/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.dto;

import alkemy.challenge.entidades.Pelicula;
import java.util.List;

public class GeneroDto {
    
    private String nombre;
    private String imagen;    
    private List <Pelicula> peliculas;
    private int idpelicula;

    public GeneroDto() {
    }

    public GeneroDto(String nombre, String imagen, List<Pelicula> peliculas, int idpelicula) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.peliculas = peliculas;
        this.idpelicula = idpelicula;
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

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public int getIdpelicula() {
        return idpelicula;
    }

    public void setIdpelicula(int idpelicula) {
        this.idpelicula = idpelicula;
    }
    
    
}
