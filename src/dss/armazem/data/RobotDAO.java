package dss.armazem.data;

import dss.armazem.business.ssgestrobots.Robot;
import java.sql.*;

public class RobotDAO {
    private static RobotDAO singleton = null;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String OPTIONS = "&useTimezone=true&serverTimezone=UTC";
    private static final String DATABASE = "localhost:3306/armazem";


    /**
     * Construtor que permite a criação da tabela Palete residente na base de dados
     */
    public RobotDAO() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `armazem`.`robot` (\n" +
                    "  `id` VARCHAR(10) NOT NULL,\n" +
                    "  `estado` VARCHAR(15) NOT NULL,\n" +
                    "  `loc` INT NOT NULL,\n" +
                    "  `idPalete` VARCHAR(10) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `fk_robot_palete1_idx` (`idPalete` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `fk_robot_palete1`\n" +
                    "    FOREIGN KEY (`idPalete`)\n" +
                    "    REFERENCES `armazem`.`palete` (`id`)\n" +
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
    public static RobotDAO getInstance() {
        if (RobotDAO.singleton == null) {
            RobotDAO.singleton = new RobotDAO();
        }
        return RobotDAO.singleton;
    }

    public void put(Robot robot) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            stm.executeUpdate("DELETE FROM robot WHERE id = '" + robot.getId() + "'");
            // Actualizar a Sala
            stm.executeUpdate(
                    "INSERT INTO robot " +
                            "VALUES ('"+ robot.getId() + "', " +
                            "'"+ robot.getEstado() + "', " +
                            robot.getLoc() + ", " +
                            "'" + robot.getPalete() + "')");
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
            stm.executeUpdate("DELETE FROM robot WHERE id = '" + id + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public Robot get(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?user=" +
                USERNAME + OPTIONS, USERNAME, PASSWORD);
             Statement stm = connection.createStatement()) {
            ResultSet rsRobot = stm.executeQuery("SELECT * FROM robot WHERE id = '" + id + "'");
            String estado = null, idPalete = null;
            int loc = 0;

            while(rsRobot.next()) {
                estado = rsRobot.getString("estado");
                loc = rsRobot.getInt("loc");
                idPalete = rsRobot.getString("idPalete");
            }

            return new Robot(id, estado, idPalete, loc);

        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
}
