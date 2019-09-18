package b3yond.items;

/**
 * KannaiteSlurry.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * KannaiteSlurry is made from Crushed Kannaite in a Leaching Plant
 */
public class KannaiteSlurry extends ResourceProcessing {

	private int stack;
	
	/**
	 * KannaiteSlurry.java
	 * Constructor for KannaiteSlurry
	 * @param stack size
	 * @return none
	 */
	public KannaiteSlurry(int stack){
		this.setStack(stack);
	} // End KannaiteSlurry constructor

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
	 * Returns an exact copy of KannaiteSlurry
	 * @param none
	 * @return KannaiteSlurry
	 */
    public KannaiteSlurry deepCopy() {
    	return new KannaiteSlurry(this.getStack());
	} // End deepCopy method
	
} // End KannaiteSlurry class
