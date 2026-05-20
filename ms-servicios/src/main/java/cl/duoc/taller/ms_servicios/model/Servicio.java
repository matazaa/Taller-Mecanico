package cl.duoc.taller.ms_servicios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre; // ej: Afilado de cadena, Cambio de aceite

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Integer precio;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos; // duracion estimada del servicio

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Integer getPrecio() { return precio; }
    public Integer getDuracionMinutos() { return duracionMinutos; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(Integer precio) { this.precio = precio; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }
}
