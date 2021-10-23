// CS321 Project 2
// Postfix.java
// Ryan Seaman and Luhang Sun
// October 20, 2021

import LLstack;
import java.io.*;
import java.util.*;


public class Postfix {

    // public LLstack <String> stack; 
    
    // public Postfix(){
    //     this.stack = new LLstack<String>();
    // }

    // takes input infix file and output to a postfix file  
    public void infixToPostfix(String filename){
        try{
            FileInputStream fis=new FileInputStream(filename);       
            Scanner sc =new Scanner(fis);
            FileWriter writer = new FileWriter("postfixOutput.txt");

            // new empty stack - MIGHT WANT TO CHANGE IT TO THE CLASS ATTRIBUTE 
            LLstack <String> transferStack = new LLstack<String>();

            //while there is another line to read  
            while(sc.hasNextLine()){  
                String thisline = sc.nextLine();

                StringTokenizer st = new StringTokenizer(thisline);

                while (st.hasMoreTokens()) {
                    String token = st.nextToken();

                    if (token == ";"){
                        String thisOutput = transferStack.pop();

                        // write to output 
                        writer.write(thisOutput + "\n");

                        // validate that the stack is empty 
                        if (!transferStack.isEmpty()){
                            System.out.println("ERROR: stack is not empty at the end of expression");
                            return;
                        }
                    }
                    else if (token == ")") {
                        String right = transferStack.pop();
                        String oper = transferStack.pop();
                        String left = transferStack.pop();
                        transferStack.push(left + " " + right + " " + oper + " ;"); // ADDITION OF ; MIGHT BE WRONG HERE
                    }
                    else  {
                        if (token != "(") transferStack.push(token);
                    }
                }
            }
        // close the scanner and writer
        sc.close();
        writer.close();
        }
        catch(IOException e)  {  
            e.printStackTrace();  
        }  
    }

}