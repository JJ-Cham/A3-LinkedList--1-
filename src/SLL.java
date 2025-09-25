/**
 * Class to implement a singly linked list
 *
 * @author 
 * @version Spring 2024
 */

 public class SLL<T> implements Phase1SLL<T> {

    private NodeSL<T>head; // head of the list, points to the first node in the list
    private int size; // number of elements in the list

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
  
 }
