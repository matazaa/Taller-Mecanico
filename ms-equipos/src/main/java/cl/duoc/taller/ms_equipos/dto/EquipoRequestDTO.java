package cl.duoc.taller.ms_equipos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EquipoRequestDTO {

    @NotBlank(message = "El tipo de maquina es obligatorio")
    @Size(max = 50, message = "El tipo de maquina no debe superar 50 caracteres")
    private String tipoMaquina;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50)
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 50)
    private String modelo;

    @NotBlank(message = "El tipo de motor es obligatorio")
    @Pattern(regexp = "^(2T|4T|ELECTRICO|DIESEL)$",
             message = "Tipo de motor invalido. Valores: 2T, 4T, ELECTRICO, DIESEL")
    private String tipoMotor;

    @Size(max = 100)
    private String numeroSerie;

    public String getTipoMaquina() { return tipoMaquina; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getTipoMotor() { return tipoMotor; }
    public String getNumeroSerie() { return numeroSerie; }

    public void setTipoMaquina(String tipoMaquina) { this.tipoMaquina = tipoMaquina; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setTipoMotor(String tipoMotor) { this.tipoMotor = tipoMotor; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
}
