package br.com.gabriel.gestaoagricola.service;

import br.com.gabriel.gestaoagricola.domain.*;
import br.com.gabriel.gestaoagricola.domain.*;

import javax.swing.text.html.parser.TagElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void removerProdutorPorId(int id){
        Produtor alvo = buscarProdutorPorId(id);
        produtores.remove(alvo);
    }

    public void atualizarProdutorPorId (int id, String nome, String telefone, String localidade, String observacoes){
        Produtor alvo = buscarProdutorPorId(id);
        if (nome == null || nome.isBlank()){
            nome = alvo.getNome();
        }
        if (telefone == null || telefone.isBlank()){
            telefone = alvo.getTelefone();
        }
        if (localidade == null || localidade.isBlank()){
            localidade = alvo.getLocalidade();
        }
        if (observacoes == null || observacoes.isBlank()){
            observacoes = alvo.getObservacoes();
        }
        Produtor modificado = new Produtor(nome, id, telefone, localidade, observacoes);
        boolean estado = avaliarMudancaProdutor(alvo, modificado);
        if (estado == false) {
            throw new IllegalArgumentException("Nenhuma característica modificada");
        } else {
            int idIndex = produtores.indexOf(alvo);
            produtores.set(idIndex, modificado);
        }
    }

    private boolean avaliarMudancaProdutor(Produtor alvo, Produtor modificado){
        if (Objects.equals(alvo.getNome(), modificado.getNome()) &&  Objects.equals(alvo.getTelefone(), modificado.getTelefone()) && Objects.equals(alvo.getLocalidade(), modificado.getLocalidade()) &&  Objects.equals(alvo.getObservacoes(), modificado.getObservacoes())) {
            return false;
        }
        return true;
    }

    public List<Produtor> listarProdutores(){
        return new ArrayList<>(produtores);
    }

    public Produtor buscarProdutorPorId(int id){
        for(Produtor produtor : produtores){
            if (produtor.getIdProdutor() == id){
                return produtor;
            }
        }
        throw new IllegalArgumentException("Produtor não encontrado!");
    }

    public AreaCultivo adicionarAreaCultivo(int idProdutor, String nomeArea, BigDecimal tamanhoArea){
        buscarProdutorPorId(idProdutor);
        int idGeradoArea = proximoIdAreaCultivo++;
        AreaCultivo areaCultivo = new AreaCultivo(idGeradoArea, idProdutor, nomeArea, tamanhoArea);
        areasCultivo.add(areaCultivo);
        return areaCultivo;
    }

    public void removerAreaCultivoPorId(int id){
        AreaCultivo alvo = buscarAreaCultivoPorId(id);
        areasCultivo.remove(alvo);
    }

    public List<AreaCultivo> listarAreasCultivo(){
        return new ArrayList<>(areasCultivo);
    }

    public AreaCultivo buscarAreaCultivoPorId(int id){
        for(AreaCultivo areaCultivo : areasCultivo){
            if (areaCultivo.getIdArea() == id){
                return areaCultivo;
            }
        }
        throw new IllegalArgumentException("Área de cultivo não encontrada!");
    }

    public Cultura adicionarCultura(String nome, int cicloDias, String observacoes) {
        int idGeradoCultura = proximoIdCultura++;
        Cultura cultura = new Cultura(idGeradoCultura, nome, cicloDias, observacoes);
        culturas.add(cultura);
        return cultura;
    }

    public void removerCulturaPorId(int id){
        Cultura alvo =  buscarCulturaPorId(id);
        culturas.remove(alvo);
    }

    //atualizarCulturaPorId(int id)


    public List<Cultura> listarCulturas(){
        return new ArrayList<>(culturas);
    }

    public Cultura buscarCulturaPorId(int id){
        for(Cultura cultura : culturas){
            if (cultura.getIdCultura() == id){
                return cultura;
            }
        }
        throw new IllegalArgumentException("Cultura não encontrada!");
    }

    public Plantio adicionarPlantio(int idAreaCultivo, int idCultura, LocalDate dataPlantio, int quantidadePlantada, String unidadeMedida){
    int idGeradoPlantio = proximoIdPlantio++;
    AreaCultivo areaCultivo = buscarAreaCultivoPorId(idAreaCultivo);
    Cultura cultura = buscarCulturaPorId(idCultura);
    Plantio plantio = new Plantio(idGeradoPlantio, areaCultivo, cultura, dataPlantio, quantidadePlantada, unidadeMedida);
    plantios.add(plantio);
    return plantio;
    }

    public void removerPlantioPorId(int id){
        Plantio alvo = buscarPlantioPorId(id);
        plantios.remove(alvo);
    }

    //atualizarPlantioPorId(int id)


    public List<Plantio> listarPlantios(){
        return new ArrayList<>(plantios);
    }

    public Plantio buscarPlantioPorId(int id){
        for(Plantio plantio : plantios){
            if (plantio.getIdPlantio() == id){
                return plantio;
            }
        }
        throw new IllegalArgumentException("Plantio não encontrado!");
    }

    public Manejo adicionarManejo(int idPlantio, LocalDate dataManejo, String tipoManjo, String descricao){
        int  idGeradoManejo = proximoIdManejo++;
        Plantio plantio = buscarPlantioPorId(idPlantio);
        Manejo manejo = new Manejo(idGeradoManejo, plantio, dataManejo, tipoManjo, descricao);
        manejos.add(manejo);
        return manejo;
    }

    public void removerManejoPorId(int id){
        Manejo alvo =  buscarManejoPorId(id);
        manejos.remove(alvo);
    }

    //atualizarManejoPorId(int id)


    public List<Manejo> listarManejos(){
        return new ArrayList<>(manejos);
    }

    public Manejo buscarManejoPorId(int id){
        for (Manejo manejo : manejos){
            if (manejo.getIdManejo() == id){
                return manejo;
            }
        }
        throw new IllegalArgumentException("Manejo não encontrado!");
    }

    public Colheita adicionarColheita(int idPlantio, LocalDate dataColheita, int quantidadeColhida, String unidadeDeMedida, int perdas){
        int idGeradoColheita = proximoIdColheita++;
        Plantio plantio =   buscarPlantioPorId(idPlantio);
        Colheita colheita = new Colheita(idGeradoColheita, plantio, dataColheita, quantidadeColhida, unidadeDeMedida, perdas);
        colheitas.add(colheita);
        return colheita;
    }

    public void removerColheitaPorId(int id){
        Colheita alvo =  buscarColheitaPorId(id);
        colheitas.remove(alvo);
    }

    //atualizarColheitaPorId(int id)


    public List<Colheita> listarColheitas(){
        return new ArrayList<>(colheitas);
    }

    public Colheita buscarColheitaPorId(int id){
        for(Colheita colheita : colheitas){
            if (colheita.getIdColheita() == id){
                return colheita;
            }
        }
        throw new IllegalArgumentException("Colheita não encontrada!");
    }

    public Venda adicionarVenda(int idColheita, LocalDate dataVenda, int quantidadeVenda, BigDecimal valorUnitario){
        int idGeradoVenda = proximoIdVenda++;
        Colheita colheita = buscarColheitaPorId(idColheita);
        Venda venda = new Venda(idGeradoVenda, colheita, dataVenda,quantidadeVenda, valorUnitario);
        vendas.add(venda);
        return venda;
    }

    public void removerVendaPorId(int id){
        Venda alvo =  buscarVendaPorId(id);
        vendas.remove(alvo);
    }

    //atualizarVendaPorId(int id)


    public List<Venda> listarVendas(){
        return new ArrayList<>(vendas);
    }

    public Venda buscarVendaPorId(int id){
        for(Venda venda : vendas){
            if (venda.getIdVenda() == id){
                return venda;
            }
        }
        throw new IllegalArgumentException("Venda não encontrada!");
    }
}