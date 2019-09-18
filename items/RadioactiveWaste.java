package b3yond.items;

/**
 * RadioactiveWaste.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * RadioactiveWaste is made my making nuclear energy
 * 
 */
public class RadioactiveWaste extends Nuclear {

	private int stack;
	
	/**
	 * RadioactiveWaste.java
	 * Constructor for RadioactiveWaste
	 * @param stack size
	 * @return none
	 */
	public RadioactiveWaste(int stack){
		this.setStack(stack);
	} // End RadioactiveWaste constructor

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
	 * Returns an exact copy of RadioactiveWaste
	 * @param none
	 * @return RadioactiveWaste
	 */
    public RadioactiveWaste deepCopy() {
    	return new RadioactiveWaste(this.getStack());
	} // End deepCopy method
	
} // End RadioactiveWaste class
