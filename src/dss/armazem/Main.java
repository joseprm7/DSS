package dss.armazem;

import dss.armazem.business.ArmazemLN;
import dss.armazem.business.ssgestrobots.Localizacao;
import dss.armazem.ui.TextUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            /*ArmazemLN armazem = new ArmazemLN();
            //new TextUI().run();
            Scanner in = new Scanner(System.in);
            armazem.notificaEntrega("0",new Localizacao(4));*/
            //Obter uma conexão
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/armazem?user=root&useTimezone=true&serverTimezone=UTC", "root", "peiobedisto2411");

            //Criar um statement
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from palete");

            while (rs.next())
                System.out.println(rs.getString("estado"));
        }
        catch (Exception e) {
            System.out.println("Não foi possível arrancar: "+e.getMessage());
        }
    }
}
