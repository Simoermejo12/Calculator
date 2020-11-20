/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #3
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */

/**
 * this exception is thrown when the equation is unbalanced
 */
public class EquationNotBalancedException extends Exception{
    /**
     * constructor for the notBalancedException
     */
    EquationNotBalancedException(){
        super("This equation is not balanced");
    }
}
