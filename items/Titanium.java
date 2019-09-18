package b3yond.items;

/**
 * Titanium.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Titanium is made from roasted RutileOre
 */
public class Titanium extends SmeltedMetals {

	private int stack;
	
	/**
	 * Titanium.java
	 * Constructor for Titanium
	 * @param stack size
	 * @return none
	 */
	public Titanium(int stack){
		this.setStack(stack);
	} // End Titanium constructor

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
	 * Returns an exact copy of Titanium
	 * @param none
	 * @return Titanium
	 */
    public Titanium deepCopy() {
    	return new Titanium(this.getStack());
    } // End deepCopy method

} // End Titanium class
