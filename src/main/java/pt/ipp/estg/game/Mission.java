package pt.ipp.estg.game;

import com.google.gson.*;
import exceptions.NonComparableElementException;
import pt.ipp.estg.interfaces.*;
import structures.ArrayOrderedList;

import java.io.FileWriter;
import java.io.IOException;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de armazena as informações de uma missão</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Mission implements IMission, Comparable<Mission> {
    private String missionID;
    private int version;
    private IAgent agent;
    private IMap map;
    private ITarget target;
    private ArrayOrderedList<IResult> results;

    /**
     * Método construtor da classe Mission
     *
     * @param missionID id da missão
     * @param version versão da missão
     * @param agent agente
     * @param map mapa da missão
     * @param target alvo
     */
    public Mission(String missionID, int version, IAgent agent, IMap map, ITarget target) {
        this.missionID = missionID;
        this.version = version;
        this.agent = agent;
        this.map = map;
        this.target = target;
        this.results = new ArrayOrderedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResult(IResult result) throws NonComparableElementException {
        this.results.add(result);
    }

    /**
     * {@inheritDoc}
     */
    public String getMissionID() {
        return missionID;
    }

    /**
     * {@inheritDoc}
     */
    public void setMissionID(String missionID) {
        this.missionID = missionID;
    }

    /**
     * {@inheritDoc}
     */
    public int getVersion() {
        return version;
    }

    /**
     * {@inheritDoc}
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    public IAgent getAgent() {
        return agent;
    }

    /**
     * {@inheritDoc}
     */
    public void setAgent(IAgent agent) {
        this.agent = agent;
    }

    /**
     * {@inheritDoc}
     */
    public IMap getMap() {
        return map;
    }

    /**
     * {@inheritDoc}
     */
    public void setMap(IMap map) {
        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    public ITarget getTarget() {
        return target;
    }

    /**
     * {@inheritDoc}
     */
    public void setTarget(ITarget target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayOrderedList<IResult> getResults() {
        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResults(ArrayOrderedList<IResult> results) {
        this.results = results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void jsonExport(IMission mission) throws IOException {
        if(this.results.isEmpty()) {
            System.out.println("There are no simulations available to export. Automatic simulations are not valid!");
        }
        else {
            GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Mission.class, new CustomSerializer());
            Gson gson = builder.setPrettyPrinting().create();
            FileWriter writer = new FileWriter("files/mission_" + this.getMissionID().replaceAll(" ", "_") + ".json");
            writer.write(gson.toJson(mission));
            writer.close();
        }
    }

    /**
     * Método toString
     *
     * @return uma representação textual da missão
     */
    @Override
    public String toString() {
        return "\tmissionID: " + missionID + "\n" +
                "\tversion: " + version + "\n" +
                "\tagent: " + agent + "\n" +
                "\tmap:\n" + map + "\n" +
                "\ttarget: " + target + "\n";
    }

    /**
     * Método compareTo
     *
     * @param o missão a comparar
     * @return Zero se forem iguais, 1 e -1 se forem diferentes
     */
    @Override
    public int compareTo(Mission o) {
        int result;
        if(this.missionID.equals(o.missionID)) {
            if (this.version == o.version) {
                result = 0;
            }
            else if (this.version > o.version)  {
                result = 1;
            }
            else {
                result = -1;
            }
        }
        else if(this.missionID.compareTo(o.missionID) > 0) {
            result = this.missionID.compareTo(o.missionID);
        }
        else {
            result = this.missionID.compareTo(o.missionID);
        }
        return result;
    }
}
