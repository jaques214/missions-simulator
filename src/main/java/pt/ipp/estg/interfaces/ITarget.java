package pt.ipp.estg.interfaces;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Interface da classe Target</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public interface ITarget {

    /**
     * Getter para a variável division
     *
     * @return retorna o nome de uma divisão onde se encontra o alvo
     */
    String getDivision();

    /**
     * Setter para a variável division
     *
     * @param division nome de uma divisão onde se encontra o alvo
     */
    void setDivision(String division);

    /**
     * Getter para a variável type
     *
     * @return retorna o tipo de alvo
     */
    String getType();

    /**
     * Setter para a variável type
     *
     * @param type tipo de alvo
     */
    void setType(String type);

}
