package pt.ipp.estg.game;

import exceptions.EmptyCollectionException;
import exceptions.InvalidOperationException;
import exceptions.NonComparableElementException;
import exceptions.UnknownPathException;
import pt.ipp.estg.interfaces.IMenu;
import pt.ipp.estg.interfaces.IMission;
import pt.ipp.estg.interfaces.IMissions;
import pt.ipp.estg.interfaces.IRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de implementa o menu</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Menu implements IMenu {
    private int option, option2, option3;
    private Scanner input;
    private IMissions missions;

    /**
     * Método construtor da classe Menu
     */
    public Menu() {
        this.option = 0;
        this.option2 = 0;
        this.option3 = 0;
        this.input = new Scanner(System.in);
        this.missions = new Missions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainMenu(){

        System.out.println("Welcome Tó Cruz. This is your mission should you choose to accept it");

        do {
            String in;
            while (true) {
                System.out.println("1 - Import mission");
                System.out.println("2 - Select mission");
                System.out.println("0 - Quit");
                System.out.print("Choose your option:\n");
                in = input.nextLine();
                try {
                    option = Integer.parseInt(in.trim());

                    switch (option) {
                        case 1:
                            importMap(missions);
                            break;
                        case 2:
                            if (missions.getMissions().size() != 0) {
                                while (true) {

                                    System.out.println(missions.viewMissions() + "\n");
                                    in = input.nextLine();

                                    try {
                                        option2 = Integer.parseInt(in.trim());

                                        if (option2 >= 0 && option2 < missions.getMissions().size()) {

                                            IMission mission = missions.getMissions().get(option2);
                                            Simulation sim = new Simulation(mission);
                                            gameMenu(sim, mission);
                                            break;
                                        } else {
                                            System.out.println("Wrong option");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Given value \"" + in.trim() + "\" is not an integer.");
                                    }
                                }
                            } else {
                                System.out.println("Please import missions");
                            }
                            break;
                        case 0:
                            System.out.println("Quiting program...");
                            break;
                        default:
                            System.out.println("Wrong option");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Given value \"" + in.trim() + "\" is not an integer.");
                }
            }
        } while (option != 0);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameMenu(Simulation sim, IMission mission) {
        Leaderboard board = new Leaderboard(mission);
        do {
            String in;
            while (true) {
                System.out.println("\n1 - Map Visualization");
                System.out.println("2 - Leaderboard");
                System.out.println("3 - Manual Simulation");
                System.out.println("4 - Automatic Simulation");
                System.out.println("5 - Export mission");
                System.out.println("0 - Back");
                System.out.print("Choose your option:\n");
                in = input.nextLine();

                try {

                    option3 = Integer.parseInt(in.trim());

                    switch (option3) {
                        case 1:
                            System.out.println("Rooms: \n");
                            for (IRoom r : mission.getMap().getRooms()) {
                                System.out.println(r.getRoom() + " -> ");
                                for (IRoom l : r.getLinks()) {
                                    System.out.println("\t- " + l.getRoom());
                                }
                            }


                            break;
                        case 2:
                            //Leaderboard board = new Leaderboard(mission);
                            String file = "leaderboard.txt";
                            try {
                                board.readScores(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println();
                            break;
                        case 3:
                            System.out.println("----Mission Start----\n");
                            try {
                                sim.setBestResult(sim.simulateManualMission());
                                System.out.println(sim.summary());
                                sim.getMission().addResult(sim.getBestResult());

                            } catch (UnknownPathException | EmptyCollectionException | NonComparableElementException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("\n----Mission End----\n");

                            try {
                                board.writeScores("leaderboard");
                            } catch (IOException | InvalidOperationException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            System.out.println("----Mission Start----\n");
                            try {
                                sim.setBestResult(sim.simulateAutomaticMission());
                                System.out.println(sim.summary());
                            } catch (UnknownPathException | EmptyCollectionException | NullPointerException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("\n----Mission End----\n");
                            break;
                        case 5:
                            try {
                                mission.jsonExport(mission);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 0:
                            System.out.println("Returning to previous menu");
                            break;
                        default:
                            System.out.println("Wrong option");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Given value \"" + in.trim() + "\" is not an integer.");
                }
            }
        }
        while(option3 != 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importMap(IMissions missions) {
        String path;
        int o;
        do {
            String in;
            while (true) {
                System.out.println("Do you want to import a new map");
                System.out.println("1 - Yes");
                System.out.println("0 - No");
                in = input.nextLine();

                try {
                    o = Integer.parseInt(in.trim());

                    switch (o) {
                        case 1:
                            boolean exists;

                            do {
                                System.out.println("Insert path");
                                path = input.nextLine();

                                File f = new File(path);

                                if (f.isFile()) {
                                    missions.readJson(path);
                                    exists = true;
                                } else {
                                    System.out.println("File doesn't exist");
                                    exists = false;
                                }

                            } while (!exists);
                            break;
                        case 0:
                            System.out.println("Returning to previous menu");
                            break;
                        default:
                            System.out.println("Wrong option");
                            break;
                    }
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Given value \"" + in.trim() + "\" is not an integer.");
                } catch (EmptyCollectionException | NonComparableElementException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } while (o != 0);
    }

}
