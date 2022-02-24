package pt.ipp.estg.interfaces;

import com.google.gson.JsonArray;
import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;
import structures.ArrayOrderedList;
import structures.ArrayUnorderedList;
import structures.Network;

public interface IMap {

    /**
     * Método que adiciona as ligações ao mapa
     *
     * @param room divisão
     * @param link ligações da divisão
     */
    void addMapEdges(IRoom room, IRoom link);

    /**
     * Método que recebe o nome de uma divisão e retorna o seu index
     *
     * @param room divisão
     * @return o index na network correspondente há divisão
     */
    int getNetworkIndex(String room);

    /**
     * Método que retorna as entradas/saídas do edifício
     *
     * @return todas as entradas/saídas
     */
    ArrayUnorderedList<String> getIn_out();

    /**
     * Método que adiciona entradas/saídas ao edifício
     *
     * @param io entradas/saídas
     */
    void addIO(String io);

    /**
     * Método que retorna o mapa do edifício
     *
     * @return o mapa do edifício
     */
    Network<String> getMap();

    /**
     * Método que define o mapa do edifício
     *
     * @param map mapa do edifício
     */
    void setMap(Network<String> map);

    /**
     * Método que retorna as divisões do edifício
     *
     * @return as divisões
     */
    ArrayUnorderedList<IRoom> getRooms();

    /**
     * Método que adiciona novas divisões ao edifício
     *
     * @param room nova divisão
     * @throws NonComparableElementException se o objeto não for comparável
     */
    void addRoom(IRoom room) throws NonComparableElementException;

    /**
     * Método que constrói o mapa do edifício através de um ficheiro json
     *
     * @param rooms divisões
     * @param enemies inimigos
     * @param linksJson ligações
     * @param target alvo
     * @throws NonComparableElementException se os objetos não forem comparáveis
     */
    void buildGraphFromJSON(JsonArray rooms, JsonArray enemies, JsonArray linksJson, ITarget target) throws EmptyCollectionException, NonComparableElementException;

    /**
     * Método que adiciona os pesos das ligações do mapa
     *
     * @param room divisão
     * @param link ligações da divisão
     */
    void setMapWeights(IRoom room, IRoom link) throws EmptyCollectionException;

    /**
     * Método que recebe uma divisão e retorna os seus vizinhos
     *
     * @param division divisão para a qual queremos saber os vizinhos
     * @return os vizinhos da divisão
     * @throws EmptyCollectionException
     * @throws NonComparableElementException
     */
    ArrayOrderedList<String> getNeighbours(String division) throws EmptyCollectionException, NonComparableElementException;
}
