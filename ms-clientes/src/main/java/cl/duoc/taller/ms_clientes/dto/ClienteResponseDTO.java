package cl.duoc.taller.ms_clientes.dto;

/**
 * DTO para responder al cliente HTTP. Separamos la entidad
 * de la respuesta para no exponer detalles internos de JPA.
 */
public class ClienteResponseDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    public ClienteResponseDTO() {}

    public ClienteResponseDTO(Long id, String rut, String nombre, String apellido, String telefono, String correo) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Long getId() { return id; }
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }

    public void setId(Long id) { this.id = id; }
    public void setRut(String rut) { this.rut = rut; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
}
