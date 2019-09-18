package b3yond.items;

/**
 * Bucket.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Bucket used to manually 
 */
public class Bucket extends Tool {
	/**
	 * Bucket
	 * Constructor for Bucket
	 * @param none
	 * @return none
	 */
	public Bucket() {
		
	} // End Bucket constructor

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
    public Bucket deepCopy() {
    	return null;
    } // End deepCopy method
	
} // End Bucket class
