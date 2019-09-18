package b3yond.items;

/**
 * Steam.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Steam is a fluid made from boiling water
 */
public class Steam extends Fluid {

	private int stack;
	
	/**
	 * Steam.java
	 * Constructor for Steam
	 * @param stack size
	 * @return none
	 */
	public Steam(int stack){
		this.setStack(stack);
	} // End Steam constructor

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
	 * Returns an exact copy of Steam
	 * @param none
	 * @return Steam
	 */
    public Steam deepCopy() {
    	return new Steam(this.getStack());
	} // End deepCopy method

} // End Steam class
