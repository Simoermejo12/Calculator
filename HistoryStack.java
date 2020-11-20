
import java.util.Stack;

/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #3
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */

/**
 * This class represents a stack that holds Equation stacks. It's like a storage of equation
 * @author Simone Mesiha
 * @see EquationStack
 * @see Equation
 */
public class HistoryStack extends Stack {
    private EquationStack myStack = null;
    /**
     * construcor for the historyStack class
     */
    public HistoryStack(){

    }

    /**
     * Pushes an equation to the top of the stack
     * @param x
     * equation to be pushed
     */
    public void push(EquationStack x){
        super.push(x);
    }

    /**
     * removes an equation from the top of the stack
     * @return
     * the equation removed
     */
    public Equation pop(){
        return (Equation)super.pop();
    }

    /**
     * checks if the stack is empty
     * @return
     * if it's empty returns true otherwise false
     */
    public boolean isEmpty(){
        return super.isEmpty();
    }

    /**
     * Returns how many equations are in the stack
     * @return
     * an integer with the size
     */
    public int getSize(){
        return super.size();
    }

    /**
     * Helps with formatting
     * @return
     * the format I want the printing to be
     */
    public String toString(){
        return String.format("%-5s%-35s%-35s%-35s%-35s%-35s%-35s%n", "#", "Equation", "Pre-Fix", "PostFix", "Answer","Binary","Hexadecimal")+
                "-----------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------------------------------------- ";
    }

    /**
     * removes top
     */
    public void undo(){
       myStack= (EquationStack)super.pop();
    }

    /**
     * adds the removed
     */
    public void redo(){
        push(myStack);
    }
}
