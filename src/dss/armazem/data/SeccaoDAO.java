package dss.armazem.data;

import dss.armazem.business.ssgestpaletes.Seccao;
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
     * Construtor que permite a criação da tabela Seccao residente na base de dados
     */
    public SeccaoDAO() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `armazem`.`seccao` (\n" +
                    "  `id` VARCHAR(10) NOT NULL,\n" +
                    "  `prateleira` INT NOT NULL,\n" +
                    "  `loc` INT NULL,\n" +
                    "  `cheia` TINYINT NOT NULL,\n" +
                    "  PRIMARY KEY (`loc`))";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     * @return devolve a instância única desta classe
     */
    public static SeccaoDAO getInstance() {
        if (SeccaoDAO.singleton == null) {
            SeccaoDAO.singleton = new SeccaoDAO();
        }
        return SeccaoDAO.singleton;
    }

    /**
     * Insere uma Seccao na BD
     * @param s Seccao
     */
    public void put(Seccao s) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            stm.executeUpdate("DELETE FROM seccao WHERE loc = " + s.getLoc() + "");
            stm.executeUpdate(
                    "INSERT INTO seccao " +
                            "VALUES ('"+ s.getId() + "', " +
                            s.getPrateleira() + ", " +
                            s.getLoc() + ", " +
                            s.isOcupado() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Remove uma Seccao com uma determinada localização da BD
     * @param loc Localização
     */
    public void remove(int loc) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            stm.executeUpdate("DELETE FROM seccao WHERE loc = " + loc + "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Obtém uma Seccao com uma determinada localização
     * @param loc localização
     * @return Seccao
     */
    public Seccao get(int loc) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            ResultSet rsPalete = stm.executeQuery("SELECT * FROM palete WHERE locSeccao = " + loc);
            List<String> listPalete = new ArrayList<>();
            while (rsPalete.next()) {
                String idPalete = rsPalete.getString("id");
                listPalete.add(idPalete);
            }
            ResultSet rsSeccao = stm.executeQuery("SELECT * FROM seccao where loc = " + loc);
            String id = null; boolean cheia = false; int prateleira = 0;
            while (rsSeccao.next()) {
                id = rsSeccao.getString("id");
                cheia = rsSeccao.getBoolean("cheia");
                prateleira = rsSeccao.getInt("prateleira");
            }
            return new Seccao(id, listPalete, cheia, loc, prateleira);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Obtém a primeira Seccao que não esteja totalmente ocupada por paletes
     * @return Seccao
     */
    public Seccao getSeccaoLivre() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {

            ResultSet rsSeccao = stm.executeQuery("SELECT * FROM seccao where cheia = false group by id");
            String id = null; boolean cheia = false; int prateleira = 0; int loc = 0;
            while (rsSeccao.next()) {
                id = rsSeccao.getString("id");
                cheia = rsSeccao.getBoolean("cheia");
                prateleira = rsSeccao.getInt("prateleira");
                loc = rsSeccao.getInt("loc");
            }
            ResultSet rsPalete = stm.executeQuery("SELECT * FROM palete WHERE locSeccao = " + loc);
            List<String> listPalete = new ArrayList<>();
            while (rsPalete.next()) {
                String idPalete = rsPalete.getString("id");
                listPalete.add(idPalete);
            }
            return new Seccao(id, listPalete, cheia, loc, prateleira);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Altera o boolean 'cheia' conforme o que for pedido pelo argumento
     * @param loc localização
     * @param cheia cheia
     */
    public void updateCheia(int loc, boolean cheia) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stmCheia = connection.createStatement()) {
            stmCheia.executeUpdate("update seccao set cheia = " + cheia  + " where loc = " + loc);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
}