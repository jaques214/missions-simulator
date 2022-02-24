package pt.ipp.estg.interfaces;

import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;
import structures.ArrayOrderedList;
import java.io.FileNotFoundException;

public interface IMissions {

    /**
     * Método que retorna a lista de missões
     *
     * @return a lista ordenada das missões
     */
    public ArrayOrderedList<IMission> getMissions();

    /**
     * Método que adiciona missões à lista
     *
     * @param m nova missão
     * @throws NonComparableElementException se o objeto não for comparável
     */
    public void addMission(IMission m) throws NonComparableElementException;

    /**
     * Método que lê o ficheiro.json com as informações da missão
     *
     * @param path caminho do ficheiro
     * @throws FileNotFoundException
     * @throws EmptyCollectionException
     * @throws NonComparableElementException
     */
    public void readJson(String path) throws FileNotFoundException, EmptyCollectionException, NonComparableElementException;

    /**
     * Método para visualizar as missões
     *
     * @return a lista escrita das missões
     */
    public String viewMissions();
}
