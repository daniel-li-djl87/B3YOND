package b3yond.items;

/**
 * CupriteOre.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * CupriteOre is made from crushed minerals
 */
public class CupriteOre extends PreSmeltingOres {

	private int stack;
	
	/**
	 * CupriteOre.java
	 * Constructor for CupriteOre
	 * @param stack size
	 * @return none
	 */
	public CupriteOre(int stack){
		this.setStack(stack);
	} // End CupriteOre constructor

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
	 * Returns an exact copy of CupriteOre
	 * @param none
	 * @return CupriteOre
	 */
    public CupriteOre deepCopy() {
    	return new CupriteOre(this.getStack());
	} // End deepCopy method

} // End CupriteOre class
