package b3yond.items;

/**
 * MoltenAluminum.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * MoltenAluminum is made from bauxite ore in a blast furnace
 */
public class MoltenAluminum extends ResourceProcessing {

	private int stack;
	
	/**
	 * MoltenAluminum.java
	 * Constructor for MoltenAluminum
	 * @param stack size
	 * @return none
	 */
	public MoltenAluminum(int stack){
		this.setStack(stack);
	} // End MoltenAluminum constructor

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
	 * Returns an exact copy of MoltenAluminum
	 * @param none
	 * @return MoltenAluminum
	 */
    public MoltenAluminum deepCopy() {
    	return new MoltenAluminum(this.getStack());
	} // End deepCopy method

} // End MoltenAluminum class
