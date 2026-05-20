package cl.duoc.taller.ms_mecanicos.dto;

public class MecanicoResponseDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;

    public MecanicoResponseDTO() {}

    public MecanicoResponseDTO(Long id, String rut, String nombre, String apellido, String especialidad, String telefono) {
        this.id = id; this.rut = rut; this.nombre = nombre;
        this.apellido = apellido; this.especialidad = especialidad; this.telefono = telefono;
    }

    public Long getId() { return id; }
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEspecialidad() { return especialidad; }
    public String getTelefono() { return telefono; }

    public void setId(Long id) { this.id = id; }
    public void setRut(String rut) { this.rut = rut; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
