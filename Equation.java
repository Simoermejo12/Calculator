/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #3
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */

/**
 * @author Simone Mesiha
 * This class represents an equation. It has an equation which can be converted into postfix, prefix, and its answer can be turned into a hexadecimal
 * and binary. Lastly it checks if the equation is balacned
 * @see EquationStack
 */
public class Equation {
    private String equation;
    private String prefix;
    private String postfix;
    private String answer;
    private String binary;
    private String hex;
    private String balanced;

    /**
     * Constructor to create an Instance of Equation
     */
    public Equation() {

    }

    /**
     *  Constructor to create an instance of equation
     * @param equation
     * what the equation will be
     */
    public Equation(String equation) {
        this.equation = equation;
    }

    /**
     * getter for equation
     * @return
     * Equation
     */
    public String getEquation() {
        return equation;
    }
    /**
     * getter for prefix
     * @return
     * prefix
     */
    public String getPrefix() {
        return prefix;
    }
    /**
     * getter for postfix
     * @return
     * postfix
     */
    public String getPostfix() {
        return postfix;
    }
    /**
     * getter for answer
     * @return
     * answer
     */
    public String getAnswer() {
        return answer;
    }
    /**
     * getter for binary
     * @return
     * binary
     */
    public String getBinary() {
        return binary;
    }
    /**
     * getter for hex
     * @return
     * hex
     */
    public String getHex() {
        return hex;
    }
    /**
     * getter for balanced equation
     * @return
     * balanced equation
     */
    public String getBalanced() {
        return balanced;
    }

    /**
     * setter for equation
     * @param equation
     * new equation
     */
    public void setEquation(String equation) {
        this.equation = equation;
    }
    /**
     * setter for prefix
     * @param prefix
     * new prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    /**
     * setter for postfix
     * @param postfix
     * new postfix
     */
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
    /**
     * setter for answer
     * @param answer
     * new answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * setter for binary
     * @param binary
     * new binary
     */
    public void setBinary(String binary) {
        this.binary = binary;
    }
    /**
     * setter for hex
     * @param hex
     * new hex
     */
    public void setHex(String hex) {
        this.hex = hex;
    }
    /**
     * setter for balanced equation
     * @param balanced
     * new balanced equation
     */
    public void setBalanced(String balanced) {
        this.balanced = balanced;
    }

    //METHODS

    /**
     * method to check if infix is balanced
     * @return
     * true if balance false if not balanced
     */
    public boolean isBalanced() {
        //Exception Left ) parenthesis before right

        if(equation.contains("/0")){
            return false;
        }


        int indexFirstLeft =0;//)
        int indexFirstRigth=0;//(


            if(equation.contains("(") && equation.contains(")")){
                if(equation.indexOf(")")<equation.indexOf("(")){
                    return false;
                }
        }
        if(equation.contains("(") && !equation.contains(")")) {
            return false;
        }

        int counterLeftPar=0;
        int counterRightPar=0;
        for(int i=0;i<equation.length();i++){
            if(equation.charAt(i)=='('){
                counterLeftPar++;
            }else if(equation.charAt(i)==')'){
                counterRightPar++;
            }
        }

        if(counterLeftPar==counterRightPar) {
            return true;
        }else{
            return true;
        }


    }

    //ALGORITHM 1)REVERSE STRING 2) POSTFIX 3)REVERSE AGAIN

    /**
     * changes infix equation to prefix
     * @param infix
     * the string that will be converted to postfix
     * @return
     * the prefix of the equation
     * @throws EmptyStackExc
     * The invoked postfix method might trigger this
     * @throws FullStackExc
     * The invoked postfix method might trigger this
     */
    public String infixToPrefix(String infix) throws EmptyStackExc, FullStackExc {
        String reversadone="";
        for(int i=infix.length()-1;i>=0;i--){
            if(infix.charAt(i)=='('){
                reversadone+=')';
            }else if(infix.charAt(i)==')'){
                reversadone+="(";
            }else{
                reversadone+=infix.charAt(i);
            }
        }

         reversadone= infixToPostFix(reversadone);
        String fina=" ";
        for(int i=reversadone.length()-1;i>=0;i--){
            fina+=reversadone.charAt(i);
        }
        this.prefix=fina.trim();

        return fina.trim();
    }

    /**
     * Takes an infix and turns to postfix
     * @param infix
     * the infix string to turn into postfix
     * @return
     * postfix of equation
     * @throws EmptyStackExc
     * if removing from empty stack
     * @throws FullStackExc
     * adding to a full stack
     */
    public String infixToPostFix(String infix) throws EmptyStackExc, FullStackExc {
            String postfix = "";
            //Stack<Character> myStack = new Stack();
            EquationStack myStack = new EquationStack(infix);
            String poppedPrior;
//Covers if input is Digit
            for (int i = 0; i < infix.length(); i++) {
                if(Character.isDigit(infix.charAt(i))==true){
                    String longNumber="";
                    //Not sure if this crap works
                    while(i<infix.length()&&Character.isDigit(infix.charAt(i))==true){
                        longNumber+=infix.charAt(i);
                        i++;
                    }
                   i--;
                    //postfix+=infix.charAt(i);
                    postfix+=longNumber+' ';

                }else if(infix.charAt(i)=='('){
                    myStack.push(String.valueOf('('));

                }else if(infix.charAt(i)=='+'||infix.charAt(i)=='-'){

                    if(myStack.isEmpty()==true){
                        myStack.push(String.valueOf(infix.charAt(i)));
                    }else{

                        //Added ^ could lead to errors
                        if (myStack.peek().equals(String.valueOf('*')) || myStack.peek().equals(String.valueOf('/')) || //Changed the or to and
                                (myStack.peek().equals(String.valueOf('%')) || myStack.peek().equals(String.valueOf('-')) || myStack.peek().equals(String.valueOf('+'))||
                                        myStack.peek().equals("^"))){
                                poppedPrior = myStack.pop();

                                postfix += poppedPrior + " ";
                                myStack.push(String.valueOf(infix.charAt(i)));

                        }else{
                            myStack.push(String.valueOf(infix.charAt(i)));
                        }
                    }
//Covers if input is multiplication
                }else if(infix.charAt(i)=='*'||infix.charAt(i)=='/'||infix.charAt(i)=='%'){
                    if(myStack.isEmpty()==true){
                        myStack.push(String.valueOf(infix.charAt(i)));
                    }else{
                        if(myStack.peek().equals(String.valueOf('*')) || myStack.peek().equals(String.valueOf('/')) ||
                                myStack.peek().equals(String.valueOf('%'))|| myStack.peek().equals(String.valueOf('^'))){
                                poppedPrior = myStack.pop();

                                postfix += poppedPrior + " ";
                                myStack.push(String.valueOf(infix.charAt(i)));
                                //

                        }else{
                            myStack.push(String.valueOf(infix.charAt(i)));
                        }
                    }

//Covers if input is a closed parenthesis
                    //Hope it works
                }else if(infix.charAt(i)==')') {
                   // myStack.push(String.valueOf(infix.charAt(i)));

                    while (myStack.peek().equals(String.valueOf('('))==false) {
                        poppedPrior = myStack.pop();
                        postfix += poppedPrior+" ";
                    }
//                    if(myStack.peek().equals(String.valueOf('('))){
//                        poppedPrior=myStack.pop();
                   // }

                }else if(infix.charAt(i)=='^'){
                    if(myStack.isEmpty()==true){
                        myStack.push(String.valueOf(infix.charAt(i)));
                    }else{
                        if(myStack.peek().equals('^')){
                            poppedPrior=myStack.pop();

                            postfix+=poppedPrior+" ";
                            myStack.push(String.valueOf(infix.charAt(i)));
                        }else{
                            myStack.push(String.valueOf(infix.charAt(i)));
                        }
                    }
                }
            }
            while(myStack.isEmpty()==false){
                poppedPrior=myStack.pop();
                postfix+=poppedPrior+" ";
            }
            //This is a bit cheap but you gotta do what you gotta do;
            //If something goes wrong remove all the This
            this.postfix=postfix.replaceAll("\\(","");

            return this.postfix;
        }

    /**
     * Takes a decimal and turns into binary
     * @param x
     * decimal that will be converted to binary
     * @return
     * a binary representation of an int
     */
    public  String decToBin(int x){
       // System.out.println(x);
        x=(int)x;
        //System.out.println(x);
        String container ="";
        int remain;
            while(x>0){
            remain = (int)(x % 2);
               // System.out.println("remain " + remain);
            x = (int)(x / 2);
               // System.out.println(x);
                //If you do container +remain then you gotta reverse it otherwise you gotta
            container = container + remain;
        }
        String reverse="";

        for(int i = 0; i<container.length();i++){
            char last = container.charAt(container.length()-1-i);
            reverse+=last;
        }
        binary=container;
        return reverse;
    }
    /**
     * Takes a decimal and turns into hex
     * @param x
     * decimal that will be converted to hex
     * @return
     * a hexadecimal representation of an int
     */
    public String decToHex(int x){
        String[] hex= {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String container="";
        int remain;
        while(x>0){
            remain=x%16;
            x=x/16;
            container =hex[remain]+container;
        }
       this.hex=container;
        return container;
    }



    }


