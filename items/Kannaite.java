package b3yond.items;

/**
 * Kannaite.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Kannaite is a naturally spawning mineral providing copper and other ores 
 */
public class Kannaite extends Mineral {

	private int stack;
	
	/**
	 * Kannaite.java
	 * Constructor for Kannaite
	 * @param stack size
	 * @return none
	 */
	public Kannaite(int stack){
		this.setStack(stack);
	} // End Kannaite constructor

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
	 * Returns an exact copy of Kannaite
	 * @param none
	 * @return Kannaite
	 */
    public Kannaite deepCopy() {
    	return new Kannaite(this.getStack());
	} // End deepCopy method

} // End Kannaite class
