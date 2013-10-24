/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacktree;
import java.util.Iterator;

/**
 *
 * @author Richard
 */
public interface BinaryTreeADT<T> {

//Return a reference to the  root element
public T getRootElement();

//Returns true if this binary tree is empty and false otherwise
public boolean  isEmpty();

//Returns the number of elements in this binary tree
public int size();

//Returns true if the binary tree contains an element that 
//matches the specified element and false otherwise
public boolean contains(T targetElement);

//Returns a reference to the specified element if it is found in this binary tree. 
//Throws an exception if the specified element is not found.
public T find(T targBetElement);

//Returns a string representation of this binary tree
public String toString();

//Returns an iterator over the elements of this tree
public Iterator<T> iterator();

//Returns an iterator that represents an inorder traversal on this binary tree
public Iterator<T> iteratorInOrder();

//Returns an iterator that represents an postorder traversal on this binary tree
public Iterator<T> iteratorPreOrder();

//Returns an iterator that represents an PostOrder traversal on this binary tree
public Iterator<T> iteratorPostOrder();

//Returns an iterator that represents an LevelOrder traversal on this binary tree
public Iterator<T> iteratorLevelOrder();

}