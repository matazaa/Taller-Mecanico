package cl.duoc.taller.ms_repuestos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "repuestos")
public class Repuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String codigo; // ej: BUJ-001, FIL-AIR-23

    @Column(nullable = false, length = 100)
    private String nombre; // ej: Bujia NGK, Filtro de aire

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 50)
    private String marca; // ej: Stihl, Husqvarna

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Integer getPrecio() { return precio; }
    public Integer getStock() { return stock; }
    public String getMarca() { return marca; }

    public void setId(Long id) { this.id = id; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(Integer precio) { this.precio = precio; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setMarca(String marca) { this.marca = marca; }
}
