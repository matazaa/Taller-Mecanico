package cl.duoc.taller.ms_ordenes_trabajo.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class OrdenTrabajoRequestDTO {

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @NotNull(message = "El equipoId es obligatorio")
    private Long equipoId;

    private Long mecanicoId; // puede asignarse despues

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;

    private LocalDate fechaSalida;

    @NotBlank(message = "La descripcion del problema es obligatoria")
    @Size(max = 500)
    private String descripcionProblema;

    @Pattern(regexp = "^(PENDIENTE|EN_PROCESO|TERMINADO|ENTREGADO)$",
             message = "Estado invalido. Valores: PENDIENTE, EN_PROCESO, TERMINADO, ENTREGADO")
    private String estado;

    public Long getClienteId() { return clienteId; }
    public Long getEquipoId() { return equipoId; }
    public Long getMecanicoId() { return mecanicoId; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public String getDescripcionProblema() { return descripcionProblema; }
    public String getEstado() { return estado; }

    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }
    public void setMecanicoId(Long mecanicoId) { this.mecanicoId = mecanicoId; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setDescripcionProblema(String descripcionProblema) { this.descripcionProblema = descripcionProblema; }
    public void setEstado(String estado) { this.estado = estado; }
}
