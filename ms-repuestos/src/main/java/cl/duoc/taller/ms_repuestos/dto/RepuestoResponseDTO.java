package cl.duoc.taller.ms_repuestos.dto;

public class RepuestoResponseDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer stock;
    private String marca;

    public RepuestoResponseDTO() {}

    public RepuestoResponseDTO(Long id, String codigo, String nombre, String descripcion,
                               Integer precio, Integer stock, String marca) {
        this.id = id; this.codigo = codigo; this.nombre = nombre; this.descripcion = descripcion;
        this.precio = precio; this.stock = stock; this.marca = marca;
    }

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
