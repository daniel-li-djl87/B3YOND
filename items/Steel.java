package b3yond.items;

/**
 * Steel.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Steel is smelted from iron
 */
public class Steel extends SmeltedMetals {
	
	private int stack;
	
	/**
	 * Steel
	 * Constructor for Steel
	 * @param stack size
	 * @return none
	 */
	public Steel(int stack) {
		this.stack = stack;
	} // End Steel constructor

	@Override
	public int getStack() {
		return this.stack;
	}

	@Override
	public void setStack(int stack) {
		this.stack = stack;

	}
	
	/**
	 * deepCopy
	 * Returns an exact copy of Steel
	 * @param none
	 * @return Steel
	 */
    public Steel deepCopy() {
    	return new Steel(this.getStack());
    } // End deepCopy method

} // End Steel class
