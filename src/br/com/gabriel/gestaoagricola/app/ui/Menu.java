package br.com.gabriel.gestaoagricola.app.ui;

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

            if(!input.hasNextInt()) {
                System.out.println("Entrada inválida, digite somente um número");
                input.nextLine();
                continue;
            }

            int opcaoPrincipal = input.nextInt();
            input.nextLine();

            switch (opcaoPrincipal) {
                case 1:
                    //menuProdutores()
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

}
