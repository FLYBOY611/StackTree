package stacktree;

public class BinaryTree<T>// implements BinaryTreeADT<T>
{

    Node root;
    Node LeftChild;
    Node RightChild;

    //We put this in for good luck
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
        if (WorkTree.root == null) {
            return true;
        } else {
            return false;
        }
    }

//Returns the number of elements in this binary tree
//We need to traverse the tree with recursion to count it
//We start at the root but don't let that fool you
//every recursive call in the tree will be named root, so even
//if we are three levels down it's still called "root"
    public int size(Node root) {

        //If the node is empty, return size zero
        if (root == null) {
            return 0;
        } //If it's the node and nothing else, its size one.
        else if (root.LeftChild == null
                & root.RightChild == null) {
            return 1;
        } //Here it gets crazy
        //Is there a left child? Return 1 if its a leaf or keep going 
        //a level deeper if that node has children. Then we add one to 
        //account for the original root note and check for the right path
        else {
            return size(root.LeftChild) + 1 + size(root.RightChild);
        }




    }

    //Returns true if the binary tree contains an element that 
    //matches the specified element and false otherwise
    //Similar deal as before, we need to traverse the tree with recursion and 
    //return true if it finds what it's looking for
    public boolean contains(T targetElement, Node currentNode) {
        
        
        if (currentNode.Element.equals(targetElement)) {
            return true;
        } 
        
        if (currentNode.LeftChild != null) {
            if (contains(targetElement, currentNode.LeftChild) == true){
                return true;
            }           
        } 
        
        if (currentNode.RightChild != null) {
            return contains(targetElement, currentNode.RightChild);
        } 
        return false;


    }

   
    /*
     //Returns a reference to the specified element if it is found in this binary tree. 
     //Throws an exception if the specified element is not found.
     public T find(T targBetElement{

     }
     
     
    
     */
//Returns a string representation of this binary tree
    public String toString() {


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