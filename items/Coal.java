package b3yond.items;

/**
 * Coal.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Coal is used to fuel for furnaces
 */
public class Coal extends Burnable {
	
	private int stack;
	
	/**
	 * Coal
	 * Constructor for Coal
	 * @param stack size
	 * @return none
	 */
	public Coal(int stack){
		this.setStack(stack);
	} // End Coal constructor

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
	 * Returns an exact copy of Coal
	 * @param none
	 * @return Coal
	 */
    public Coal deepCopy() {
    	return new Coal(this.getStack());
    } // End deepCopy method


} // End Coal class
