package br.com.gabriel.gestaoagricola.domain;

import java.time.LocalDate;

public class Plantio {
    private final int idPlantio;
    private int idAreaCultivo;
    private int idCultura;
    private LocalDate dataPlantio;
    private int quantidadePlantada;
    private String unidadeMedida;

    public Plantio(int idPlantio, int idAreaCultivo, int idCultura, LocalDate dataPlantio, int quantidadePlantada, String unidadeMedida) {

        if (idPlantio <= 0) {
            throw new IllegalArgumentException("O Id do Plantio deve ser positivo");
        }
        if (idAreaCultivo <= 0) {
            throw new IllegalArgumentException("a Área de Cultivo deve existir");
        }
        if (idCultura <= 0) {
            throw new IllegalArgumentException("A Cultura deve existir");
        }
        if (dataPlantio == null) {
            throw new IllegalArgumentException("A data do Plantio não pode ser nula");
        } else if (dataPlantio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de Plantio não pode ser futura");
        }
        if (quantidadePlantada <= 0) {
            throw new IllegalArgumentException("A quantidade plantada deve ser positiva");
        }
        if (unidadeMedida == null || unidadeMedida.isBlank()) {
            throw new IllegalArgumentException("A unidade de medida deve existir");
        }

        this.idPlantio = idPlantio;
        this.idAreaCultivo = idAreaCultivo;
        this.idCultura = idCultura;
        this.dataPlantio = dataPlantio;
        this.quantidadePlantada = quantidadePlantada;
        this.unidadeMedida = unidadeMedida;
    }

    public int getIdPlantio() {
        return idPlantio;
    }

    public int getIdAreaCultivo() {
        return idAreaCultivo;
    }

    public int getIdCultura() {
        return idCultura;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public int getQuantidadePlantada() {
        return quantidadePlantada;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    @Override
    public String toString() {
        return "[Id: " + getIdPlantio() + " | ID Área de Cultivo: " + getIdAreaCultivo() + " | ID Cultura: " + getIdCultura() + " | Data de Planto: " + getDataPlantio() + " | Quantidade Plantada: " + getQuantidadePlantada() + " " + getUnidadeMedida() + "]";
    }
}
