/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #3
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */

/**
 * Exception when trying to add to a full stack
 */
public class FullStackExc extends Exception{
    /**
     * Constructor for the exception
     */
    public FullStackExc(){
        super("Stack is full. Can't add no more");
    }
}
