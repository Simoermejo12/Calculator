/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #3
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */

/**
 * This class represents an EquationStack it is used to evaluate the answer of an equation. Since it is a stack it is used to evaluate an equation
 * using the postfix. It has a top and a capacity and is implemented using an array
 * @author Simone Mesiha
 * @see Equation
 */
public class EquationStack {
    private int top = -1;
    private final int CAPACITY = 1000;
    private String[] arrayStack;

    /**
     * getter for top
     * @return
     * top
     */
    public int getTop() {
        return top;
    }

    /**
     * setter for top
     * @param top
     * new top
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * getter for arrayStack
     * @return
     * arrayStack
     */
    public String[] getArrayStack() {
        return arrayStack;
    }

    /**
     * setter for arrayStack
     * @param arrayStack
     * new ArrayStack
     */
    public void setArrayStack(String[] arrayStack) {
        this.arrayStack = arrayStack;
    }

    /**
     * Consructor for equationStack
     * @param equationGetEquation
     * the String of the equation from Equation
     */
    public EquationStack(String equationGetEquation) {
        arrayStack = new String[CAPACITY];
    }


//    public void pushAll(String x) throws FullStackExc {
//        /**
//         * Splitting method
//         */
//        String[] array = x.split(" ");
//        for (String seperate : array) {
//            if (top == CAPACITY - 1) {
//                throw new FullStackExc();
//            } else {
//                top++;
//                arrayStack[top] = seperate;
//            }
//        }
//
//    }

    //Parameter is postfix expression

    /**
     * push method for stack
     * @param x
     * the string to push
     * @throws FullStackExc
     * if stack is full
     */
    public void push(String x) throws FullStackExc {

        String[] arr = x.split(" ");
        int counter=0;
        if(top==CAPACITY-1){
            throw new FullStackExc();
        }else {
            top++;
            arrayStack[top]=x;
            }
        }

    //Parameter is postfix expression

    /**
     * pop method for the stack :removes top
     * @return
     * String removed
     * @throws EmptyStackExc
     * if trying to remove fron emptystack
     */
    public String pop() throws EmptyStackExc {
        if (top == -1) {
            throw new EmptyStackExc();
        }
        top--;

        return arrayStack[top+1];
    }

    /**
     * peek method for stack : returns top
     * @return
     * the top of stack
     * @throws EmptyStackExc
     * if stack is empty
     */
    public String peek() throws EmptyStackExc {
        if (top == -1) {
            throw new EmptyStackExc();
        }
        return arrayStack[top];
    }

    /**
     * checks if stack is empty
     * @return
     * true if empty false otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * checks the size
     * @return
     * int with the size of the stakc
     */
    public int size() {
        return top;
    }

    /**
     *
     * @param x
     * the postfix to be solved
     * @return
     * solution of the equaiton
     * @throws EmptyStackExc
     * removing from empty stack
     * @throws FullStackExc
     * adding to fullstack
     */
    public double solveStack(String x) throws EmptyStackExc, FullStackExc {
        String equationToPop=x.replaceAll("  ","");
         equationToPop = equationToPop.replaceAll("    ", " ");
        equationToPop = equationToPop.replaceAll("   ", " ");


        String[] arr = equationToPop.split(" ");

        String storeOperator;
        String storeFirstPopped;
        String storeSecondPopped;
        double singleOp;
        int counter=0;
        double answer = 0;

//        if (isEmpty() == true) {
//            throw new EmptyStackExc();
//        }
        String[] popStore = new String[100];
        while (counter<arr.length) {

           String item = arr[counter];

                counter++;
                push(item);
                if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("%") || item.equals("/")|| item.equals("^")) {
                    switch (item) {

                        case "+":
                            storeOperator = pop();
                            storeFirstPopped = pop();
                            storeSecondPopped = pop();
                            singleOp = Double.parseDouble(storeFirstPopped) + Double.parseDouble(storeSecondPopped);

                            push(String.valueOf(singleOp));
                            break;

                        case "-":
                            storeOperator = pop();
                            storeFirstPopped = pop();
                            storeSecondPopped = pop();
                            //If ERROR TRY TO REVERSE THE ORDER OF THE POPPED
                            singleOp = Double.parseDouble(storeSecondPopped) - Double.parseDouble(storeFirstPopped);

                            push(String.valueOf(singleOp));
                            break;

                        case "*":
                            storeOperator = pop();
                            storeFirstPopped = pop();
                            storeSecondPopped = pop();
                            singleOp = Double.parseDouble(storeFirstPopped) * Double.parseDouble(storeSecondPopped);

                            push(String.valueOf(singleOp));
                            break;

                        case "/":
                            storeOperator = pop();
                            storeFirstPopped = pop();
                            storeSecondPopped = pop();
                            singleOp = Double.parseDouble(storeSecondPopped) / Double.parseDouble(storeFirstPopped);

                            push(String.valueOf(singleOp));
                            break;
                        case "%":
                            storeOperator = pop();
                            storeFirstPopped = pop();
                            storeSecondPopped = pop();
                            singleOp = Double.parseDouble(storeSecondPopped) % Double.parseDouble(storeFirstPopped);

                            push(String.valueOf(singleOp));
                            break;
                        case "^":
                            storeOperator=pop();
                            storeFirstPopped=pop();
                            storeSecondPopped=pop();
                            singleOp = Math.pow(Double.parseDouble(storeSecondPopped),  Double.parseDouble(storeFirstPopped));

                            push(String.valueOf(singleOp));
                            break;
                    }
                }
            }
        String stackFinalValue= pop();
        answer=Double.parseDouble(stackFinalValue);
        return Double.parseDouble(String.format("%.2f",answer));
    }
}
