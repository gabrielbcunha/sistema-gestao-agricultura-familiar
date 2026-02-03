public class Cultura {
    private final int idCultura;
    private String nome;
    private int cicloDias;
    private String observacoes;


    public Cultura(int idCultura, String nome, int cicloDias, String observacoes) {

        if (idCultura <= 0) {
            throw new IllegalArgumentException("O Id da cultura deve ser positivo");
        }
        if (cicloDias <= 0) {
            throw new IllegalArgumentException("O número de dias do ciclo deve ser positivo");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("A cultura deve possuir um nome");
        }

        this.idCultura = idCultura;
        this.nome = nome;
        this.cicloDias = cicloDias;
        this.observacoes = observacoes;
    }

    public int getIdCultura() {
        return idCultura;
    }

    public String getNome() {
        return nome;
    }

    public int getCicloDias() {
        return cicloDias;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
