package b3yond.items;

/**
 * Petroleum.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Petroleum is made from oil
 */
public class Petroleum extends OilProducts {

	private int stack;
	
	/**
	 * Petroleum.java
	 * Constructor for Petroleum
	 * @param stack size
	 * @return none
	 */
	public Petroleum(int stack){
		this.setStack(stack);
	} // End Petroleum constructor

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
	 * Returns an exact copy of Petroleum
	 * @param none
	 * @return Petroleum
	 */
    public Petroleum deepCopy() {
    	return new Petroleum(this.getStack());
	} // End deepCopy method

} // End Petroleum class
