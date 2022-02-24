package pt.ipp.estg.game;

import exceptions.NonComparableElementException;
import pt.ipp.estg.interfaces.IEnemy;
import pt.ipp.estg.interfaces.ITarget;
import structures.ArrayUnorderedList;
import pt.ipp.estg.interfaces.IRoom;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe que que armazena informação relativas a cada divisão do edifício</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Room implements IRoom {
    private String room;
    private ArrayUnorderedList<IRoom> links;
    private ArrayUnorderedList<IEnemy> enemies;
    private ITarget target;
    private boolean buildingAccess;

    /**
     * Método Construtor para a Classe Room
     * @param room nome da divisão do edifício
     */
    public Room(String room) {
        this.enemies = new ArrayUnorderedList<>();
        this.room = room;
        this.links = new ArrayUnorderedList<>();
        this.target = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBuildingAccess() {
        return this.buildingAccess;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBuildingAccess(boolean buildingAccess) {
        this.buildingAccess = buildingAccess;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(ITarget target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLink(IRoom link) {
        this.links.addToRear(link);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEnemy(IEnemy enemy) throws NonComparableElementException {
        if(enemy == null) throw new NonComparableElementException("No enemies");
        this.enemies.addToRear(enemy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearEnemies() {
        this.enemies = new ArrayUnorderedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEnemiesPower() {
        int total = 0;
        for (IEnemy e: enemies) {
            total += e.getPower();
        }
        return total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayUnorderedList<IEnemy> getEnemies() {
        return enemies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRoom() {
        return this.room;
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
    public ArrayUnorderedList<IRoom> getLinks() {
        return this.links;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITarget getTarget() {
        return this.target;
    }

    /**
     *
     * @return retorna uma representação da classe em formato textual
     */
    @Override
    public String toString() {
        String s = "\n\t\t\tdivision: " + this.room + "\n\t\t\tlinks: ";
        for (IRoom link: this.links) {
            s += link.getRoom() + " ";
        }
        s += "\n\t\t\tenemies: ";
        for (IEnemy enemy: this.enemies) {
            s +=  enemy.toString();
        }
        if(this.enemies.isEmpty()) {
            s += "This room is clear no enemies sighted";
        }
        s += "\n\t\t\ttargets: ";
        if(this.target != null) {
            s += "\n\t\t\t\tType: " + this.target.getType();
        }
        else {
            s += "No targets";
        }
        return s;
    }
}
