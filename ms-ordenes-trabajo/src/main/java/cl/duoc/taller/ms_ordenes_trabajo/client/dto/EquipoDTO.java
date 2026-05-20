package cl.duoc.taller.ms_ordenes_trabajo.client.dto;

public class EquipoDTO {
    private Long id;
    private String tipoMaquina;
    private String marca;
    private String modelo;

    public Long getId() { return id; }
    public String getTipoMaquina() { return tipoMaquina; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }

    public void setId(Long id) { this.id = id; }
    public void setTipoMaquina(String tipoMaquina) { this.tipoMaquina = tipoMaquina; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
}
