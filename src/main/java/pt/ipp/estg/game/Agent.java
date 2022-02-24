package pt.ipp.estg.game;

import exceptions.EmptyCollectionException;
import pt.ipp.estg.interfaces.IAgent;
import pt.ipp.estg.interfaces.IRoom;
import structures.ArrayUnorderedList;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe que armazena as informações do agente</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */

public class Agent implements IAgent {
    private int points;
    private IRoom room;
    private static final String name = "Tó Cruz";
    private boolean acquiredTarget;
    private int enemiesDefeated;
    private final ArrayUnorderedList<IRoom> path;

    /**
     * Método construtor da classe Agent
     */
    public Agent() {
        this.points = 100;
        this.room = null;
        this.acquiredTarget = false;
        this.enemiesDefeated = 0;
        this.path = new ArrayUnorderedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRoom getRoom() {
        try {
            return this.path.last();
        } catch (EmptyCollectionException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStepCount() {
        return this.path.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayUnorderedList<IRoom> getPath() {
        return path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRoom(IRoom room) {
        this.room = room;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(IRoom room) {
        boolean visited = this.path.contains(room);
        this.path.addToRear(room);
        if(visited) return;

        if(room.getTarget() != null) {
            this.acquiredTarget = true;
        }
        if(!room.getEnemies().isEmpty()) {
            this.points -= room.getEnemiesPower();
            this.enemiesDefeated += room.getEnemies().size();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEnemiesDefeated() {
        return this.enemiesDefeated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAcquiredTarget() {
        return this.acquiredTarget;
    }

    /**
     * Método toString
     *
     * @return uma representação textual das informações do agente
     */
    @Override
    public String toString() {
        return "\n\t\tname: " + name +
                "\n\t\tpoints: " + this.points +
                "\n\t\troom: " + this.room;
    }
}
