package cl.duoc.taller.ms_mecanicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MecanicoRequestDTO {

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^[0-9]{1,2}\\.?[0-9]{3}\\.?[0-9]{3}-[0-9kK]$",
             message = "RUT con formato invalido")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50)
    private String apellido;

    @Size(max = 50)
    private String especialidad;

    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Telefono invalido")
    private String telefono;

    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEspecialidad() { return especialidad; }
    public String getTelefono() { return telefono; }

    public void setRut(String rut) { this.rut = rut; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
