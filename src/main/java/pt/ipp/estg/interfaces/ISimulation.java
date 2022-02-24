package pt.ipp.estg.interfaces;

import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;
import exceptions.UnknownPathException;
import java.util.Iterator;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Interface da classe Simulation</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public interface ISimulation {
    /**
     * Método que executa a simulação automática de uma missão, com um iterator
     *
     * @return retorna o melhor resultado de uma simulação automática baseado num iterador
     * @throws UnknownPathException exceção herdada da estrutura selecionada
     * @throws EmptyCollectionException exceção herdada da estrutura selecionada
     */
    IResult simulateAutomaticMission() throws UnknownPathException, EmptyCollectionException;

    /**
     * Método que utiliza um iterador para percorrer todas as entradas possíveis do agente
     *
     * @param entrance a entrada que o agente escolhe para entrar no edifício
     * @param itr o iterador que percorre todas as entradas e escolhe o melhor trajeto
     * @param agent o objeto com as informações sobre o agente
     * @param map o objeto com as informações sobre o mapa do edifício
     * @return retorna o melhor trajeto do agente baseado num iterador
     */
    String simulate(String entrance, Iterator<String> itr, IAgent agent, IMap map);

    /**
     * Método que executa a simulação manual de uma missão, através do input do utilizador
     *
     * @return retorna o resultado de uma simulação manual, baseado nos inputs do utilizador
     * @throws UnknownPathException exceção herdada da estrutura selecionada
     * @throws EmptyCollectionException exceção herdada da estrutura selecionada
     * @throws NonComparableElementException exceção herdada da estrutura selecionada
     */
    IResult simulateManualMission() throws UnknownPathException, EmptyCollectionException, NonComparableElementException;

    /**
     * Método que valida os inputs do utilizador no menu
     *
     * @param size tamanho da lista de opções do utilizador
     * @return retorna o valor do input do utilizador
     */
    int getInput(int size);

    /**
     * Método que percorre cada sala e imprime uma lista de inimigos
     *
     * @param room um objeto com as informações sde uma divisão do edifício
     * @param power poder de uma inimigo associado a uma divisão
     */
    void printRooms(IRoom room, double power);

    /**
     * Método que limpa as alterações aos pesos do grafo e reverte para o seu estado inicial
     *
     * @param map um objeto com as informações do mapa
     * @throws EmptyCollectionException exceção herdada da estrutura selecionada
     */
    void resetSimulation(IMap map) throws EmptyCollectionException;

    /**
     * Getter para a variável bestResult
     *
     * @return retorna um objeto com as informações do resultado
     */
    IResult getBestResult();

    /**
     * Setter para a variável bestResult
     *
     * @param bestResult um objeto com as informações do resultado
     */
    void setBestResult(IResult bestResult);

    /**
     * Método que armazena o resultado de uma simulação (manual ou automática) em formato textual
     *
     * @return retorna uma representação textual do resultado da simulação
     */
    String summary();

    /**
     * Getter para o objeto mission
     *
     * @return retorna o objeto mission
     */
    IMission getMission();
}
