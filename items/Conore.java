package b3yond.items;

/**
 * Conore.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Conore is a naturally spawning mineral providing iron and other ores
 */
public class Conore extends Mineral {

	private int stack;
	
	/**
	 * Conore.java
	 * Constructor for Conore
	 * @param stack size
	 * @return none
	 */
	public Conore(int stack){
		this.setStack(stack);
	} // End Conore constructor

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
	 * Returns an exact copy of Conore
	 * @param none
	 * @return Conore
	 */
    public Conore deepCopy() {
    	return new Conore(this.getStack());
    } // End deepCopy method
	
} // End Conore class
