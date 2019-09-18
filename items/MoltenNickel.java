package b3yond.items;

/**
 * MoltenNickel.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * MoltenNickel is made from nickel ore in a blast furnace
 */
public class MoltenNickel extends ResourceProcessing {

	private int stack;
	
	/**
	 * MoltenNickel.java
	 * Constructor for MoltenNickel
	 * @param stack size
	 * @return none
	 */
	public MoltenNickel(int stack){
		this.setStack(stack);
	} // End MoltenNickel constructor

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
	 * Returns an exact copy of MoltenNickel
	 * @param none
	 * @return MoltenNickel
	 */
    public MoltenNickel deepCopy() {
    	return new MoltenNickel(this.getStack());
	} // End deepCopy method

} // End MoltenNickel class
