package dss.armazem;

import dss.armazem.ui.TextUI;

public class Main {

    public static void main(String[] args) {
        try {
            new TextUI().run();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
