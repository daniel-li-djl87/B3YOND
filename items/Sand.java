package b3yond.items;

/**
 * Sand.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Sand is made from crushed stone
 */
public class Sand extends PreSmeltingOres {

	private int stack;
	
	/**
	 * Sand.java
	 * Constructor for Sand
	 * @param stack size
	 * @return none
	 */
	public Sand(int stack){
		this.setStack(stack);
	} // End Sand constructor

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
	 * Returns an exact copy of Sand
	 * @param none
	 * @return Sand
	 */
    public Sand deepCopy() {
    	return new Sand(this.getStack());
	} // End deepCopy method

} // End Sand class
