package br.com.gabriel.gestaoagricola.app.ui;

import br.com.gabriel.gestaoagricola.domain.Produtor;
import br.com.gabriel.gestaoagricola.service.GestaoAgricola;

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
                    //menuAreasDeCultivo()
                    break;
                case 3:
                    //menuCulturas()
                    break;
                case 4:
                    //menuPlantios()
                    break;
                case 5:
                    //menuManejos()
                    break;
                case 6:
                    //menuColheitas()
                    break;
                case 7:
                    //menuVendas()
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
        boolean estadoProdutor = true;
        while (estadoProdutor) {
            System.out.println();
            exibirSubMenuProdutores();

            int opcaoProdutor = validadorInputIntIntervalo(">",0,4);

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
                    System.out.println("Digite o Id do Produtor a ser excluído: ");
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
                    System.out.println("Digite o Id do Produtor a ser procurado");
                    int buscaProdutorId = validadorInputInt(">");
                    Produtor produtorProcurado =  gestaoAgricola.buscarProdutorId(buscaProdutorId);
                    if (produtorProcurado == null) {
                        System.out.println("Nenhum produtor cadastrado!");
                    } else {
                        System.out.println("Produtor procurado com sucesso!");
                        System.out.println(produtorProcurado);
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal");
                    estadoProdutor = false;
                    break;
                default:
                    System.out.println("Digite uma opção válida");
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
        System.out.println("4 - Buscar Produtor por Id");
        System.out.println("0 - Voltar");
    }

    private int validadorInputInt (String prompt) {
        while (true) {
            System.out.println(prompt);

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
                System.out.print("Digite um valor entre " + min + " e " + max);
                continue;
            }
            return valor;
        }
    }
}
