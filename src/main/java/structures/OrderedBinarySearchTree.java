/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;

import java.util.Iterator;
/**
 *
 * @author Jaques
 * @param <T>
 */
public class OrderedBinarySearchTree<T> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {

    public OrderedBinarySearchTree() {
        super();
    }

    public OrderedBinarySearchTree(T element) {
        super(element);
    }
    
    @Override
    public void add(T element) throws NonComparableElementException {
        addElement(element);
        iteratorInOrder();
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        T first = removeMin();
        return first;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        T last = removeMax();
        return last;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException {
        T target = removeElement(element);
        return target;
    }

    @Override
    public T first() throws EmptyCollectionException {
        T min = findMin();
        return min;
    }

    @Override
    public T last() throws EmptyCollectionException {
        T max = findMax();
        return max;
    }

    @Override
    public Iterator<T> iterator() {
        return iteratorInOrder();
    }    
}
