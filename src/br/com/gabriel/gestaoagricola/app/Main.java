package br.com.gabriel.gestaoagricola.app;

import br.com.gabriel.gestaoagricola.app.ui.Menu;
import br.com.gabriel.gestaoagricola.service.GestaoAgricola;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    GestaoAgricola gestaoAgricola = new GestaoAgricola();

    Menu menu = new Menu(input, gestaoAgricola);
    menu.menuPrincipal();
    }
}