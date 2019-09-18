package b3yond.items;

/**
 * Glass.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Glass is made from roasted sand
 */
public class Glass extends SmeltedMetals {

	private int stack;
	
	/**
	 * Glass.java
	 * Constructor for Glass
	 * @param stack size
	 * @return none
	 */
	public Glass(int stack){
		this.setStack(stack);
	} // End Glass constructor

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
	 * Returns an exact copy of Glass
	 * @param none
	 * @return Glass
	 */
    public Glass deepCopy() {
    	return new Glass(this.getStack());
	} // End deepCopy method

} // End Glass class
