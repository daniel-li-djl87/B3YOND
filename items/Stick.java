package b3yond.items;

/**
 * Stick.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Stick easter egg
 */
public class Stick extends Tool {

	/**
	 * Stick
	 * Constructor for Stick
	 * @param none
	 * @return none
	 */
	public Stick(){

	} // End Stick constructor

	@Override
	public int getStack() {
		return 1;
	}

	@Override
	public void setStack(int stack) {
	}
	
	/**
	 * deepCopy
	 * Do nothing
	 * @param none
	 * @return null
	 */
    public Stick deepCopy() {
    	return null;
    } // End deepCopy method

} // End Stick class
