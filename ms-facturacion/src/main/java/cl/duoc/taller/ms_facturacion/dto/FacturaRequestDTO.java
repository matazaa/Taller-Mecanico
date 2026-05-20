package cl.duoc.taller.ms_facturacion.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class FacturaRequestDTO {

    @NotBlank(message = "El numero de documento es obligatorio")
    @Size(max = 30)
    private String numeroDocumento;

    @NotNull(message = "El ordenTrabajoId es obligatorio")
    private Long ordenTrabajoId;

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser mayor a 0")
    private Integer total;

    @NotBlank(message = "El metodo de pago es obligatorio")
    @Pattern(regexp = "^(EFECTIVO|TRANSFERENCIA|TARJETA)$",
             message = "Metodo de pago invalido. Valores: EFECTIVO, TRANSFERENCIA, TARJETA")
    private String metodoPago;

    @Pattern(regexp = "^(EMITIDA|PAGADA|ANULADA)$",
             message = "Estado invalido. Valores: EMITIDA, PAGADA, ANULADA")
    private String estado;

    public String getNumeroDocumento() { return numeroDocumento; }
    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public Long getClienteId() { return clienteId; }
    public LocalDate getFecha() { return fecha; }
    public Integer getTotal() { return total; }
    public String getMetodoPago() { return metodoPago; }
    public String getEstado() { return estado; }

    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTotal(Integer total) { this.total = total; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public void setEstado(String estado) { this.estado = estado; }
}
