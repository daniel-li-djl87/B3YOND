package b3yond.items;

/**
 * RocketPart.java
 * @author Aaron Ng
 * @date Jan 15, 2018
 * Required tool to make a rocket
 */
public class RocketPart extends Tool {
	
	/**
	 * RocketPart
	 * Constructor for RocketPart
	 * @param none
	 * @return none
	 */
	public RocketPart() {
		
	} // End RocketPart constructor
	
	@Override
	public int getStack() {
		return 1;
	}

	@Override
	public void setStack(int stack) {

	}

	@Override
	/**
	 * deepCopy
	 * Do nothing
	 * @param none
	 * @return null
	 */
	public Item deepCopy() {
		return null;
	}

} // End RocketPart class
