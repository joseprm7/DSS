package dss.armazem.data;

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
                    "  `idSeccao` VARCHAR(10) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `fk_palete_seccao_idx` (`idSeccao` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `fk_palete_seccao`\n" +
                    "    FOREIGN KEY (`idSeccao`)\n" +
                    "    REFERENCES `armazem`.`seccao` (`id`)\n" +
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

    public int size() {
        int i = 0;
        try (Connection conn =
                     DriverManager.getConnection("jdbc:mariadb://"+DATABASE+CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM turmas")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

}
