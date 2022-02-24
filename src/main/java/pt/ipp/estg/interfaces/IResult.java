package pt.ipp.estg.interfaces;

import pt.ipp.estg.game.SimulationType;

public interface IResult {

    /**
     * Método que diz se a missão falhou ou não
     *
     * @return o estado da missão
     */
    String stateToString();

    /**
     * Método que retorna o tipo da simulação (automática/manual)
     *
     * @return o tipo
     */
    SimulationType getSimulationType();

    /**
     * Método que retorna a contagem de passos
     *
     * @return o número de passos
     */
    int getStepCount();

    /**
     * Método que retorna os pontos
     *
     * @return os pontos
     */
    int getPoints();

    /**
     * Método que define os pontos
     *
     * @param points pontos
     */
    void setPoints(int points);

    /**
     * Método que retorna o estado da missão
     *
     * @return true se sucesso, false se fracasso
     */
    boolean getMissionState();

    /**
     * Método que define o estado da missão
     *
     * @param missionState estado da missão
     */
    void setMissionState(boolean missionState);

    /**
     * Método que retorna o caminho para o alvo
     *
     * @return o caminho
     */
    String getPathToTarget();

    /**
     * Método que define o caminho para o alvo
     * @param pathToTarget caminho
     */
    void setPathToTarget(String pathToTarget);

    /**
     * Método que retorna o caminho para a saída
     *
     * @return o caminho
     */
    String getPathToExit();

    /**
     * Método que define o caminho para a saída
     *
     * @param pathToExit caminho
     */
    void setPathToExit(String pathToExit);
}
