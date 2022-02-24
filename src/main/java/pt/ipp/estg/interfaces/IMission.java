package pt.ipp.estg.interfaces;

import exceptions.NonComparableElementException;
import structures.ArrayOrderedList;

import java.io.IOException;

public interface IMission {

    /**
     * Método que retorna os resultados das missões
     * @return os resultados
     */
    ArrayOrderedList<IResult> getResults();

    /**
     * Método que armazena os resultados das missões
     *
     * @param results resultados
     */
    public void setResults(ArrayOrderedList<IResult> results);

    /**
     * Método que adiciona resultados à lista
     *
     * @param result novo resultado
     * @throws NonComparableElementException se objeto não for comparável
     */
    public void addResult(IResult result) throws NonComparableElementException;

    /**
     * Método que exporta o resultado da missão para json
     *
     * @param mission missão a exportar
     * @throws IOException
     */
    public void jsonExport(IMission mission) throws IOException;

    /**
     * Método que retorna o ID da missão
     *
     * @return o ID
     */
    public String getMissionID();

    /**
     * Método que define o ID da missão
     *
     * @param missionID ID da missão
     */
    public void setMissionID(String missionID);

    /**
     * Método que retorna a versão da missão
     *
     * @return a versão
     */
    public int getVersion();

    /**
     * Método que define a versão da missão
     *
     * @param version versão da missão
     */
    public void setVersion(int version);

    /**
     * Método que retorna o agente da missão
     *
     * @return o agente
     */
    public IAgent getAgent();

    /**
     * Método que define o agente da missão
     *
     * @param agent o agente
     */
    public void setAgent(IAgent agent);

    /**
     * Método que retorna o mapa da missão
     *
     * @return o mapa
     */
    public IMap getMap();

    /**
     * Método que define o mapa da missão
     *
     * @param map mapa
     */
    public void setMap(IMap map);

    /**
     * Método que retorna o alvo
     *
     * @return o alvo
     */
    public ITarget getTarget();

    /**
     * Método que define o alvo
     *
     * @param target alvo
     */
    public void setTarget(ITarget target);
}
