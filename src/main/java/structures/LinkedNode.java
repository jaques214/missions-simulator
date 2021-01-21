/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 * Nó de uma lista ligada que contém a infomação do seu próximo, cria a sua 
 * referência e armazena a sua informação
 * @author Jaques
 * @param <T>
 */
public class LinkedNode<T> {
    private LinkedNode<T> next;
    private T element;
    
    public LinkedNode() {
    }

    public LinkedNode(T element) {
        this.element = element;
        this.next = null;
    }
    
    public LinkedNode<T> getNext() {
        return this.next;
    }
    
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "LinkedNode{" + "element=" + element + '}';
    }
}
