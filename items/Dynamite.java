package b3yond.items;

/**
 * Dynamite.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Dynamite is made from oil
 */
public class Dynamite extends OilProducts {

	private int stack;
	
	/**
	 * Dynamite.java
	 * Constructor for Dynamite
	 * @param stack size
	 * @return none
	 */
	public Dynamite(int stack){
		this.setStack(stack);
	} // End Dynamite constructor

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
	 * Returns an exact copy of Dynamite
	 * @param none
	 * @return Dynamite
	 */
    public Dynamite deepCopy() {
    	return new Dynamite(this.getStack());
	} // End deepCopy method

} // End Dynamite class
