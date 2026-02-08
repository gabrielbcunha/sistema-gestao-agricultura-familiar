package br.com.gabriel.gestaoagricola.app;

import br.com.gabriel.gestaoagricola.domain.*;
import br.com.gabriel.gestaoagricola.service.GestaoAgricola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestaoAgricola gestaoAgricola = new GestaoAgricola();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Sistema de gestão agrícola");
            System.out.println("---Selecione a operação---");
            System.out.println("1 - Produtores");
            System.out.println("2 - Áreas de Cultivo");
            System.out.println("3 - Culturas");
            System.out.println("4 - Plantio");
            System.out.println("5 - Manejo");
            System.out.println("6 - Colheita");
            System.out.println("7 - Venda");
            System.out.println("0 - Encerrar");
            int opcaoPrincipal = input.nextInt();
            input.nextLine();

            switch (opcaoPrincipal) {
                case 1:
                    boolean estadoProdutor = true;
                     while (estadoProdutor) {
                        System.out.println("Selecione a Operação com: Produtores");
                        System.out.println("1 - Cadastrar Produtor");
                        System.out.println("2 - Remover Produtor");
                        System.out.println("3 - Listar Produtores");
                        System.out.println("4 - Buscar Produtor por Id");
                        System.out.println("0 - Voltar");
                        int opcaoProdutor = input.nextInt();
                        input.nextLine();

                        switch (opcaoProdutor) {
                            case 1:
                                try {
                                    System.out.println("Digite o nome do produtor: ");
                                    String nomeProdutor = input.nextLine();
                                    System.out.println("Digite o número de Telefone do produtor");
                                    String telefoneProdutor = input.nextLine();
                                    System.out.println("Digite a localidade do produtor");
                                    String localidadeProdutor = input.nextLine();
                                    System.out.println("Digite as observações sobre o produtor");
                                    String observacoesProdutor = input.nextLine();
                                    Produtor produtorCriado = gestaoAgricola.adicionarProdutor(nomeProdutor,telefoneProdutor,localidadeProdutor,observacoesProdutor);
                                    System.out.println("Produtor cadastrado com sucesso! " + produtorCriado);
                                } catch (Exception e) {
                                    System.out.println("Erro: " + e.getMessage());
                                }
                                break;
                            case 2:
                                try{
                                    System.out.println("Digite o Id do Produtor a ser excluído: ");
                                    int idProdutorExcluido = input.nextInt();
                                    input.nextLine();
                                    Produtor produtorExcluido = gestaoAgricola.buscarProdutorId(idProdutorExcluido);
                                    if (produtorExcluido == null){
                                        System.out.println("Produtor não encontrado!");
                                    } else {
                                        System.out.println("Confirme se é o produtor a ser excluído:");
                                        System.out.println(produtorExcluido);
                                        System.out.println("1 - Sim");
                                        System.out.println("2 - Não");
                                        if (input.nextInt() == 1) {
                                            input.nextLine();
                                            gestaoAgricola.removerProdutor(idProdutorExcluido);
                                            System.out.println("Produtor removido com sucesso!");
                                        } else {
                                            System.out.println("Operação cancelada!");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro: " + e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.println("Lista de Produtores");
                                var lista = gestaoAgricola.listarProdutores();
                                if (lista.isEmpty()) {
                                    System.out.println("Produtor não encontrado!");
                                } else {
                                    for (Produtor produtores : lista) {
                                        System.out.println(produtores);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Digite o Id do Produtor a ser procurado");
                                int buscaProdutorId = input.nextInt();
                                input.nextLine();
                                Produtor produtorProcurado =  gestaoAgricola.buscarProdutorId(buscaProdutorId);
                                if (produtorProcurado == null) {
                                    System.out.println("Nenhum produtor encontrado!");
                                } else {
                                    System.out.println("Produtor procurado com sucesso!");
                                    System.out.println(produtorProcurado);
                                }
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                estadoProdutor = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean estadoAreaCultura = true;
                    while (estadoAreaCultura) {
                        System.out.println("Selecione a Operação com: Áreas de Cultivo");
                        System.out.println("1 - Cadastrar Área ");
                        System.out.println("2 - Remover Área ");
                        System.out.println("3 - Listar Áreas");
                        System.out.println("4 - Buscar Área por Id");
                        System.out.println("0 - Voltar");
                        int opcaoAreaCultivo = input.nextInt();
                        input.nextLine();

                        switch (opcaoAreaCultivo) {
                            case 1:
                                try {
                                    System.out.println("Digite o Id do Produtor designado da Área");
                                    int idProdutor = input.nextInt();
                                    input.nextLine();
                                    Produtor produtorEscolhido = gestaoAgricola.buscarProdutorId(idProdutor);
                                    System.out.println("Digite o nome da Área de Cultivo");
                                    String nomeAreaCultivo = input.nextLine();
                                    System.out.println("Digite o tamanho da Área de Cultivo");
                                    String tamanhoAreaCultivo = input.nextLine();
                                    AreaCultivo areaCriada = gestaoAgricola.adicionarAreaCultivo(produtorEscolhido, nomeAreaCultivo, tamanhoAreaCultivo);
                                    System.out.println("Area cadastrada com sucesso! " + areaCriada);
                                } catch (Exception e) {
                                    System.out.println("Erro: " + e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    System.out.println("Digite o Id da Área de Cultivo a ser excluída: ");
                                    int idAreaExcluida = input.nextInt();
                                    input.nextLine();
                                    AreaCultivo areaExcluida = gestaoAgricola.buscarAreaCultivoId(idAreaExcluida);
                                    if (areaExcluida == null) {
                                        System.out.println("Área não encontrada!");
                                    } else {
                                        System.out.println("Confirme que é a Área de Cultivo a ser excluída");
                                        System.out.println(areaExcluida);
                                        System.out.println("1 - Sim");
                                        System.out.println("2 - Não");
                                        if (input.nextInt() == 1) {
                                            input.nextLine();
                                            gestaoAgricola.removerAreaCultivo(idAreaExcluida);
                                            System.out.println("Area excluída com sucesso!");
                                        } else {
                                            System.out.println("Operação Cancelada!");
                                        }
                                    }
                                }catch (Exception e) {
                                    System.out.println("Erro: " + e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.println("Lista de Áreas de Cultivo");
                                var listaCultivo = gestaoAgricola.listarAreasCultivo();
                                if (listaCultivo.isEmpty()) {
                                    System.out.println("Nenhum Área encontrada!");
                                } else {
                                    for (AreaCultivo areasCultivo : listaCultivo) {
                                        System.out.println(areasCultivo);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Digite o Id da Área a ser procurada");
                                int  idAreaProcurado = input.nextInt();
                                input.nextLine();
                                AreaCultivo areaCultivoProcurada = gestaoAgricola.buscarAreaCultivoId(idAreaProcurado);
                                if (areaCultivoProcurada == null) {
                                    System.out.println("Área não encontrada!");
                                } else {
                                    System.out.println("Área de Cultivo encontrada com sucesso!");
                                    System.out.println(areaCultivoProcurada);
                                }
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                estadoAreaCultura = false;
                                 break;
                        }
                    }
                    break;
                case 3:
                    boolean estadoCultura = true;
                    while (estadoCultura) {
                        System.out.println("Selecione a Operação com: Culturas");
                        System.out.println("1 - Cadastrar Cultura");
                        System.out.println("2 - Remover Cultura");
                        System.out.println("3 - Listar Culturas");
                        System.out.println("4 - Buscar Cultura por Id");
                        System.out.println("0 - Voltar");
                        int opcaoCultura = input.nextInt();
                        input.nextLine();

                        switch (opcaoCultura) {
                            case 1:
                                try {
                                    System.out.println("Digite o nome da Cultura");
                                    String nomeCultura = input.nextLine();
                                    System.out.println("Digite o ciclo de Manejo da cultura");
                                    int cicloManejo = input.nextInt();
                                    input.nextLine();
                                    System.out.println("Digite as observações sobre a cultura");
                                    String observacaoCultura = input.nextLine();
                                    Cultura culturaCriada = gestaoAgricola.adicionarCultura(nomeCultura, cicloManejo, observacaoCultura);
                                    System.out.println("Cultura cadastrada com sucesso! " + culturaCriada);
                                } catch (Exception e) {
                                    System.out.println("Erro: " + e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("Digite o Id da Cultura a ser excluída");
                                int idCulturaExcluir = input.nextInt();
                                input.nextLine();
                                Cultura culturaExcluir = gestaoAgricola.buscarCulturaId(idCulturaExcluir);
                                if (culturaExcluir == null) {
                                    System.out.println("Cultura não encontrada!");
                                } else {
                                    System.out.println("Confirme que é a Cultura a ser excluída");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    System.out.println(culturaExcluir);
                                    if (input.nextInt() == 1) {
                                        input.nextLine();
                                        gestaoAgricola.removerCultura(idCulturaExcluir);
                                        System.out.println("Cultura removida com sucesso!");
                                    } else {
                                        System.out.println("Operação Cancelada!");
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Lista de Culturas");
                                var lista = gestaoAgricola.listarCulturas();
                                if (lista.isEmpty()) {
                                    System.out.println("Nenhum Cultura encontrada!");
                                } else {
                                    for (Cultura culturas : lista) {
                                        System.out.println(culturas);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Digite o Id da Cultura a ser procurada");
                                int idCulturaProcurado = input.nextInt();
                                input.nextLine();
                                Cultura culturaProcurada = gestaoAgricola.buscarCulturaId(idCulturaProcurado);
                                if (culturaProcurada == null) {
                                    System.out.println("Cultura não encontrada!");
                                } else {
                                    System.out.println("Cultura encontra com sucesso!");
                                    System.out.println(culturaProcurada);
                                }
                                break;
                            case 0:
                            System.out.println("Voltando ao Menu Principal...");
                            estadoCultura = false;
                                break;
                            }
                        }
                    break;
                case 4:
                    boolean estadoPlantio = true;
                    while (estadoPlantio) {
                        System.out.println("Selecione a Operação com: Plantio");
                        System.out.println("1 - Cadastrar Plantio");
                        System.out.println("2 - Remover Plantio");
                        System.out.println("3 - Listar Plantios");
                        System.out.println("4 - Buscar Plantio por Id");
                        System.out.println("0 - Voltar");
                        int opcaoPlantio = input.nextInt();
                        input.nextLine();

                        switch (opcaoPlantio) {
                            case 1:
                                try {
                                    System.out.println("Digite o Id da Área de Cultivo onde foi feito o Plantio");
                                    int idAreaCultivoPlantio = input.nextInt();
                                    input.nextLine();
                                    AreaCultivo areaCultivoPlantio = gestaoAgricola.buscarAreaCultivoId(idAreaCultivoPlantio);
                                    System.out.println("Digite o Id da Cultura que foi plantada");
                                    int idCulturaPlantio = input.nextInt();
                                    input.nextLine();
                                    Cultura culturaPlantada = gestaoAgricola.buscarCulturaId(idCulturaPlantio);
                                    System.out.println("Digite a data do Plantio ANO-MES-DIA");
                                    LocalDate dataPlantio = LocalDate.parse(input.nextLine());
                                    System.out.println("Digite a quantidade plantada");
                                    int quantidadePlantio = input.nextInt();
                                    input.nextLine();
                                    System.out.println("Digite a unidade de médida utilizada");
                                    String unidadeMedida =  input.nextLine();
                                    Plantio plantioCriado = gestaoAgricola.adicionarPlantio(areaCultivoPlantio, culturaPlantada, dataPlantio, quantidadePlantio, unidadeMedida);
                                    System.out.println("Plantio Criado com sucesso!" + plantioCriado);
                                } catch (Exception e) {
                                    System.out.println("Erro: " + e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("Digite o Id do Plantio a ser excluído");
                                int  idPlantioExcluir = input.nextInt();
                                input.nextLine();
                                Plantio plantioExcluir = gestaoAgricola.buscarPlantioId(idPlantioExcluir);
                                if (plantioExcluir == null) {
                                    System.out.println("Nenhum Plantio encontrado!");
                                } else {
                                    System.out.println("Confirme que é o Plantio a ser excluído");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    System.out.println(plantioExcluir);
                                    if (input.nextInt() == 1) {
                                        input.nextLine();
                                        gestaoAgricola.removerPlantio(idPlantioExcluir);
                                        System.out.println("Cultura removida com sucesso!");
                                    } else {
                                        System.out.println("Operação Cancelada!");
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Lista de Plantios");
                                var lista = gestaoAgricola.listarPlantios();
                                if (lista.isEmpty()) {
                                    System.out.println("Nenhum Plantio encontrado!");
                                } else {
                                    for (Plantio plantios : lista) {
                                        System.out.println(plantios);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Digite o Id do Plantio a ser procurado");
                                int idPlantioProcurado = input.nextInt();
                                input.nextLine();
                                Plantio plantioProcurado = gestaoAgricola.buscarPlantioId(idPlantioProcurado);
                                if (plantioProcurado == null) {
                                    System.out.println("Nenhum Plantio encontrado!");
                                } else {
                                    System.out.println("Plantio encontrado com sucesso!");
                                    System.out.println(plantioProcurado);
                                }
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                estadoPlantio = false;
                                break;
                        }
                    }
                    break;
                case 5:
                    boolean estadoManejo = true;
                    while (estadoManejo) {
                        System.out.println("Selecione a Operação com: Manejo");
                        System.out.println("1 - Cadastrar Manejo");
                        System.out.println("2 - Remover Manejo");
                        System.out.println("3 - Listar Manejos");
                        System.out.println("4 - Buscar Manejo por Id");
                        System.out.println("0 - Voltar");
                        int opcaoManejo = input.nextInt();
                        input.nextLine();

                        switch (opcaoManejo) {
                            case 1:
                                try {
                                    System.out.println("Digite o Id do Plantio onde o Manejo foi realizado");
                                    int idPlantioManejo = input.nextInt();
                                    input.nextLine();
                                    Plantio plantioManejo = gestaoAgricola.buscarPlantioId(idPlantioManejo);
                                    System.out.println("Digite a data em que o Manejo foi realizado ANO-MES-DIA");
                                    LocalDate dataManejo = LocalDate.parse(input.nextLine());
                                    System.out.println("Digite o tipo de Manejo realizado");
                                    String tipoManejo = input.nextLine();
                                    System.out.println("Digite a descrição do Manejo");
                                    String descricaoManejo = input.nextLine();
                                    Manejo manejoCriado = gestaoAgricola.adicionarManejo(plantioManejo, dataManejo, tipoManejo, descricaoManejo);
                                    System.out.println("Manejo criado com sucesso!" + manejoCriado);
                                } catch (Exception e) {
                                    System.out.println("Erro: " + e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("Digite o Id do Manejo a ser excluído");
                                int idManejoExcluir= input.nextInt();
                                input.nextLine();
                                Manejo manejoExcluido = gestaoAgricola.buscarManejoId(idManejoExcluir);
                                if  (manejoExcluido == null) {
                                    System.out.println("Nenhum Manejo encontrado!");
                                } else {
                                    System.out.println("Confirme que é o Manejo a ser excluído");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    System.out.println(manejoExcluido);
                                    if (input.nextInt() == 1) {
                                        input.nextLine();
                                        gestaoAgricola.removerManejo(idManejoExcluir);
                                        System.out.println("Manejo removida com sucesso!");
                                    } else {
                                        System.out.println("Operação Cancelada!");
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Lista de Manejos");
                                var lista = gestaoAgricola.listarManejos();
                                if (lista.isEmpty()) {
                                    System.out.println("Nenhum Manejo encontrado!");
                                } else {
                                    for (Manejo manejos : lista) {
                                        System.out.println(manejos);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Digite o Id do Manejo a ser procurado");
                                int idManejoProcurado = input.nextInt();
                                input.nextLine();
                                Manejo manejoProcurado = gestaoAgricola.buscarManejoId(idManejoProcurado);
                                if (manejoProcurado == null) {
                                    System.out.println("Nenhum Manejo encontrado!");
                                } else {
                                    System.out.println("Manejo encontrado com sucesso!");
                                    System.out.println(manejoProcurado);
                                }
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                estadoManejo = false;
                                break;
                        }
                    }
                     break;
                case 6:
                    boolean estadoColheita = true;
                    while (estadoColheita) {
                        System.out.println("Selecione a Operação com: Colheita");
                        System.out.println("1 - Cadastrar Colheita");
                        System.out.println("2 - Remover Colheita");
                        System.out.println("3 - Listar Colheitas");
                        System.out.println("4 - Buscar Colheita por Id");
                        System.out.println("0 - Voltar");
                        int opcaoColheita = input.nextInt();
                        input.nextLine();

                        switch (opcaoColheita) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                estadoColheita = false;
                                break;
                        }
                    }
                    //Colheita
                    break;
                case 7:
                    boolean estadoVenda = true;
                    while (estadoVenda) {
                        System.out.println("Selecione a Operação com: Venda");
                        System.out.println("1 - Cadastrar Venda");
                        System.out.println("2 - Remover Venda");
                        System.out.println("3 - Listar Vendas");
                        System.out.println("4 - Buscar Venda por Id");
                        System.out.println("0 - Voltar");
                        int opcaoVenda = input.nextInt();
                        input.nextLine();

                        switch (opcaoVenda) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 0:
                                System.out.println("Voltando ao Menu Principal...");
                                estadoVenda = false;
                                break;
                        }
                    }
                    //Venda
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
}