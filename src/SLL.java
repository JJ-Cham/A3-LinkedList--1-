import java.lang.classfile.components.ClassPrinter.Node;

/**
 * Class to implement a singly linked list
 *
 * @author 
 * @version Spring 2024
 */

 public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T> {

    private NodeSL<T>head; // head of the list, points to the first node in the list
    private int size; // number of elements in the list

    //Phase 1 methods only
    //constructor 
    public SLL(){
        this.head = null;
        this.size=0;
    }

    @Override
    public NodeSL<T> getHead(){
        return head;
    }

    @Override
    public NodeSL<T> getTail(){
        if (head == null) return null; // empty list

        NodeSL<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
        //return null;
    }

    @Override
    public String toString(){
        //print the list in the form [C,B,A]
        if (head == null) return "[]"; // empty list

        StringBuilder sb = new StringBuilder("[");
        NodeSL<T> current = head; 

        while (current.getNext() != null){
            sb.append(current.getData()).append(",");
            current = current.getNext();
        }
        sb.append(current.getData()).append("]");

        return sb.toString();
        //return "[]"; //stub 
    }

    @Override 
    public boolean isEmpty(){
        return head == null; //stub
    }

    @Override
    public void addFirst(T data){
        //add new node to the front of the list
        NodeSL<T> newNode = new NodeSL<>(data, head);
        head = newNode;
        size++;
    }

    //Phase 2
    @Override
    public void addLast(T v) { 
        NodeSL<T> newNode = new NodeSL<>(v, null);
        if (head == null) {
            head = newNode;
        } 
        else {
            NodeSL<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    @Override
    public void addAfter(NodeSL<T> here, T v) { 
        if (here == null) return; // cannot add after a null node
        NodeSL<T> newNode = new NodeSL<>(v, here.getNext());
        here.setNext(newNode);
        //size++;?
    }

    @Override
    public T removeFirst() { 
        if (head == null) return null; // empty list

        T data = head.getData();
        head = head.getNext();
        return data;
    }

    @Override
    public T removeLast() { 
        if (head == null) return null;

        if(head.getNext() == null){
            T data = head.getData();
            head = null;
            return data;
        }
        NodeSL<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        T data = current.getNext().getData();
        current.setNext(null);
        return data;
    }

    @Override
    public T removeAfter(NodeSL<T> here) { 
        if (here == null || here.getNext() == null) return null; // cannot remove after a null node or if there is no next node

        NodeSL<T> temp = here.getNext();

        T data = temp.getData();
        here.setNext(temp.getNext());
        return data;
    }

    @Override
    public int size() { 
        int size = 0;
        NodeSL<T> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size; 
    }

  
 }
