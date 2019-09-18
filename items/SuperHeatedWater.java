package b3yond.items;

/**
 * SuperHeatedWater.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * SuperHeatedWater is water made from Nuclear energy
 */
public class SuperHeatedWater extends Fluid {

	private int stack;
	
	/**
	 * SuperHeatedWater.java
	 * Constructor for SuperHeatedWater
	 * @param stack size
	 * @return none
	 */
	public SuperHeatedWater(int stack){
		this.setStack(stack);
	} // End SuperHeatedWater constructor

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
	 * Returns an exact copy of SuperHeatedWater
	 * @param none
	 * @return SuperHeatedWater
	 */
    public SuperHeatedWater deepCopy() {
    	return new SuperHeatedWater(this.getStack());
    } // End deepCopy method


} // End SuperHeatedWater class
