// CS321 Project 2
// LLstack.java
// Ryan Seaman and Luhang Sun
// October 20, 2021

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
    
// This checks if the stack is empty.
public boolean isEmpty () {
 return top == null;
 }

// This returns the size of the stack.
public int size () {
 return size;
 }

    // This pushes (adds) a new node to the stack.
    public void push (T val) {
        Node node = new Node(val, top);
        top = node;
        size++;
    }


// This returns and removes the top node in the stack
public T pop () {
 T data = null;
 if (isEmpty()) 
 {
    System.out.println("Stack is empty!"); // Print statement if the stack is empty.
 }

    else {
        data = top.getData(); // Sets the data from the top node to data variable.
        Node tmp = top; // This sets a temporary pointer to the top node. 
        top = top.getNext(); // This updates the top pointer.
        size--; // this decrements the size by one.
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

 LLstack <String> string_stack = new LLstack<String>(); // creates a new LL. 

 System.out.println("If the stack is empty: " + string_stack.isEmpty()); // Tests to see if the linked list is empty. 

 string_stack.push("a"); // pushes 3 new nodes (strings)
 string_stack.push("3");
 string_stack.push(")");
 string_stack.push("2 + 3 + 5");
 string_stack.push("(23 + (5 * 10)");


 System.out.println("Size of the stack: " + string_stack.size()); // Checks the size of the stack
//  System.out.println("Top element: " + string_stack.peek());

//  System.out.println("*** Testing pop ***");
 while (!string_stack.isEmpty()){
 System.out.println("Now popping: "+ string_stack.pop()); // pops everything in the stack
 }

 System.out.println("After popping, size of the stack: " + string_stack.size()); // checks to see that everything was removed. 

//  keep popping after the stack is empty
string_stack.pop(); // tries to pop after stack is already empty. Should have a print statement saying stack is empty. 
 }
}