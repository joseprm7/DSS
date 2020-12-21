package dss.armazem.data;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestpaletes.Seccao;
import dss.armazem.business.ssgestrobots.Localizacao;
import dss.armazem.business.ssgestrobots.Robot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeccaoDAO {
    private static SeccaoDAO singleton = null;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String OPTIONS = "&useTimezone=true&serverTimezone=UTC";
    private static final String DATABASE = "localhost:3306/armazem";


    /**
     * Construtor que permite a criação da tabela Palete residente na base de dados
     */
    public SeccaoDAO() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `armazem`.`seccao` (\n" +
                    "  `id` VARCHAR(10) NOT NULL,\n" +
                    "  `locInicial` INT NOT NULL,\n" +
                    "  `locFinal` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static SeccaoDAO getInstance() {
        if (SeccaoDAO.singleton == null) {
            SeccaoDAO.singleton = new SeccaoDAO();
        }
        return SeccaoDAO.singleton;
    }

    /**
     * Métodos put, remove e get
     */

    public void put(String id, int locInicial, int locFinal) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO seccao " +
                            "VALUES ('"+ id + "', " +
                            "'"+ locInicial + "', " +
                            "'" + locFinal + "')");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void remove(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            stm.executeUpdate("DELETE FROM seccao WHERE id = '" + id + "'");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public Seccao get(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM palete WHERE idSeccao = '" + id + "'");
            List<Palete> listPalete = new ArrayList<>();
            while (rs.next()) {
                String idPalete = rs.getString("id");
                String estado = rs.getString("estado");
                String descricao = rs.getString("descricao");
                boolean queue = rs.getBoolean("queue");
                int loc = rs.getInt("loc");
                listPalete.add(new Palete(idPalete, estado, descricao, new Localizacao(loc)));
            }
            return new Seccao(id, listPalete);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

}
