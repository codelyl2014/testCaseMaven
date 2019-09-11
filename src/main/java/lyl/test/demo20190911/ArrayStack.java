package lyl.test.demo20190911;


import java.util.*;

/**
 * @author loong
 *
 */
public class ArrayStack<E> extends Stack<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  List<E> array = new ArrayList<>();

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public E push(E item) {
        addElement(item);
        return item;
    }
    
    @Override
	public synchronized E pop() {
        E       obj;
        int     len = size();
        obj = peek();
        removeElementAt(len - 1);
        return obj;
    }

}
