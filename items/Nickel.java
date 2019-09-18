package b3yond.items;

/**
 * Nickel.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Nickel is made from roasted NickelOre
 */
public class Nickel extends SmeltedMetals {

	private int stack;
	
	/**
	 * Nickel.java
	 * Constructor for Nickel
	 * @param stack size
	 * @return none
	 */
	public Nickel(int stack){
		this.setStack(stack);
	} // End Nickel constructor

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
	 * Returns an exact copy of Nickel
	 * @param none
	 * @return Nickel
	 */
    public Nickel deepCopy() {
    	return new Nickel(this.getStack());
	} // End deepCopy method

} // End Nickel class
