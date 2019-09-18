package b3yond.items;

/**
 * Item.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Superclass for items
 */
abstract public class Item {
	
	private int stack;
	
	/**
	 * Item
	 * Constructor for Item
	 * @param none
	 * @return none
	 */
	public Item() {
		
	} // End Item constructor
	
	public abstract int getStack();
	
	public abstract void setStack(int stack);
	
    public abstract Item deepCopy();

	
} // End Item class
