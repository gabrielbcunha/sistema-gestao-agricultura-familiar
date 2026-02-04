package br.com.gabriel.gestaoagricola.domain;

public class AreaCultivo {
    private final int idArea;
    private Produtor produtor;
    private String nomeArea;
    private String tamanhoArea;

    public AreaCultivo(int idArea, Produtor produtor, String nomeArea, String tamanhoArea ) {

        if (idArea <= 0){
            throw new IllegalArgumentException("idArea deve ser positivo");
        }
        if (produtor == null){
            throw new IllegalArgumentException("produtor deve existir");
        }
        if (nomeArea == null || nomeArea.isBlank()){
            throw new IllegalArgumentException("Area deve ter um nome");
        }
        if (tamanhoArea == null || tamanhoArea.isBlank()){
            throw new IllegalArgumentException("Area deve ter um tamanho");
        }

        this.idArea = idArea;
        this.produtor = produtor;
        this.nomeArea = nomeArea;
        this.tamanhoArea = tamanhoArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public String getTamanhoArea() {
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
}
