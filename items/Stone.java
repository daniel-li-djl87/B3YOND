package b3yond.items;

/**
 * Stone.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Stone is acquired from sorting Conore and Kannaite
 */
public class Stone extends PreSmeltingOres {

	private int stack;
	
	/**
	 * Stone.java
	 * Constructor for Stone
	 * @param stack size
	 * @return none
	 */
	public Stone(int stack){
		this.setStack(stack);
	} // End Stone constructor

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
	 * Returns an exact copy of Stone
	 * @param none
	 * @return Stone
	 */
    public Stone deepCopy() {
    	return new Stone(this.getStack());
    } // End deepCopy method

} // End Stone class
