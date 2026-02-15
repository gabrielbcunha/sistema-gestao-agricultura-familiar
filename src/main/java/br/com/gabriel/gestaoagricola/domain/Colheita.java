package br.com.gabriel.gestaoagricola.domain;

import java.time.LocalDate;

public class Colheita {
    private final int idColheita;
    private int idPlantio;
    private LocalDate dataColheita;
    private int quantidadeColhida;
    private String unidadeMedida;
    private int perdas;

    public Colheita(int idColheita, int idPlantio, LocalDate dataColheita, int quantidadeColhida, String unidadeMedida, int perdas) {

        if (idColheita <= 0) {
            throw new IllegalArgumentException("O Id da Colheita deve ser positivo");
        }
        if (idPlantio <= 0) {
            throw new IllegalArgumentException("O Id de Plantio deve ser positivo");
        }
        if (dataColheita == null) {
            throw new IllegalArgumentException("Data de Colheita deve Existir");
        } else if (dataColheita.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de Colheita não pode ser futura");
        }
        if (quantidadeColhida <= 0) {
            throw new IllegalArgumentException("A quantidade colhida deve ser positiva");
        }
        if (unidadeMedida == null || unidadeMedida.isBlank()) {
            throw new IllegalArgumentException("Unidade de Medida deve existir");
        }
        if (perdas < 0) {
            throw new IllegalArgumentException("O número de perdas não pode ser negativo");
        }

        this.idColheita = idColheita;
        this.idPlantio = idPlantio;
        this.dataColheita = dataColheita;
        this.quantidadeColhida = quantidadeColhida;
        this.unidadeMedida = unidadeMedida;
        this.perdas = perdas;
        validarPerdas();
    }

    public int getIdColheita() {
        return idColheita;
    }

    public int getIdPlantio() {
        return idPlantio;
    }

    public LocalDate getDataColheita() {
        return dataColheita;
    }

    public int getQuantidadeColhida() {
        return quantidadeColhida;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public int getPerdas() {
        return perdas;
    }

    private void validarPerdas() {
        if (quantidadeColhida < perdas) {
            throw new IllegalArgumentException("A quantidade de perdas não pode ser maior do que a quantidade colhida");
        }
    }

    @Override
    public String toString(){
        return "[id: " + getIdColheita() + " | Id Plantio: " + getIdPlantio() + " | Data de Colheita: " + getDataColheita() + " | Quantidade colhida: " + getQuantidadeColhida() + " " + getUnidadeMedida() + " | Perdas: " + getPerdas() + "]";
    }
}
