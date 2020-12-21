package dss.armazem.data;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestrobots.Robot;

import java.sql.*;
import java.util.*;

public class PaleteDAO {
    private static PaleteDAO singleton = null;

    private static final String USERNAME = "g41";
    private static final String PASSWORD = "g41";
    private static final String CREDENTIALS = "?user="+USERNAME+"&password="+PASSWORD;
    private static final String DATABASE = "localhost:3306/turmas3l";


    /**
     * Construtor que permite a criação da tabela Palete residente na base de dados
     */
    private PaleteDAO() {
//        Driver é carregado automaticamente quando se abre uma conexão
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//        }
//        catch (ClassNotFoundException e) {
//            // Driver não disponível
//            e.printStackTrace();
//            throw new NullPointerException(e.getMessage());
//        }
        try (Connection conn =
                     DriverManager.getConnection("jdbc:mariadb://"+DATABASE+CREDENTIALS);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `armazem`.`palete` (\n" +
                    "  `id` VARCHAR(10) NOT NULL,\n" +
                    "  `estado` VARCHAR(15) NOT NULL,\n" +
                    "  `descricao` VARCHAR(45) NULL,\n" +
                    "  `seccao_id` VARCHAR(10) NOT NULL,\n" +
                    "  `queue` TINYINT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `fk_palete_seccao_idx` (`seccao_id` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `fk_palete_seccao`\n" +
                    "    FOREIGN KEY (`seccao_id`)\n" +
                    "    REFERENCES `mydb`.`seccao` (`id`)\n" +
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

    public void put(String id, String estado, String descricao, int seccao_id, boolean queue) {
        try (Connection conn =
                     DriverManager.getConnection("jdbc:mariadb://"+DATABASE+CREDENTIALS);
             Statement stm = conn.createStatement()) {

            // Actualizar a Sala
            stm.executeUpdate(
                    "INSERT INTO salas " +
                            "VALUES ('"+ id + "', " +
                            "'"+ estado + "', " +
                            "'"+ descricao + "', " +
                            "'" + seccao_id + "', " +
                            "'" + queue + "')");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void remove(String id) {
        try (Connection conn =
                     DriverManager.getConnection("jdbc:mariadb://"+DATABASE+CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM palete WHERE id ='" + id + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

}
