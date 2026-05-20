package cl.duoc.taller.ms_mecanicos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mecanicos")
public class Mecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 15)
    private String rut;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(length = 50)
    private String especialidad; // ej: Motosierras, Motores 2T, Generadores

    @Column(length = 20)
    private String telefono;

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
