package b3yond.items;

/**
 * Lithium.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Lithium is made from the ore sorter
 */
public class Lithium extends SmeltedMetals {

	private int stack;
	
	/**
	 * Lithium.java
	 * Constructor for Lithium
	 * @param stack size
	 * @return none
	 */
	public Lithium(int stack){
		this.setStack(stack);
	} // End Lithium constructor

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
	 * Returns an exact copy of Lithium
	 * @param none
	 * @return Lithium
	 */
    public Lithium deepCopy() {
    	return new Lithium(this.getStack());
	} // End deepCopy method

} // End Lithium class
