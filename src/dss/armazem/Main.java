package dss.armazem;


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
            /*ArmazemLN armazem = new ArmazemLN();
            //new TextUI().run();
            Scanner in = new Scanner(System.in);
            armazem.notificaEntrega("0",new Localizacao(4));*/
            //Obter uma conexão

            /*Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/armazem?user=root&useTimezone=true&serverTimezone=UTC", "root", "123456");

            //Criar um statement
            Statement statement = connection.createStatement();

            /*ResultSet rs = statement.executeQuery("select * from palete");

            while (rs.next())
                System.out.println(rs.getString("estado"));*/
            //SeccaoDAO seccaoDAO = new SeccaoDAO();
            //seccaoDAO.put("2", 1, 5, false);
            //seccaoDAO.remove(3);
            /*Mapa m = new Mapa();
            for (int i = 1; i <= 5; i++)
                m.put(Integer.toString(i), new ArrayList<>());
            m.addNodo("1", new Node("2", 1));
            m.addNodo("2", new Node("3", 1));
            m.addNodo("2", new Node("5", 1));
            m.addNodo("2", new Node("1", 1));
            m.addNodo("3", new Node("4", 1));
            m.addNodo("3", new Node("2", 1));
            m.addNodo("4", new Node("5", 1));
            m.addNodo("4", new Node("3", 1));
            m.addNodo("5", new Node("2", 1));
            m.addNodo("5", new Node("4", 1));
            Map<String, Integer> caminho = m.caminhoMaisRapido("5", "2");
            for(Map.Entry<String, Integer> map : caminho.entrySet())
                System.out.println("Vértice: " + map.getKey() + ", Peso: " + map.getValue());*/

        }
        catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}
