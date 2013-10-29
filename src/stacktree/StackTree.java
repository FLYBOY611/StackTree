package stacktree;

import java.util.Scanner;

/**
 *
 * @author Richard
 */

//IT DETECTS OPERATOR BEFORE PAREN MISMATCH
public class StackTree {

    public static void main(String[] args) {

        String entry = "0";


        //Start the scanner and enter the first line
        System.out.println("Welcome to the stack program!");

        //Keep running so long as an empty expression isn't given
        while (!entry.equals("")) {
            System.out.println("\n" + "Please enter in characters, "
                    + "enter an empty expression to end");
            Scanner input = new Scanner(System.in);
            entry = input.nextLine();
            if(entry.equals("")){
                break;
            }
            boolean ErrorFree = true;

            //Check for errors in the statement
            ErrorFree = ErrorCheck(entry);
            if (ErrorFree == true) {

                String FinalPostfix = InfixToPost(entry);

                System.out.println("The original infix is: " + entry);
                System.out.println("The postfix is:" + FinalPostfix);
                Node temp = BuildATree(FinalPostfix);
                System.out.println(temp.toString());
                System.out.print("The evaluation is " + 
                        evaluateNode(BuildATree(FinalPostfix)) + "\n");
            }
        }


    }//End of Main

    public static String InfixToPost(String InfixExpression) {

        ListStack<String> Listo = new ListStack<String>();
        String WorkingEntry = "";
        String postfix = "";


        //Replace all blank space in the String entry 
        WorkingEntry = InfixExpression.replaceAll("\\s+", "");


        //Scroll through the String entry left to right
        for (int i = 0; i < WorkingEntry.length(); i++) {

            String currentItem = "";
            //If its a Digit move it to the postfix
            if (Character.isDigit(WorkingEntry.charAt(i)) == true) {
                int k = i;

                //Check to see how many digits the number is 
                while (k < WorkingEntry.length()
                        && Character.isDigit(WorkingEntry.charAt(k)) == true) {

                    currentItem += Character.toString(WorkingEntry.charAt(k));
                    i = k;
                    k++;
                }

                postfix = postfix + " " + currentItem;

            } else {
                currentItem = Character.toString(WorkingEntry.charAt(i));
            }

            //If its a left brace push it to the stack
            if (WorkingEntry.charAt(i) == '(') {
                Listo.push(Character.toString(WorkingEntry.charAt(i)));

            }

            //If its an Operator
            if (WorkingEntry.charAt(i) == '+' || WorkingEntry.charAt(i) == '-'
                    || WorkingEntry.charAt(i) == '*' || 
                    WorkingEntry.charAt(i) == '/') {
                //Push to the stack if the stack is empty
                //OR if the stack item is a left paren                
                if (Listo.isEmpty() == true || Listo.peek().equals("(")) {
                    Listo.push(Character.toString(WorkingEntry.charAt(i)));
                } //Otherwise we compare the precedence of the operators
                else if (Listo.isEmpty() == false) {
                    boolean stackwins = true;

                    while (Listo.isEmpty() == false && stackwins == true) {

                        //If the stack has greater priority we pop                            
                        int opPrecedence = comparePrecedence(currentItem, Listo.peek());

                        if (opPrecedence == OP_ONE_TAKES_PRECEDENCE) {
                            stackwins = false;
                        } else if (opPrecedence == OP_TWO_TAKES_PRECEDENCE
                                || opPrecedence == SAME_PRECEDENCE) {
                            postfix = postfix + " " + Listo.pop();
                        }
                    }
                    //At the end of it all we need to debated operand pushed
                    //onto the stack
                    Listo.push(Character.toString(WorkingEntry.charAt(i)));
                }
            }


            //If its a right brace then
            if (WorkingEntry.charAt(i) == ')') {
                //Keep poping until a left brace
                while (Listo.isEmpty() == false && !Listo.peek().equals("(")) {
                    postfix = postfix + " " + Listo.pop();
                }
                //Finally, pop out the leftbrace
                if (Listo.peek().equals("(")) {
                    Listo.pop();
                }
            }
        }
        //After the infix expression has been evaluated pop all remaining
        //Operators and add them to the stack
        while (Listo.isEmpty() == false) {
            postfix = postfix + " " + Listo.pop();
        }



        String output = postfix;
        return output;
    } 
//EVALUATION CODE

    //HOW THIS WORKS
    //
    //If it is a number, turn it into a leaf node and push it on the stack.
    //If it is an operator, pop two items from the stack, construct  
    //a mini tree with those children, and push all of it onto the stack.
    //At the end you have exactly one "node" on the stack which is your tree
    public static Node BuildATree(String postfix) {
        Node NumNode1 = new Node(null);
        ListStack<Node> treebuild = new ListStack<Node>();

        //Scroll throught the postfix, left to right
        for (int j = 0; j < postfix.length(); j++) {
            String tempdigit = "";
            //If its a digit 
            if (Character.isDigit(postfix.charAt(j)) == true) {
                //Check how many digits it is
                int f = j;
                while (f < postfix.length()
                        && Character.isDigit(postfix.charAt(f)) == true) {
                    tempdigit += Character.toString(postfix.charAt(f));
                    j = f;
                    f++;
                }

                //Then make a node out of it!
                //and push it to the stack     
                NumNode1 = new Node(tempdigit);
                treebuild.push(NumNode1);


            }
            //Otherwise, we're working with an operator
            tempdigit = Character.toString(postfix.charAt(j));
            
            //If it's an operator
            if (tempdigit.equals("+") || tempdigit.equals("-")
                    || tempdigit.equals("*") || tempdigit.equals("/")) {

                //Make a new node, 
                //Pop two elements from the stack and attach them
                Node OpNode = new Node(tempdigit);           
                OpNode.RightChild = treebuild.pop();
                OpNode.LeftChild = treebuild.pop();
                //Push the new mini tree back onto the stack                
                treebuild.push(OpNode);

            }
        }
        //At the end of the process we are left with one "node"
        //on the stack which is our entire tree
        return treebuild.pop();
    }

    //We use recursion to evaluate every node the tree has
    public static int evaluateNode(Node root) {
        int result;
        int Num1;
        int Num2;
        String temp;

        if (root == null) {
            result = 0;
        } else {
            temp = root.Element.toString();
            

            //If it's an operator
            if (temp.equals("+") || temp.equals("-")
                    || temp.equals("*") || temp.equals("/")) {

                Num1 = evaluateNode(root.LeftChild);
                Num2 = evaluateNode(root.RightChild);
                result = computeTerm(temp, Num1, Num2);
            } else {
                result = Integer.parseInt(temp);
            }
        }
        return result;
    }

    //This is called by the evaluation to do the acutal math 
    public static int computeTerm(String operator, int Num1, int Num2) {
        int result = 0;

        if (operator.equals("+")) {
            result = Num1 + Num2;
        } else if (operator.equals("-")) {
            result = Num1 - Num2;
        } else if (operator.equals("*")) {
            result = Num1 * Num2;
        } else if (operator.equals("/")) {
            result = Num1 / Num2;
        }

        return result;
    }

    //Find the operator precedence for making the postfix
    public static int getPrecedence(String Oper) {
        if (Oper.equals("*") || Oper.equals("/")) {
            return 2;
        } else if (Oper.equals("+") || Oper.equals("-")) {
            return 1;
        } else {
            throw (new ClassCastException("Not an operator"));
        }
    }
    public static int OP_ONE_TAKES_PRECEDENCE = -1;
    public static int OP_TWO_TAKES_PRECEDENCE = 1;
    public static int SAME_PRECEDENCE = 0;

    //Compare the operator precedence for the postfix
    public static int comparePrecedence(String Oper1, String Oper2) {
        int operOnePrec = getPrecedence(Oper1);
        int operTwoPrec = getPrecedence(Oper2);

        //If the stack has greater priority we pop    
        if (operOnePrec > operTwoPrec) {
            return OP_ONE_TAKES_PRECEDENCE;
        } else if (operOnePrec < operTwoPrec) {
            return OP_TWO_TAKES_PRECEDENCE;
        } else {
            return SAME_PRECEDENCE;
        }
    }

//ERROR CHECKING
    public static boolean ErrorCheck(String ErrorEntry) {
        String WorkingEntry = ErrorEntry;
        String comp1 = "";
        String comp2 = "";
        int j = 0;
        int k = 0;
        int z = 0;
        int q = 0;
        int Lparen = 0;
        int Rparen = 0;
        boolean OpParen = false;
        boolean NotValid = false;



        //Error check loop
        for (int i = 0; i < WorkingEntry.length(); i++) {
            comp1 = "";
            comp2 = "";

            //Check for invalid characters
            if (!(WorkingEntry.charAt(i) == '+' || WorkingEntry.charAt(i) == '-'
                    || WorkingEntry.charAt(i) == '*' || WorkingEntry.charAt(i) == '/'
                    || WorkingEntry.charAt(i) == '(' || WorkingEntry.charAt(i) == ')'
                    || WorkingEntry.charAt(i) == ' '
                    || (Character.isDigit(WorkingEntry.charAt(i)) == true))) {
                NotValid = true;
                q = i;

            }

            //Count left parens
            if ((i < WorkingEntry.length())
                    && (WorkingEntry.charAt(i) == '(')) {
                Lparen++;
                z = i;
            }

            //Count right parens
            if ((i < WorkingEntry.length())
                    && (WorkingEntry.charAt(i) == ')')) {
                //and make sure a number doesn't directly follow a right paren        
                if ((i + 1 < WorkingEntry.length()) && 
                     (Character.isDigit(WorkingEntry.charAt(i + 1)) == true)) {
                    z = i;
                    OpParen = true;
                }
                Rparen++;
                z = i;
            }

            //Check numbers and digits 
            if ((i < WorkingEntry.length())
                    && (Character.isDigit(WorkingEntry.charAt(i)) == true)) {
                //Make sure a left paren doesn't follow a digit
                if ((i + 1 < WorkingEntry.length())
                        && (WorkingEntry.charAt(i + 1) == '(')) {
                    z = i;
                    OpParen = true;
                }
                comp1 = "Number";
                k = i + 1;
                while (k < WorkingEntry.length()
                        && Character.isDigit(WorkingEntry.charAt(k)) == true) {
                    k++;

                }
                j = k;
            }

            //Check for Operators
            if ((i < WorkingEntry.length())
                    && (WorkingEntry.charAt(i) == '+' || WorkingEntry.charAt(i) == '-'
                    || WorkingEntry.charAt(i) == '*' || WorkingEntry.charAt(i) == '/')) {
                comp1 = "Operator";
                j = i + 1;
            }
            //Check if the next symbol is an operator  
            while (j < WorkingEntry.length()) {
                if ((j < WorkingEntry.length())
                        && (WorkingEntry.charAt(j) == '+' || WorkingEntry.charAt(j) == '-'
                        || WorkingEntry.charAt(j) == '*' || WorkingEntry.charAt(j) == '/')) {
                    comp2 = "Operator";
                    break;
                }
                //Or if its a number
                if ((j < WorkingEntry.length())
                        && (Character.isDigit(WorkingEntry.charAt(j)) == true)) {
                    comp2 = "Number";
                    break;
                }

                j++;
            }

//Error conditions                

            //Not a valid character
            if (NotValid == true) {
                System.out.println("\n" + "ERROR!! Not a valid character");
                System.out.println("At this point");
                System.out.println(WorkingEntry);
                while (q > 0) {
                    System.out.print(" ");
                    q--;
                }
                System.out.print("^" + "\n");
                return false;
            }

            //Operator next to parenthesis detected
            if (OpParen == true) {
                System.out.println("\n" + 
                        "ERROR!! Operator and parenthesis detected");
                System.out.println("At this point");
                System.out.println(WorkingEntry);
                while (z > 0) {
                    System.out.print(" ");
                    z--;
                }
                System.out.print("^" + "\n");
                return false;
            }

            //Two operators in succesion
            if (comp1 == "Operator" && comp2 == "Operator") {
                System.out.println("\n" + 
                        "ERROR!! Two Operators in a row detected!");
                System.out.println("At this point");
                System.out.println(WorkingEntry);
                while (j > 0) {
                    System.out.print(" ");
                    j--;
                }
                System.out.print("^" + "\n");
                return false;
            }

            //Two operands in succession 
            if (comp1 == "Number" && comp2 == "Number") {
                System.out.println("\n" + 
                        "ERROR!! Two Numbers in a row detected!");
                System.out.println("At this point");
                System.out.println(WorkingEntry);
                while (j > 0) {
                    System.out.print(" ");
                    j--;
                }
                System.out.print("^" + "\n");
                return false;
            }



        } //End of the For loop

        //Parentheses Mismatch
        if (!(Lparen == Rparen)) {
            System.out.println("\n" + "ERROR!! Parentheses mismatch!");
            System.out.println("At this point");
            System.out.println(WorkingEntry);
            while (z > 0) {
                System.out.print(" ");
                z--;
            }
            System.out.print("^" + "\n");
            return false;
        }
        return true;
    }
}
