// CS321 Project 2
// Assembler.java
// Luhang Sun & Ryan Seaman

import java.io.*;
import java.util.*;


public class Assembler {

    private int memoryNo;

    public Assembler(){
        this.memoryNo = 1;
    }
    
    // evaluate a single line of postfix expression and convert to assembly
    public void toAssembly (String postfix, String outFileName){
        LLstack<String> astack = new LLstack<String>();
        List <String> operators = Arrays.asList("+", "-", "*", "/");

        StringTokenizer st = new StringTokenizer(postfix);

        while (st.hasMoreTokens()){
            String t = st.nextToken();
            if (t == ";") break;
            if (!operators.contains(t)){
                astack.push(t);
            }
            else{
                String right = astack.pop();
                String left = astack.pop();
                astack.push(this.evaluate(left, t, right, outFileName));
            }
        }
        // check -- top of stack has value 
    }

    public String evaluate(String left, String token, String right, String outFileName){
        String tmpn = "TMP" + Integer.toString(this.memoryNo);
        
        // format the operation line
        String secondLine = "\t\t\t\t";

        if (token == "+") secondLine += "AD";
        else if (token == "-") secondLine += "SB";
        else if (token == "*") secondLine += "ML";
        else if (token == "/") secondLine += "DV";
    
        secondLine += "\t\t\t\t\t\t" + right;

        if (outFileName == null){ // print to terminal
            System.out.println("\t\t\t\tLD" + "\t\t\t\t\t\t" + left);
            System.out.println(secondLine);
            System.out.println("\t\t\t\tST " + "\t\t\t\t\t" + tmpn);
        }
        else{ // write to output file
            try{
                FileWriter writer = new FileWriter(outFileName, true); // append exsiting file if the file exists
                writer.write("\t\t\t\tLD" + "\t\t\t\t\t\t" + left + "\n");
                writer.write(secondLine + "\n");
                writer.write("\t\t\t\tST " + "\t\t\t\t\t" + tmpn + "\n");
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.memoryNo ++;
        return tmpn;
    }

    // overloading the evaluate function when no output filename is given -> print to terminal
    public String evaluate(String left, String token, String right){
        return this.evaluate(left, token, right, null);
    }

    public static void main(String[] args){
        if (args.length < 1){
            System.out.println("Usage: <infix file name> [output filename]");
            return;
        }
        String input = args[0];
        String output = null;

        if (args.length > 1) { // if an output file name is given
            output = args[1];
        }
        Postfix postfixConverter = new Postfix ();
        // postfixConverter.infixToPostfix(input, output);
    }
}
