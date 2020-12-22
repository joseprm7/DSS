package dss.armazem.ui;

import dss.armazem.business.ArmazemLN;
import dss.armazem.business.IArmazemLN;
import dss.armazem.business.ssgestpaletes.Palete;

import java.util.Collection;
import java.util.Scanner;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private final IArmazemLN model;

    // Menus da aplicação
    private final Menu menu;

    // Scanner para leitura
    private final Scanner sc;


    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {
        // Criar o menu
        String[] opcoes = {
                "Ler código QR",
                "Comunicar ordem de transporte",
                "Comunicar entrega de palete",
                "Comunicar recolha de palete",
                "Consultar listagem de localizacoes"
                };
        this.menu = new Menu(opcoes);
        //this.model = new ();
        this.sc = new Scanner(System.in);
        this.model = new ArmazemLN();
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    System.out.println("Id da palete");
                    String id = this.sc.next();
                    System.out.println("Descrição da palete");
                    String d = this.sc.next();
                    this.model.validaCodigo(id, d);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    Collection<Palete> listaPaletes = this.model.getListaPaletes();
                    for(Palete palete : listaPaletes)
                        System.out.println(palete.toString());
                    break;
            }
        } while (menu.getOpcao()!=0);
        System.out.println("Fechando os portões...");
    }

}
