package pt.ipp.estg.game;

import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;
import exceptions.UnknownPathException;
import pt.ipp.estg.interfaces.*;
import structures.ArrayOrderedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de implementa as simulações de uma missão</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Simulation implements ISimulation {
    private IResult bestResult;
    private IMission mission;

    /**
     * Método Construtor para a Classe Simulation
     * @param mission objeto com a informação relativa a uma missão
     */
    public Simulation(IMission mission) {
        this.bestResult = null;
        this.mission = mission;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResult simulateAutomaticMission() throws UnknownPathException, EmptyCollectionException {
        IMap map = this.mission.getMap();
        String paths;
        String pathsToExit;
        String targetDivision = this.mission.getTarget().getDivision();

        for (String entrance : map.getIn_out()) {
            IAgent agent = new Agent();
            this.mission.setAgent(agent);

            Iterator<String> listToTarget = map.getMap().iteratorShortestWeight(entrance, targetDivision);
            Iterator<String> listToExit = map.getMap().iteratorShortestWeight(targetDivision, entrance);

            paths = simulate(entrance, listToTarget, agent, map);
            pathsToExit = simulate(targetDivision, listToExit, agent, map);

            this.resetSimulation(map);

            IResult result = new Result(agent.getPoints(), paths, pathsToExit, agent.getStepCount(), SimulationType.AUTOMATIC);
            if (agent.isAcquiredTarget() && agent.getPoints() == 100) {
                result.setMissionState(true);
            }
            if (this.bestResult == null) {
                this.bestResult = result;
            } else if ((bestResult.getPoints() <= agent.getPoints()) || (agent.isAcquiredTarget() && !bestResult.getMissionState())) {
                if (agent.isAcquiredTarget() && !bestResult.getMissionState()) {
                    this.bestResult = result;
                } else if (this.bestResult.getMissionState() && agent.isAcquiredTarget() && this.bestResult.getPoints() < agent.getPoints()) {
                    this.bestResult = result;
                } else if (agent.getStepCount() < this.bestResult.getStepCount() && agent.isAcquiredTarget() && agent.getPoints() == bestResult.getPoints()) {
                    this.bestResult = result;
                }
            }
        }
        if (this.mission.getAgent().isAcquiredTarget() && this.mission.getAgent().getPoints() < 100) {
            System.out.println("There are no scenarios where the mission is successful. The following is the best case scenario.");
        }
        return this.bestResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String simulate(String entrance, Iterator<String> itr, IAgent agent, IMap map) {
        StringBuilder path = new StringBuilder();
        while (itr.hasNext()) {
            String name = itr.next();
            if (name.equals(entrance)) {
                path.append(name);
            } else {
                path.append(" - ").append(name);
            }
            int index = map.getNetworkIndex(name);
            IRoom current_room = agent.getRoom();
            agent.move(map.getRooms().get(index));

            if (current_room != null) {
                map.getMap().setEdgeWeight(current_room.getRoom(), name, 0.0);
                map.getMap().setEdgeWeight(name, current_room.getRoom(), 0.0);
            }
        }
        return path.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResult simulateManualMission() throws UnknownPathException, EmptyCollectionException, NonComparableElementException {
        Scanner input = new Scanner(System.in);
        int i = 0;
        IMap map = this.mission.getMap();
        IAgent agent = new Agent();
        this.mission.setAgent(agent);

        System.out.println("Possible entrances");
        for (String entrances : map.getIn_out()) {
            System.out.println("Entrance " + i + ": " + entrances);
            int pos = map.getNetworkIndex(entrances);
            IRoom room = map.getRooms().get(pos);
            for (IEnemy enemy : room.getEnemies()) {
                System.out.println("\t - " + enemy.getName() + ", " + enemy.getPower() + " points");
            }
            i++;
        }
        System.out.println("Where should agent Tó Cruz enter?");
        int option = getInput(map.getIn_out().size());
        String entry = map.getIn_out().get(option);
        System.out.println("The agent choose to enter the building from " + entry);
        int index = map.getMap().getIndex(entry);
        agent.move(map.getRooms().get(index));
        boolean inProgress = true;
        String targetRoom = this.mission.getTarget().getDivision();
        String pathToTarget = entry;
        String pathToExit = targetRoom;
        do {
            System.out.println("What room should he choose next?");
            ArrayOrderedList<String> neighbours = map.getNeighbours(agent.getRoom().getRoom());
            int j = 0;
            for (String n : neighbours) {
                System.out.println(j + " - " + n);
                int pos2 = map.getNetworkIndex(n);
                IRoom room = map.getRooms().get(pos2);
                printRooms(room, map.getMap().getEdgeWeight(agent.getRoom().getRoom(), n));
                j++;
            }
            System.out.println("Agent current points: " + agent.getPoints());
            System.out.println("Enemies defeated: " + agent.getEnemiesDefeated());

            int agentRoom = getInput(neighbours.size());
            int index2 = map.getNetworkIndex(neighbours.get(agentRoom));
            IRoom nextRoom = map.getRooms().get(index2);
            String previousRoom = agent.getRoom().getRoom();
            agent.move(nextRoom);
            map.getMap().setEdgeWeight(previousRoom, nextRoom.getRoom(), 0.0);
            map.getMap().setEdgeWeight(nextRoom.getRoom(), previousRoom, 0.0);

            if (!agent.isAcquiredTarget()) {
                pathToTarget += " - " + nextRoom.getRoom();
            } else {
                if (nextRoom.getRoom().equals(targetRoom)) {
                    System.out.println("-----------\nTarget Acquired!\n-----------");
                } else {
                    pathToExit += " - " + nextRoom.getRoom();
                }
            }

            if (map.getIn_out().contains(agent.getRoom().getRoom())) {
                System.out.println("This room is an exit. Do you want to leave? ");
                System.out.println("0 - No");
                System.out.println("1 - Yes");
                int confirmation = input.nextInt();
                if (confirmation == 1) {
                    inProgress = false;
                } else if (confirmation != 0) {
                    System.out.println("Invalid option");
                }
            }
            if (this.mission.getAgent().getPoints() <= 0) {
                System.out.println("You Died");
                inProgress = false;
            }
        }
        while (inProgress);
        if (agent.isAcquiredTarget()) {
            pathToTarget += " - " + targetRoom;
        } else {
            pathToExit = pathToTarget;
            pathToTarget = "No Target Found";
        }
        resetSimulation(map);
        IResult result = new Result(agent.getPoints(), pathToTarget, pathToExit, agent.getStepCount(), SimulationType.MANUAL);
        if (agent.isAcquiredTarget() && result.getPoints() > 0) {
            result.setMissionState(true);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInput(int size) {
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        int userInput = 0;
        while (invalidInput) {
            try {
                userInput = input.nextInt();
                if (userInput < 0 || userInput >= size) {
                    System.out.println("Invalid option. Please choose again");
                } else {
                    invalidInput = false;
                }
            } catch (NoSuchElementException ex) {
                System.out.println("Not a valid type. Expecting a number");
                userInput = 0;
                input = new Scanner(System.in);
            }
        }
        return userInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printRooms(IRoom room, double power) {
        for (IEnemy enemy : room.getEnemies()) {
            System.out.println("\t - " + enemy.getName() + ", " + power + " points");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetSimulation(IMap map) throws EmptyCollectionException {
        for (IRoom room : map.getRooms()) {
            for (IRoom link : room.getLinks()) {
                map.setMapWeights(room, link);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResult getBestResult() {
        return this.bestResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBestResult(IResult bestResult) {
        this.bestResult = bestResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String summary() {
        String s = "";
        s += "MissionID: " + this.mission.getMissionID() + "\nVersion: " + this.mission.getVersion() + "\nInitial Points: 100";
        if (this.bestResult.getSimulationType() == SimulationType.AUTOMATIC) {
            s += "\nPossible entrances: ";
            for (String access : this.mission.getMap().getIn_out()) {
                s += "\n\t" + access;
            }
        }
        s += "\n\nThe agent choose the following path:\n";
        if (this.mission.getAgent().isAcquiredTarget()) {
            s += "To Target:\n\t" + this.bestResult.getPathToTarget() + "\n";
            s += "To Exit:\n\t" + this.bestResult.getPathToExit() + "\n";

            s += "\nTarget acquired in " + mission.getTarget().getDivision() + "!";
        } else {
            s += "\t" + this.bestResult.getPathToTarget();
            s += "\nNo Target found!";
        }
        s += "\nTotal enemies defeated: " + this.mission.getAgent().getEnemiesDefeated();

        s += "\nFinal Points: " + this.bestResult.getPoints();
        s += "\nMission ";
        if (this.bestResult.getMissionState()) {
            s += "Success";
        } else {
            s += "Failed";
        }
        return s;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IMission getMission() {
        return this.mission;
    }

    /**
     *
     * @return retorna uma representação da classe em formato textual
     */
    @Override
    public String toString() {
        return "Simulation{" +
                "bestResult=" + bestResult.toString() + "simulation= " + mission.toString() + "}";
    }
}
