package dss.armazem;

import dss.armazem.business.ArmazemLN;
import dss.armazem.business.ssgestrobots.Localizacao;
import dss.armazem.ui.TextUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            ArmazemLN armazem = new ArmazemLN();
            //new TextUI().run();
            Scanner in = new Scanner(System.in);
            armazem.notificaEntrega("0",new Localizacao(4));

        }
        catch (Exception e) {
            System.out.println("Não foi possível arrancar: "+e.getMessage());
        }
    }
}
