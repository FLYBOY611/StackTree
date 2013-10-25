/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacktree;

/**
 *
 * @author Richard
 */
public class Node<T> {
    public T Element;
    public Node LeftChild;
    public Node RightChild;
    
    
    //Returns a string representation of this binary tree
    public String toString(){
        //NOT a recursive call. Actually just a toString
        String str = ""; 
        str += "(";
        
        
        //Recursive call to go down the left sides and print stuff out
        if(LeftChild != null){
            str += LeftChild.toString();
             
        }
        str += Element.toString();
        
        //Recursive call to go down the right sides and print stuff out
        if(RightChild != null){
            str += RightChild.toString();
            
        }
        str += ")";
        
        
        return str;
    }
    
    //Constructor for operators that have children
    public Node(T InElement, Node Left, Node Right){
        Element = InElement;
        LeftChild = Left;
        RightChild = Right;
    }
    
    //Default Constructor if it has not left, right child
    public Node(T InElement){
        Element = InElement;
        LeftChild = null;
        RightChild = null;
    }
}