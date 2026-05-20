package cl.duoc.taller.ms_clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para recibir datos del cliente desde el cliente HTTP.
 * Aqui aplicamos Bean Validation (JSR 380).
 */
public class ClienteRequestDTO {

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^[0-9]{1,2}\\.?[0-9]{3}\\.?[0-9]{3}-[0-9kK]$",
             message = "El RUT debe tener formato 12.345.678-9 o 12345678-9")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;

    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Telefono invalido")
    private String telefono;

    @Email(message = "El correo no tiene un formato valido")
    private String correo;

    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }

    public void setRut(String rut) { this.rut = rut; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
}
