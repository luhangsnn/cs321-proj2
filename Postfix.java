// CS321 Project 2
// Postfix.java
// Ryan Seaman and Luhang Sun
// October 20, 2021

import LLstack;
import java.io.File;


public class Postfix <T> {

    public S LLstack;
    public File input;
    public File output;
    public Tok token;

    // constructor
    public Postfix(){

    }

    // output needs to be specified as file (maybe use StringTokenizer)
    public voids readFile(){

    }

    // also needs to change to specified object name of returns 
    public void infixToPostfix(Tok token){
        // read token from readFile line of code.

        // new empty stack
        LLstack <String> transferStack = new LLstack<String>();
        // new empty string for final postfix expression
        String tempString = new String("");

        while (token! = ";") {
            if (token == ")") {
                right = transferStack.pop();
                oper = transferStack.pop();
                left = transferStack.pop();
                push(left + right + oper);
            }
            else {
                if (token != "("){
                    transferStack.push(token);
                }
            // read next token
            }
        }
        // top of stack is a postfix expression (not sure if this is exaclty)
        // for (String i : transferStack){}
        for (int i = 1; i <= transferStack.size(); i = i++){
            tempString = tempString + transferStack[i].getData();
        }
        transferStack.push(tempString);
    }



}