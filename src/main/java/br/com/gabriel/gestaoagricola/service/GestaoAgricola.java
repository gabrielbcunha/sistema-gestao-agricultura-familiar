package br.com.gabriel.gestaoagricola.service;

import br.com.gabriel.gestaoagricola.domain.*;

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
        boolean existeMudanca = avaliarMudancaProdutor(alvo, modificado);
        if (!existeMudanca) {
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

    public void atualizarAreaCultivoPorId (int id, int idProdutor, String nomeArea, BigDecimal tamanhoArea){
        AreaCultivo alvo = buscarAreaCultivoPorId(id);

        if (idProdutor <= 0){
            idProdutor = alvo.getIdProdutor();
        } else {
            buscarProdutorPorId(idProdutor);
        }
        if (nomeArea == null || nomeArea.isBlank()){
            nomeArea = alvo.getNomeArea();
        }
        if (tamanhoArea == null){
            tamanhoArea = alvo.getTamanhoArea();
        }

        AreaCultivo modificado = new AreaCultivo(id, idProdutor, nomeArea, tamanhoArea);
        boolean existeMudanca = avaliarMudancaAreaCultivo(alvo, modificado);
        if (!existeMudanca) {
            throw new IllegalArgumentException("Nenhuma característica modificada");
        } else {
            int idIndex = areasCultivo.indexOf(alvo);
            areasCultivo.set(idIndex, modificado);
        }
    }

    private boolean avaliarMudancaAreaCultivo(AreaCultivo alvo, AreaCultivo modificado){
        if (Objects.equals(alvo.getNomeArea(), modificado.getNomeArea()) &&  Objects.equals(alvo.getIdProdutor(), modificado.getIdProdutor()) && Objects.equals(alvo.getTamanhoArea(), modificado.getTamanhoArea())) {
            return false;
        }
        return true;
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

    public void atualizarCulturaPorId (int id, String nome, int cicloDias, String observacoes){
        Cultura alvo = buscarCulturaPorId(id);

        if (nome == null || nome.isBlank()){
            nome = alvo.getNome();
        }
        if (cicloDias <= 0){
            cicloDias = alvo.getCicloDias();
        }
        if (observacoes == null || observacoes.isBlank()){
            observacoes = alvo.getObservacoes();
        }

        Cultura modificado = new Cultura(id, nome, cicloDias, observacoes);
        boolean existeMudanca = avaliarMudancaCultura(alvo, modificado);
        if (!existeMudanca) {
            throw new IllegalArgumentException("Nenhuma característica modificada");
        } else {
            int idIndex = culturas.indexOf(alvo);
            culturas.set(idIndex, modificado);
        }
    }

    private boolean avaliarMudancaCultura(Cultura alvo, Cultura modificado){
        if (Objects.equals(alvo.getNome(), modificado.getNome()) && Objects.equals(alvo.getCicloDias(), modificado.getCicloDias()) &&  Objects.equals(alvo.getObservacoes(), modificado.getObservacoes())) {
            return false;
        }
        return true;
    }

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
    buscarAreaCultivoPorId(idAreaCultivo);
    buscarCulturaPorId(idCultura);
    Plantio plantio = new Plantio(idGeradoPlantio, idAreaCultivo, idCultura, dataPlantio, quantidadePlantada, unidadeMedida);
    plantios.add(plantio);
    return plantio;
    }

    public void removerPlantioPorId(int id){
        Plantio alvo = buscarPlantioPorId(id);
        plantios.remove(alvo);
    }

    public void atualizarPlantioPorId (int id, int idAreaCultivo, int idCultura, LocalDate dataPlantio, int quantidadePlantada, String unidadeMedida){
        Plantio alvo = buscarPlantioPorId(id);

        if (idAreaCultivo <= 0){
            idAreaCultivo = alvo.getIdAreaCultivo();
        } else {
            buscarAreaCultivoPorId(idAreaCultivo);
        }
        if (idCultura <= 0){
            idCultura = alvo.getIdCultura();
        } else {
            buscarCulturaPorId(idCultura);
        }
        if (dataPlantio == null){
            dataPlantio = alvo.getDataPlantio();
        }
        if (quantidadePlantada <= 0){
            quantidadePlantada = alvo.getQuantidadePlantada();
        }
        if (unidadeMedida == null || unidadeMedida.isBlank()){
            unidadeMedida = alvo.getUnidadeMedida();
        }

        Plantio modificado = new Plantio(id, idAreaCultivo, idCultura, dataPlantio, quantidadePlantada, unidadeMedida);
        boolean existeMudanca = avaliarMudancaPlantio(alvo, modificado);
        if (!existeMudanca) {
            throw new IllegalArgumentException("Nenhuma característica modificada");
        } else {
            int idIndex = plantios.indexOf(alvo);
            plantios.set(idIndex, modificado);
        }
    }

    private boolean avaliarMudancaPlantio(Plantio alvo, Plantio modificado){
        if (Objects.equals(alvo.getIdAreaCultivo(), modificado.getIdAreaCultivo()) && Objects.equals(alvo.getIdCultura(), modificado.getIdCultura()) && Objects.equals(alvo.getDataPlantio(), modificado.getDataPlantio()) && Objects.equals(alvo.getQuantidadePlantada(), modificado.getQuantidadePlantada()) && Objects.equals(alvo.getUnidadeMedida(), modificado.getUnidadeMedida())) {
            return false;
        }
        return true;
    }

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

    public Manejo adicionarManejo(int idPlantio, LocalDate dataManejo, String tipoManejo, String descricao){
        int  idGeradoManejo = proximoIdManejo++;
        buscarPlantioPorId(idPlantio);
        Manejo manejo = new Manejo(idGeradoManejo, idPlantio, dataManejo, tipoManejo, descricao);
        manejos.add(manejo);
        return manejo;
    }

    public void removerManejoPorId(int id){
        Manejo alvo =  buscarManejoPorId(id);
        manejos.remove(alvo);
    }

    public void atualizarManejoPorId (int id, int idPlantio, LocalDate dataManejo, String tipoManejo, String descricao){
        Manejo alvo = buscarManejoPorId(id);

        if (idPlantio <= 0){
            idPlantio = alvo.getIdPlantio();
        } else {
            buscarPlantioPorId(idPlantio);
        }
        if (dataManejo == null){
            dataManejo = alvo.getDataManejo();
        }
        if (tipoManejo == null || tipoManejo.isBlank()){
            tipoManejo = alvo.getTipoManejo();
        }
        if (descricao == null || descricao.isBlank()){
            descricao = alvo.getDescricao();
        }

        Manejo modificado = new Manejo(id, idPlantio, dataManejo, tipoManejo, descricao);
        boolean existeMudanca = avaliarMudancaManejo(alvo, modificado);
        if (!existeMudanca) {
            throw new IllegalArgumentException("Nenhuma característica modificada");
        } else {
            int idIndex = manejos.indexOf(alvo);
            manejos.set(idIndex, modificado);
        }
    }

    private boolean avaliarMudancaManejo(Manejo alvo, Manejo modificado){
        if (Objects.equals(alvo.getIdPlantio(), modificado.getIdPlantio()) && Objects.equals(alvo.getDataManejo(), modificado.getDataManejo()) && Objects.equals(alvo.getTipoManejo(), modificado.getTipoManejo()) && Objects.equals(alvo.getDescricao(), modificado.getDescricao())) {
            return false;
        }
        return true;
    }

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
        buscarPlantioPorId(idPlantio);
        Colheita colheita = new Colheita(idGeradoColheita, idPlantio, dataColheita, quantidadeColhida, unidadeDeMedida, perdas);
        colheitas.add(colheita);
        return colheita;
    }

    public void removerColheitaPorId(int id){
        Colheita alvo =  buscarColheitaPorId(id);
        colheitas.remove(alvo);
    }

    //atualizarColheitaPorId(int id)
//LocalDate dataColheita, int quantidadeColhida, String unidadeDeMedida, int perdas

    public void atualizarColheitaPorId (int id, int idPlantio, LocalDate dataColheita, int quantidadeColhida, String unidadeDeMedida, int perdas ){
        Colheita alvo = buscarColheitaPorId(id);

        if (idPlantio <= 0){
            idPlantio = alvo.getIdPlantio();
        } else {
            buscarPlantioPorId(idPlantio);
        }
        if (dataColheita == null){
            dataColheita = alvo.getDataColheita();
        }
        if (quantidadeColhida <= 0){
            quantidadeColhida = alvo.getQuantidadeColhida();
        }
        if (unidadeDeMedida == null || unidadeDeMedida.isBlank()){
            unidadeDeMedida = alvo.getUnidadeMedida();
        }
        if (perdas < 0){
            perdas = alvo.getPerdas();
        }

        Colheita modificado = new Colheita(id, idPlantio, dataColheita, quantidadeColhida, unidadeDeMedida, perdas);
        boolean existeMudanca = avaliarMudancaColheita(alvo, modificado);
        if (!existeMudanca) {
            throw new IllegalArgumentException("Nenhuma característica modificada");
        } else {
            int idIndex = colheitas.indexOf(alvo);
            colheitas.set(idIndex, modificado);
        }
    }

    private boolean avaliarMudancaColheita(Colheita alvo, Colheita modificado){
        if (Objects.equals(alvo.getIdPlantio(), modificado.getIdPlantio()) &&  Objects.equals(alvo.getDataColheita(), modificado.getDataColheita()) && Objects.equals(alvo.getQuantidadeColhida(), modificado.getQuantidadeColhida()) && Objects.equals(alvo.getUnidadeMedida(), modificado.getUnidadeMedida()) && Objects.equals(alvo.getPerdas(), modificado.getPerdas())) {
            return false;
        }
        return true;
    }

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