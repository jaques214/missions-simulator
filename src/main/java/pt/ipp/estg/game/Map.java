package pt.ipp.estg.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;
import pt.ipp.estg.interfaces.IEnemy;
import pt.ipp.estg.interfaces.IMap;
import pt.ipp.estg.interfaces.IRoom;
import pt.ipp.estg.interfaces.ITarget;
import structures.ArrayOrderedList;
import structures.ArrayUnorderedList;
import structures.Network;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de guarda o mapa do edifício</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Map implements IMap {
    protected Network<String> map;
    private ArrayUnorderedList<IRoom> rooms;
    private ArrayUnorderedList<String> in_out;

    /**
     * Método construtor da classe Map
     */
    public Map() {
        this.rooms = new ArrayUnorderedList<>();
        this.map = new Network<>();
        this.in_out = new ArrayUnorderedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayUnorderedList<String> getIn_out() {
        return in_out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addIO(String io) {
        this.in_out.addToRear(io);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Network<String> getMap() {
        return map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMap(Network<String> map) {
        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayUnorderedList<IRoom> getRooms() {
        return rooms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRoom(IRoom room) throws NonComparableElementException {
        if(room == null) throw new NonComparableElementException("Empty building");
        this.rooms.addToRear(room);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildGraphFromJSON(JsonArray rooms, JsonArray enemies, JsonArray linksJson, ITarget target) throws NonComparableElementException {
        for (JsonElement rJson: rooms) {
            String nameRoom = rJson.getAsString();
            this.map.addVertex(nameRoom);
            IRoom r = new Room(nameRoom);
            if(nameRoom.equals(target.getDivision())) {
                r.setTarget(target);
            }
            for(JsonElement e : enemies) {
                JsonObject enemy = e.getAsJsonObject();
                String divisaoInimigo = enemy.get("divisao").getAsString();
                if(nameRoom.equals(divisaoInimigo)){
                    IEnemy enemyObject = new Enemy(enemy.get("nome").getAsString(), enemy.get("poder").getAsInt(),divisaoInimigo);
                    r.addEnemy(enemyObject);
                }
            }
            this.addRoom(r);
        }
        for (JsonElement linkJson : linksJson) {
            JsonArray links = linkJson.getAsJsonArray();
            String roomDivisionA = links.get(0).getAsString();
            String roomDivisionB = links.get(1).getAsString();
            int indexA = getNetworkIndex(roomDivisionA);
            IRoom room = this.rooms.get(indexA);
            int indexB = getNetworkIndex(roomDivisionB);
            IRoom link = this.rooms.get(indexB);
            room.addLink(link);
            link.addLink(room);
            addMapEdges(room, link);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMapEdges(IRoom room, IRoom link) {
        if(room.getEnemies().isEmpty()) {
            this.map.addEdge(link.getRoom(), room.getRoom(), 0.0);
        }
        else {
            this.map.addEdge(link.getRoom(), room.getRoom(), room.getEnemiesPower());
        }
        if(link.getEnemies().isEmpty()) {
            this.map.addEdge(room.getRoom(), link.getRoom(), 0.0);
        }
        else {
            this.map.addEdge(room.getRoom(), link.getRoom(), link.getEnemiesPower());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMapWeights(IRoom room, IRoom link) {
        if(room.getEnemies().isEmpty()) {
            this.map.setEdgeWeight(link.getRoom(), room.getRoom(), 0.0);
        }
        else {
            this.map.setEdgeWeight(link.getRoom(), room.getRoom(), room.getEnemiesPower());
        }
        if(link.getEnemies().isEmpty()) {
            this.map.setEdgeWeight(room.getRoom(), link.getRoom(), 0.0);
        }
        else {
            this.map.setEdgeWeight(room.getRoom(), link.getRoom(), link.getEnemiesPower());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNetworkIndex(String room) {
        return this.map.getIndex(room);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayOrderedList<String> getNeighbours(String division) throws EmptyCollectionException, NonComparableElementException {
        return this.map.getNeighbours(division);
    }

    /**
     * Método toString
     *
     * @return uma representação textual do edifício
     */
    @Override
    public String toString() {
        String s = "\t\trooms: ";
        s += this.map.toString();

        s += "\n\t\tarray_rooms:";
        for (IRoom room: rooms) {
            s += room.toString() + "\n";
        }
            s += "\n\t\tentrances/exits: \n";
            for (String io: this.in_out) {
                s += "\t\t\t- " + io + "\n";
            }
        return s;
    }
}
