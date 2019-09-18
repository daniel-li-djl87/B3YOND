package b3yond.items;

/**
 * Sulphur.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Sulphur is made from oil
 */
public class Sulphur extends OilProducts {

	private int stack;
	
	/**
	 * Sulphur.java
	 * Constructor for Sulphur
	 * @param stack size
	 * @return none
	 */
	public Sulphur(int stack){
		this.setStack(stack);
	} // End Sulphur constructor

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
	 * Returns an exact copy of Sulphur
	 * @param none
	 * @return Sulphur
	 */
    public Sulphur deepCopy() {
    	return new Sulphur(this.getStack());
    } // End deepCopy method

} // End Sulphur class
