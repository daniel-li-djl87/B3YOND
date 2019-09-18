package b3yond.items;

/**
 * MoltenZinc.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * MoltenZinc is made from zinc ore in a blast furnace
 */
public class MoltenZinc extends ResourceProcessing {

	private int stack;
	
	/**
	 * MoltenZinc.java
	 * Constructor for MoltenZinc
	 * @param stack size
	 * @return none
	 */
	public MoltenZinc(int stack){
		this.setStack(stack);
	} // End MoltenZinc constructor

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
	 * Returns an exact copy of MoltenZinc
	 * @param none
	 * @return MoltenZinc
	 */
    public MoltenZinc deepCopy() {
    	return new MoltenZinc(this.getStack());
	} // End deepCopy method

} // End MoltenZinc class
