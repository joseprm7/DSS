package dss.armazem.ui;

import dss.armazem.business.ArmazemLN;
import dss.armazem.business.IArmazemLN;
import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestrobots.MyEntry;

import java.util.Collection;
import java.util.List;
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
                "Comunicar recolha de palete",
                "Comunicar entrega de palete",
                "Consultar listagem de localizacoes"
                };
        this.menu = new Menu(opcoes);
        this.sc = new Scanner(System.in);
        this.model = new ArmazemLN();
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run(){
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    System.out.println("Id da palete");
                    String id = this.sc.next();
                    System.out.println("Descrição da palete");
                    String d = this.sc.next();
                    Collection<MyEntry<String, Integer>> caminho = this.model.validaCodigo(id, d);
                    if(caminho != null) this.menu.printaCaminho((List<MyEntry<String, Integer>>) caminho);
                    break;
                case 2:
                    caminho = this.model.transporte();
                    if(caminho != null) this.menu.printaCaminho((List<MyEntry<String, Integer>>) caminho);
                    break;
                case 3:
                    System.out.println("ID da palete");
                    String pa = this.sc.next();
                    this.model.notificaRecolha(pa);
                    break;
                case 4:
                    System.out.println("ID do Robot");
                    String rt = this.sc.next();
                    System.out.println("Localização Atual");
                    int loc = this.sc.nextInt();
                    this.model.notificaEntrega(rt,loc);
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
