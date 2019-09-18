package b3yond.items;

/**
 * Sapling.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Sapling used to grow trees
 */
public class Sapling extends Burnable {

	private int stack;
	
	/**
	 * Sapling.java
	 * Constructor for Sapling
	 * @param stack size
	 * @return none
	 */
	public Sapling(int stack){
		this.setStack(stack);
	} // End Sapling constructor

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
	 * Returns an exact copy of Sapling
	 * @param none
	 * @return Sapling
	 */
    public Sapling deepCopy() {
    	return new Sapling(this.getStack());
	} // End deepCopy method

} // End Sapling class
