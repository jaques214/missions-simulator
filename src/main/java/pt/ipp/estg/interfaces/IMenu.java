package pt.ipp.estg.interfaces;

import pt.ipp.estg.game.Simulation;

public interface IMenu {

    /**
     * Método do menu principal
     */
    void mainMenu();

    /**
     * Método do menu de jogo
     *
     * @param sim simulador da missão
     * @param mission missão a jogar
     */
    void gameMenu(Simulation sim, IMission mission);

    /**
     * Método para escolher os mapas a importar
     *
     * @param missions lista de missões
     */
    void importMap(IMissions missions);

}
