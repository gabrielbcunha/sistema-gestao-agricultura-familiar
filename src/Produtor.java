import java.lang.reflect.Array;

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
        if (telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone do Produtor deve ser preenchido");
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
        validarEAjustarFormatacaoTelefone();
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

    private void validarEAjustarFormatacaoTelefone() {
        String numeroValidar = telefone;
        numeroValidar = numeroValidar.replaceAll("[^0-9]+", "");
        String inicial;
        String ddd;

        if(numeroValidar.isBlank()){
            throw new IllegalArgumentException("Telefone deve ser preenchido");
        } else if (numeroValidar.length() == 11) {
            inicial = String.valueOf(numeroValidar.charAt(2));
            ddd = String.valueOf(numeroValidar.charAt(0)) + String.valueOf(numeroValidar.charAt(1));
            final String[] listaDDD = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "99"};
            boolean dddEncontrado = false;
            if (!inicial.equals("9")) {
                throw new IllegalArgumentException("A inicial do telefone deve ser 9");
            }
            for (String s : listaDDD) {
                if (s.equals(ddd)) {
                    dddEncontrado = true;
                    break;
                }
            }
            if (!dddEncontrado) {
                throw new IllegalArgumentException("O número deve conter um DDD valido");
            }

        } else if (numeroValidar.length() == 9) {
            inicial = String.valueOf(numeroValidar.charAt(0));
            if (!inicial.equals("9")) {
                throw new IllegalArgumentException("A inicial do telefone deve ser 9");
            }
        } else {
            throw new IllegalArgumentException("Insira um telefone valido");
        }
        telefone = numeroValidar;
    }
}
