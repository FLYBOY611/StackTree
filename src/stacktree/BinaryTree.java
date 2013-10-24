
package stacktree;


public class BinaryTree<T>// implements BinaryTreeADT<T>
{

    
    Node root;
    BinaryTree Oak = new BinaryTree();

    public BinaryTree() {
    }

//Return a reference to the  root element
//This should probably be generic....but I'm not sure how to put that in 
//Public T?
    public Object getRootElement(BinaryTree WorkTree) {

        return WorkTree.root.Element;
    }

//Returns true if this binary tree is empty and false otherwise
    public boolean isEmpty(BinaryTree WorkTree) {

        //If the root is empty then the tree is empty
        if (WorkTree.root.Element == null) {
            return true;
        } else {
            return false;
        }
    }

//Returns the number of elements in this binary tree
//I really feel like cheating and just returning the number of elements
//in the postfix expression....
    public int size(Node current) {
        int NumEle = 0;
        //Node current = GoNode;
 
     
        //Just use the recursive method of moving through the tree to 
        //count the number of elements

        
        //Recursive call to go down the left sides and print stuff out
        if(current.LeftChild != null){            
            //We need to call recursion on the LeftChild...but can't
            //current.LeftChild.size(GoNode.LeftChild);
            //I think this works?
            NumEle++;
            size(current.LeftChild);
        }
        NumEle++;
        
        //Recursive call to go down the right sides and print stuff out
        if(current.RightChild != null){
            NumEle++;
            size(current.RightChild);
            
        }
        
        return NumEle;
    }

    
/*
 //Returns true if the binary tree contains an element that 
 //matches the specified element and false otherwise
 //Similar deal as before, we need to traverse the tree with recursion and 
 //return true if it finds what it's looking for
    public boolean contains(T targetElement){

     
   
 }

     //Returns a reference to the specified element if it is found in this binary tree. 
     //Throws an exception if the specified element is not found.
     public T find(T targBetElement{

     }

     * */
//Returns a string representation of this binary tree
    public String toString() {

//System.out.println(root.toString());
        return root.toString();
    }

    /*
     //Returns an iterator over the elements of this tree
     public Iterator<T> iterator(){

     }

     //Returns an iterator that represents an inorder traversal on this binary tree
     public Iterator<T> iteratorInOrder(){

     }

     //Returns an iterator that represents an postorder traversal on this binary tree
     public Iterator<T> iteratorPreOrder(){

     }

     //Returns an iterator that represents an PostOrder traversal on this binary tree
     public Iterator<T> iteratorPostOrder(){

     }

     //Returns an iterator that represents an LevelOrder traversal on this binary tree
     public Iterator<T> iteratorLevelOrder(){

     }
     * 
     * */
}