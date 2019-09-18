package b3yond.items;

/**
 * Plastic.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Plastic is made from oil
 */
public class Plastic extends OilProducts {

	private int stack;
	
	/**
	 * Plastic.java
	 * Constructor for Plastic
	 * @param stack size
	 * @return none
	 */
	public Plastic(int stack){
		this.setStack(stack);
	} // End Plastic constructor

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
	 * Returns an exact copy of Plastic
	 * @param none
	 * @return Plastic
	 */
    public Plastic deepCopy() {
    	return new Plastic(this.getStack());
	} // End deepCopy method

} // End Plastic class
