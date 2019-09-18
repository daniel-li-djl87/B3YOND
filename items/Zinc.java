package b3yond.items;

/**
 * Zinc.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Zinc is made from roasted ZincOre
 */
public class Zinc extends SmeltedMetals {

	private int stack;
	
	/**
	 * Zinc.java
	 * Constructor for Zinc
	 * @param stack size
	 * @return none
	 */
	public Zinc(int stack){
		this.setStack(stack);
	} // End Zinc constructor

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
	 * Returns an exact copy of Zinc
	 * @param none
	 * @return Zinc
	 */
    public Zinc deepCopy() {
    	return new Zinc(this.getStack());
    } // End deepCopy method

} // End Zinc class
