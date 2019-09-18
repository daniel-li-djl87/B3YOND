package b3yond.items;

/**
 * MoltenCopper.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * MoltenCopper is made from cuprite ore in a blast furnace
 */
public class MoltenCopper extends ResourceProcessing {

	private int stack;
	
	/**
	 * MoltenCopper.java
	 * Constructor for MoltenCopper
	 * @param stack size
	 * @return none
	 */
	public MoltenCopper(int stack){
		this.setStack(stack);
	} // End MoltenCopper constructor

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
	 * Returns an exact copy of MoltenCopper
	 * @param none
	 * @return MoltenCopper
	 */
    public MoltenCopper deepCopy() {
    	return new MoltenCopper(this.getStack());
	} // End deepCopy method

} // End MoltenCopper class
