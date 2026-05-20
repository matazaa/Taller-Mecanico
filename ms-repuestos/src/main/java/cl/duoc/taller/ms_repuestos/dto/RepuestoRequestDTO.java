package cl.duoc.taller.ms_repuestos.dto;

import jakarta.validation.constraints.*;

public class RepuestoRequestDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @Size(max = 50)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Integer precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Size(max = 50)
    private String marca;

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Integer getPrecio() { return precio; }
    public Integer getStock() { return stock; }
    public String getMarca() { return marca; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(Integer precio) { this.precio = precio; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setMarca(String marca) { this.marca = marca; }
}
