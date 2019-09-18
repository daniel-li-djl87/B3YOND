package b3yond.items;

/**
 * Axe.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Axe used to manually mine wood
 */
public class Axe extends Tool {
	/**
	 * Axe
	 * Constructor for Axe
	 * @param none
	 * @return none
	 */
	public Axe() {
		
	} // End Axe constructor

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
    public Axe deepCopy() {
    	return null;
    } // End deepCopy method

} // End Axe class
