package b3yond.items;

/**
 * Copper.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Copper is made from roasted CupriteOre
 */
public class Copper extends SmeltedMetals {

	private int stack;
	
	/**
	 * Copper.java
	 * Constructor for Copper
	 * @param stack size
	 * @return none
	 */
	public Copper(int stack){
		this.setStack(stack);
	} // End Copper constructor

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
	 * Returns an exact copy of Copper
	 * @param none
	 * @return Copper
	 */
    public Copper deepCopy() {
    	return new Copper(this.getStack());
    } // End deepCopy method

} // End Copper class
