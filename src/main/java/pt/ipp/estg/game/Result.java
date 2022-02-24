package pt.ipp.estg.game;

import pt.ipp.estg.interfaces.IResult;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de armazena os resultados</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Result implements IResult, Comparable<Result> {
    private int points;
    private boolean missionState;
    private SimulationType simulationType;
    private String pathToTarget;
    private String pathToExit;
    private int stepCount;

    /**
     * Método construtor da classe Result
     *
     * @param points pontos
     * @param pathToTarget caminho para o target
     * @param pathToExit caminho para a saída
     * @param stepCount contagem de passos
     * @param simulationType tipo de simulação
     */
    public Result(int points, String pathToTarget, String pathToExit, int stepCount, SimulationType simulationType) {
        this.points = points;
        this.missionState = false;
        this.pathToTarget = pathToTarget;
        this.pathToExit = pathToExit;
        this.simulationType = simulationType;
        this.stepCount = stepCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimulationType getSimulationType() {
        return this.simulationType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStepCount() {
        return stepCount;
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
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getMissionState() {
        return missionState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stateToString() {
        String s = "";
        if(getMissionState()) {
            s += "Mission Success";
        }
        else {
            s += "Mission Failed";
        }
        return s;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMissionState(boolean missionState) {
        this.missionState = missionState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPathToTarget() {
        return this.pathToTarget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathToTarget(String pathToTarget) {
        this.pathToTarget = pathToTarget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPathToExit() {
        return this.pathToExit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathToExit(String pathToExit) {
        this.pathToExit = pathToExit;
    }

    /**
     * Método toString
     *
     * @return uma representação textual do resultado da missão
     */
    @Override
    public String toString() {
        return "Result{" +
                "points=" + points +
                ", state='" + missionState + '\'' +
                ", pathToTarget='" + pathToTarget + '\'' +
                ", pathToExit='" + pathToExit + '\'' +
                ", steps='" + stepCount + '\'' +
                '}';
    }

    /**
     * Método compareTo
     *
     * @param o resultado a comparar
     * @return Zero se forem iguais, 1 e -1 se forem diferentes
     */
    @Override
    public int compareTo(Result o) {
        if(this.getPoints() == o.getPoints()) {
            return 0;
        }
        else if (this.getPoints() > o.getPoints()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
