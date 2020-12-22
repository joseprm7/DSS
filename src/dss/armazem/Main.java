package dss.armazem;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestpaletes.Seccao;
import dss.armazem.business.ssgestrobots.Robot;
import dss.armazem.data.PaleteDAO;
import dss.armazem.data.RobotDAO;
import dss.armazem.data.SeccaoDAO;
import dss.armazem.ui.TextUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

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

        }
        catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}
