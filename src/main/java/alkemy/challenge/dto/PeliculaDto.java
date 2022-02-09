
/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PeliculaDto {
      private String titulo;        
    private String foto;
    private int calificacion;   
    private int dia;
    private int mes;
    private int anio;
    private LocalDateTime creacion;
//    private List <Personaje> personajes;
//    private Genero genero;
    private List< Integer> idPersonajes;
    private int idGenero;

    public PeliculaDto() {
    }

    public PeliculaDto(String titulo, String foto, int calificacion, int dia, int mes, int anio, LocalDateTime creacion, List<Integer> idPersonajes, int idGenero) {
        this.titulo = titulo;
        this.foto = foto;
        this.calificacion = calificacion;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.creacion = creacion;
        this.idPersonajes = idPersonajes;
        this.idGenero = idGenero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    public List<Integer> getIdPersonajes() {
        return idPersonajes;
    }

    public void setIdPersonajes(List<Integer> idPersonajes) {
        this.idPersonajes = idPersonajes;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
    
}
