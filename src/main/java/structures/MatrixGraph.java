/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import exceptions.EmptyCollectionException;
import exceptions.NullException;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaques
 */
public class MatrixGraph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // values of vertices

    public MatrixGraph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    private void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        boolean[][] largerAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i][j + 1];
                }
            }
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    private boolean indexIsValid(int index) {
        return ((index < numVertices) && index >= 0);
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    private Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            try {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x.intValue()]);

                /**
                 * Find all vertices adjacent to x that have not been visited
                 * and queue them up
                 */
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[x.intValue()][i] && !visited[i]) {
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            } catch (EmptyCollectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    private Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;
        while (!traversalStack.isEmpty()) {
            try {
                x = traversalStack.peek();

                found = false;
                /**
                 * Find a vertex adjacent to x that has not been visited and
                 * push it on the stack
                 */
                for (int i = 0; (i < numVertices) && !found; i++) {
                    if (adjMatrix[x.intValue()][i] && !visited[i]) {
                        traversalStack.push(i);
                        resultList.addToRear(vertices[i]);
                        visited[i] = true;
                        found = true;
                    }
                }
                if (!found && !traversalStack.isEmpty()) {
                    traversalStack.pop();
                }
            } catch (NullException ex) {
                Logger.getLogger(MatrixGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList.iterator();

    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

       public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
//        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
//        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
//            return resultList.iterator();
//        }
//
//        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
//        while (it.hasNext()) {
//            resultList.addToRear(vertices[((Integer) it.next()).intValue()]);
//        }
//        return resultList.iterator();
//    }
//
//    private Iterator<T> iteratorShortestPathIndices(int startIndex, int targetIndex) {
//        int index = startIndex;
//        int[] pathLength = new int[numVertices];
//        int[] predecessor = new int[numVertices];
//        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
//        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
//
//        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
//                || (startIndex == targetIndex)) {
//            return resultList.iterator();
//        }
//
//        boolean[] visited = new boolean[numVertices];
//        for (int i = 0; i < numVertices; i++) {
//            visited[i] = false;
//        }
//
//        traversalQueue.enqueue(startIndex);
//        visited[startIndex] = true;
//        pathLength[startIndex] = 0;
//        predecessor[startIndex] = -1;
//
//        while (!traversalQueue.isEmpty() && (index != targetIndex)) {
//            index = (traversalQueue.dequeue());
//
//            /**
//             * Update the pathLength for each unvisited vertex adjacent to the
//             * vertex at the current index.
//             */
//            for (int i = 0; i < numVertices; i++) {
//                if (adjMatrix[index][i] && !visited[i]) {
//                    pathLength[i] = pathLength[index] + 1;
//                    predecessor[i] = index;
//                    traversalQueue.enqueue(i);
//                    visited[i] = true;
//                }
//            }
//        }
//        if (index != targetIndex) // no path must have been found
//        {
//            return resultList.iterator();
//        }
//
//        LinkedStack<Integer> stack = new LinkedStack<>();
//        index = targetIndex;
//        stack.push(index);
//        do {
//            index = predecessor[index];
//            stack.push(index);
//        } while (index != startIndex);
//
//        while (!stack.isEmpty()) {
//            resultList.addToRear(((Integer) stack.pop()));
//        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (this.numVertices == 0);
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> it = iteratorBFS(0);
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }
        return (count == numVertices);
    }

    @Override
    public int size() {
        return this.numVertices;
    }

    /*@Override
    public String toString() {
        T startVertex;
        String s = "Breadth-first traversal (Travessia em largura):";
        Iterator<T> itr = iteratorBFS(startVertex);
        s += " | ";
        while (itr.hasNext()) {
            s += itr.next() + " | ";
        }
        return s;
    }*/
}
