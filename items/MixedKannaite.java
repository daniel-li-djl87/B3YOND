package b3yond.items;

/**
 * MixedKannaite.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * MixedKannaite is made from Kannaite Slurry in a sedimentation plant
 */
public class MixedKannaite extends ResourceProcessing {

	private int stack;
	
	/**
	 * MixedKannaite.java
	 * Constructor for MixedKannaite
	 * @param stack size
	 * @return none
	 */
	public MixedKannaite(int stack){
		this.setStack(stack);
	} // End MixedKannaite constructor

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
	 * Returns an exact copy of MixedKannaite
	 * @param none
	 * @return MixedKannaite
	 */
    public MixedKannaite deepCopy() {
    	return new MixedKannaite(this.getStack());
	} // End deepCopy method

} // End MixedKannaite class
