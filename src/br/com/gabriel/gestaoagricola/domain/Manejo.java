package br.com.gabriel.gestaoagricola.domain;

import java.time.LocalDate;

public class Manejo {
    private final int idManejo;
    private final Plantio plantio;
    private LocalDate dataManejo;
    private String tipoManejo;
    private String descricao;

    public Manejo(int idManejo, Plantio plantio, LocalDate dataManejo, String tipoManejo, String descricao) {

        if (idManejo <= 0) {
            throw new IllegalArgumentException("O Id do br.com.gabriel.gestaoagricola.domain.Manejo deve ser positivo");
        }
        if (plantio == null) {
            throw new IllegalArgumentException("br.com.gabriel.gestaoagricola.domain.Plantio deve existir");
        }
        if (dataManejo == null) {
            throw new IllegalArgumentException("A data do br.com.gabriel.gestaoagricola.domain.Manejo deve existir");
        } else if (dataManejo.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de br.com.gabriel.gestaoagricola.domain.Manejo não pode ser futura");
        }
        if (tipoManejo == null || tipoManejo.isBlank()) {
            throw new IllegalArgumentException("O tipo do br.com.gabriel.gestaoagricola.domain.Manejo deve existir");
        }

        this.idManejo = idManejo;
        this.plantio = plantio;
        this.dataManejo = dataManejo;
        this.tipoManejo = tipoManejo;
        this.descricao = descricao;
    }

    public int getIdManejo() {
        return idManejo;
    }

    public Plantio getPlantio() {
        return plantio;
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
        return "[Id: " + getIdManejo() + " | Plantio: " + getPlantio() + " | Data de Manejo: " + getDataManejo() + " | Tipo de Manejo: " + getTipoManejo() + " | Descricao: " + getDescricao() + "]";
    }
}
