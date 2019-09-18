package b3yond.items;

/**
 * RutileOre.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * RutileOre is made from crushed minerals
 */
public class RutileOre extends PreSmeltingOres {

	private int stack;
	
	/**
	 * RutileOre.java
	 * Constructor for RutileOre
	 * @param stack size
	 * @return none
	 */
	public RutileOre(int stack){
		this.setStack(stack);
	} // End RutileOre constructor

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
	 * Returns an exact copy of RutileOre
	 * @param none
	 * @return RutileOre
	 */
    public RutileOre deepCopy() {
    	return new RutileOre(this.getStack());
	} // End deepCopy method

} // End RutileOre class
