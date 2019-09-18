package b3yond.items;

/**
 * Aluminum.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Aluminum is made from roasted BauxiteOre
 */
public class Aluminum extends SmeltedMetals {

	private int stack;
	
	/**
	 * Aluminum.java
	 * Constructor for Aluminum
	 * @param stack size
	 * @return none
	 */
	public Aluminum(int stack){
		this.setStack(stack);
	} // End Aluminum constructor

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
	 * Returns an exact copy of Aluminum
	 * @param none
	 * @return Aluminum
	 */
    public Aluminum deepCopy() {
    	return new Aluminum(this.getStack());
    } // End deepCopy method

} // End Aluminum class
