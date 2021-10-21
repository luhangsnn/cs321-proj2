// CS321 Project 2
// Postfix.java
// Ryan Seaman and Luhang Sun
// October 20, 2021

import LLstack;
import java.io.*;
import java.util.*;


public class Postfix <T> {

    public LLstack <T> stack;
    public File input;
    public File output;

    // constructor
    public Postfix(){
        
    }

    // also needs to change to specified object name of returns 
    public void infixToPostfix(String filename){
        try{
            FileInputStream fis=new FileInputStream(filename);       
            Scanner sc =new Scanner(fis);
            FileWriter writer = new FileWriter("output.txt");

            // new empty stack
            LLstack <String> transferStack = new LLstack<String>();

            //while there is another line to read  
            while(sc.hasNextLine()){  
                String thisline = sc.nextLine();

                StringTokenizer st = new StringTokenizer(thisline);

                while (st.hasMoreTokens()) {
                    String token = st.nextToken();

                    if (token == ";"){
                        String thisOutput = transferStack.pop();

                        // ADD TO AN OUTPUT FILE
                        writer.write(thisOutput + "\n");

                        // validate that the stack is empty 
                        if (!transferStack.isEmpty()){
                            System.out.println("ERROR: stack is not empty at the end of expression");
                        }
                    }
                    else if (token == ")") {
                        String right = transferStack.pop();
                        String oper = transferStack.pop();
                        String left = transferStack.pop();
                        transferStack.push(left + " " + right + " " + oper + ";");
                    }
                    else  {
                        if (token != "(") transferStack.push(token);
                    }
                }
            }
        // close the scanner object
        sc.close();
        writer.close();
        }
        catch(IOException e)  {  
            e.printStackTrace();  
        }  
    }



}