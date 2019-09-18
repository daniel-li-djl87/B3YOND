package b3yond.items;

/**
 * NickelOre.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * NickelOre is made from crushed minerals
 */
public class NickelOre extends PreSmeltingOres {

	private int stack;
	
	/**
	 * NickelOre.java
	 * Constructor for NickelOre
	 * @param stack size
	 * @return none
	 */
	public NickelOre(int stack){
		this.setStack(stack);
	} // End NickelOre constructor

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
	 * Returns an exact copy of NickelOre
	 * @param none
	 * @return NickelOre
	 */
    public NickelOre deepCopy() {
    	return new NickelOre(this.getStack());
	} // End deepCopy method

} // End NickelOre class
