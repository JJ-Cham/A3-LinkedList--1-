/**
 * Class to implement a singly linked list
 *
 * @author 
 * @version Spring 2024
 */

public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase4SLL<T> {

    private NodeSL<T> head; // first node in the list

    // Constructor: empty list
    public SLL() {
        head = null;
    }

    // ---------------- Phase 1 ----------------
    // Is the list empty?
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // Get the first node (head)
    @Override
    public NodeSL<T> getHead() {
        return head;
    }

    // Get the last node (tail)
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
    @Override
    public void addFirst(T v) {
        NodeSL<T> newNode = new NodeSL<>(v, head);
        head = newNode;
    }

    // Return string representation of the list
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
    @Override
    public void addAfter(NodeSL<T> here, T v) {
        if (here == null){
            throw new MissingElementException("Not enough elements in list");
        } //return; // later: maybe exception
        NodeSL<T> newNode = new NodeSL<>(v, here.getNext());
        here.setNext(newNode);
    }

    // Remove node after a given node
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

    /**
     * Makes a copy of n elements starting from "here"
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

    /**
     * Places a copy of another list after "afterHere"
     */
    @Override
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere) {
        if (list == null || list.isEmpty()) return;
        if (afterHere == null) {
            throw new MissingElementException("Not enough elements in list");
        }
        if (this == list) {
            throw new SelfInsertException();
        }

        // Make a copy of list
        SLL<T> copy = new SLL<>();

        // Splice copy after afterHere
        NodeSL<T> after = afterHere.getNext();
        afterHere.setNext(copy.getHead());
        copy.getTail().setNext(after);
    }

    /**
     * Extracts a subsequence of nodes and transfers them into a new list
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

    /**
     * Splices another list into this one after afterHere, emptying the other list
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


