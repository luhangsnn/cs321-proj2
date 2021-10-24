# cs321 Project 2
###### Luhang Sun & Ryan Seaman 

In this project we completed a converter from an infix mathematical expression into assembly code, similar to the machine instructors by CPU. There are 3 classes in this project:
- LLstack: node-based stack class
- Profix: contains algorithm that converts infix to profix. The main function of the class takes an input file from command line with multiple lines of infix expressions and outputs (or prints to terminal) the profix expressions.
  
  Usage: java Profix <input filename> [output filename]
- Assembler: contains algorithm that converts profix to assembly. The main function of the class takes an input file from command line with multiple lines of infix expressions and outputs botht the profix and assembly expressions. 
  
  Usage: java Assembler <input filename> [output filename]
  
The text file output.txt is a sample output from the program using the data provided. 
