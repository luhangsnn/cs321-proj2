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
    public void toAssembly (String postfix, FileWriter writer){
        LLstack<String> astack = new LLstack<String>();
        List <String> operators = Arrays.asList("+", "-", "*", "/");

        StringTokenizer st = new StringTokenizer(postfix);

        while (st.hasMoreTokens()){
            String t = st.nextToken();
            if (t.equals(";")) break;
            if (!operators.contains(t)){
                astack.push(t);
            }
            else{
                String right = astack.pop();
                String left = astack.pop();
                astack.push(this.evaluate(left, t, right, writer));
            }
        }
    }

    // print/write the assembly code
    // takes a FileWriter parameter that will be null in the main fuction if no output filename is given
    public String evaluate(String left, String token, String right, FileWriter writer){
        String tmpn = "TMP" + Integer.toString(this.memoryNo);
        
        // format the operation line
        String secondLine = "    ";

        if (token.equals("+")) secondLine += "AD";
        else if (token.equals("-")) secondLine += "SB";
        else if (token.equals("*")) secondLine += "ML";
        else if (token.equals("/")) secondLine += "DV";
    
        secondLine += "      " + right;

        if (writer == null){ // print to terminal
            System.out.println("    LD" + "      " + left);
            System.out.println(secondLine);
            System.out.println("    ST " + "     " + tmpn);
        }
        else{ // write to output file
            try{
                writer.write("    LD" + "      " + left + "\n");
                writer.write(secondLine + "\n");
                writer.write("    ST " + "     " + tmpn + "\n");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.memoryNo++;
        return tmpn;
    }

    public static void main(String[] args){
        if (args.length < 1){
            System.out.println("Usage: <infix file name> [output filename]");
            return;
        }
        String infixInput = args[0];
        String output = null;

        if (args.length > 1) { // if an output file name is given
            output = args[1];
        }

        Postfix postfixConverter = new Postfix ();
        Assembler assemblyConverter = new Assembler();
        FileWriter writer;

        try{
            FileInputStream fis = new FileInputStream(infixInput);       
            Scanner sc = new Scanner(fis);

            if (output != null) writer = new FileWriter(output); // initialize the filewriter if an output file name is given
            else writer = null; // to avoid instance not intialized error below when using writer

            while (sc.hasNextLine()){
                String infixLine = sc.nextLine();
                String thisPostfix = postfixConverter.infixToPostfix(infixLine);

                if (output == null) {
                    System.out.println("Infix Expression: " + infixLine);
                    System.out.println("Postfix Expression: " + thisPostfix);
                }
                else{
                    writer.write("Infix Expression: " + infixLine + "\n");
                    writer.write("Postfix Expression: " + thisPostfix + "\n");
                }
                assemblyConverter.toAssembly(thisPostfix, writer);
            }
            sc.close();
            writer.close();
        }
        catch(IOException e)  {  
            e.printStackTrace();  
        }
    }
}
