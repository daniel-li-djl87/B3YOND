package b3yond.items;

/**
 * Water.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Water is a fluid found on the map
 */
public class Water extends Fluid {

	private int stack;
	
	/**
	 * Water.java
	 * Constructor for Water
	 * @param stack size
	 * @return none
	 */
	public Water(int stack){
		this.setStack(stack);
	} // End Water constructor

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
	 * Returns an exact copy of Water
	 * @param none
	 * @return Water
	 */
    public Water deepCopy() {
    	return new Water(this.getStack());
    } // End deepCopy method

} // End Water class
