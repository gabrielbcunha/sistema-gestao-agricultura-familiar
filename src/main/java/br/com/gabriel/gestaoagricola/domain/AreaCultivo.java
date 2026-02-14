package br.com.gabriel.gestaoagricola.domain;

import java.math.BigDecimal;

public class AreaCultivo {
    private final int idArea;
    private int idProdutor;
    private String nomeArea;
    private BigDecimal tamanhoArea;

    public AreaCultivo(int idArea, int idProdutor, String nomeArea, BigDecimal tamanhoArea ) {

        if (idArea <= 0){
            throw new IllegalArgumentException("idArea deve ser positivo");
        }
        if (idProdutor <= 0){
            throw new IllegalArgumentException("idProdutor deve ser positivo");
        }
        if (nomeArea == null || nomeArea.isBlank()){
            throw new IllegalArgumentException("Área deve ter um nome");
        }
        if (tamanhoArea == null) {
            throw new IllegalArgumentException("o tamanho da Área de Cultivo deve ter um tamanho");
        } else if (tamanhoArea.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O tamanho Área de Cultivo deve ser maior que zero");
        }

        this.idArea = idArea;
        this.idProdutor = idProdutor;
        this.nomeArea = nomeArea;
        this.tamanhoArea = tamanhoArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public int getIdProdutor() {
        return idProdutor;
    }

    public BigDecimal getTamanhoArea() {
        return tamanhoArea;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    @Override
    public String toString() {
        return "[Id: " + getIdArea() + " | Id Produtor: " + getIdProdutor() + " | Nome da Área: " + getNomeArea() + " | Tamanho Área: " + getTamanhoArea() + "]";
    }
}
