package pt.ipp.estg.game;

import pt.ipp.estg.interfaces.ITarget;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe de armazena as informações do alvo do agente</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class Target implements ITarget {
    private String division;
    private String type;

    /**
     * Método Construtor para a Classe Target
     * @param division nome de uma divisão onde se encontra o alvo
     * @param type tipo de alvo
     */
    public Target(String division, String type) {
        this.division = division;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDivision() {
        return division;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return division.equals(target.division) && type.equals(target.type);
    }

    /**
     *
     * @return retorna uma representação da classe em formato textual
     */
    @Override
    public String toString() {
        return "\n\t\tdivision: " + division +
                "\n\t\ttype: " + type;
    }
}
