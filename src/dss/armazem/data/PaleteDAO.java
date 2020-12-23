package dss.armazem.data;

import dss.armazem.business.ssgestpaletes.Palete;
import java.sql.*;
import java.util.*;

public class PaleteDAO {
    private static PaleteDAO singleton = null;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String OPTIONS = "&useTimezone=true&serverTimezone=UTC";
    private static final String DATABASE = "localhost:3306/armazem";


    /**
     * Construtor que permite a criação da tabela Palete residente na base de dados
     */
    public PaleteDAO() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `armazem`.`palete` (\n" +
                    "  `id` VARCHAR(10) NOT NULL,\n" +
                    "  `estado` VARCHAR(15) NOT NULL,\n" +
                    "  `descricao` VARCHAR(45) NOT NULL,\n" +
                    "  `locSeccao` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `fk_palete_seccao1_idx` (`locSeccao` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `fk_palete_seccao1`\n" +
                    "    FOREIGN KEY (`locSeccao`)\n" +
                    "    REFERENCES `armazem`.`seccao` (`loc`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)";
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
    public static PaleteDAO getInstance() {
        if (PaleteDAO.singleton == null) {
            PaleteDAO.singleton = new PaleteDAO();
        }
        return PaleteDAO.singleton;
    }

    /**
     * Insere uma Palete na base de dados
     * @param p Palete
     */
    public void put(Palete p) throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement();
             Statement stmTeste = connection.createStatement()) {
            ResultSet teste = stmTeste.executeQuery("select * from palete where id = '" + p.getID() + "'");
            String idTeste = null;
            while (teste.next()) {
                idTeste = teste.getString("id");
            }
            if (idTeste != null) throw new Exception("Identificador já existente!");
            stm.executeUpdate("DELETE FROM palete WHERE id = '" + p.getID() + "'");
            stm.executeUpdate("INSERT INTO palete " +
                                "VALUES ('" + p.getID() + "', " +
                                "'" + p.getEstado() + "', " +
                                "'" + p.getDescricao() + "', " +
                                p.getLoc() + ")");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Atualiza o estado e a localização de uma pelete com um determinado identificador
     * @param id identificador
     * @param loc localização
     * @param estado estado
     */
    public void updateEstadoLoc(String id, int loc, String estado) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stmLoc = connection.createStatement();
             Statement stmEstado = connection.createStatement()) {
            stmLoc.executeUpdate("update palete set locSeccao = " + loc  + " where id = '" + id + "'");
            stmEstado.executeUpdate("update palete set estado = '" + estado  + "' where id = '" + id + "'");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Remove uma Palete da base de dados
     * @param id identificador
     */
    public void remove(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            stm.executeUpdate("DELETE FROM palete WHERE id = '" + id + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Obtém uma Palete com um determinado identificador a partir da base de dados
     * @param id identificador
     * @return Palete
     */
    public Palete get(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT estado, descricao, locSeccao FROM palete WHERE id = '" + id + "'");
            String estado = null, descricao = null;
            int loc = 0;
            while (rs.next()) {
                estado = rs.getString("estado");
                descricao = rs.getString("descricao");
                loc = rs.getInt("locSeccao");
            }
            return new Palete(estado, id, descricao, loc);
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Obtém a lista de Paletes contidas na base de dados
     * @return Lista de Paletes
     */
    public Collection<Palete> get() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("select * from palete order by locSeccao asc");
            Collection<Palete> list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String estado = rs.getString("estado");
                String descricao = rs.getString("descricao");
                int loc = rs.getInt("locSeccao");
                list.add(new Palete(estado, id, descricao, loc));
            }
            return list;
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Obtém a primeira Palete que tenha o estado "Queue"
     * @return Palete
     */
    public Palete getFirstPaleteInQueue() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("select * from palete where id = (select min(id) from palete where estado = 'Queue');");
            String id = null, estado = null, descricao = null;
            int loc = 0;
            while (rs.next()) {
                id = rs.getString("id");
                estado = rs.getString("estado");
                descricao = rs.getString("descricao");
                loc = rs.getInt("locSeccao");
            }
            return new Palete(estado, id, descricao, loc);
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

}
