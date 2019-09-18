package b3yond.items;

/**
 * Tree.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Tree used for wood
 */
public class Tree extends Burnable {

	private int stack;
	
	/**
	 * Tree.java
	 * Constructor for Tree
	 * @param stack size
	 * @return none
	 */
	public Tree(int stack){
		this.setStack(stack);
	} // End Tree constructor

	/**
	 * @return the stack
	 */
	public int getStack() {
		return stack;
	}

	/**
	 * @param stack the stack to set
	 */
	public void setStack(int stack) {
		this.stack = stack;
	}
	
	/**
	 * deepCopy
	 * Returns an exact copy of Tree
	 * @param none
	 * @return Tree
	 */
    public Tree deepCopy() {
    	return new Tree(this.getStack());
    } // End deepCopy method

} // End Tree class
