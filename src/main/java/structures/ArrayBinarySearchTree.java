/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 * @author Jaques
 */
public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T>
        implements BinarySearchTreeADT<T> {

    protected int height;
    protected int maxIndex;

    /**
     * Creates an empty binary search tree.
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Creates a binary search with the specified element as its root
     *
     * @param element the element that will become the root of the new tree
     */
    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    @Override
    public void addElement(T element) {
        if (tree.length < maxIndex * 2 + 3) {
            expandCapacity();
        }
        Comparable<T> tempelement = (Comparable<T>) element;

        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            while (!added) {
                if (tempelement.compareTo((tree[currentIndex])) < 0) {
                    /**
                     * go left
                     */
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;

                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    /**
                     * go right
                     */
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }

    private void expandCapacity() {
        int tam = tree.length + 1;
        T[] temp = (T[]) (new Object[tam]);
        for (int i = 0; i < count; i++) {
            temp[i] = tree[i];
        }
        tree = temp;
    }

    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        boolean found = false;

        if (isEmpty()) {
            return result;
        }

        for (int i = 0; (i <= maxIndex) && !found; i++) {
            if (tree[i] != null && targetElement.equals(tree[i])) {
                found = true;
                result = tree[i];
                try {
                    replace(i);
                } catch (EmptyCollectionException ex) {
                    Logger.getLogger(ArrayBinarySearchTree.class.getName()).log(Level.SEVERE, null, ex);
                }
                count--;
            }
        }

        if (!found) {
            throw new ElementNotFoundException("element not found in the binary tree");
        }

        int temp = maxIndex;
        maxIndex = -1;
        for (int i = 0; i <= temp; i++) {
            if (tree[i] != null) {
                maxIndex = i;
            }
        }

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;

        return result;
    }

    protected void replace(int targetIndex) throws EmptyCollectionException {
        int currentIndex, oldIndex, newIndex;
        ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<>();
        Iterator<Integer> oldIt, newIt;

        // if target node has no children
        if ((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length)) {
            tree[targetIndex] = null;
        } // if target node has no children
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null)) {
            tree[targetIndex] = null;
        } // if target node only has a left child
        else if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {

            // fill newlist with indices of nodes that will replace 
            // the corresponding indices in oldlist
            // fill newlist
            currentIndex = targetIndex * 2 + 1;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                currentIndex = (templist.removeFirst());
                newlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                currentIndex = (templist.removeFirst());
                oldlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // if target node only has a right child
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {

            // fill newlist with indices of nodes that will replace 
            // the corresponding indices in oldlist
            // fill newlist
            currentIndex = targetIndex * 2 + 2;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                oldlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // target node has two children
        else {
            currentIndex = targetIndex * 2 + 2;

            while (tree[currentIndex * 2 + 1] != null) {
                currentIndex = currentIndex * 2 + 1;
            }

            tree[targetIndex] = tree[currentIndex];

            // the index of the root of the subtree to be replaced
            int currentRoot = currentIndex;

            // if currentIndex has a right child
            if (tree[currentRoot * 2 + 2] != null) {

                // fill newlist with indices of nodes that will replace 
                // the corresponding indices in oldlist
                // fill newlist
                currentIndex = currentRoot * 2 + 2;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    newlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }

                // fill oldlist
                currentIndex = currentRoot;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    oldlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }

                // do replacement
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();
                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            } else {
                tree[currentRoot] = null;
            }
        }
    }

    @Override
    public void removeAllOccurrences(T targetElement) {
        try {
            while (contains(targetElement)) {
                removeElement(targetElement);
            }
        } catch (Exception ElementNotFoundException) {
        }
    }

    @Override
    public T removeMin() {
        T result = null;
        if (findMin() != null) {
            result = removeElement(findMin());
        }
        return result;
    }

    @Override
    public T removeMax() {
        T result = null;
        if (findMax() != null) {
            result = removeElement(findMax());
        }
        return result;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new ElementNotFoundException("árvore vazia");
        }

        int currentIndex = 0;
        while (tree[currentIndex * 2 + 1] != null) {
            currentIndex = currentIndex * 2 + 1;
        }

        return tree[currentIndex];
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new ElementNotFoundException("árvore vazia");
        }

        int currentIndex = 0;
        while (tree[currentIndex * 2 + 2] != null) {
            currentIndex = currentIndex * 2 + 2;
        }

        return tree[currentIndex];
    }

}
