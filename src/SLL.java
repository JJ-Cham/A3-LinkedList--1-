/**
 * Class to implement a singly linked list
 *
 * @author 
 * @version Spring 2024
 */

public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T> {

    private NodeSL<T> head; // head of the list, points to the first node in the list
    private int size; // number of elements in the list

    // Phase 1 methods only
    public SLL() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public NodeSL<T> getHead() {
        return head;
    }

    @Override
    public NodeSL<T> getTail() {
        if (head == null) return null;
        NodeSL<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public String toString() {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        NodeSL<T> current = head;
        while (current.getNext() != null) {
            sb.append(current.getData()).append(",");
            current = current.getNext();
        }
        sb.append(current.getData()).append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void addFirst(T data) {
        NodeSL<T> newNode = new NodeSL<>(data, head);
        head = newNode;
        size++;
    }

    // Phase 2
    @Override
    public void addLast(T v) {
        NodeSL<T> newNode = new NodeSL<>(v, null);
        if (head == null) {
            head = newNode;
        } else {
            NodeSL<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public void addAfter(NodeSL<T> here, T v) {
        if (here == null) return;
        NodeSL<T> newNode = new NodeSL<>(v, here.getNext());
        here.setNext(newNode);
        size++;
    }

    @Override
    public T removeFirst() {
        if (head == null) return null;
        T data = head.getData();
        head = head.getNext();
        size--;
        return data;
    }

    @Override
    public T removeLast() {
        if (head == null) return null;
        if (head.getNext() == null) {
            T data = head.getData();
            head = null;
            size--;
            return data;
        }
        NodeSL<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        T data = current.getNext().getData();
        current.setNext(null);
        size--;
        return data;
    }

    @Override
    public T removeAfter(NodeSL<T> here) {
        if (here == null || here.getNext() == null)
            throw new MissingElementException("No element to remove after this node.");
        NodeSL<T> temp = here.getNext();
        T data = temp.getData();
        here.setNext(temp.getNext());
        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }
}


