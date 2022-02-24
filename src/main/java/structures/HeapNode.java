/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;
    
    /**
     * Creates a new heap node with the specified data
     * @param obj the data to be contained within the new heap nodes
     * 
     */
    public HeapNode(T obj) {
        super(obj);
        this.parent = null;
    }

    public HeapNode<T> getParent() {
        return parent;
    }

    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }    
}
