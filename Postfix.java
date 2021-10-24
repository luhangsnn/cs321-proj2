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
    public void infixToPostfix(String inputFilename, String outFilename, boolean verbal){
        FileWriter writer;
        try{
            FileInputStream fis=new FileInputStream(inputFilename);       
            Scanner sc =new Scanner(fis);

            if (outFilename != null) writer = new FileWriter(outFilename); // initialize the filewriter if an output file name is given

            // new empty stack - MIGHT WANT TO CHANGE IT TO THE CLASS ATTRIBUTE 
            LLstack <String> transferStack = new LLstack<String>();

            //while there is another line to read  
            while(sc.hasNextLine()){  
                String thisline = sc.nextLine();

                if (outFilename != null) writer.write("Infix Expression: " + thisline + "\n"); // write to output 
                else System.out.println("Infix Expression: " + thisline);

                StringTokenizer st = new StringTokenizer(thisline);

                while (st.hasMoreTokens()) {
                    String token = st.nextToken();

                    if (token == ";"){
                        String thisOutput = transferStack.pop() + ";";

                        if (outFilename != null) writer.write("Postfix Expression: " + thisOutput + "\n"); // write to output 
                        else System.out.println("Postfix Expression: " + thisOutput);

                        // validate that the stack is empty 
                        if (!transferStack.isEmpty()){
                            System.out.println("ERROR: stack is not empty at the end of an expression");
                            return;
                        }
                    }
                    else if (token == ")") {
                        String right = transferStack.pop();
                        String oper = transferStack.pop();
                        String left = transferStack.pop();
                        transferStack.push(left + " " + right + " " + oper);
                    }
                    else  {
                        if (token != "(") transferStack.push(token);
                    }
                }
            }
        // close the scanner and writer
        sc.close();
        if (outFilename != null) writer.close();
        }
        catch(IOException e)  {  
            e.printStackTrace();  
        }  
    }
}