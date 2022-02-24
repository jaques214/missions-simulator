/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import exceptions.EmptyCollectionException;
import exceptions.UnknownPathException;

/**
 * NetworkADT defines the interface to a network.
 * @param <T>
 * 
 */
public interface NetworkADT<T> extends GraphADT<T> {
    /**
    * Inserts an edge between two vertices of this graph.
    *
    * @param vertex1 the first vertex
    * @param vertex2 the second vertex
    * @param weight the weight
    */
    public void addEdge (T vertex1, T vertex2, double weight) throws EmptyCollectionException;
    
    /**
    * Returns the weight of the shortest path in this network.
    *
    * @param vertex1 the first vertex
    * @param vertex2 the second vertex
    * @return the weight of the shortest path in this network
    */
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException;
}
