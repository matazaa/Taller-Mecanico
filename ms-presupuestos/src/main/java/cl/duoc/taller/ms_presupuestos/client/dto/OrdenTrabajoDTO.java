package cl.duoc.taller.ms_presupuestos.client.dto;

import java.time.LocalDate;

public class OrdenTrabajoDTO {
    private Long id;
    private Long clienteId;
    private Long equipoId;
    private Long mecanicoId;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private String descripcionProblema;
    private String estado;

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public Long getEquipoId() { return equipoId; }
    public Long getMecanicoId() { return mecanicoId; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public String getDescripcionProblema() { return descripcionProblema; }
    public String getEstado() { return estado; }

    public void setId(Long id) { this.id = id; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }
    public void setMecanicoId(Long mecanicoId) { this.mecanicoId = mecanicoId; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setDescripcionProblema(String descripcionProblema) { this.descripcionProblema = descripcionProblema; }
    public void setEstado(String estado) { this.estado = estado; }
}
