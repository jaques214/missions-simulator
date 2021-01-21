/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author Jaques
 */
public class LinkedListSortingAndSearching {
//
//    //------------------------Algoritmos de pesquisa----------------------------
//    /**
//    * Searches a specified element on a single linked list
//    * using a linear search algorithm.
//    *
//    * @param <T>
//    * @param head beginning of single linked list
//    * @param target the element being searched for
//    * @return true if the desired element is found
//    */
//    public static <T extends Comparable<? super T>> boolean
//        linearSearch (LinkedNode head, T target) {
//          LinkedNode<T> current = head;
//          boolean found = false;
//          while (current != null && !found) {
//            if (target.equals(current.getElement()))
//                found = true;
//            else {
//                current = current.getNext();
//            }
//        }
//        return found;
//    }
//
//    /*public static <T extends Comparable<? super T>> boolean
//        RecursivelinearSearch (LinkedNode head, T target) {
//            if(head != null) {
//                return false;
//            }
//            if (target.compareTo(head.getElement()) == 0) {
//                return true;
//            }
//        return RecursivelinearSearch (head.getNext(), target);
//    }*/
//
//    /**
//     * Function to find middle element
//     * @param head beginning of single linked list
//     * @param tail ending of single linked list
//     * @return
//     */
//    public static LinkedNode getMiddleNode(LinkedNode head, LinkedNode tail) {
//        if (head == null) {
//            return null;
//        }
//
//        LinkedNode slow = head;
//        LinkedNode fast = head.getNext();
//
//        while (fast != tail) {
//            fast = fast.getNext();
//            if (fast != tail) {
//                slow = slow.getNext();
//                fast = fast.getNext();
//            }
//        }
//        return slow;
//    }
//
//    /**
//     * function to insert a node at the beginning of the Singly Linked List
//     * @param head
//     * @param value
//     * @return
//     */
//    public static LinkedNode binarySearch(LinkedNode head, int value) {
//        LinkedNode start = head;
//        LinkedNode last = null;
//
//        do {
//            // Find Middle
//            LinkedNode mid = getMiddleNode(start, last);
//
//            // If middle is empty
//            if (mid == null)
//                return null;
//
//            // If value is present at middle
//            if (mid.getElement() == value)
//                return mid;
//
//            // If value is less than mid
//            else if (mid.getElement() > value)
//            {
//                start = mid.getNext();
//            }
//
//            // If the value is more than mid.
//            else
//                last = mid;
//        } while (last == null || last != start);
//
//        // value not present
//        return null;
//    }
//
//   //------------------------Algoritmos de ordenação----------------------------
//
//   /**
//    * Sorts the specified element on a single linked list using the selection
//    * sort algorithm.
//    *
//    * @param data element to be sorted
//    */
//    public static <T extends Comparable<? super T>> void
//        selectionSort (T data) {
//
//    }
//
//    public static <T extends Comparable<? super T>> void
//        insertionSort (T data) {
//
//   }
//
//    /**
//    * Sorts the specified array of objects using a bubble sort
//    * algorithm.
//    *
//    * @param data the array to be sorted
//    */
//    public static <T extends Comparable<? super T>> void bubbleSort (T[] data) {
//        int position, scan;
//        T temp;
//        for (position = data.length - 1; position >= 0; position--) {
//            for (scan = 0; scan <= position - 1; scan++) {
//                if (data[scan].compareTo(data[scan+1]) > 0) {
//                    /** Swap the values */
//                    temp = data[scan];
//                    data[scan] = data[scan + 1];
//                    data[scan + 1] = temp;
//                }
//            }
//        }
//    }
//
//    /**
//    * Sorts the specified array of objects using the quick sort
//    * algorithm.
//    * Recursive algorithm
//    *
//    * @param data the array to be sorted
//    * @param min the integer representation of the minimum value
//    * @param max the integer representation of the maximum value
//        */
////    public static <T extends Comparable<? super T>> void
////        quickSort (T[] data, int min, int max) {
////        int indexofpartition;
////
////        if (max - min > 0) {
////            /** Create partitions */
////            indexofpartition = findPartition(data, min, max);
////
////            /** Sort the left side */
////            quickSort(data, min, indexofpartition - 1);
////
////            /** Sort the right side */
////            quickSort(data, indexofpartition + 1, max);
////        }
////    }
//
//    public static <T extends Comparable<? super T>> void
//        quickSort(LinkedNode start, LinkedNode end) {
//        if(start == end )
//            return;
//
//    // split list and partion recurse
//    LinkedNode pivot_prev = paritionLast(start, end);
//    sort(start, pivot_prev);
//
//    // if pivot is picked and moved to the start,
//    // that means start and pivot is same
//    // so pick from next of pivot
//    if( pivot_prev != null &&
//        pivot_prev == start )
//        sort(pivot_prev.next, end);
//
//    // if pivot is in between of the list,
//    // start from next of pivot,
//    // since we have pivot_prev, so we move two nodes
//    else if(pivot_prev != null &&
//            pivot_prev.next != null)
//        sort(pivot_prev.next.next, end);
//}
//
//    /**
//    * Used by the quick sort algorithm to find the partition.
//    *
//    * @param data the array to be sorted
//    * @param min the integer representation of the minimum value
//    * @param max the integer representation of the maximum value
//    */
//    private static <T extends Comparable<? super T>> int
//        findPartition (T[] data, int min, int max) {
//
//        int left, right;
//        T temp, partitionelement;
//        int middle = (min + max)/2;
//
//        // use middle element as partition
//        partitionelement = data[middle];
//        left = min;
//        right = max;
//
//        while (left < right) {
//            /** search for an element that is > the partitionelement */
//            while (data[left].compareTo(partitionelement) < 0) {
//                left++;
//            }
//
//            /** search for an element that is < the partitionelement */
//            while (data[right].compareTo(partitionelement) > 0)
//                right--;
//
//            /** swap the elements */
//            if (left < right) {
//                temp = data[left];
//                data[left] = data[right];
//                data[right] = temp;
//            }
//        }
//
//        /** move partition element to partition index*/
//        temp = data[min];
//        data[min] = data[right];
//        data[right] = temp;
//
//        return right;
//    }
//
//    /**
//    * Sorts the specified array of objects using the merge sort
//    * algorithm.
//    * Recursive algorithm
//    *
//    * @param data the array to be sorted
//    * @param min the integer representation of the minimum value
//    * @param max the integer representation of the maximum value
//    */
//
//
//    node sortedMerge(node a, node b)
//    {
//        node result = null;
//        /* Base cases */
//        if (a == null)
//            return b;
//        if (b == null)
//            return a;
//
//        /* Pick either a or b, and recur */
//        if (a.val <= b.val) {
//            result = a;
//            result.next = sortedMerge(a.next, b);
//        }
//        else {
//            result = b;
//            result.next = sortedMerge(a, b.next);
//        }
//        return result;
//    }
//
//    public static <T extends Comparable<? super T>> LinkedNode mergeSort(LinkedNode h)
//    {
//        // Base case : if head is null
//        if (h == null || h.getNext() == null) {
//            return h;
//        }
//
//        // get the middle of the list
//        LinkedNode middle = getMiddle(h);
//        node nextofmiddle = middle.next;
//
//        // set the next of middle node to null
//        middle.next = null;
//
//        // Apply mergeSort on left list
//        node left = mergeSort(h);
//
//        // Apply mergeSort on right list
//        node right = mergeSort(nextofmiddle);
//
//        // Merge the left and right lists
//        node sortedlist = sortedMerge(left, right);
//        return sortedlist;
//    }
}
