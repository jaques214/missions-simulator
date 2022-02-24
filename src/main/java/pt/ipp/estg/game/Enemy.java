package pt.ipp.estg.game;

import pt.ipp.estg.interfaces.IEnemy;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de armazena as informações de um inimigo</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */

public class Enemy implements IEnemy {

    private String name;
    private int power;
    private String room;

    /**
     * Método construtor da classe Enemy
     *
     * @param name nome do inimigo
     * @param power poder do inimigo
     * @param room divisão onde o inimigo se encontra
     */
    public Enemy(String name, int power, String room) {
        this.name = name;
        this.power = power;
        this.room = room;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPower() {
        return power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRoom() {
        return room;
    }

    /**
     * Método toString
     *
     * @return uma representação textual das informações do inimigo
     */
    @Override
    public String toString() {
        return "Enemy{" +
                "name: " + name +
                ", power: " + power +
                '}';
    }
}
