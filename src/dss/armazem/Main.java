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
            new TextUI().run();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
