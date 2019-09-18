package b3yond.items;

/**
 * Wood.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Wood cut from trees as fuel
 */
public class Wood extends Burnable {
	
	private int stack;
	
	/**
	 * Wood
	 * Constructor for Wood
	 * @param stack size
	 * @return none
	 */
	public Wood(int stack){
		this.setStack(stack);
	} // End Wood constructor

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
	 * Returns an exact copy of Wood
	 * @param none
	 * @return Wood
	 */
    public Wood deepCopy() {
    	return new Wood(this.getStack());
    } // End deepCopy method
    
} // End Wood class