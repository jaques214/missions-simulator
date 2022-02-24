package pt.ipp.estg.game;

import exceptions.InvalidOperationException;
import pt.ipp.estg.interfaces.ILeaderboard;
import pt.ipp.estg.interfaces.IMission;
import pt.ipp.estg.interfaces.IResult;
import structures.ArrayUnorderedList;

import java.io.*;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de cria a leaderboard</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Leaderboard implements ILeaderboard {
    private IMission mission;

    /**
     * Método construtor para mostrar resultados de uma simulação manual
     *
     * @param mission missão
     */
    public Leaderboard(IMission mission) {
        this.mission = mission;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeScores(String path) throws IOException, InvalidOperationException {
        ArrayUnorderedList<IResult> failuresResults = new ArrayUnorderedList<>();

        PrintWriter out = new PrintWriter(new FileWriter(path + ".txt"));

        out.println("MissionID: " + this.mission.getMissionID() + ", Version: " + this.mission.getVersion() + "\n");
        for (IResult result : this.mission.getResults()) {
            if(result.getMissionState()) {
                printResult(out, result);
            }
            else {
                failuresResults.addToRear(result);
            }
        }

        for (IResult result : failuresResults) {
            printResult(out, result);
        }

        out.close();
    }

    /**
     * Método que estrutura a informação a imprimir para ficheiro
     *
     * @param out ficheiro .txt
     * @param result resultados
     */
    private void printResult(PrintWriter out, IResult result) {
        String s = "State: ";
        if (result.getMissionState()) {
            s += "Success";
        } else {
            s += "Failure";
        }
        s += ", Points: " + result.getPoints() + ", Steps: " + result.getStepCount();
        out.println(s);
        out.println("\tPath to Target: " + result.getPathToTarget());
        out.println("\tPath to Exit: " + result.getPathToExit() + "\n\n");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readScores(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null) {
            System.out.println(st);
        }
    }
}
