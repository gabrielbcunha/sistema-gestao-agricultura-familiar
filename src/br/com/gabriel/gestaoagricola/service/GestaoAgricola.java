package br.com.gabriel.gestaoagricola.service;

import br.com.gabriel.gestaoagricola.domain.AreaCultivo;
import br.com.gabriel.gestaoagricola.domain.Cultura;
import br.com.gabriel.gestaoagricola.domain.Produtor;

import java.util.ArrayList;
import java.util.List;

public class GestaoAgricola {
    private int proximoIdProdutor = 1;
    private int proximoIdAreaCultivo = 1;
    private int proximoIdCultura = 1;

    private final List<Produtor> produtores = new ArrayList<>();
    private final List<AreaCultivo> areasCultivo = new ArrayList<>();
    private final List<Cultura> culturas = new ArrayList<>();


    public Produtor adicionarProdutor(String nome, String telefone, String localidade, String observacoes) {
        int idGeradoProdutor = proximoIdProdutor++;
        Produtor produtor = new Produtor(nome, idGeradoProdutor, telefone, localidade, observacoes);
        produtores.add(produtor);
        return produtor;
    }

    public boolean removerProdutor(int id){
        Produtor alvo = buscarProdutorId(id);
        if(alvo != null){
            produtores.remove(alvo);
            return true;
        }
        return false;
    }

    public List<Produtor> listarProdutores(){
        return new ArrayList<>(produtores);
    }

    public Produtor buscarProdutorId(int id){
        for(Produtor produtor : produtores){
            if (produtor.getIdProdutor() == id){
            return produtor;
            }
        }
        return null;
    }

    public AreaCultivo adicionarAreaCultivo(Produtor produtor, String nomeArea, String tamanhoArea){
        int idGeradoArea = proximoIdAreaCultivo++;
        AreaCultivo areaCultivo =  new AreaCultivo(idGeradoArea, produtor, nomeArea, tamanhoArea);
        areasCultivo.add(areaCultivo);
        return areaCultivo;
    }

    public boolean removerAreaCultivo(int id){
        AreaCultivo alvo = buscarAreaCultivoId(id);
        if(alvo != null){
            areasCultivo.remove(alvo);
            return true;
        }
        return false;
    }

    public List<AreaCultivo> listarAreasCultivo(){
        return new ArrayList<>(areasCultivo);
    }

    public AreaCultivo buscarAreaCultivoId(int id){
        for(AreaCultivo areaCultivo : areasCultivo){
            if (areaCultivo.getIdArea() == id){
                return areaCultivo;
            }
        }
        return null;
    }

    public Cultura adicionarCultura(String nome, int cicloDias, String observacoes) {
        int idGeradoCultura = proximoIdCultura++;
        Cultura cultura = new  Cultura(idGeradoCultura, nome, cicloDias, observacoes);
        culturas.add(cultura);
        return cultura;
    }

    public boolean removerCultura(int id){
        Cultura alvo =  buscarCulturaId(id);
        if(alvo != null){
            culturas.remove(alvo);
            return true;
        }
        return false;
    }

    public List<Cultura> listarCulturas(){
        return new ArrayList<>(culturas);
    }

    public Cultura buscarCulturaId(int id){
        for(Cultura cultura : culturas){
            if (cultura.getIdCultura() == id){
                return cultura;
            }
        }
        return null;
    }
}
