package dss.armazem.ui;

import dss.armazem.business.IArmazemLN;

import java.util.Scanner;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private IArmazemLN model;

    // Menus da aplicação
    private final Menu menu;

    // Scanner para leitura
    private Scanner sc;


    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {
        // Criar o menu
        String[] opcoes = {
                "Comunicar código QR",
                "Comunicar ordem de transporte",
                "Consultar listagem de localizacoes",
                "Adicionar Turma",
                };
        this.menu = new Menu(opcoes);
        //this.model = new ();
        this.sc = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
            }
        } while (menu.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        System.out.println("Até breve!...");
    }

    //Métodos auxiliares

}
