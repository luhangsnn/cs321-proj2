// CS321 Project 2
// LLstack.java
// Ryan Seaman and Luhang Sun
// October 20, 2021

public class LLstack <T> {

// a private Node class
    private class Node {
        private T data;
        private Node next;

        public Node (T val, Node n){
            this.data = val;
            this.next = n;
        }

        public void setData (T val) {
            this.data = val;
        }

        public T getData(){
            return this.data;
        }

        public Node getNext(){
            return this.next;
        }

        public void setNext(Node n) {
            this.next = n;
        }
    } // end of private Node class


    // field of LLstack class
    public Node top;
    public int size;

    // constructor of the LLstack
    public LLstack () {
        this.top = null;
        this.size = 0;
    }
    
    // check if the stack is empty
    public boolean isEmpty () {
        return this.top == null;
    }

    // size of the stack
    public int size () {
        return this.size;
    }

    // adds a new node to the stack
    public void push (T content) {
        Node node = new Node(content, this.top);
        this.top = node;
        size++;
    }


    // removes the top node and returns the content 
    public T pop() {
        T data = null;
        if (this.isEmpty()){
            System.out.println("Stack is empty!"); // Print statement if the stack is empty.
        }
        else {
            data = this.top.getData(); // store the data in the top node
            // Node tmp = this.top; // sets a temporary pointer to the top node. 
            this.top = this.top.getNext(); // This updates the top pointer.
            this.size--; // decrements the size by one.
        }
        return data;
    }

public static void main (String[] args) {

    LLstack <String> string_stack = new LLstack<String>(); // creates a new LL. 

    System.out.println("If the stack is empty: " + string_stack.isEmpty()); // Tests to see if the linked list is empty. 

    string_stack.push("a"); // pushes 3 new nodes (strings)
    string_stack.push("3");
    string_stack.push(")");
    string_stack.push("2 + 3 + 5");
    string_stack.push("(23 + (5 * 10)");


    System.out.println("Size of the stack: " + string_stack.size()); // Checks the size of the stack

    //  System.out.println("*** Testing pop ***");
    while (!string_stack.isEmpty()){
    System.out.println("Now popping: "+ string_stack.pop()); // pops everything in the stack
    }

    System.out.println("After popping, size of the stack: " + string_stack.size()); // checks to see that everything was removed. 

    //  keep popping after the stack is empty
    string_stack.pop(); // tries to pop after stack is already empty. Should have a print statement saying stack is empty. 
    }
}