package cl.duoc.taller.ms_facturacion.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroDocumento; // numero correlativo de la boleta/factura

    @Column(nullable = false)
    private Long ordenTrabajoId; // referencia a ms-ordenes-trabajo

    @Column(nullable = false)
    private Long clienteId; // referencia a ms-clientes

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer total;

    @Column(length = 30)
    private String metodoPago; // EFECTIVO, TRANSFERENCIA, TARJETA

    @Column(length = 20)
    private String estado; // EMITIDA, PAGADA, ANULADA

    public Long getId() { return id; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public Long getClienteId() { return clienteId; }
    public LocalDate getFecha() { return fecha; }
    public Integer getTotal() { return total; }
    public String getMetodoPago() { return metodoPago; }
    public String getEstado() { return estado; }

    public void setId(Long id) { this.id = id; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTotal(Integer total) { this.total = total; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public void setEstado(String estado) { this.estado = estado; }
}
