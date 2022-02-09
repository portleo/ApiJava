/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.entidades;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pelicula {
      @Id    
   @GeneratedValue (strategy = GenerationType.IDENTITY) 
   private int id;
   private String imagen;
   private String titulo;
   private int anio;
   private int dia;
   private int mes;
   private LocalDateTime fechaCreacion;
   private int calificacion;
   
   @OneToMany (mappedBy = "pelicula")
   private List <Personaje> personajes;;
   @ManyToOne
   private Genero genero;

    public Pelicula() {
    }

    public Pelicula(int id, String imagen, String titulo, int anio, int dia, int mes, LocalDateTime fechaCreacion, int calificacion, List<Personaje> personajes, Genero genero) {
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.anio = anio;
        this.dia = dia;
        this.mes = mes;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.personajes = personajes;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
   
   
}

