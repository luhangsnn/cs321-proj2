// CS321 Project 2
// Assembler.java
// Luhang Sun & Ryan Seaman

import LLstack;
import Postfix;
import java.io.*;
import java.util.*;


public class Assembler {

    private int memoryNo;

    public Assembler(){
        this.memoryNo = 1;
    }
    
    // evaluate a single link of postfix expression and convert to assembly
    public void toAssembly (String postfix){
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
                astack.push(this.evaluate(left, t, right));
            }
        }
        // check -- top of stack has value 
    }

    public String evaluate(String left, String token, String right){
        String assemblyOutput = "";
        assemblyOutput += "LD " + left + "\n";

        if (token == "+") assemblyOutput += "AD ";
        else if (token == "-") assemblyOutput += "SB ";
        else if (token == "*") assemblyOutput += "ML ";
        else if (token == "/") assemblyOutput += "DV ";

        assemblyOutput += right + "\n";
        String tmpn = "TMP" + Integer.toString(this.memoryNo);
        assemblyOutput += "ST " + tmpn + "\n";

        this.memoryNo ++;
        // write out assemblyOutput, only push TMPn to the stack

        return tmpn;
    }

    public static void main(){

    }
}
