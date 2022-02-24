package pt.ipp.estg.interfaces;

import structures.ArrayUnorderedList;

public interface IAgent {

    /**
     * Método que retorna quantos passos o agente fez para entrar e sair do edifício
     *
     * @return o número de passos
     */
    int getStepCount();

    /**
     * Método que retorna o caminho que o agente fez
     *
     * @return o caminho
     */
    ArrayUnorderedList<IRoom> getPath();

    /**
     * Método que define em que divisão o agente se encontra
     *
     * @param room divisão
     */
    void setRoom(IRoom room);

    /**
     * Método que retorna os pontos de vida do agente
     *
     * @return os pontos de vida
     */
    int getPoints();

    /**
     * Método retorna a divisão em que o agente se encontra
     *
     * @return a divisão
     */
    IRoom getRoom();

    /**
     * Método que executa a movimentação do agente e calcula o dano sofrido
     * caso haja inimigos na nova divisão
     *
     * @param room divisão de destino
     */
    void move(IRoom room);

    /**
     * Método que retorna quantos inimigos o agente derrotou
     *
     * @return o número de inimigos derrotados
     */
    int getEnemiesDefeated();

    /**
     * Método que diz se o agente encontrou o alvo ou não
     *
     * @return true se encontrou, false se não encontrou
     */
    boolean isAcquiredTarget();
}
