/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.dto;

import alkemy.challenge.entidades.Pelicula;

public class PersonajeDto {
      private String imagen;
    private String nombre;
    private int edad;
    private Float Peso;
    private String historia;
    private Pelicula pelicula;
    private int idPelicula;

    public PersonajeDto() {
    }

    public PersonajeDto(String imagen, String nombre, int edad, Float Peso, String historia, Pelicula pelicula, int idPelicula) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.Peso = Peso;
        this.historia = historia;
        this.pelicula = pelicula;
        this.idPelicula = idPelicula;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Float getPeso() {
        return Peso;
    }

    public void setPeso(Float Peso) {
        this.Peso = Peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
  
}


