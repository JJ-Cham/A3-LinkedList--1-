/**
 * Class to implement a singly linked list
 *
 * @author 
 * @version Spring 2024
 */

public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T> {

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
            throw new MissingElementException(null);
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
            throw new MissingElementException(null);
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
            throw new MissingElementException(null);
        } //return; // later: maybe exception
        NodeSL<T> newNode = new NodeSL<>(v, here.getNext());
        here.setNext(newNode);
    }

    // Remove node after a given node
    @Override
    public T removeAfter(NodeSL<T> here) {
        if (here == null || here.getNext() == null) {
            //return null; // later: throw exception
            throw new MissingElementException(null);
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

    //deep copy constructor
    //making a new list and copying over the elements from other list
    public SLL(SLL<T> other) {
        


    }
}


