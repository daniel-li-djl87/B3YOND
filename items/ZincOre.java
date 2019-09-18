package b3yond.items;

/**
 * ZincOre.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * ZincOre is made from crushed minerals
 */
public class ZincOre extends PreSmeltingOres {

	private int stack;
	
	/**
	 * ZincOre.java
	 * Constructor for ZincOre
	 * @param stack size
	 * @return none
	 */
	public ZincOre(int stack){
		this.setStack(stack);
	} // End ZincOre constructor

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
	 * Returns an exact copy of ZincOre
	 * @param none
	 * @return ZincOre
	 */
    public ZincOre deepCopy() {
    	return new ZincOre(this.getStack());
    } // End deepCopy method


} // End ZincOre class
