package cl.duoc.taller.ms_ordenes_trabajo.client.dto;

/**
 * DTO que representa la respuesta del microservicio ms-clientes.
 * Solo incluye los campos que necesitamos aqui.
 */
public class ClienteDTO {
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;

    public Long getId() { return id; }
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    public void setId(Long id) { this.id = id; }
    public void setRut(String rut) { this.rut = rut; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}
