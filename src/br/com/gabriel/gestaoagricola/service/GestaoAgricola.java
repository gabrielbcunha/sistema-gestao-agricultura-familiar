package br.com.gabriel.gestaoagricola.service;

import br.com.gabriel.gestaoagricola.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestaoAgricola {
    private int proximoIdProdutor = 1;
    private int proximoIdAreaCultivo = 1;
    private int proximoIdCultura = 1;
    private int proximoIdPlantio = 1;
    private int proximoIdManejo = 1;
    private int proximoIdColheita = 1;
    private int proximoIdVenda = 1;

    private final List<Produtor> produtores = new ArrayList<>();
    private final List<AreaCultivo> areasCultivo = new ArrayList<>();
    private final List<Cultura> culturas = new ArrayList<>();
    private final List<Plantio> plantios = new ArrayList<>();
    private final List<Manejo> manejos = new ArrayList<>();
    private final List<Colheita> colheitas = new ArrayList<>();
    private final List<Venda> vendas = new ArrayList<>();

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

    public AreaCultivo adicionarAreaCultivo(Produtor produtor, String nomeArea, BigDecimal tamanhoArea){
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

    public Plantio adicionarPlantio(AreaCultivo areaCultivo, Cultura cultura, LocalDate dataPlantio, int quantidadePlantada, String unidadeMedida){
    int idGeradoPlantio = proximoIdPlantio++;
    Plantio plantio = new Plantio(idGeradoPlantio, areaCultivo, cultura, dataPlantio, quantidadePlantada, unidadeMedida);
    plantios.add(plantio);
    return plantio;
    }

    public boolean removerPlantio(int id){
        Plantio alvo = buscarPlantioId(id);
        if(alvo != null){
            plantios.remove(alvo);
            return true;
        }
        return false;
    }

    public List<Plantio> listarPlantios(){
        return new ArrayList<>(plantios);
    }

    public Plantio buscarPlantioId(int id){
        for(Plantio plantio : plantios){
            if (plantio.getIdPlantio() == id){
                return plantio;
            }
        }
        return null;
    }

    public Manejo adicionarManejo(Plantio plantio, LocalDate dataManejo, String tipoManjo, String descricao){
        int  idGeradoManejo = proximoIdManejo++;
        Manejo manejo = new Manejo(idGeradoManejo, plantio, dataManejo, tipoManjo, descricao);
        manejos.add(manejo);
        return manejo;
    }

    public boolean removerManejo(int id){
        Manejo alvo =  buscarManejoId(id);
        if(alvo != null){
            manejos.remove(alvo);
            return true;
        } else {
            return false;
        }
    }

    public List<Manejo> listarManejos(){
        return new ArrayList<>(manejos);
    }

    public Manejo buscarManejoId(int id){
        for (Manejo manejo : manejos){
            if (manejo.getIdManejo() == id){
                return manejo;
            }
        }
        return null;
    }

    public Colheita adicionarColheita(Plantio plantio, LocalDate dataColheita, int quantidadeColhida, String unidadeDeMedida, int perdas){
        int idGeradoColheita = proximoIdColheita++;
        Colheita colheita = new Colheita(idGeradoColheita, plantio, dataColheita, quantidadeColhida, unidadeDeMedida, perdas);
        colheitas.add(colheita);
        return colheita;
    }

    public boolean removerColheita(int id){
        Colheita alvo =  buscarColheitaId(id);
        if(alvo != null){
            colheitas.remove(alvo);
            return true;
        } else {
            return false;
        }
    }

    public List<Colheita> listarColheitas(){
        return new ArrayList<>(colheitas);
    }

    public Colheita buscarColheitaId(int id){
        for(Colheita colheita : colheitas){
            if (colheita.getIdColheita() == id){
                return colheita;
            }
        }
        return null;
    }

    public Venda adicionarVenda(Colheita colheita, LocalDate dataVenda, int quantidadeVenda, BigDecimal valorUnitario){
        int idGeradoVenda = proximoIdVenda++;
        Venda venda = new Venda(idGeradoVenda, colheita, dataVenda,quantidadeVenda, valorUnitario);
        vendas.add(venda);
        return venda;
    }

    public boolean removerVenda(int id){
        Venda alvo =  buscarVendaId(id);
        if(alvo != null){
            vendas.remove(alvo);
            return true;
        } else {
            return false;
        }
    }

    public List<Venda> listarVendas(){
        return new ArrayList<>(vendas);
    }

    public Venda buscarVendaId(int id){
        for(Venda venda : vendas){
            if (venda.getIdVenda() == id){
                return venda;
            }
        }
        return null;
    }
}
