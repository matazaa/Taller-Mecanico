package cl.duoc.taller.ms_facturacion.dto;

import cl.duoc.taller.ms_facturacion.client.dto.ClienteDTO;
import cl.duoc.taller.ms_facturacion.client.dto.OrdenTrabajoDTO;

import java.time.LocalDate;

public class FacturaResponseDTO {

    private Long id;
    private String numeroDocumento;
    private Long ordenTrabajoId;
    private Long clienteId;
    private LocalDate fecha;
    private Integer total;
    private String metodoPago;
    private String estado;
    private ClienteDTO cliente;
    private OrdenTrabajoDTO ordenTrabajo;

    public FacturaResponseDTO() {}

    public Long getId() { return id; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public Long getClienteId() { return clienteId; }
    public LocalDate getFecha() { return fecha; }
    public Integer getTotal() { return total; }
    public String getMetodoPago() { return metodoPago; }
    public String getEstado() { return estado; }
    public ClienteDTO getCliente() { return cliente; }
    public OrdenTrabajoDTO getOrdenTrabajo() { return ordenTrabajo; }

    public void setId(Long id) { this.id = id; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTotal(Integer total) { this.total = total; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }
    public void setOrdenTrabajo(OrdenTrabajoDTO ordenTrabajo) { this.ordenTrabajo = ordenTrabajo; }
}
