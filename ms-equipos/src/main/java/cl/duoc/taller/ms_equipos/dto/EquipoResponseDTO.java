package cl.duoc.taller.ms_equipos.dto;

public class EquipoResponseDTO {

    private Long id;
    private String tipoMaquina;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private String numeroSerie;

    public EquipoResponseDTO() {}

    public EquipoResponseDTO(Long id, String tipoMaquina, String marca, String modelo, String tipoMotor, String numeroSerie) {
        this.id = id;
        this.tipoMaquina = tipoMaquina;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.numeroSerie = numeroSerie;
    }

    public Long getId() { return id; }
    public String getTipoMaquina() { return tipoMaquina; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getTipoMotor() { return tipoMotor; }
    public String getNumeroSerie() { return numeroSerie; }

    public void setId(Long id) { this.id = id; }
    public void setTipoMaquina(String tipoMaquina) { this.tipoMaquina = tipoMaquina; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setTipoMotor(String tipoMotor) { this.tipoMotor = tipoMotor; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
}
