package cl.duoc.taller.ms_ordenes_trabajo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId; // referencia al cliente (ms-clientes)

    @Column(nullable = false)
    private Long equipoId; // referencia al equipo (ms-equipos)

    private Long mecanicoId; // referencia al mecanico asignado (ms-mecanicos)

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    private LocalDate fechaSalida;

    @Column(length = 500)
    private String descripcionProblema;

    @Column(length = 20)
    private String estado; // PENDIENTE, EN_PROCESO, TERMINADO, ENTREGADO

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
