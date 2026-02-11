package br.com.gabriel.gestaoagricola.app.ui;

import br.com.gabriel.gestaoagricola.domain.AreaCultivo;
import br.com.gabriel.gestaoagricola.domain.Cultura;
import br.com.gabriel.gestaoagricola.domain.Produtor;
import br.com.gabriel.gestaoagricola.service.GestaoAgricola;

import java.math.BigDecimal;
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
                    //menuPlantios();
                    break;
                case 5:
                    //menuManejos();
                    break;
                case 6:
                    //menuColheitas();
                    break;
                case 7:
                    //menuVendas();
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

            int opcaoProdutores = validadorInputIntIntervalo(">",0,4);

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
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite o id do produtor a ser excluído: ");
                    int idProdutorExcluido = validadorInputInt(">");
                    Produtor produtorExcluido = gestaoAgricola.buscarProdutorId(idProdutorExcluido);
                    if (produtorExcluido == null){
                        System.out.println("Produtor não encontrado!");
                    } else {
                        System.out.println("Confirme se é o produtor a ser excluído:");
                        System.out.println(produtorExcluido);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarProdutorExcluido = validadorInputIntIntervalo(">",1,2);
                        if (confirmarProdutorExcluido == 1) {
                            gestaoAgricola.removerProdutor(idProdutorExcluido);
                            System.out.println("Produtor removido com sucesso!");
                        } else {
                            System.out.println("Operação cancelada!");
                            break;
                        }
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
                    System.out.println("Digite o id do produtor a ser procurado");
                    int buscaProdutorId = validadorInputInt(">");
                    Produtor produtorProcurado =  gestaoAgricola.buscarProdutorId(buscaProdutorId);
                    if (produtorProcurado == null) {
                        System.out.println("Nenhum produtor cadastrado!");
                    } else {
                        System.out.println("Produtor encontrado com sucesso!");
                        System.out.println(produtorProcurado);
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
        System.out.println("4 - Buscar Produtor por id");
        System.out.println("0 - Voltar");
    }

    private void menuAreasDeCultivo() {
        boolean estadoAreasDeCultivo = true;
        while (estadoAreasDeCultivo) {
            System.out.println();
            exibirSubMenuAreasDeCultivo();

            int opcaoAreasDeCultivo = validadorInputIntIntervalo(">",0,4);

            switch (opcaoAreasDeCultivo) {
                case 1:
                    try {
                        System.out.println("Digite o id do produtor designado da área");
                        int idProdutor = validadorInputInt(">");
                        Produtor produtorEscolhido = gestaoAgricola.buscarProdutorId(idProdutor);
                        if (produtorEscolhido == null) {
                            System.out.println("Produtor não cadastrado!");
                            System.out.println("Escolha um produtor válido!");
                            break;
                        } else {
                            System.out.println("Digite o nome da área de cultivo");
                            String nomeAreaCultivo = input.nextLine();
                            System.out.println("Digite o tamanho da área de cultivo no formato: 0000.00");
                            BigDecimal tamanhoAreaCultivo = validadorInputBigDecimal(">");
                            AreaCultivo areaCriada = gestaoAgricola.adicionarAreaCultivo(produtorEscolhido, nomeAreaCultivo, tamanhoAreaCultivo);
                            System.out.println("Área cadastrada com sucesso! " + areaCriada);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite o id da área de cultivo a ser excluída: ");
                    int idAreaExcluida = validadorInputInt(">");
                     AreaCultivo areaExcluida = gestaoAgricola.buscarAreaCultivoId(idAreaExcluida);
                    if (areaExcluida == null) {
                        System.out.println("Área não encontrada!");
                    } else {
                        System.out.println("Confirme que é a área de cultivo a ser excluída");
                        System.out.println(areaExcluida);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarAreaExcluida = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarAreaExcluida == 1) {
                            gestaoAgricola.removerAreaCultivo(idAreaExcluida);
                            System.out.println("Area excluída com sucesso!");
                        } else {
                            System.out.println("Operação Cancelada!");
                        }
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
                    System.out.println("Digite o id da área a ser procurada");
                    int  idAreaProcurado = validadorInputInt(">");
                     AreaCultivo areaCultivoProcurada = gestaoAgricola.buscarAreaCultivoId(idAreaProcurado);
                    if (areaCultivoProcurada == null) {
                        System.out.println("Área não encontrada!");
                    } else {
                        System.out.println("Área de cultivo encontrada com sucesso!");
                        System.out.println(areaCultivoProcurada);
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
        System.out.println("4 - Buscar Área por id");
        System.out.println("0 - Voltar");
    }

    private void menuCulturas(){
        boolean estadoCulturas = true;
        while (estadoCulturas) {
            System.out.println();
            exibirSubMenuCulturas();

            int opcaoCulturas = validadorInputIntIntervalo(">", 0, 4);

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
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite o id da cultura a ser excluída");
                    int idCulturaExcluir = validadorInputInt(">");
                    Cultura culturaExcluir = gestaoAgricola.buscarCulturaId(idCulturaExcluir);
                    if (culturaExcluir == null) {
                        System.out.println("Cultura não encontrada!");
                    } else {
                        System.out.println("Confirme que é a cultura a ser excluída");
                        System.out.println(culturaExcluir);
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int confirmarCulturaExcluida = validadorInputIntIntervalo(">", 1, 2);
                        if (confirmarCulturaExcluida == 1) {
                            gestaoAgricola.removerCultura(idCulturaExcluir);
                            System.out.println("Cultura removida com sucesso!");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
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
                    System.out.println("Digite o id da cultura a ser procurada");
                    int idCulturaProcurado = validadorInputInt(">");
                    Cultura culturaProcurada = gestaoAgricola.buscarCulturaId(idCulturaProcurado);
                    if (culturaProcurada == null) {
                        System.out.println("Cultura não encontrada!");
                    } else {
                        System.out.println("Cultura encontrada com sucesso!");
                        System.out.println(culturaProcurada);
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
        System.out.println("4 - Buscar Cultura por id");
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
            if (valor.compareTo(min) < 0 || valor.compareTo(max) > 0) {
                System.out.println("Digite um valor entre " + min + " e " + max);
                continue;
            }
            return valor;
        }
    }
}