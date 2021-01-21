/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Jaques
 */
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message) {
        super("Erro " + message);
    }
}
