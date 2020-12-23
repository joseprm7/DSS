package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.IGestPaletes;
import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestpaletes.SSGestPaletes;
import dss.armazem.business.ssgestrobots.IGestRobots;
import dss.armazem.business.ssgestrobots.MyEntry;
import dss.armazem.business.ssgestrobots.Robot;
import dss.armazem.business.ssgestrobots.SSGestRobots;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe principal que visa gerir o Armazém.
 * Nela, existem como variáveis os seus subsistemas:
 * O subsistema de gestão de paletes e o de gestão de robots.
 */
public class ArmazemLN implements IArmazemLN {
    private final IGestPaletes gestPaletes;
    private final IGestRobots gestRobots;

    /**
     * Construtor vazio
     */
    public ArmazemLN() {
        this.gestPaletes = new SSGestPaletes();
        this.gestRobots = new SSGestRobots();
    }

    /**
     * Adiciona o identificador e a descrição de uma Palete às variáveis de instância
     * necessárias do subsistema das Paletes. Depois chama a função transporte para
     * verificar se há algum robot livre para guardar a encomenda.
     * @param id Identificador da Palete
     * @param descricao Descrição da Palete
     */
    public Collection<MyEntry<String, Integer>> validaCodigo(String id, String descricao) throws Exception {
        this.gestPaletes.validaCodigo(id, descricao);
        return this.transporte();
    }

    /**
     * Vai à base de dados buscar a lista de todas as paletes existentes, ordenadas
     * pela localização, por ordem crescente, para depois mostrar ao gestor.
     * @return lista de paletes existentes
     */
    public Collection<Palete> getListaPaletes() {
        return this.gestPaletes.getListaPaletes();
    }


    /**
     * A partir de um determinado identificador, um robot irá notificar o Sistema
     * que já entregou a Palete e alterará o seu estado para "Livre". Este método será
     * realizado pelo Robot. Uma vez que este se encontra Livre, chama-se a função transporte()
     * para verificar se há paletes para este transportar.
     * @param idRobot identificador
     * @param locAtual localização atual do Robot em questão
     */
    public Collection<MyEntry<String, Integer>> notificaEntrega(String idRobot, int locAtual) throws Exception {
        String idPalete = this.gestRobots.notificaEntrega(idRobot, locAtual);
        this.gestPaletes.notificaEntrega(idPalete, locAtual);
        return this.transporte();
    }

    /**
     * Atribui paletes em "Queue" a robots "Livres". Estas vão ser guardadas
     * numa seccão que não esteja cheia, logo começa por procurar robots livres, caso não haja não faz mais
     * nada, depois procura uma palete em queue e se encontrar procura um lugar para a guardar.
     * Caso encontre estes três elementos, através da localização do robot e da prateleira, calcula um
     * percurso para o robot.
     * @return caminho para o robot percorrer, caso seja necessario o robot ir buscar uma palete
     */
    public Collection<MyEntry<String, Integer>> transporte() throws Exception {
        Collection<MyEntry<String, Integer>> res = null;
        Robot r = this.gestRobots.robotLivre();
        if(r.getId() != null) {
            Palete p = this.gestPaletes.transporte();
            if(p.getID() != null) {
                res = new ArrayList<>();
                res.add(new MyEntry<>(r.getId(), 0));
                res.add(new MyEntry<>(p.getID(), 0));
                res.addAll(this.gestRobots.transporte(r, p));
                this.gestRobots.alteraLoc(p.getLoc(), r.getId());
            }
        }
        return res;
    }

    /**
     * O robot utiliza este método para avisar o Sistema que já recolheu a palete que lhe
     * foi solicitada. Depois o sistema altera o estado da palete e calcula um novo percurso para o robot.
     * @param idPalete identificador da Palete
     */
    public Collection<MyEntry<String, Integer>> notificaRecolha(String idPalete, String idRobot) {
        Palete p = this.gestPaletes.notificaRecolha(idPalete);
        Robot r = this.gestRobots.getRobot(idRobot);
        return this.gestRobots.transporte(r, p);
    }
}