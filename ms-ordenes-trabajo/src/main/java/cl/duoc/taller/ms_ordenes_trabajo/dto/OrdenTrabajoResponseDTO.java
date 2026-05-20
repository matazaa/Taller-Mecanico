package cl.duoc.taller.ms_ordenes_trabajo.dto;

import cl.duoc.taller.ms_ordenes_trabajo.client.dto.ClienteDTO;
import cl.duoc.taller.ms_ordenes_trabajo.client.dto.EquipoDTO;
import cl.duoc.taller.ms_ordenes_trabajo.client.dto.MecanicoDTO;

import java.time.LocalDate;

/**
 * Respuesta enriquecida: incluye los IDs y, opcionalmente, los datos
 * remotos del cliente/equipo/mecanico obtenidos via Feign.
 */
public class OrdenTrabajoResponseDTO {

    private Long id;
    private Long clienteId;
    private Long equipoId;
    private Long mecanicoId;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private String descripcionProblema;
    private String estado;

    // Datos remotos (pueden venir nulos si solo es un listado simple)
    private ClienteDTO cliente;
    private EquipoDTO equipo;
    private MecanicoDTO mecanico;

    public OrdenTrabajoResponseDTO() {}

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public Long getEquipoId() { return equipoId; }
    public Long getMecanicoId() { return mecanicoId; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public String getDescripcionProblema() { return descripcionProblema; }
    public String getEstado() { return estado; }
    public ClienteDTO getCliente() { return cliente; }
    public EquipoDTO getEquipo() { return equipo; }
    public MecanicoDTO getMecanico() { return mecanico; }

    public void setId(Long id) { this.id = id; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }
    public void setMecanicoId(Long mecanicoId) { this.mecanicoId = mecanicoId; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setDescripcionProblema(String descripcionProblema) { this.descripcionProblema = descripcionProblema; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }
    public void setEquipo(EquipoDTO equipo) { this.equipo = equipo; }
    public void setMecanico(MecanicoDTO mecanico) { this.mecanico = mecanico; }
}
