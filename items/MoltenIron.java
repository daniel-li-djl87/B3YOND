package b3yond.items;

/**
 * MoltenIron.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * MoltenIron is made from hematite ore in a blast furnace
 */
public class MoltenIron extends ResourceProcessing {

	private int stack;
	
	/**
	 * MoltenIron.java
	 * Constructor for MoltenIron
	 * @param stack size
	 * @return none
	 */
	public MoltenIron(int stack){
		this.setStack(stack);
	} // End MoltenIron constructor

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
	 * Returns an exact copy of MoltenIron
	 * @param none
	 * @return MoltenIron
	 */
    public MoltenIron deepCopy() {
    	return new MoltenIron(this.getStack());
	} // End deepCopy method

} // End MoltenIron class
