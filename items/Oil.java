package b3yond.items;

/**
 * Oil.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Oil is a type of fluid used for energy
 */
public class Oil extends Fluid {

	private int stack;
	
	/**
	 * Oil.java
	 * Constructor for Oil
	 * @param stack size
	 * @return none
	 */
	public Oil(int stack){
		this.setStack(stack);
	} // End Oil constructor

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
	 * Returns an exact copy of Oil
	 * @param none
	 * @return Oil
	 */
    public Oil deepCopy() {
    	return new Oil(this.getStack());
	} // End deepCopy method

} // End Oil class
