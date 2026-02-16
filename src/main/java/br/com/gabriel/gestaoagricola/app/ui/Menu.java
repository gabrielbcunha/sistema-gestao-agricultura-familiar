package br.com.gabriel.gestaoagricola.app.ui;

import br.com.gabriel.gestaoagricola.domain.*;
import br.com.gabriel.gestaoagricola.domain.*;
import br.com.gabriel.gestaoagricola.service.GestaoAgricola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final GestaoAgricola gestaoAgricola;
    private final Scanner input;
    private static final String TITLE = "---------------------------\nSistema de gestão agrícola\n---------------------------";
    public Menu(Scanner input, GestaoAgricola gestaoAgricola) {
        this.input = input;
        this.gestaoAgricola = gestaoAgricola;
    }

    public void menuPrincipal() {
        while (true) {
            System.out.println();
            exibirMenuPrincipal();

            int opcaoPrincipal = validadorInputIntIntervalo(">",0,7);

            switch (opcaoPrincipal) {
                case 1:
                    menuProdutores();
                    break;
                case 2:
                    menuAreasDeCultivo();
                    break;
                case 3:
                    menuCulturas();
                    break;
                case 4:
                    menuPlantios();
                    break;
                case 5:
                    menuManejos();
                    break;
                case 6:
                    menuColheitas();
                    break;
                case 7:
                    menuVendas();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Insira uma opção válida");
                    break;
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println(TITLE);
        System.out.println("---Selecione a operação---");
        System.out.println("1 - Produtores");
        System.out.println("2 - Áreas de Cultivo");
        System.out.println("3 - Culturas");
        System.out.println("4 - Plantio");
        System.out.println("5 - Manejo");
        System.out.println("6 - Colheita");
        System.out.println("7 - Venda");
        System.out.println("0 - Encerrar");
    }

    private void menuProdutores(){
        boolean estadoProdutores = true;
        while (estadoProdutores) {
            System.out.println();
            exibirSubMenuProdutores();

            int opcaoProdutores = validadorInputIntIntervalo(">",0,5);

            switch (opcaoProdutores) {
                case 1:
                    try {
                        System.out.println("Digite o nome do produtor: ");
                        String nomeProdutor = input.nextLine();
                        System.out.println("Digite o número de telefone do produtor");
                        String telefoneProdutor = input.nextLine();
                        System.out.println("Digite a localidade do produtor");
                        String localidadeProdutor = input.nextLine();
                        System.out.println("Digite as observações sobre o produtor");
                        String observacoesProdutor = input.nextLine();
                        Produtor produtorCriado = gestaoAgricola.adicionarProdutor(nomeProdutor,telefoneProdutor,localidadeProdutor,observacoesProdutor);
                        System.out.println("Produtor cadastrado com sucesso! " + produtorCriado);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 2:
                try {
                    System.out.println("Digite o ID do produtor a ser excluído: ");
                    int idProdutorExcluido = validadorInputInt(">");
                    Produtor produtorExcluido = gestaoAgricola.buscarProdutorPorId(idProdutorExcluido);
                    System.out.println("Confirme se é o produtor a ser excluído:");
                    System.out.println(produtorExcluido);
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    int confirmarProdutorExcluido = validadorInputIntIntervalo(">",1,2);
                    if (confirmarProdutorExcluido == 1) {
                        gestaoAgricola.removerProdutorPorId(idProdutorExcluido);
                            System.out.println("Produtor removido com sucesso!");
                    } else {
                        System.out.println("Operação cancelada!");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println();
                    System.out.println("----------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("----------------------------");
                    System.out.println();
                }
                    break;
                case 3:
                    System.out.println("Lista de Produtores");
                    var lista = gestaoAgricola.listarProdutores();
                    if (lista.isEmpty()) {
                        System.out.println("Produtor não encontrado!");
                    } else {
                        for (Produtor produtor : lista) {
                            System.out.println(produtor);
                        }
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o ID do produtor a ser procurado");
                        int buscaProdutorId = validadorInputInt(">");
                        Produtor produtorProcurado =  gestaoAgricola.buscarProdutorPorId(buscaProdutorId);
                        System.out.println("Produtor encontrado com sucesso!");
                        System.out.println(produtorProcurado);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o ID do produtor a ser modificado");
                        int idProdutorModificado = validadorInputInt(">");
                        Produtor produtor = gestaoAgricola.buscarProdutorPorId(idProdutorModificado);
                        System.out.println("Confirme se é o produtor a ser modificado:");
                        System.out.println(produtor);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoProdutorModificado = validadorInputIntIntervalo(">",1,2);
                        if (opcaoProdutorModificado == 1) {
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println("Insira as novas características para o Produtor ");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println("Informe o nome do produtor: (deixe em branco caso não haja mudança)");
                            String nomeProdutor = input.nextLine();
                            System.out.println("Informe o telefone do produtor: (deixe em branco caso não haja mudança)");
                            String telefoneProdutor = input.nextLine();
                            System.out.println("Informe a localidade do produtor: (deixe em branco caso não haja mudança)");
                            String localidadeProdutor = input.nextLine();
                            System.out.println("Informe as observações sobre o produtor: (deixe em branco caso não haja mudança)");
                            String observacoesProdutor = input.nextLine();
                            gestaoAgricola.atualizarProdutorPorId(idProdutorModificado, nomeProdutor, telefoneProdutor, localidadeProdutor, observacoesProdutor);
                            System.out.println("Produtor atualizado com sucesso!");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println(gestaoAgricola.buscarProdutorPorId(idProdutorModificado));
                            System.out.println("--------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal");
                    estadoProdutores = false;
                    break;
                default:
                    System.out.println("Insira uma opção válida");
                    break;
            }
        }
    }

    private void exibirSubMenuProdutores(){
        System.out.println(TITLE);
        System.out.println("Selecione a Operação com: Produtores");
        System.out.println("1 - Cadastrar Produtor");
        System.out.println("2 - Remover Produtor");
        System.out.println("3 - Listar Produtores");
        System.out.println("4 - Buscar Produtor por ID");
        System.out.println("5 - Editar informações de Produtor");
        System.out.println("0 - Voltar");
    }

    private void menuAreasDeCultivo() {
        boolean estadoAreasDeCultivo = true;
        while (estadoAreasDeCultivo) {
            System.out.println();
            exibirSubMenuAreasDeCultivo();

            int opcaoAreasDeCultivo = validadorInputIntIntervalo(">",0,5);

            switch (opcaoAreasDeCultivo) {
                case 1:
                    try {
                        System.out.println("Digite o ID do produtor designado da área");
                        int idProdutor = validadorInputInt(">");
                        System.out.println("Digite o nome da área de cultivo");
                        String nomeAreaCultivo = input.nextLine();
                        System.out.println("Digite o tamanho da área de cultivo no formato: 0000.00");
                        BigDecimal tamanhoAreaCultivo = validadorInputBigDecimal(">");
                        AreaCultivo areaCriada = gestaoAgricola.adicionarAreaCultivo(idProdutor, nomeAreaCultivo, tamanhoAreaCultivo);
                        System.out.println("Área cadastrada com sucesso! " + areaCriada);

                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Digite o ID da área de cultivo a ser excluída: ");
                        int idAreaExcluida = validadorInputInt(">");
                        AreaCultivo areaExcluida = gestaoAgricola.buscarAreaCultivoPorId(idAreaExcluida);
                        System.out.println("Confirme que é a área de cultivo a ser excluída");
                        System.out.println(areaExcluida);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarAreaExcluida = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarAreaExcluida == 1) {
                            gestaoAgricola.removerAreaCultivoPorId(idAreaExcluida);
                            System.out.println("Area excluída com sucesso!");
                        } else {
                            System.out.println("Operação Cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Lista de áreas de cultivo");
                    var listaCultivo = gestaoAgricola.listarAreasCultivo();
                    if (listaCultivo.isEmpty()) {
                        System.out.println("Nenhuma área cadastrada!");
                    } else {
                        for (AreaCultivo areaCultivo : listaCultivo) {
                            System.out.println(areaCultivo);
                        }
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o ID da área a ser procurada");
                        int idAreaProcurado = validadorInputInt(">");
                        AreaCultivo areaCultivoProcurada = gestaoAgricola.buscarAreaCultivoPorId(idAreaProcurado);
                        System.out.println("Área de cultivo encontrada com sucesso!");
                        System.out.println(areaCultivoProcurada);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o ID da área de cultivo a ser modificada");
                        int idAreaCultivoModificada = validadorInputInt(">");
                        AreaCultivo areaCultivo = gestaoAgricola.buscarAreaCultivoPorId(idAreaCultivoModificada);
                        System.out.println("Confirme se é a área de cultivo a ser modificada:");
                        System.out.println(areaCultivo);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoAreaCultivoModificada = validadorInputIntIntervalo(">",1,2);
                        if (opcaoAreaCultivoModificada == 1) {
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Insira as novas características para a Área de Cultivo");
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Informe o id do produtor: (Insira 0 para manter o mesmo produtor)");
                            int idProdutorArea = validadorInputInt(">");
                            System.out.println("Informe o nome da área de cultivo: (deixe em branco caso não haja mudança)");
                            String nomeArea = input.nextLine();
                            System.out.println("Informe o tamanho da área de cultivo: (Insira 0 para manter o mesmo tamanho)");
                            BigDecimal tamanhoAreaCultivo = validadorInputBigDecimal(">");
                            gestaoAgricola.atualizarAreaCultivoPorId(idAreaCultivoModificada, idProdutorArea, nomeArea, tamanhoAreaCultivo);
                            System.out.println("Área de Cultivo atualizada com sucesso!");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println(gestaoAgricola.buscarAreaCultivoPorId(idAreaCultivoModificada));
                            System.out.println("--------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal");
                    estadoAreasDeCultivo = false;
                    break;
                default:
                    System.out.println("Insira uma opção válida");
                    break;
            }
        }
    }

    private void exibirSubMenuAreasDeCultivo() {
        System.out.println(TITLE);
        System.out.println("Selecione a Operação com: Áreas de Cultivo");
        System.out.println("1 - Cadastrar Área ");
        System.out.println("2 - Remover Área ");
        System.out.println("3 - Listar Áreas");
        System.out.println("4 - Buscar Área por ID");
        System.out.println("5 - Editar informações da Área");
        System.out.println("0 - Voltar");
    }

    private void menuCulturas(){
        boolean estadoCulturas = true;
        while (estadoCulturas) {
            System.out.println();
            exibirSubMenuCulturas();

            int opcaoCulturas = validadorInputIntIntervalo(">", 0, 5);

            switch (opcaoCulturas) {
                case 1:
                    try {
                        System.out.println("Digite o nome da cultura");
                        String nomeCultura = input.nextLine();
                        System.out.println("Digite o ciclo de manejo (em dias) da cultura");
                        int cicloManejo = validadorInputInt(">");
                        System.out.println("Digite as observações sobre a cultura");
                        String observacaoCultura = input.nextLine();
                        Cultura culturaCriada = gestaoAgricola.adicionarCultura(nomeCultura, cicloManejo, observacaoCultura);
                        System.out.println("Cultura cadastrada com sucesso! " + culturaCriada);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Digite o ID da cultura a ser excluída");
                        int idCulturaExcluir = validadorInputInt(">");
                        Cultura culturaExcluir = gestaoAgricola.buscarCulturaPorId(idCulturaExcluir);
                        System.out.println("Confirme que é a cultura a ser excluída");
                        System.out.println(culturaExcluir);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarCulturaExcluida = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarCulturaExcluida == 1) {
                            gestaoAgricola.removerCulturaPorId(idCulturaExcluir);
                            System.out.println("Cultura removida com sucesso!");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Lista de culturas");
                    var lista = gestaoAgricola.listarCulturas();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma cultura cadastrada!");
                    } else {
                        for (Cultura cultura : lista) {
                            System.out.println(cultura);
                        }
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o ID da cultura a ser procurada");
                        int idCulturaProcurado = validadorInputInt(">");
                        Cultura culturaProcurada = gestaoAgricola.buscarCulturaPorId(idCulturaProcurado);
                        System.out.println("Cultura encontrada com sucesso!");
                        System.out.println(culturaProcurada);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o ID da cultura a ser modificada");
                        int idCulturaModificado = validadorInputInt(">");
                        Cultura cultura = gestaoAgricola.buscarCulturaPorId(idCulturaModificado);
                        System.out.println("Confirme se é a cultura a ser modificada:");
                        System.out.println(cultura);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoCulturaModificada = validadorInputIntIntervalo(">",1,2);
                        if (opcaoCulturaModificada == 1) {
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println("Insira as novas características para a Cultura");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println("Informe o nome da cultura: (deixe em branco caso não haja mudança)");
                            String nomeCultura = input.nextLine();
                            System.out.println("Informe o ciclo de dias da cultura: (Insira 0 para manter o mesmo ciclo)");
                            int cicloDias = validadorInputInt(">");
                            System.out.println("Informe as observações da cultura: (deixe em branco caso não haja mudança)");
                            String observacoesCultura = input.nextLine();
                            gestaoAgricola.atualizarCulturaPorId(idCulturaModificado, nomeCultura, cicloDias, observacoesCultura);
                            System.out.println("Cultura atualizada com sucesso!");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println(gestaoAgricola.buscarCulturaPorId(idCulturaModificado));
                            System.out.println("--------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal");
                    estadoCulturas = false;
                    break;
                default:
                    System.out.println("Insira uma opção válida");
                    break;
            }
        }
    }

    private void exibirSubMenuCulturas(){
        System.out.println(TITLE);
        System.out.println("Selecione a Operação com: Culturas");
        System.out.println("1 - Cadastrar Cultura");
        System.out.println("2 - Remover Cultura");
        System.out.println("3 - Listar Culturas");
        System.out.println("4 - Buscar Cultura por ID");
        System.out.println("5 - Editar informações de Cultura");
        System.out.println("0 - Voltar");
    }

    private void menuPlantios(){
        boolean estadoPlantios = true;
        while (estadoPlantios) {
            System.out.println();
            exibirSubMenuPlantios();

            int opcaoPlantios = validadorInputIntIntervalo(">", 0, 5);

            switch (opcaoPlantios) {
                case 1:
                    try {
                        System.out.println("Digite o Id da Área de Cultivo onde foi feito o Plantio");
                        int idAreaCultivoPlantio = validadorInputInt(">");
                        System.out.println("Digite o Id da Cultura que foi plantada");
                        int idCulturaPlantio = validadorInputInt(">");
                        System.out.println("Digite a data do Plantio DIA-MES-ANO");
                        LocalDate dataPlantio = validadorInputLocalDate(">");
                        System.out.println("Digite a quantidade plantada");
                        int quantidadePlantio = validadorInputInt(">");
                        System.out.println("Digite a unidade de médida utilizada");
                        String unidadeMedida =  input.nextLine();
                        Plantio plantioCriado = gestaoAgricola.adicionarPlantio(idAreaCultivoPlantio, idCulturaPlantio, dataPlantio, quantidadePlantio, unidadeMedida);
                        System.out.println("Plantio Criado com sucesso!" + plantioCriado);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Digite o Id do Plantio a ser excluído");
                        int idPlantioExcluir = validadorInputInt(">");
                        Plantio plantioExcluir = gestaoAgricola.buscarPlantioPorId(idPlantioExcluir);
                        System.out.println("Confirme que é o Plantio a ser excluído");
                        System.out.println(plantioExcluir);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarPlantioExcluido = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarPlantioExcluido == 1) {
                            gestaoAgricola.removerPlantioPorId(idPlantioExcluir);
                            System.out.println("Plantio removida com sucesso!");
                        } else {
                            System.out.println("Operação Cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Lista de Plantios");
                    var lista = gestaoAgricola.listarPlantios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum Plantio Cadastrado!");
                    } else {
                        for (Plantio plantio : lista) {
                            System.out.println(plantio);
                        }
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o Id do Plantio a ser procurado");
                        int idPlantioProcurado = validadorInputInt(">");
                        Plantio plantioProcurado = gestaoAgricola.buscarPlantioPorId(idPlantioProcurado);
                        System.out.println("Plantio encontrado com sucesso!");
                        System.out.println(plantioProcurado);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o ID do plantio a ser modificado");
                        int idPlantioModificado = validadorInputInt(">");
                        Plantio plantio = gestaoAgricola.buscarPlantioPorId(idPlantioModificado);
                        System.out.println("Confirme se é o plantio a ser modificado:");
                        System.out.println(plantio);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoPlantioModificado = validadorInputIntIntervalo(">",1,2);
                        if (opcaoPlantioModificado == 1) {
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Insira as novas características para o Plantio");
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Informe o id da área de cultivo: (Insira 0 para manter a mesma área de cultivo)");
                            int idAreaCultivoPlantio = validadorInputInt(">");
                            System.out.println("Informe o ID da cultura plantada: (Insira 0 para manter a mesma)");
                            int idCulturaPlantio = validadorInputInt(">");
                            System.out.println("Informe a data do plantio: (deixe em branco caso não haja mudança)");
                            LocalDate dataPlantio = validadorInputLocalDate(">");
                            System.out.println("Informe a quantidade plantada: (Insira 0 para manter a mesma)");
                            int quantidadePlantada = validadorInputInt(">");
                            System.out.println("Insira a unidade de médida: (deixe em branco caso não haja mudança)");
                            String unidadeMedidaPlantio = input.nextLine();
                            gestaoAgricola.atualizarPlantioPorId(idPlantioModificado, idAreaCultivoPlantio, idCulturaPlantio, dataPlantio, quantidadePlantada, unidadeMedidaPlantio);
                            System.out.println("Plantio atualizado com sucesso!");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println(gestaoAgricola.buscarPlantioPorId(idPlantioModificado));
                            System.out.println("--------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal");
                    estadoPlantios = false;
                    break;
                default:
                    System.out.println("Insira uma opção válida");
                    break;
            }
        }
    }

    private void exibirSubMenuPlantios() {
        System.out.println(TITLE);
        System.out.println("Selecione a Operação com: Plantio");
        System.out.println("1 - Cadastrar Plantio");
        System.out.println("2 - Remover Plantio");
        System.out.println("3 - Listar Plantios");
        System.out.println("4 - Buscar Plantio por ID");
        System.out.println("5 - Editar informações de Plantios");
        System.out.println("0 - Voltar");
    }

    private void menuManejos(){
        boolean estadoManejos = true;
        while (estadoManejos) {
            System.out.println();
            exibirSubMenuManejos();

            int opcaoManejos = validadorInputIntIntervalo(">",0 ,5);

            switch (opcaoManejos) {
                case 1:
                    try {
                        System.out.println("Digite o Id do Plantio onde o Manejo foi realizado");
                        int idPlantioManejo = validadorInputInt(">");
                        System.out.println("Digite a data em que o Manejo foi realizado DIA-MES-ANO");
                        LocalDate dataManejo = validadorInputLocalDate(">");
                        System.out.println("Digite o tipo de Manejo realizado");
                        String tipoManejo = input.nextLine();
                        System.out.println("Digite a descrição do Manejo");
                        String descricaoManejo = input.nextLine();
                        Manejo manejoCriado = gestaoAgricola.adicionarManejo(idPlantioManejo, dataManejo, tipoManejo, descricaoManejo);
                        System.out.println("Manejo criado com sucesso!" + manejoCriado);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Digite o Id do Manejo a ser excluído");
                        int idManejoExcluido = validadorInputInt(">");
                        Manejo manejoExcluido = gestaoAgricola.buscarManejoPorId(idManejoExcluido);
                        System.out.println("Confirme que é o Manejo a ser excluído");
                        System.out.println(manejoExcluido);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarManejoExcluido = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarManejoExcluido == 1) {
                            gestaoAgricola.removerManejoPorId(idManejoExcluido);
                            System.out.println("Manejo removido com sucesso!");
                        } else {
                            System.out.println("Operação Cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Lista de Manejos");
                    var lista = gestaoAgricola.listarManejos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum Manejo cadastrado!");
                    } else {
                        for (Manejo manejo : lista) {
                            System.out.println(manejo);
                        }
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o Id do Manejo a ser procurado");
                        int idManejoProcurado = validadorInputInt(">");
                        Manejo manejoProcurado = gestaoAgricola.buscarManejoPorId(idManejoProcurado);
                        System.out.println("Manejo encontrado com sucesso!");
                        System.out.println(manejoProcurado);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o ID do manejo a ser modificado");
                        int idManejoModificado = validadorInputInt(">");
                        Manejo manejo = gestaoAgricola.buscarManejoPorId(idManejoModificado);
                        System.out.println("Confirme se é o manejo a ser modificado:");
                        System.out.println(manejo);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoManejoModificado = validadorInputIntIntervalo(">",1,2);
                        if (opcaoManejoModificado == 1) {
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Insira as novas características para o Manejo");
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Informe o ID do plantio: (Insira 0 para manter o mesmo plantio)");
                            int idPlantioManejo = validadorInputInt(">");
                            System.out.println("Informe a data do Manejo: (deixe em branco caso não haja mudança)");
                            LocalDate dataManejo = validadorInputLocalDate(">");
                            System.out.println("Informe o tipo de Manejo: (deixe em branco caso não haja mudança)");
                            String tipoManejo = input.nextLine();
                            System.out.println("Informe a descrição do manejo: (deixe em branco caso não haja mudança)");
                            String descricaoManejo = input.nextLine();
                            gestaoAgricola.atualizarManejoPorId(idManejoModificado, idPlantioManejo, dataManejo, tipoManejo, descricaoManejo);
                            System.out.println("Manejo atualizado com sucesso!");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println(gestaoAgricola.buscarManejoPorId(idManejoModificado));
                            System.out.println("--------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal");
                    estadoManejos = false;
                    break;
                default:
                    System.out.println("Insira uma opção valida");
                    break;
            }
        }
    }

    private void exibirSubMenuManejos(){
        System.out.println(TITLE);
        System.out.println("Selecione a Operação com: Manejo");
        System.out.println("1 - Cadastrar Manejo");
        System.out.println("2 - Remover Manejo");
        System.out.println("3 - Listar Manejos");
        System.out.println("4 - Buscar Manejo por Id");
        System.out.println("5 - Editar informações de Manejos");
        System.out.println("0 - Voltar");
    }

    private void menuColheitas(){
        boolean estadoColheitas = true;
        while (estadoColheitas) {
            System.out.println();
            exibirSubMenuColheitas();

            int opcaoColheita = validadorInputIntIntervalo(">",0 ,5);

            switch (opcaoColheita) {
                case 1:
                    try {
                        System.out.println("Digite o Id do Plantio que foi colhido");
                        int idPlantioColheita = validadorInputInt(">");
                        System.out.println("Digite a data que foi realizada a Colheita DIA-MES-ANO");
                        LocalDate dataColheita = validadorInputLocalDate(">");
                        System.out.println("Digite a quantidade Colhida");
                        int quantidadeColhida = validadorInputInt(">");
                        System.out.println("Digite a unidade de medida");
                        String unidadeMedida = input.nextLine();
                        System.out.println("Digite a quantidade de perdas da colheita");
                        int quantidadePerdas = validadorInputInt(">");
                        Colheita colheitaNova = gestaoAgricola.adicionarColheita(idPlantioColheita, dataColheita, quantidadeColhida, unidadeMedida, quantidadePerdas);
                        System.out.println("Colheita adicionada com sucesso!" + colheitaNova);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Digite o Id da Colheita");
                        int idColheitaExcluida = validadorInputInt(">");
                        Colheita colheitaExcluida = gestaoAgricola.buscarColheitaPorId(idColheitaExcluida);
                        System.out.println("Confirme que é a Colheita a ser excluída");
                        System.out.println(colheitaExcluida);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarColheitaExcluida = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarColheitaExcluida == 1) {
                            gestaoAgricola.removerColheitaPorId(idColheitaExcluida);
                            System.out.println("Colheita removida com sucesso!");
                        } else {
                            System.out.println("Operação Cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Lista de Colheitas");
                    var lista = gestaoAgricola.listarColheitas();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma colheita cadastrada!");
                    } else {
                        for (Colheita colheita : lista) {
                            System.out.println(colheita);
                        }
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o Id da Colheita a ser procurada");
                        int idColheitaProcurado = validadorInputInt(">");
                        Colheita colheitaProcurada = gestaoAgricola.buscarColheitaPorId(idColheitaProcurado);
                        System.out.println("Colheita encontrada com sucesso!");
                        System.out.println(colheitaProcurada);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o ID da Colheita a ser modificada");
                        int idColheitaModificada = validadorInputInt(">");
                        Colheita colheita = gestaoAgricola.buscarColheitaPorId(idColheitaModificada);
                        System.out.println("Confirme se é a colheita a ser modificada:");
                        System.out.println(colheita);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoColheitaModificada = validadorInputIntIntervalo(">",1,2);
                        if (opcaoColheitaModificada == 1) {
                            //int id, int idPlantio, LocalDate dataColheita, int quantidadeColhida, String unidadeDeMedida, int perdas
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Insira as novas características para a Colheita");
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Informe o ID do plantio: (Insira 0 para manter o mesmo plantio)");
                            int idPlantioColheita = validadorInputInt(">");
                            System.out.println("Informe a data da colheita: (deixe em branco caso não haja mudança)");
                            LocalDate dataColheita = validadorInputLocalDate(">");
                            System.out.println("Informe a quantidade colhida: (Insira 0 para manter a mesma quantidade)");
                            int quantidadeColhida = validadorInputInt(">");
                            System.out.println("Informe a unidade de medida: (deixe em branco caso não haja mudança)");
                            String unidadeMedidaColheita = input.nextLine();
                            System.out.println("Informe a quantidade de perdas: (Insira -1 para manter a mesma quantidade)");
                            int quantidadePerdas = validadorInputInt(">");
                            gestaoAgricola.atualizarColheitaPorId(idColheitaModificada, idPlantioColheita, dataColheita, quantidadeColhida, unidadeMedidaColheita, quantidadePerdas);
                            System.out.println("Colheita atualizada com sucesso!");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println(gestaoAgricola.buscarColheitaPorId(idColheitaModificada));
                            System.out.println("--------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal");
                    estadoColheitas = false;
                    break;
                default:
                    System.out.println("Insira uma opção valida");
                    break;
            }
        }
    }

    private void exibirSubMenuColheitas(){
        System.out.println(TITLE);
        System.out.println("Selecione a Operação com: Colheita");
        System.out.println("1 - Cadastrar Colheita");
        System.out.println("2 - Remover Colheita");
        System.out.println("3 - Listar Colheitas");
        System.out.println("4 - Buscar Colheita por Id");
        System.out.println("5 - Editar informações de Colheitas");
        System.out.println("0 - Voltar");
    }

    private void menuVendas(){
        boolean estadoVendas = true;
        while (estadoVendas) {
            System.out.println();
            exibirSubMenuVendas();

            int opcaoVendas = validadorInputIntIntervalo(">",0,5);

            switch (opcaoVendas) {
                case 1:
                    try {
                        System.out.println("Digite o Id da Colheita Vendida");
                        int idColheitaVendida = validadorInputInt(">");
                        System.out.println("Digite a data da venda DIA-MES-ANO");
                        LocalDate dataVenda = validadorInputLocalDate(">");
                        System.out.println("Digite a quantidade vendida");
                        int quantidadeVendida = validadorInputInt(">");
                        System.out.println("Digite o valor unitário de cada produto");
                        BigDecimal valorUnitario = validadorInputBigDecimal(">");
                        Venda vendaCriada = gestaoAgricola.adicionarVenda(idColheitaVendida, dataVenda, quantidadeVendida, valorUnitario);
                        System.out.println("Venda adicionada com sucesso!" + vendaCriada);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Digite o Id da vendida a ser excluída");
                        int idVendaExcluida = validadorInputInt(">");
                        Venda vendaExcluida = gestaoAgricola.buscarVendaPorId(idVendaExcluida);
                        System.out.println("Confirme que é a Venda a ser excluída");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        System.out.println(vendaExcluida);
                        int confirmarVendaExcluida = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarVendaExcluida == 1) {
                            gestaoAgricola.removerVendaPorId(idVendaExcluida);
                            System.out.println("Venda removida com sucesso!");
                        } else {
                            System.out.println("Operação Cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Lista de Vendas");
                    var lista = gestaoAgricola.listarVendas();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma venda cadastrada!");
                    } else {
                        for (Venda venda : lista) {
                            System.out.println(venda);
                        }
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o ID da venda a ser procurada");
                        int idVendaProcurado = validadorInputInt(">");
                        Venda vendaProcurada = gestaoAgricola.buscarVendaPorId(idVendaProcurado);
                        System.out.println("Venda encontrada com sucesso!");
                        System.out.println(vendaProcurada);
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o ID da venda a ser modificada");
                        int idVendaModificada = validadorInputInt(">");
                        Venda venda = gestaoAgricola.buscarVendaPorId(idVendaModificada);
                        System.out.println("Confirme se é a venda a ser modificada:");
                        System.out.println(venda);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoVendaModificada = validadorInputIntIntervalo(">",1,2);
                        if (opcaoVendaModificada == 1) {
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Insira as novas características para a Venda");
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println("Informe o id da colheita: (Insira 0 para manter a mesma colheita)");
                            int idColheitaVenda = validadorInputInt(">");
                            System.out.println("Informe a data da venda: (deixe em branco caso não haja mudança)");
                            LocalDate dataVenda = validadorInputLocalDate(">");
                            System.out.println("Informe a quantidade vendida: (Insira 0 para manter a mesma quantidade)");
                            int quantidadeVendida = validadorInputInt(">");
                            System.out.println("Informe o valor unitário de cada produto vendido: (deixe em branco caso não haja mudança)");
                            BigDecimal valorUnitarioVenda = validadorInputBigDecimal(">");
                            gestaoAgricola.atualizarVendaPorId(idVendaModificada, idColheitaVenda, dataVenda, quantidadeVendida, valorUnitarioVenda);
                            System.out.println("Venda atualizada com sucesso!");
                            System.out.println("--------------------------------------------------------------------------------------");
                            System.out.println(gestaoAgricola.buscarVendaPorId(idVendaModificada));
                            System.out.println("--------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("----------------------------");
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal");
                    estadoVendas = false;
                    break;
                default:
                    System.out.println("Insira uma opção valida");
                    break;
            }
        }
    }

    private void exibirSubMenuVendas(){
        System.out.println(TITLE);
        System.out.println("Selecione a Operação com: Venda");
        System.out.println("1 - Cadastrar Venda");
        System.out.println("2 - Remover Venda");
        System.out.println("3 - Listar Vendas");
        System.out.println("4 - Buscar Venda por Id");
        System.out.println("5 - Editar informações de Vendas");
        System.out.println("0 - Voltar");
    }

    private int validadorInputInt (String prompt) {
        while (true) {
            System.out.print(prompt);

            if(!input.hasNextInt()) {
                System.out.println("Entrada inválida, digite somente números");
                input.nextLine();
                continue;
            }

            int valor = input.nextInt();
            input.nextLine();
            return valor;
        }
    }

    private int validadorInputIntIntervalo (String prompt, int min, int max) {
        while (true) {
            int valor = validadorInputInt(prompt);
            if (valor < min || valor > max) {
                System.out.println("Digite um valor entre " + min + " e " + max);
                continue;
            }
            return valor;
        }
    }

    private BigDecimal validadorInputBigDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);

            String inputString = input.nextLine();
            if (inputString.isBlank()) {
                return null;
            }

            inputString = inputString.replace(",", ".").trim();

            try {
                BigDecimal valor = new BigDecimal(inputString);
                return valor;
            } catch (Exception e){
                System.out.println("Utilize ponto ou vírgula para separar as casas decimais ");
            }
        }
    }

    private BigDecimal validadorInputBigDecimalIntervalo(String prompt, BigDecimal min, BigDecimal max) {
        while (true) {
            BigDecimal valor = validadorInputBigDecimal(prompt);
            if (valor != null && valor.compareTo(min) < 0 || valor != null && valor.compareTo(max) > 0) {
                System.out.println("Digite um valor entre " + min + " e " + max);
                continue;
            }
            return valor;
        }
    }

    private LocalDate validadorInputLocalDate(String prompt) {
        while (true) {
            System.out.print(prompt);

            String inputString = input.nextLine();
            if (inputString.trim().isBlank()) {
                return null;
            }

            inputString = inputString.replaceAll("[^0-9]+", "").trim();
            List<String> pares = new ArrayList<>();
            if (inputString.length() != 8) {
                System.out.println("Insira uma data válida com 8 caracteres [00-00-0000]");
                continue;
            } else {
                for (int i = 0; i < inputString.length(); i += 2) {
                    String substring =  inputString.substring(i, i+2);
                    pares.add(substring);
                }
            }

            try {
                DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = pares.get(0) + "/" + pares.get(1) + "/" + pares.get(2) + pares.get(3);
                LocalDate data = LocalDate.parse(dataFormatada, formatacao);
                if (data.isAfter(LocalDate.now())) {
                    System.out.println("A data não pode ser futura!");
                    continue;
                }else {
                    return data;
                }
            } catch (Exception e) {
                System.out.println("Insira uma data valida!");
            }
        }
    }
}