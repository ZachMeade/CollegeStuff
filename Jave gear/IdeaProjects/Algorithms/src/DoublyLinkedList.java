import sun.jvm.hotspot.debugger.windbg.DLL;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data:
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 *
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
        // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode)
        {
            data = theData;
            prev = prevNode;
            next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int size;

    public int getSize() {
        return size;
    }



    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: theta(1)
     *
     * Justification:
     *  As there is no looping or other function calls we can assume that the function will run in theta(1) time.
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: theta(N-1)
     *
     * Justification:
     *  If we say the list has N elements then in the worst case the function
     *  will run through the for loop in theta(N-1) time. This is because if the position
     *  we're looking for is the last element in the array the loop will run N-1 times.
     *  we know that the other lines in the function will execute in theta(1)
     *  Therefore we can say that the worst-case running time for hte function is (N-1)*theta(1)
     */
    public void insertBefore( int pos, T data )
    {
        DLLNode previous= null;
        DLLNode next =null;
        DLLNode current;
        if(!this.isEmpty() && 0< pos && pos < this.getSize()-1) {
            current = this.head;

            for (int i = 0; i < pos+1; i++) {

                if (i == pos - 1) {
                    previous = current;
                }
                else if(i == pos){
                    next = current;
                }
                current = current.next;

            }
            DLLNode newNode = new DLLNode(data, previous, next);
            previous.next = newNode;
            next.prev = newNode;
            this.size++;

        }
        else if(this.isEmpty()) {
            this.head = new DLLNode(data, null, null);
            this.tail = this.head;
            this.size++;
        }

        else if(pos<= 0){
            DLLNode oldHead = this.head;
            this.head = new DLLNode(data, null, oldHead);
            this.size++;
        }
        else if (this.getSize()<= pos){

            DLLNode oldTail = this.tail;
            DLLNode newNode = new DLLNode(data, oldTail, null);
            oldTail.next = newNode;
            this.tail = newNode;
            this.size++;
        }

    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: theta(N)
     *
     * Justification:
     *  If the list has N elements the for loop in the worst case will execute N times.
     *  This means that the function will have a worst case running time of theta(N)
     *
     */
    public T get(int pos)
    {
        DLLNode current = this.head;
        T data = null;
        if(!isEmpty()&& pos < this.getSize()&& pos>= 0) {
            for (int i = 0; i <= pos; i++) {
                if (i == pos) {
                    data = current.data;
                }
                if (current.next != null)
                    current = current.next;

            }
        }
        return data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     *
     * Worst-case asymptotic running time cost: theta(N-1)
     *
     * Justification:
     *  if the list has N elements the worst case running tim would be theta(N-1)
     *  as the for loop would in its worst case will run to the second last element
     *  times.
     */
    public boolean deleteAt(int pos)
    {
        DLLNode previous= null;
        DLLNode next =null;
        DLLNode current = this.head;
        if(pos>=0 && pos< this.getSize()) {
            if (pos == 0 && this.getSize()==1){
                this.head = null;
                this.tail = null;
            }
            else if(pos<=0){
                if(size != 2){
                    this.head = this.head.next;
                    this.head.next.prev = this.head;
                }
                else {
                    this.head = this.head.next;
                    this.tail = this.head;
                }


            }
            else if(pos>=this.getSize()-1){
                this.tail = this.tail.prev;
                this.tail.next = null;
            }
            else
            {
                for(int i = 0; i<= pos; i++){
                    if (i == pos - 1) {
                        previous = current;
                    }
                    else if(i == pos){
                        next = current.next;
                    }
                    current = current.next;

                }
                previous.next = next;
                next.prev = previous;
            }
            size--;
            return true;
        }

        return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: theta(N/2)
     *
     * Justification:
     *  If the list is N elements long in the worst case the while loop will run half N times.
     *  This is because every time the while loop runs 2 elements from the list will be swapped
     *  and because of this it will run in theta(N/2) time.
     */
    public void reverse() {
        //TODO
        if(isEmpty())
            return;
        DLLNode reverse = head;
        DLLNode tmp = null;
        while (reverse != null) {
            tmp = reverse.prev;
            reverse.prev = reverse.next;
            reverse.next = tmp;
            reverse = reverse.prev;
        }
        if(tmp!=null)
            head = tmp.prev;
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: theta(N^2-N)
     *
     * Justification:
     *  The first for loop in this method will always run in theta(N) time, assuming
     *  the list has N elements. As the second for loop always starts on 1 it will execute
     *  in theta(N-1) time. We also assume that all other method calls execute in theta(1) time
     *  Therefore the total worst case running time for the method is theta(N-1)*N. Which is the
     *  same as theta(N^2 - N).
     *  */
    public void makeUnique()
    {
        //TODO

        if(!isEmpty()&& this.getSize()> 1) {
            DLLNode compare = this.head;
            for (int i = 0; i < this.getSize()-1 && compare.next != null; i++) {
                DLLNode toCompare = compare.next;
                for (int j = i+1; j<this.getSize(); j++){
                    if(compare.data.equals(toCompare.data)){
                        DLLNode previous = toCompare.prev;
                        DLLNode next = toCompare.next;
                        if(next !=null) {
                            next.prev = previous;
                            previous.next = next;
                        }
                        else
                            previous.next = null;
                        this.size--;
                        j--;
                    }
                    toCompare= toCompare.next;
                }
                compare = compare.next;
            }
        }
    }

    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: theta(1)
     *
     * Justification:
     *  As there is no loops or method calls we assume that it will execute in theta(1) time
     */
    public void push(T item)
    {
        //TODO
        DLLNode newNode = new DLLNode(item, null, null);
        if(this.isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            DLLNode temp = this.head;
            this.head = newNode;
            newNode.next = temp;
            temp.prev = newNode;
        }
        size++;
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: theta(1)
     *
     * Justification:
     *  As there is no loops or method calls we assume that it will execute in theta(1) time
     */
    public T pop()
    {
        if(this.getSize()>1){
            DLLNode popNode = this.head;
            this.head = this.head.next;
            size--;
            return popNode.data;
        }
        else if(!isEmpty()){
            DLLNode popNode = this.head;
            this.head = null;
            this.tail = null;
            size--;
            return popNode.data;
        }

        return null;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: theta(1)
     *
     * Justification:
     *  As there is no loops or method calls we assume that it will execute in theta(1) time
     */
    public void enqueue(T item)
    {
        //TODO
        DLLNode newNode = new DLLNode(item, null, null);
        if(this.isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            DLLNode temp = this.head;
            this.head = newNode;
            newNode.next = temp;
            temp.prev = newNode;
        }
        size++;
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: theta(1)
     *
     * Justification:
     *  As there is no loops or method calls we assume that it will execute in theta(1) time
     */
    public T dequeue()
    {
        //TODO
        if(this.getSize()>1){
            DLLNode popNode = this.tail;
            this.tail = this.tail.prev;
            this.tail.next = null;
            size--;
            return popNode.data;


        }
        else if(!isEmpty()){
            DLLNode popNode = this.tail;
            this.tail = null;
            this.head = null;
            size--;
            return popNode.data;
        }

        return null;
    }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;

        // iterate over the list, starting from the head
        for (DLLNode iter = head; iter != null; iter = iter.next)
        {
            if (!isFirst)
            {
                s.append(",");
            } else {
                isFirst = false;
            }
            s.append(iter.data.toString());
        }

        return s.toString();
    }


}


