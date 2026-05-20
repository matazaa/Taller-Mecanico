package cl.duoc.taller.ms_equipos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String tipoMaquina; // ej: Desbrozadora, Motosierra, Generador

    @Column(nullable = false, length = 50)
    private String marca; // ej: Stihl, Husqvarna

    @Column(nullable = false, length = 50)
    private String modelo; // ej: FS 85

    @Column(nullable = false, length = 20)
    private String tipoMotor; // ej: 2T, 4T

    @Column(name = "numero_serie", unique = true, length = 100)
    private String numeroSerie;

    public Long getId() {
        return id;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
}
