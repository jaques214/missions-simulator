package pt.ipp.estg.interfaces;

public interface IEnemy {

    /**
     * Método que define o nome de um inimigo
     *
     * @param name nome do inimigo
     */
    void setName(String name);

    /**
     * Método que retorna o nome de um inimigo
     *
     * @return o nome do inimigo
     */
    String getName();

    /**
     * Método que define o poder de um inimigo
     *
     * @param power poder do inimigo
     */
    void setPower(int power);

    /**
     * Método que retorna o poder do inimigo
     *
     * @return o poder do inimigo
     */
    int getPower();

    /**
     * Método que define a divisão onde o inimigo se encontra
     *
     * @param room divisão onde o inimigo se encontra
     */
    void setRoom(String room);

    /**
     * Método que retorna a divisão onde o inimigo se encontra
     *
     * @return a divisão onde o inimigo se encontra
     */
    String getRoom();
}
