package pt.ipp.estg.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;
import pt.ipp.estg.interfaces.*;
import structures.ArrayOrderedList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de armazena a lista de missões</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Missions implements IMissions {

    private ArrayOrderedList<IMission> missions;

    /**
     * Método construtor da classe Missions
     */
    public Missions() {
        this.missions = new ArrayOrderedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayOrderedList<IMission> getMissions() {
        return missions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMission(IMission m) throws NonComparableElementException {
        this.missions.add(m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readJson(String path) throws FileNotFoundException, EmptyCollectionException, NonComparableElementException {
        JsonElement element = JsonParser.parseReader(new JsonReader(new BufferedReader(new FileReader(path))));

        JsonObject jsonDoc = element.getAsJsonObject();

        IMap m = new Map();
        JsonArray building = jsonDoc.getAsJsonArray("edificio");
        JsonArray e = jsonDoc.getAsJsonArray("inimigos");
        JsonArray links = jsonDoc.getAsJsonArray("ligacoes");


        JsonObject targets = jsonDoc.getAsJsonObject("alvo");
        String divisaoAlvo = targets.get("divisao").getAsString();
        ITarget t = new Target(divisaoAlvo, targets.get("tipo").getAsString());
        m.buildGraphFromJSON(building, e, links, t);
        IAgent agent = new Agent();

        IMission mission = new Mission(jsonDoc.get("cod-missao").getAsString(), jsonDoc.get("versao").getAsInt(), agent, m, t);

        JsonArray in_outs = jsonDoc.getAsJsonArray("entradas-saidas");
        int numIO = in_outs.size();

        for (int i = 0; i < numIO; i++) {
            mission.getMap().addIO(in_outs.get(i).getAsString());
        }

        addMission(mission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String viewMissions() {
        int i = 0;
        String s = "Select your mission, agent Tó Cruz:";
        for (IMission mission: missions) {
            s += "\n" + i + " - MissionID: " + mission.getMissionID() + ", Version: " + mission.getVersion();
            i++;
        }
        return s;
    }

    /**
     * Método toString
     *
     * @return uma representação textual da lista de missões
     */
    @Override
    public String toString() {
        String s = "Missions\n";
        s += "*-------------------------------*\n";
        for (IMission mission: missions) {
            s += mission.toString();
        }
        s += "*-------------------------------*\n";
        return s;
    }
}
