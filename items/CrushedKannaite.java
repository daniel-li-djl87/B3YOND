package b3yond.items;

/**
 * CrushedKannaite.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * CrushedKannaite is made in an ore crusher
 */
public class CrushedKannaite extends ResourceProcessing {

	private int stack;
	
	/**
	 * CrushedKannaite.java
	 * Constructor for CrushedKannaite
	 * @param stack size
	 * @return none
	 */
	public CrushedKannaite(int stack){
		this.setStack(stack);
	} // End CrushedKannaite constructor

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
	 * Returns an exact copy of CrushedKannaite
	 * @param none
	 * @return CrushedKannaite
	 */
    public CrushedKannaite deepCopy() {
    	return new CrushedKannaite(this.getStack());
    } // End deepCopy method
	
} // End CrushedKannaite class
