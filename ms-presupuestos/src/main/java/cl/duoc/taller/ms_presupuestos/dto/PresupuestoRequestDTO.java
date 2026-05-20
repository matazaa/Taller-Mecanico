package cl.duoc.taller.ms_presupuestos.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PresupuestoRequestDTO {

    @NotNull(message = "El ordenTrabajoId es obligatorio")
    private Long ordenTrabajoId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser mayor a 0")
    private Integer total;

    @Size(max = 500)
    private String detalle;

    @Pattern(regexp = "^(PENDIENTE|APROBADO|RECHAZADO)$",
             message = "Estado invalido. Valores: PENDIENTE, APROBADO, RECHAZADO")
    private String estado;

    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public LocalDate getFecha() { return fecha; }
    public Integer getTotal() { return total; }
    public String getDetalle() { return detalle; }
    public String getEstado() { return estado; }

    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTotal(Integer total) { this.total = total; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public void setEstado(String estado) { this.estado = estado; }
}
