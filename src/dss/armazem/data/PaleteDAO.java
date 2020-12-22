package dss.armazem.data;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestrobots.Robot;

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

    public void put(Palete p) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            stm.executeUpdate("DELETE FROM palete WHERE id = '" + p.getID() + "'");
            // Actualizar a Sala
            stm.executeUpdate(
                    "INSERT INTO palete " +
                            "VALUES ('"+ p.getID() + "', " +
                            "'"+ p.getEstado() + "', " +
                            "'"+ p.getDescricao() + "', " +
                            p.getLoc() + ")");
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
            stm.executeUpdate("DELETE FROM palete WHERE id = '" + id + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

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

    public Collection<Palete> get() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM palete");
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

}
