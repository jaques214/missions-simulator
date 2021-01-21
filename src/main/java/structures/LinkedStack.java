/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;
import exceptions.NullException;
/**
 *
 * @author Jaques
 */
public class LinkedStack<T> {
    int count;
    LinkedNode<T> top;

    public LinkedStack() {
        this.count = 0;
        this.top = null;
    }
    
    public void push(T element) {
        //1º Definir LinkedNode
        LinkedNode<T> newNode = new LinkedNode<T>(element);
        newNode.setElement(element);
        if(this.top == null) {
            this.top = newNode;
        }
        else {
            newNode.setNext(top);
            top = newNode;   
        }
        this.count++;
    }
    
    public void pop() throws NullException {
        if(this.count == 0) throw new NullException("Não existem elementos na lista ligada");
        LinkedNode<T> temp = this.top;
        LinkedNode<T> prev = null;

        if (temp != null) {
            top = temp.getNext();
        }

        prev = temp;
        temp = temp.getNext();
        
        if (prev == null) {
            this.top = temp.getNext();
        }
        
        this.count--;
    }
    
    public T peek() throws NullException {
        if (isEmpty()) throw new NullException("Stack");
        LinkedNode<T> temp = this.top;
        return temp.getElement();
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        LinkedNode<T> current = top;
        String s = "LinkedList:\n";
        while(current != null) {
            s += current.getElement().toString() + "\n";
            current = current.getNext();
        }
        return s;
    }  
    
}
