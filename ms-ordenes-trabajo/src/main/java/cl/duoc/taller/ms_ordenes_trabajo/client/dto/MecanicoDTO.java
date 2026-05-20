package cl.duoc.taller.ms_ordenes_trabajo.client.dto;

public class MecanicoDTO {
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String especialidad;

    public Long getId() { return id; }
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEspecialidad() { return especialidad; }

    public void setId(Long id) { this.id = id; }
    public void setRut(String rut) { this.rut = rut; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}
