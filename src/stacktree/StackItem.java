/*******************************************************************************
* Module Name: StackItem										
*												
* Module Description: Creates the format of the items that go into our list
*												
* @Param:											
*												
* @Return:                      									
*												
*													
*******************************************************************************/
package stacktree;

/**
 *
 * @author Richard
 */
public class StackItem<T> {
    public T x;
    public StackItem next;
    
    
    public StackItem(T init_x){
        x = init_x;
    }
    public StackItem(T init_x, StackItem init_next){
        x = init_x;
        next = init_next;
    }
}
