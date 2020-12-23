package dss.armazem.ui;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestrobots.MyEntry;
import java.util.*;

public class Menu {
    private static final Scanner is = new Scanner(System.in);
    private final List<String> opcoes;
    private int op;

    /**
     * Constructor for objects of class Menu
     */
    public Menu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /** Apresentar o menu */
    private void showMenu() {
        System.out.println("\n ---------------- Menu ---------------- ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1 + " - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    /** Ler uma opção válida */
    private int lerOpcao() {
        int op;

        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
            System.out.println(e.toString());
        }
        if (op < 0 || op > this.opcoes.size()) {
            System.out.println("Opção Inválida!");
            op = -1;
        }
        return op;
    }

    /**
     * Método para obter a última opção lida
     */
    public int getOpcao() {
        return this.op;
    }

    public void printaCaminho(List<MyEntry<String, Integer>> caminho) {
        String robotID = caminho.get(0).getKey();
        String paleteID = caminho.get(1).getKey();
        System.out.println("O robot com o id " + robotID + " vai transportar-se até à palete " + paleteID);

        String[] r = new String[caminho.size() - 2];
        for(int i = 2; i < caminho.size(); i++) {
            r[i - 2] = caminho.get(i).getKey();
        }

        System.out.println("O robot recebeu o seguinte caminho: " + Arrays.toString(r));
    }

    /**
     * Método que mostra ao Utilizador a localização e o id duma lista de paletes recebida.
     * @param paletes Lista de paletes a mostrar.
     */
    public void printaListaPaletes(Collection<Palete> paletes) {
        for(Palete p : paletes) {
            System.out.println("Palete: " + p.getID() + ", Localização -> " + p.getLoc());
        }
    }
}