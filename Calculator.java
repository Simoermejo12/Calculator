/**
 * @author Simone Mesiha
 * 112798336
 * simone.mesiha@stonybrook.edu
 * Assignment #3
 * CSE 214: Data Structures
 * R02 TA's :Sabrina Margetic, Rachel Sheridan
 */
import java.util.Scanner;

/**
 * This class contains the main method
 */
public class Calculator {
    public static void main(String[] args) throws EmptyStackExc, FullStackExc {
    Scanner stdin = new Scanner(System.in);
    String userInput;
    HistoryStack myHistory = new HistoryStack();

    Equation undoedEq = null;
    do{
        System.out.println("Welcome to Calculator\n");
        System.out.println("[A] Add new equation");
        System.out.println("[F] Change equation from history");
        System.out.println("[B] Print previous equation");
        System.out.println("[P] Print full history");
        System.out.println("[U] Undo");
        System.out.println("[R] Redo");
        System.out.println("[C] Clear history");
        System.out.println("[Q] Quit");

        System.out.print("Select an option: ");
        userInput = stdin.nextLine();


            if(userInput.equalsIgnoreCase("a")){
                String equationInput;
                System.out.print("Please enter an equation (in in-fix notation): ");
                equationInput=stdin.nextLine();
                Equation myEquation = new Equation(equationInput);
                //Push it to history Stack
                myHistory.push(myEquation);
                if(myEquation.isBalanced()==true){
                    EquationStack myEquationStack = new EquationStack(myEquation.getEquation());
                    myEquation.setPostfix(myEquation.infixToPostFix(myEquation.getEquation()));
                    double solve =myEquationStack.solveStack(myEquation.getPostfix());
                    System.out.println("The equation is balanced and the answer is "+String.format("%-5.3f",solve) +"\n");
                    //System.out.println("PostFix::: "+  myEquation.getPostfix());
                }else{
                    System.out.println("The equation is not balanced.\n");
                }




            }else if(userInput.equalsIgnoreCase("f")){                                                                      //Needs work
                Equation [] equationArray= new Equation[40];
                System.out.print("Which Equation would you like to change? ");
                int eqPosition = stdin.nextInt();
                stdin.nextLine();
                //String yupOrNo="n";
                for(int i=1;i<=myHistory.getSize();i++){
                    equationArray[i]=myHistory.pop();
                }
                String yupOrNo;
                Equation newEquation= new Equation(equationArray[eqPosition].getEquation());
                System.out.println("Equation at position "+ eqPosition+" : "+equationArray[eqPosition].getEquation());


               do {
                    System.out.print("would you like to do to the equation ?(Replace / remove / add) ");
                    String userAction = stdin.nextLine();


                    if (userAction.equalsIgnoreCase("replace")) {
                        System.out.print("What position would you like to change? ");
                        int position = stdin.nextInt();
                        stdin.nextLine();
                        System.out.print("What would you like to replace it with ");
                        String positionChange = stdin.nextLine();
                        StringBuilder replacer= new StringBuilder();
                        try {
                             replacer = new StringBuilder(newEquation.getEquation());//equationArray[eqPosition].getEquation()
                        }catch (NullPointerException ex){
                            System.out.println("Invalid position");
                        }
                        replacer.replace(position - 1, position, positionChange);

                        System.out.println("Equation: " + replacer);
                         newEquation = new Equation(String.valueOf(replacer));
                        //if(myHistory.isEmpty()) {
                            for (int i = 0; i < equationArray.length; i++) {
                                if (equationArray[i] != null) {
                                    myHistory.push(equationArray[i]);
                                }
                            //}
                        }


                        System.out.print("Would you like to make any more changes ?");
                        yupOrNo = stdin.nextLine();
                        if(yupOrNo.equalsIgnoreCase("n")||yupOrNo.equalsIgnoreCase("no")){
                            myHistory.push(newEquation);
                        }
                        //To make sure this is the that will be edited again
                       // eqPosition = myHistory.getSize() - 1;


                    } else if (userAction.equalsIgnoreCase("add")) {

                            System.out.print("What position would you like to add something? ");
                            int position = stdin.nextInt();
                            stdin.nextLine();
                            System.out.print("What would you like to add");
                            String add = stdin.nextLine();

                            StringBuilder adder = new StringBuilder(newEquation.getEquation());

                            adder.insert(position-1,add);

                            System.out.println("Equation: " + adder);
                             newEquation = new Equation(String.valueOf(adder));
                            if(myHistory.isEmpty()) {
                                for (int i = 0; i < equationArray.length; i++) {
                                    if (equationArray[i] != null) {
                                        myHistory.push(equationArray[i]);
                                    }
                                }
                            }
                           //myHistory.push(newEquation);

                            System.out.print("Would you like to make any more changes ?");
                            yupOrNo = stdin.nextLine();
                        if(yupOrNo.equalsIgnoreCase("n")||yupOrNo.equalsIgnoreCase("no")){
                            myHistory.push(newEquation);
                        }
                            //To make sure this is the that will be edited again
                            //eqPosition = myHistory.getSize() - 1;


                        } else if (userAction.equalsIgnoreCase("remove")) {

                            System.out.print("What position would you like to remove? ");
                            int position = stdin.nextInt();
                            stdin.nextLine();
                            StringBuilder remover= new StringBuilder();
                            try {
                                remover = new StringBuilder(newEquation.getEquation());
                            }catch (NullPointerException ex){
                                System.out.println("Invalid Equation");
                            }
                            remover.replace(position - 1, position, "");

                            System.out.println("Equation: " + remover);
                             newEquation = new Equation(String.valueOf(remover));

                            if(myHistory.isEmpty()) {
                                for (int i = 0; i < equationArray.length; i++) {
                                    if (equationArray[i] != null) {
                                        myHistory.push(equationArray[i]);
                                    }
                                }
                            }

                            //myHistory.push(newEquation);

                            System.out.print("Would you like to make any more changes ? ");
                            yupOrNo = stdin.nextLine();
                        if(yupOrNo.equalsIgnoreCase("n")||yupOrNo.equalsIgnoreCase("no")){
                            myHistory.push(newEquation);
                        }

                            //To make sure this is the that will be edited again
                            //eqPosition = myHistory.getSize() - 1;


                        } else {
                        System.out.print("Would you like to make any more changes ?");
                        yupOrNo = stdin.nextLine();

                    }

                } while(!yupOrNo.equalsIgnoreCase("n") && yupOrNo.equalsIgnoreCase("no")!=true);




            }else if(userInput.equalsIgnoreCase("b")){
                //myHistory.toString();
                try {
                    if(myHistory.isEmpty()==true){
                        throw new EmptyStackExc();
                    }
                    Equation onTopEq;
                    onTopEq = myHistory.pop();
                    EquationStack stackOfMyEq = new EquationStack(onTopEq.getEquation());
                    if (onTopEq.isBalanced()) {
                        onTopEq.setPrefix(onTopEq.infixToPrefix(onTopEq.getEquation()));
                        onTopEq.setHex((onTopEq.decToHex((int) Math.round(stackOfMyEq.solveStack(onTopEq.infixToPostFix(onTopEq.getEquation()))))));
                        onTopEq.setBinary((onTopEq.decToBin((int) Math.round(stackOfMyEq.solveStack(onTopEq.infixToPostFix(onTopEq.getEquation()))))));
                        System.out.println(myHistory.toString());
                        System.out.printf("%-5d%-35s%-35s%-35s%-35.3f%-35s%-35s%n", myHistory.size() + 1, onTopEq.getEquation()
                                , onTopEq.getPrefix(), onTopEq.getPostfix(), stackOfMyEq.solveStack(onTopEq.infixToPostFix(onTopEq.getEquation()))
                                , onTopEq.getBinary(), onTopEq.getHex());
                    } else {
                        System.out.println((myHistory.toString()));
                        System.out.printf("%-5d%-35s%-35s%-35s%-35s%-35s%n", myHistory.size() + 1, onTopEq.getEquation()
                                , "N/A", "N/A", "0.000", "0", "0");
                    }
                    myHistory.push(onTopEq);
                }catch (EmptyStackExc ex){
                    System.out.println(ex);
                }

            }else if(userInput.equalsIgnoreCase("p")){
                System.out.println(myHistory.toString());
                HistoryStack holder= new HistoryStack();
                while(myHistory.isEmpty()==false){

                    Equation onTopEq;
                    onTopEq=myHistory.pop();

                    holder.push(onTopEq);

                    EquationStack stackOfMyEq= new EquationStack(onTopEq.getEquation());
                    if(onTopEq.isBalanced()){
                        onTopEq.setPrefix(onTopEq.infixToPrefix(onTopEq.getEquation()));
                        onTopEq.setHex((onTopEq.decToHex((int)Math.round(stackOfMyEq.solveStack(onTopEq.infixToPostFix(onTopEq.getEquation()))))));
                        onTopEq.setBinary((onTopEq.decToBin((int)Math.round(stackOfMyEq.solveStack(onTopEq.infixToPostFix(onTopEq.getEquation()))))));
                        //System.out.println(myHistory.toString());
                        System.out.printf("%-5d%-35s%-35s%-35s%-35.3f%-35s%-35s%n", myHistory.size()+1, onTopEq.getEquation()
                                , onTopEq.getPrefix(), onTopEq.getPostfix(), stackOfMyEq.solveStack(onTopEq.infixToPostFix(onTopEq.getEquation()))
                                ,onTopEq.getBinary(),onTopEq.getHex());
                    }else{
                        //System.out.println((myHistory.toString()));
                        System.out.printf("%-5d%-35s%-35s%-35s%-35s%-35s%-35s%n", myHistory.size()+1, onTopEq.getEquation()
                                , "N/A", "N/A", "0.000","0","0");
                    }

                }
                while (holder.isEmpty()==false){
                    myHistory.push(holder.pop());
                }
            }else if(userInput.equalsIgnoreCase("u")) {
                try {
                    if(myHistory.isEmpty()==true){
                        throw new EmptyStackExc();
                    }
                    undoedEq=myHistory.pop();
                } catch (Exception ex) {
                    System.out.println("Can't undo because it is empty.");
                }

                if (undoedEq != null) {
                    System.out.println("Equation "+undoedEq.getEquation()+" undone");
                }

            }else if(userInput.equalsIgnoreCase("r")){
                try{
                    if(undoedEq==null){
                        throw new NoRedoExc();
                    }
                    if(myHistory.isEmpty()==true){
                        throw new NoRedoExc();
                    }

                    myHistory.push(undoedEq);
                    System.out.println();
                    if(undoedEq.getEquation()!=null){
                        System.out.println("Redoing equation"+ undoedEq.getEquation());
                    }else{
                        System.out.println("There is nothing to redo;");
                    }
                } catch (NoRedoExc ex) {
                    System.out.println(ex);
                }
//               if(undoedEq.getEquation()!=null){
//                   System.out.println("Redoing equation"+ undoedEq.getEquation());
//               }else{
//                   System.out.println("There is nothing to redo;");
//               }
            }else if(userInput.equals("c")){
                while(myHistory.isEmpty()==false){
                    myHistory.pop();
                }
                undoedEq=null;
            }



    }while (userInput.equalsIgnoreCase("q")!=true);

//    HistoryStack myH = new HistoryStack();
//    Equation x = new Equation("(5839 /23+  ((68-7) % 8))");
//       x.setPostfix(x.infixToPostFix(x.getEquation()));
//        EquationStack a = new EquationStack(x.getEquation());
//        System.out.println(x.infixToPostFix(x.getEquation()));
//        // System.out.println(a.solveStack(x.getPostfix()));
//        System.out.println(x.getPostfix());
//        System.out.println(a.solveStack(x.getPostfix()));
//
//        int xfas = (int) Math.round(a.solveStack(x.infixToPostFix(x.getEquation())));
//
//
//        //System.out.println(x.getBinary(xfas));
//
//        x.setHex((x.decToHex((int)Math.round(a.solveStack(x.infixToPostFix(x.getEquation()))))));
//        System.out.println(x.getHex());
//        x.setPrefix(x.infixToPrefix(x.getEquation()));
//        System.out.println(x.getPrefix());
//
//        Equation da= new Equation("2+2");
//        myH.push(da);
//        myH.push(x);
//        System.out.println(myH.getSize());



    }
}
