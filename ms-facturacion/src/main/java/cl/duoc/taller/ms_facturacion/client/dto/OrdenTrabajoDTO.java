package cl.duoc.taller.ms_facturacion.client.dto;

import java.time.LocalDate;

public class OrdenTrabajoDTO {
    private Long id;
    private Long clienteId;
    private Long equipoId;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private String estado;

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public Long getEquipoId() { return equipoId; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public String getEstado() { return estado; }

    public void setId(Long id) { this.id = id; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setEstado(String estado) { this.estado = estado; }
}
