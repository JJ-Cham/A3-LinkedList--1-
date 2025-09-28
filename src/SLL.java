/**
 * Class to implement a singly linked list
 *
 * @author 
 * @version Spring 2024
 */
/**
 * A basic singly linked list implementation.
 *
 * @param <T> the type of elements stored in the list
 */
public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase4SLL<T> {

    private NodeSL<T> head; // first node in the list

    // Constructor: empty list
    /**
     * Constructs an empty singly linked list.
     */
    public SLL() {
        head = null;
    }

    // ---------------- Phase 1 ----------------
    // Is the list empty?
    /**
     * Determines whether the list is empty.
     *
     * @return true if the list has no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // Get the first node (head)
    /**
     * Returns the head node of the list.
     *
     * @return the first node, or null if the list is empty
     */
    @Override
    public NodeSL<T> getHead() {
        return head;
    }

    // Get the last node (tail)
    /**
     * Returns the tail node of the list.
     *
     * @return the last node, or null if the list is empty
     */
    @Override
    public NodeSL<T> getTail() {
        if (head == null) return null;
        NodeSL<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }

    // Add a new element at the front
    /**
     * Inserts a new element at the front of the list.
     *
     * @param v the element to insert
     */
    @Override
    public void addFirst(T v) {
        NodeSL<T> newNode = new NodeSL<>(v, head);
        head = newNode;
    }

    // Return string representation of the list
    /**
     * Returns a string representation of the list.
     *
     * @return a string containing the list elements in order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        NodeSL<T> current = head;

        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

    // ---------------- Phase 2 & 3 ----------------

    // Remove first node and return its value
    /**
     * Removes the first node and returns its value.
     *
     * @return the value of the removed node
     * @throws MissingElementException if the list is empty
     */
    @Override
    public T removeFirst() {
        if (head == null) {
            //return null; // later Phase 3: throw exception
            throw new MissingElementException("Not enough elements in list");
        }
        T value = head.getData();
        head = head.getNext();
        return value;
    }

    // Add new node at the end
    /**
     * Inserts a new element at the end of the list.
     *
     * @param v the element to insert
     */
    @Override
    public void addLast(T v) {
        NodeSL<T> newNode = new NodeSL<>(v, null);
        if (head == null) {
            head = newNode;
        } else {
            NodeSL<T> tail = getTail();
            tail.setNext(newNode);
        }
    }

    // Remove last node and return its value
    /**
     * Removes the last node and returns its value.
     *
     * @return the value of the removed node
     * @throws MissingElementException if the list is empty
     */
    @Override
    public T removeLast() {
        if (head == null) {
            //return null; // later: throw exception
            throw new MissingElementException("Not enough elements in list");
        }
        if (head.getNext() == null) { // only one node
            T value = head.getData();
            head = null;
            return value;
        }
        NodeSL<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        T value = current.getNext().getData();
        current.setNext(null);
        return value;
    }

    // Add new node after a given node
    /**
     * Inserts a new element after the given node.
     *
     * @param here the node to insert after
     * @param v the element to insert
     * @throws MissingElementException if the reference node is null
     */
    @Override
    public void addAfter(NodeSL<T> here, T v) {
        if (here == null){
            throw new MissingElementException("Not enough elements in list");
        } //return; // later: maybe exception
        NodeSL<T> newNode = new NodeSL<>(v, here.getNext());
        here.setNext(newNode);
    }

    // Remove node after a given node
    /**
     * Removes the node after the given node and returns its value.
     *
     * @param here the node before the one to remove
     * @return the value of the removed node
     * @throws MissingElementException if there is no node to remove
     */
    @Override
    public T removeAfter(NodeSL<T> here) {
        if (here == null || here.getNext() == null) {
            //return null; // later: throw exception
            throw new MissingElementException("Not enough elements in list");
        }
        NodeSL<T> toRemove = here.getNext();
        here.setNext(toRemove.getNext());
        return toRemove.getData();
    }

    // Count nodes
    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        int count = 0;
        NodeSL<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    // ---------------- Phase 4 ----------------

    //copy constructor
    /**
     * Constructs a copy of another list.
     *
     * @param other the list to copy
     */ 
    public SLL(SLL<T> other) {
        this.head = null;
        if (other != null && !other.isEmpty()) {
            NodeSL<T> current = other.getHead();
            while (current != null) {
                this.addLast(current.getData());
                current = current.getNext();
            }
        }
    }

    
      //Makes a copy of n elements starting from "here"
    /**
     * Creates a new list by copying n elements starting at the given node.
     *
     * @param here the starting node
     * @param n the number of elements to copy
     * @return a new list containing the copied elements
     * @throws MissingElementException if not enough elements are available
     */
    @Override
    public SLL<T> subseqByCopy(NodeSL<T> here, int n) {
        if (here == null || n < 0) {
            throw new MissingElementException("Not enough elements in list");
        }

        SLL<T> result = new SLL<>();
        NodeSL<T> current = here;

        for (int i = 0; i < n; i++) {
            if (current == null) {
                throw new MissingElementException("Not enough elements in list"); // not enough elements
            }
            result.addLast(current.getData());
            current = current.getNext();
        }

        return result;
    }

    
     //Places a copy of another list after "afterHere"
    /**
     * Inserts a copy of another list into this one after the given node.
     *
     * @param list the list to copy and insert
     * @param afterHere the node after which to insert
     * @throws MissingElementException if afterHere is null
     * @throws SelfInsertException if the list tries to splice itself
     */
    @Override
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere) {
        if (list == null || list.isEmpty()) return; //nothing to splice 
        if (afterHere == null) {
            throw new MissingElementException("AfterHere is null");
        }
        if (this == list) {
            throw new SelfInsertException();   
        }

        // Make a copy of list
        SLL<T> copy = new SLL<>(list);

        //if (copy.isEmpty()) return; // nothing to splice

        // Splice copy after afterHere
        NodeSL<T> after = afterHere.getNext();
        afterHere.setNext(copy.getHead());
        copy.getTail().setNext(after);
    }

    
    //Extracts a subsequence of nodes and transfers them into a new list
    /**
     * Extracts a subsequence of nodes between two given nodes and
     * returns them as a new list.
     *
     * @param afterHere the node before the subsequence
     * @param toHere the last node of the subsequence
     * @return a new list containing the extracted nodes
     * @throws MissingElementException if the subsequence cannot be found
     */
    @Override
    public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere) {
        if (afterHere == null || afterHere.getNext() == null) {
            throw new MissingElementException("Not enough elements in list");
        }

        SLL<T> result = new SLL<>();

        NodeSL<T> start = afterHere.getNext(); // first node being moved
        NodeSL<T> end = start;

        // Walk until we reach toHere
        while (end != null && end != toHere) {
            end = end.getNext();
        }

        if (end == null) {
            throw new MissingElementException("Not enough elements in list"); // toHere not found
        }

        // Detach [start ... toHere] from this list
        afterHere.setNext(toHere.getNext());

        // Hook into result list
        result.head = start;
        toHere.setNext(null);

        return result;
    }

    
    //Splices another list into this one after afterHere, emptying the other list
    /**
     * Splices another list into this one after the given node.
     * The other list becomes empty after the operation.
     *
     * @param list the list to splice into this one
     * @param afterHere the node after which to splice
     * @throws MissingElementException if afterHere is null
     * @throws SelfInsertException if the list tries to splice itself
     */
    @Override
    public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {
        if (list == null || list.isEmpty()) return;
        if (afterHere == null) {
            throw new MissingElementException("Not enough elements in list");
        }
        if (this == list) {
            throw new SelfInsertException();
        }

        NodeSL<T> after = afterHere.getNext();
        afterHere.setNext(list.getHead());
        list.getTail().setNext(after);

        // Empty out list
        list.head = null;
    }


}


