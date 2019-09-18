package b3yond.items;

/**
 * Uranium.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Uranium is found from mining fuel
 * 
 */
public class Uranium extends Nuclear {

	private int stack;
	
	/**
	 * Uranium.java
	 * Constructor for Uranium
	 * @param stack size
	 * @return none
	 */
	public Uranium(int stack){
		this.setStack(stack);
	} // End Uranium constructor

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
	 * Returns an exact copy of Uranium
	 * @param none
	 * @return Uranium
	 */
    public Uranium deepCopy() {
    	return new Uranium(this.getStack());
    } // End deepCopy method
	
} // End Uranium class
