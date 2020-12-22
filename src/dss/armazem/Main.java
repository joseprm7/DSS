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
            for (int i = 1; i <= 5; i++)
                m.put(new MyEntry<>(Integer.toString(i), new ArrayList<>()));
            m.addNodo(1, new Node("2", 1));
            m.addNodo(2, new Node("3", 1));
            m.addNodo(2, new Node("5", 1));
            m.addNodo(2, new Node("1", 1));
            m.addNodo(3, new Node("4", 1));
            m.addNodo(3, new Node("2", 1));
            m.addNodo(4, new Node("5", 1));
            m.addNodo(4, new Node("3", 1));
            m.addNodo(5, new Node("2", 1));
            m.addNodo(5, new Node("4", 1));
            Collection<Node> nodosSucessores = m.getGrafo().get(Integer.getInteger("6")-1).getValue();

            //Collection<MyEntry<String, Integer>> caminho = m.caminhoMaisRapido("5", "2", 5);
            //System.out.println(caminho);
            /*for (MyEntry<String, Collection<Node>> entry : m.getGrafo())
                System.out.println(entry.toString());
            for (MyEntry<String, Integer> map : caminho)
                System.out.println("Vértice: " + map.getKey() + ", Peso: " + map.getValue());*/
            for(MyEntry<String, Collection<Node>> entry : m.getGrafo()) {
                System.out.println("Vértice: " + entry.getKey());
                for (Node node : entry.getValue())
                    System.out.println("Sucessor: " + node.getDestino());
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
