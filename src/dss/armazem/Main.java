package dss.armazem;


import dss.armazem.business.ssgestrobots.Mapa;
import dss.armazem.business.ssgestrobots.MyEntry;
import dss.armazem.business.ssgestrobots.Node;
import dss.armazem.ui.TextUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        try {
            //new TextUI().run();
            Mapa m = new Mapa();

            int[] visitados = new int[14];
            for (int i = 0; i < 14; i++)
                visitados[i] = 0;
            Collection<MyEntry<String, Integer>> caminho = m.caminhoMaisRapido("5", "9", 14, visitados);
            for (MyEntry<String, Integer> entry : caminho)
                System.out.println("Vértice: " + entry.getKey());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
