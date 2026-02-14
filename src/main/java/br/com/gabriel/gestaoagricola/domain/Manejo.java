package br.com.gabriel.gestaoagricola.domain;

import java.time.LocalDate;

public class Manejo {
    private final int idManejo;
    private int idPlantio;
    private LocalDate dataManejo;
    private String tipoManejo;
    private String descricao;

    public Manejo(int idManejo, int idPlantio, LocalDate dataManejo, String tipoManejo, String descricao) {

        if (idManejo <= 0) {
            throw new IllegalArgumentException("O Id do Manejo deve ser positivo");
        }
        if (idPlantio <= 0) {
            throw new IllegalArgumentException("O Id do Plantio deve ser positivo");
        }
        if (dataManejo == null) {
            throw new IllegalArgumentException("A data do  Manejo deve existir");
        } else if (dataManejo.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data do Manejo não pode ser futura");
        }
        if (tipoManejo == null || tipoManejo.isBlank()) {
            throw new IllegalArgumentException("O tipo do Manejo deve existir");
        }

        this.idManejo = idManejo;
        this.idPlantio = idPlantio;
        this.dataManejo = dataManejo;
        this.tipoManejo = tipoManejo;
        this.descricao = descricao;
    }

    public int getIdManejo() {
        return idManejo;
    }

    public int getIdPlantio() {
        return idPlantio;
    }

    public LocalDate getDataManejo() {
        return dataManejo;
    }

    public String getTipoManejo() {
        return tipoManejo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "[Id: " + getIdManejo() + " | Plantio: " + getIdPlantio() + " | Data de Manejo: " + getDataManejo() + " | Tipo de Manejo: " + getTipoManejo() + " | Descricao: " + getDescricao() + "]";
    }
}
