package pt.ipp.estg.interfaces;

import exceptions.NonComparableElementException;
import structures.ArrayUnorderedList;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Interface da classe Room</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public interface IRoom {

    /**
     * Setter para a variável buildingAccess
     *
     * @param buildingAccess valor booleano que representa uma entrada ou saída
     */
    public void setBuildingAccess(boolean buildingAccess);

    /**
     * Getter para a variável buildingAccess
     *
     * @return retorna verdadeiro se a divisão for uma entrada ou saída
     */
    public boolean isBuildingAccess();

    /**
     * Setter para o objeto target
     *
     * @param target um objeto com as informações do alvo
     */
    public void setTarget(ITarget target);

    /**
     * Método que adiciona uma ligação do tipo IRoom à lista de links
     *
     * @param link objeto referente às ligações de uma divisão
     */
    public void addLink(IRoom link);

    /**
     * Método que adiciona um inimigo lista de inimigos referentes a uma divisão
     * @param enemy objeto referente aos inimigos de uma divisão
     * @throws NonComparableElementException exceção herdada da estrutura selecionada
     */
    public void addEnemy(IEnemy enemy) throws NonComparableElementException;

    /**
     * Método que limpa a lista de inimigos para quando o agente os derrotar
     */
    public void clearEnemies();

    /**
     * Método que calcula o somatório do poder dos inimigos associados a uma divisão
     * @return retorna o valor do somatório do poder dos inimigos associados a uma divisão
     */
    public int getEnemiesPower();

    /**
     * Getter para uma lista desordenada de inimigos
     * @return retorna uma lista desordenada de inimigos
     */
    public ArrayUnorderedList<IEnemy> getEnemies();

    /**
     * Getter para a variável room
     * @return retorna o nome da divisão do edifício
     */
    public String getRoom();

    /**
     * Setter para a variável room
     * @param room o nome da divisão do edifício
     */
    public void setRoom(String room);

    /**
     * Getter para uma lista desordenada de ligações entre divisões
     * @return retorna uma lista desordenada de ligações entre divisões
     */
    public ArrayUnorderedList<IRoom> getLinks();

    /**
     * Getter para um objeto do tipo ITarget
     * @return retorna um objeto com as informações relativas ao alvo do agente
     */
    public ITarget getTarget();
}
