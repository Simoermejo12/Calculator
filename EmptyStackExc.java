/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #1
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */

/**
 * this class is thrown when trying to remove from an empty stack
 */
public class EmptyStackExc extends Exception{
    /**
     * constructor for the exception
     */
    public EmptyStackExc(){
        super("Stack is empty");
    }
}
