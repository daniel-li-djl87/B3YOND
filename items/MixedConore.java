package b3yond.items;

/**
 * MixedConore.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * MixedConore is made from Conore Slurry in a sedimentation plant
 */
public class MixedConore extends ResourceProcessing {

	private int stack;
	
	/**
	 * MixedConore.java
	 * Constructor for MixedConore
	 * @param stack size
	 * @return none
	 */
	public MixedConore(int stack){
		this.setStack(stack);
	} // End MixedConore constructor

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
	 * Returns an exact copy of MixedConore
	 * @param none
	 * @return MixedConore
	 */
    public MixedConore deepCopy() {
    	return new MixedConore(this.getStack());
	} // End deepCopy method

} // End MixedConore class
