package b3yond.items;

/**
 * Iron.java
 * @author Naymul Mohammed
 * @date Dec 14, 2017
 * Iron is made from roasted HematiteOre
 */
public class Iron extends SmeltedMetals {

	private int stack;
	
	/**
	 * Iron.java
	 * Constructor for Iron
	 * @param stack size
	 * @return none
	 */
	public Iron(int stack){
		this.setStack(stack);
	} // End Iron constructor

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
	
    public  Iron deepCopy() {
    	return new Iron(this.getStack());
    }


} // End Iron class
