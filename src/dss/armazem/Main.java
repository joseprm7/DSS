package dss.armazem;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.data.PaleteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {
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
            /*PaleteDAO paleteDAO = new PaleteDAO();
            paleteDAO.put("3", "Transporte", "Bróculos", "0", 43);
            Palete palete = paleteDAO.get("3");
            System.out.println(palete.getID() + ", " + palete.getEstado() + ", " + palete.getDescricao() + ", " + palete.getLoc() + ", " + palete.getLoc());*/

        }
        catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}
