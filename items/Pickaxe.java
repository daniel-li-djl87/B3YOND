package b3yond.items;

/**
 * Pickaxe.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Pickaxe used to manually mine ore
 */
public class Pickaxe extends Tool {
	/**
	 * Pickaxe
	 * Constructor for Pickaxe
	 * @param none
	 * @return none
	 */
	public Pickaxe() {
		
	} // End Pickaxe constructor

	@Override
	public int getStack() {
		return 1;
	}

	@Override
	public void setStack(int stack) {
		
	}
	
	/**
	 * deepCopy
	 * Do Nothing
	 * @param none
	 * @return none
	 */
    public Petroleum deepCopy() {
    	return null;
	} // End deepCopy method
    
} // End Pickaxe class
