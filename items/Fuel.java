package b3yond.items;

/**
 * Fuel.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Fuel is a naturally occurring resource that provides coal and uranium
 */
public class Fuel extends Mineral {

	private int stack;
	
	/**
	 * Fuel.java
	 * Constructor for Fuel
	 * @param stack size
	 * @return none
	 */
	public Fuel(int stack){
		this.setStack(stack);
	} // End Fuel constructor

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
	 * Returns an exact copy of Fuel
	 * @param none
	 * @return Fuel
	 */
    public Fuel deepCopy() {
    	return new Fuel(this.getStack());
	} // End deepCopy method

} // End Fuel class
