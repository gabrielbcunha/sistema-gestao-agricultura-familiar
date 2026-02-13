package br.com.gabriel.gestaoagricola.domain;

import java.math.BigDecimal;

public class AreaCultivo {
    private final int idArea;
    private Produtor produtor;
    private String nomeArea;
    private BigDecimal tamanhoArea;

    public AreaCultivo(int idArea, Produtor produtor, String nomeArea, BigDecimal tamanhoArea ) {

        if (idArea <= 0){
            throw new IllegalArgumentException("idArea deve ser positivo");
        }
        if (produtor == null){
            throw new IllegalArgumentException("Produtor deve existir");
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
        this.produtor = produtor;
        this.nomeArea = nomeArea;
        this.tamanhoArea = tamanhoArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public BigDecimal getTamanhoArea() {
        return tamanhoArea;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public int getIdProdutor() {
        return produtor.getIdProdutor();
    }

    public void alterarProdutor(Produtor produtor) {
        if (produtor == null){
            throw new IllegalArgumentException("produtor deve existir");
        }
        this.produtor =  produtor;
    }

    @Override
    public String toString() {
        return "[Id: " + getIdArea() + " | Produtor: " + getProdutor() + " | Nome da Área: " + getNomeArea() + " | Tamanho Área: " + getTamanhoArea() + "]";
    }
}
