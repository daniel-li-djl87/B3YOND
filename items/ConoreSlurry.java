package b3yond.items;

/**
 * ConoreSlurry.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * ConoreSlurry is made from Crushed Conore in a Leaching Plant
 */
public class ConoreSlurry extends ResourceProcessing {

	private int stack;
	
	/**
	 * ConoreSlurry.java
	 * Constructor for ConoreSlurry
	 * @param stack size
	 * @return none
	 */
	public ConoreSlurry(int stack){
		this.setStack(stack);
	} // End ConoreSlurry constructor

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
	 * Returns an exact copy of ConoreSlurry
	 * @param none
	 * @return ConoreSlurry
	 */
    public ConoreSlurry deepCopy() {
    	return new ConoreSlurry(this.getStack());
    } // End deepCopy method

} // End ConoreSlurry class
