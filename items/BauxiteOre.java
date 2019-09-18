package b3yond.items;

/**
 * BauxiteOre.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * BauxiteOre is made from crushed minerals
 */
public class BauxiteOre extends PreSmeltingOres {

	private int stack;
	
	/**
	 * BauxiteOre.java
	 * Constructor for BauxiteOre
	 * @param stack size
	 * @return none
	 */
	public BauxiteOre(int stack){
		this.setStack(stack);
	} // End BauxiteOre constructor

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
	 * Returns an exact copy of BauxiteOre
	 * @param none
	 * @return BauxiteOre
	 */
    public BauxiteOre deepCopy() {
    	return new BauxiteOre(this.getStack());
    } // End deepCopy method

} // End BauxiteOre class
