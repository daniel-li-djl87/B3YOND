package b3yond.items;

/**
 * CrushedConore.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * CrushedConore is made in ore crusher
 */
public class CrushedConore extends ResourceProcessing {

	private int stack;
	
	/**
	 * CrushedConore.java
	 * Constructor for CrushedConore
	 * @param stack size
	 * @return none
	 */
	public CrushedConore(int stack){
		this.setStack(stack);
	} // End CrushedConore constructor

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
	 * Returns an exact copy of CrushedConore
	 * @param none
	 * @return CrushedConore
	 */
    public CrushedConore deepCopy() {
    	return new CrushedConore(this.getStack());
    } // End deepCopy method

} // End CrushedConore class
