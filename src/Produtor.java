public class Produtor {
    private final int idProdutor;
    private String nome;
    private String telefone;
    private String localidade;
    private String observacoes;

    public Produtor(String nome, int idProdutor, String telefone, String localidade, String observacoes) {

        if (idProdutor <=0){
            throw new IllegalArgumentException("Id do Produtor deve ser positivo");
        }
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome do Produtor deve ser preenchido");
        }
        if (localidade == null || localidade.isBlank()){
            throw new IllegalArgumentException("Localidade do Produtor deve ser preenchida");
        }
        if (observacoes == null || observacoes.isBlank()){
            throw new IllegalArgumentException("Observações do Produtor deve ser preenchidas");
        }

        this.nome = nome;
        this.idProdutor = idProdutor;
        this.telefone = telefone;
        validarTelefoneFormatacao();
        this.localidade = localidade;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public int getIdProdutor() {
        return idProdutor;
    }

    public void validarTelefoneFormatacao() {
        String numeroValidar = telefone;
        numeroValidar = numeroValidar.replaceAll("[^0-9]+", "");
        String inicial;
        if (numeroValidar.length() == 11) {
            inicial = String.valueOf(numeroValidar.charAt(2));
            if (!inicial.equals("9")) {
                throw new IllegalArgumentException("A inicial do telefone deve ser 9");
            }
        } else if (numeroValidar.length() == 9) {
            inicial = String.valueOf(numeroValidar.charAt(0));
            if (!inicial.equals("9")) {
                throw new IllegalArgumentException("A inicial do telefone deve ser 9");
            }
        } else {
            throw new IllegalArgumentException("Insira um telefone valido");
        }
    }
}
