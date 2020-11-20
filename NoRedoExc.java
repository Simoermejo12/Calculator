/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #3
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */

/**
 * this class gets called when there is nothing to redo but redo is called regardless
 */
public class NoRedoExc extends Exception {
    /**
     * constructor for the redo Exception
     */
    public NoRedoExc(){
        super("Can't redo because nothing was undone");
    }
}
