/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import exceptions.EmptyCollectionException;

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
    * Adds the specified element to the front of the list
    *
    * @param element the element to be added to this list
    */
    public void addToFront(T element);
    
    /**
    * Adds the specified element to the rear of the list
    *
    * @param element the element to be added to this list
    */
    public void addToRear(T element);
    
    /**
    * Adds the specified element after a particular element already in the list
    *
    * @param element the element to be added to this list
     * @param target the target element already in the list
    */
    public void addAfter(T element, T target) throws EmptyCollectionException;
}
