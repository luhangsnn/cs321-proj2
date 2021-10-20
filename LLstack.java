

public class LLstack <T> {

// a private Node class
private class Node {
 private T data;
 private Node next;

//  constructor 
 public Node (T val, Node n) {
 data = val;
 next = n;
 }

 public void setData (T val) {
 data = val;
 }

 public T getData () {
 return data;
 }

 public Node getNext () {
 return next;
 }

 public void setNext (Node n) {
 next = n;
 }
 }
// end of private Node class


    // field of LLstack class ***** check if it's private or public 
    public Node top;
    public int size;

    // constructor of the LLstack
    public LLstack () {
        top = null;
        size = 0;
    }
    
// check if the stack is empty
public boolean isEmpty () {
 return top == null;
 }

// get the number of elements in the stack
public int size () {
 return size;
 }

    // push a node to the stack
    public void push (T val) {
        Node node = new Node(val, top);
        top = node;
        size++;
    }


// return the top element and remove it from the stack
public T pop () {
 T data = null;
 if (isEmpty()) 
 {
    System.out.println("Stack is empty!");
 }

    else {
        data = top.getData(); // get the data stored in the top element
        Node tmp = top; // set a tmp pointer points to the top element
        top = top.getNext(); // update the top pointer
        size--;
    }

    return data;
 }

// return the value of the top element
// public T peek () {
//  T data = null;
//  if (isEmpty()) throw new EmptyStackException();
//  else data = top.getData(); // get the data stored in the top element
//  return data;
//  }

public static void main (String[] args) {

 LLstack <String> string_stack = new LLstack<String>();

 System.out.println("If the stack is empty: " + string_stack.isEmpty());

 string_stack.push("a");
 string_stack.push("b");
 string_stack.push("ab123");

 System.out.println("Size of the stack: " + string_stack.size());
//  System.out.println("Top element: " + string_stack.peek());

//  System.out.println("*** Testing pop ***");
 while (!string_stack.isEmpty()){
 System.out.println("Now popping: "+ string_stack.pop());
 }

 System.out.println("After popping, size of the stack: " + string_stack.size());

//  keep popping after the stack is empty
string_stack.pop();
 }
}