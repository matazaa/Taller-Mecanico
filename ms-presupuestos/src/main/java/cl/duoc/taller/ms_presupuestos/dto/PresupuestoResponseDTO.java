package cl.duoc.taller.ms_presupuestos.dto;

import cl.duoc.taller.ms_presupuestos.client.dto.OrdenTrabajoDTO;
import java.time.LocalDate;

public class PresupuestoResponseDTO {

    private Long id;
    private Long ordenTrabajoId;
    private LocalDate fecha;
    private Integer total;
    private String detalle;
    private String estado;
    private OrdenTrabajoDTO ordenTrabajo;

    public PresupuestoResponseDTO() {}

    public Long getId() { return id; }
    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public LocalDate getFecha() { return fecha; }
    public Integer getTotal() { return total; }
    public String getDetalle() { return detalle; }
    public String getEstado() { return estado; }
    public OrdenTrabajoDTO getOrdenTrabajo() { return ordenTrabajo; }

    public void setId(Long id) { this.id = id; }
    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTotal(Integer total) { this.total = total; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setOrdenTrabajo(OrdenTrabajoDTO ordenTrabajo) { this.ordenTrabajo = ordenTrabajo; }
}
