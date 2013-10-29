/*
 * ***************************************************************************
 * Module Name: StackTest
* 
* Module Description: Takes an infix expression and converts it to a postfix
 expression while also outputting the evaluation. 
 * Also includes error checking
 * 
* @Param:
 * 
* @Return:
 * 
*
 ****************************************************************************
 */
package stacktree;

import java.util.Scanner;

/**
 *
 * @author Richard
 */
public class StackTree {

    public static void main(String[] args) {

        Node rooty = new Node("-");


        Node lefty = new Node("*");
        Node righty = new Node(2);

        Node superLefty = new Node(3);
        Node superRighty = new Node(6);

        lefty.LeftChild = superLefty;
        lefty.RightChild = superRighty;

        rooty.LeftChild = lefty;
        rooty.RightChild = righty;

        BinaryTree Cedar = new BinaryTree();
        Cedar.root = rooty;

        //Check the toString
        System.out.println(Cedar.toString());
        //Check the size
        System.out.println(Cedar.size(Cedar.root));
        //Check the contains
        if (Cedar.contains(6, Cedar.root) == true) {
            System.out.println("true");
        }
        /*
         String entry = "0";


         //Start the scanner and enter the first line
         System.out.println("Welcome to the stack program!");

         //Keep running so long as an empty expression isn't given
         while (!entry.equals("")) {
         System.out.println("\n" + "Please enter in characters, "
         + "enter an empty expression to end");
         Scanner input = new Scanner(System.in);
         entry = input.nextLine();
         boolean ErrorFree = true;

         //Check for errors in the statement
         ErrorFree = ErrorCheck(entry);
         if (ErrorFree == true) {

         PostfixAndAnswer GrandFinale = InfixToPost(entry);

         System.out.println("The original infix is: " + entry);
         System.out.println("The postfix is:" + GrandFinale.postfix);
         System.out.print("The evaluation is " + GrandFinale.answer + "\n");
         }
         }

         */
    }

    public static PostfixAndAnswer InfixToPost(String InfixExpression) {

        ListStack Listo = new ListStack();
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
                    || WorkingEntry.charAt(i) == '*' || WorkingEntry.charAt(i) == '/') {
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

//EVALUATION CODE

        /*
         WHAT I NEED TO DO:
         * Turn the postfix expression into a binary tree
         * evaluate that binary tree to give the answer
         * 
         * Create some sort of ADT and constructor to build Nodes
         * Nodes will have left and right child along with an elemental value
         * Leafs will be Nodes that have a null left and right value
         * The Nodes will be held in a stack???
         *  
         * Numbers are always leaves, operators are always nodes
         * Each node has a maximum of two leaves. Binary tree.
         * Whenever we hit a operator it means we move up a level? Or not? 
         * 23*45/-
         * Becomes       -
         *            *    /
         *           2 3  4 5
        
         * To make the tree, the first two numbers become leaves
         * and the next operator becomes their parent node
         * if the next character is a operator it becomes a parent
         * 
         * The code to determine the number of digits in a number is rock solid
         * leave it in place for the tree
         */

 
    
    /*
 //PLEASE IGNORE THIS CODE FOR NOW   
    //Scroll throught the postfix, left to right
    for (int j = 0; j< postfix.length(); j++) {
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
            //Then push it onto the stack
            Listo.push(tempdigit);

        }

    }  */
    
        //BuildATree(postfix);
        
    PostfixAndAnswer output = new PostfixAndAnswer();
    output.postfix  = postfix;
    output.answer  = Listo.pop();
    return output ;
}
    
    
    
    
    
//End of Main
    
    //HOW WE WILL DO THIS
    //
    //If it is a number, turn it into a leaf node and push it on the stack.
    //If it is an operator, pop two items from the stack, construct an operator 
    //node with those children, and push all of it onto the stack.
    //At the end you have exactly one tree on the stack
    public Node BuildATree(String postfix){
        int flicker = 1;
        String previousNodeDetect = null;
        Node previousOpNode = new Node(null);
        Node SuperPreviousOpNode = new Node(null);
        Node NumNode1 = new Node(null);
        Node NumNode2 = new Node(null);
        StackForTrees treebuild = new StackForTrees();
        
        //Scroll throught the postfix, left to right
        for (int j = 0; j< postfix.length(); j++) {
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
                 
                //Signal that the previous node was an operator
                previousNodeDetect = "num";
            
            }
           //If it's an operator
           if (tempdigit == "+" || tempdigit == "-"
                || tempdigit == "*" || tempdigit == "/"){       
               
               //Make a new node, 
               //Pop two elements from the stack and attach them
               //Push all of that back onto the stack
               Node OpNode = new Node(tempdigit);             
               OpNode.LeftChild = treebuild.pop();
               OpNode.RightChild = treebuild.pop();
               treebuild.push(OpNode);
              /* 
               
               //If the previous node was an operator
               //We link the two previous operator nodes together
               if(previousNodeDetect == "op"){
                   OpNode.LeftChild = SuperPreviousOpNode;
                   OpNode.RightChild = previousOpNode;            
               }
               //If the previous node was a number
               //We link the previous operator node and last number node
               else if(previousNodeDetect == "num"){
                   OpNode.LeftChild = previousOpNode;
                     if(flicker == -1){
                     OpNode.RightChild = NumNode1;
                     }else{
                     OpNode.RightChild = NumNode2;
                 }
               }
               
               */
         
           } 
        }
        //At the end of the process we are left with one "node"
        //on the stack which is our entire tree
        return treebuild.pop();
    }
    
    //We use recursion to evaluate every node the tree has
    //I need to figure out what type temp is that it can hold num or char
    public int evaluateNode(Node root) {
                int result;
                int Num1;
                int Num2;
                char temp;
                //DECIDE WHAT TYPE WE USE

                if (root == null) {
                    result = 0;
                } else {
                    temp = (char)root.Element;
                    //Does this correctly cast?

                    //If it's an operator
                    if (temp == '+' || temp == '-'
                            || temp == '*' || temp == '/') {

                        Num1 = evaluateNode(root.LeftChild);
                        Num2 = evaluateNode(root.RightChild);
                        result = computeTerm(temp, Num1, Num2);
                    } else{
                        //I THINK WE'LL NEED A CAST HERE?
                        result = (int)temp;
                    }
                }
                return result;
            }
    
    //This is what actually calculates the answers 
    public int computeTerm(char operator, int Num1, int Num2){
        int result = 0;
        
        if (operator == '+'){
            result = Num1 + Num2;
        }
        else if (operator == '-'){
            result = Num1 - Num2;
        }
        else if (operator == '*'){
            result = Num1 * Num2;
        }
        else if (operator == '/'){
            result = Num1 / Num2;
        }
        
        return result;
    }    
    
    
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
                if ((i + 1 < WorkingEntry.length()) && (Character.isDigit(WorkingEntry.charAt(i + 1)) == true)) {
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
                System.out.println("\n" + "ERROR!! Operator and parenthesis detected");
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
                System.out.println("\n" + "ERROR!! Two Operators in a row detected!");
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
                System.out.println("\n" + "ERROR!! Two Numbers in a row detected!");
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
