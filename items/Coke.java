package b3yond.items;

/**
 * Coke.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Coke is used to fuel blast furnaces 
 */
public class Coke extends Burnable {

	private int stack;
		
	/**
	 * Coke.java
	 * Constructor for Coke
	 * @param stack size
	 * @return none
	 */
	public Coke(int stack){
		this.setStack(stack);
	} // End Coke constructor

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
	 * Returns an exact copy of Coke
	 * @param none
	 * @return Coal
	 */
    public Coke deepCopy() {
    	return new Coke(this.getStack());
    } // End deepCopy method

} // End Coke class
