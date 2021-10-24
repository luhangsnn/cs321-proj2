// CS321 Project 2
// Postfix.java
// Ryan Seaman and Luhang Sun
// October 20, 2021

import java.io.*;
import java.util.*;


public class Postfix {

    // takes a single line of infix and convert to postfix - reading file functions will be in the main function
    public String infixToPostfix(String thisline){
            LLstack <String> transferStack = new LLstack<String>();          
            StringTokenizer st = new StringTokenizer(thisline);
            String thisOutput = "";

            while (st.hasMoreTokens()) {
                String token = st.nextToken();

                if (token.equals(";")){
                    thisOutput = transferStack.pop() + " ;";

                    // validate that the stack is empty 
                    if (!transferStack.isEmpty()){
                        System.out.println("ERROR: stack is not empty at the end of an expression");
                        return null;
                    }
                }
                else if (token.equals(")")) {
                    String right = transferStack.pop();
                    String oper = transferStack.pop();
                    String left = transferStack.pop();
                    transferStack.push(left + " " + right + " " + oper);
                }
                else  {
                    if (!token.equals("(")){
                        transferStack.push(token);
                    }
                }
            }
            return thisOutput;
        }
        
    // command line interface to read in .txt files with multiple lines of infix 
    // print or write the conversion to postfix 
    public static void main (String args[]){
        Postfix postfixConversion = new Postfix();

        if (args.length < 1){
            System.out.println("Usage: <infix file name> [output filename]");
            return;
        }

        String input = args[0];
        String output = null;
        if (args.length > 1) { // if an output file name is given
            output = args[1];
        }

        FileWriter writer;
        try{
            FileInputStream fis = new FileInputStream(input);       
            Scanner sc = new Scanner(fis);
            if (output != null) writer = new FileWriter(output); // initialize the filewriter if an output file name is given
            else writer = null; // to avoid instance not intialized error below when using writer
            
            while(sc.hasNextLine()){  //while there is another line to read  
                String thisline = sc.nextLine();

                // first print the infix expression (if no output filename is given)
                // not writing infix expression to output file - will be done in Assembler.java
                if (output == null) System.out.println("Infix Expression: " + thisline);
                
                // convert to postfix
                String thisPostfix = postfixConversion.infixToPostfix(thisline);

                // print/write the postfix expressions 
                if (output != null) writer.write(thisPostfix + "\n"); 
                else System.out.println("Postfix Expression: " + thisPostfix);

            
            }
        // close the scanner and writer
        sc.close();
        if (output != null) writer.close();
        }
            
        catch(IOException e)  {  
            e.printStackTrace();  
        }  
    }
}