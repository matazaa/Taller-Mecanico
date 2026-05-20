package cl.duoc.taller.ms_servicios.dto;

public class ServicioResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer duracionMinutos;

    public ServicioResponseDTO() {}

    public ServicioResponseDTO(Long id, String nombre, String descripcion, Integer precio, Integer duracionMinutos) {
        this.id = id; this.nombre = nombre; this.descripcion = descripcion;
        this.precio = precio; this.duracionMinutos = duracionMinutos;
    }

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
