package b3yond.items;

/**
 * HematiteOre.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * HematiteOre is made from crushed minerals
 */
public class HematiteOre extends PreSmeltingOres {

	private int stack;
	
	/**
	 * HematiteOre.java
	 * Constructor for HematiteOre
	 * @param stack size
	 * @return none
	 */
	public HematiteOre(int stack){
		this.setStack(stack);
	} // End HematiteOre instructor

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
	 * Returns an exact copy of HematiteOre
	 * @param none
	 * @return HematiteOre
	 */
    public HematiteOre deepCopy() {
    	return new HematiteOre(this.getStack());
	} // End deepCopy method

} // End HematiteOre class
