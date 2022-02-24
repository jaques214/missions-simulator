package pt.ipp.estg.interfaces;

import exceptions.InvalidOperationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ILeaderboard {

    /**
     * Método que insere os scores no ficheiro
     *
     * @param path ficheiro
     * @throws IOException
     * @throws InvalidOperationException
     */
    void writeScores(String path) throws IOException, InvalidOperationException;

    /**
     * Método que obtém os scores que foram guardados no ficheiro
     *
     * @param path nome do ficheiro
     * @throws FileNotFoundException
     * @throws IOException
     */
    void readScores(String path) throws FileNotFoundException, IOException;
}
