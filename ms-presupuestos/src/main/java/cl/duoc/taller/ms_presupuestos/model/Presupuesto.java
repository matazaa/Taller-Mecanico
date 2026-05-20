package cl.duoc.taller.ms_presupuestos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "presupuestos")
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ordenTrabajoId; // referencia a ms-ordenes-trabajo

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer total;

    @Column(length = 500)
    private String detalle; // descripcion de items (mano de obra + repuestos)

    @Column(length = 20)
    private String estado; // PENDIENTE, APROBADO, RECHAZADO

    public Long getId() { return id; }
    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public LocalDate getFecha() { return fecha; }
    public Integer getTotal() { return total; }
    public String getDetalle() { return detalle; }
    public String getEstado() { return estado; }

    public void setId(Long id) { this.id = id; }
    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTotal(Integer total) { this.total = total; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public void setEstado(String estado) { this.estado = estado; }
}
