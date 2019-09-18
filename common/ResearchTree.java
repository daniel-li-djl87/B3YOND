package b3yond.common;

/**
 * ResearchTree.java
 * @author Naymul Mohammed & Aaron Ng
 * @date January 2,2018
 * Object to store what the player can research
 *
 */
public class ResearchTree {
	
	public int tier; // Most recent researched tier
	public boolean[] researchItems; // Array of researched technologies 
	private int researching; // Keeps track of currently researching technology in array (-1 if nothing being researched)
	
	/**
	 * ResearchTree
	 * Constructor for ResearchTree
	 * @param none
	 * @return none
	 */
	public ResearchTree() {
		this.tier=2;
		this.researchItems=new boolean[25];
		setResearching(-1);
		researchItems[0]=true;
		researchItems[1]=true;
		researchItems[2]=true;
		for(int i=3;i<researchItems.length;i++) {
			researchItems[i]=false;
		} // End for loop
	} // End constructor
	
	/**
	 * setItem
	 * sets the research of the item
	 * @param selection, research
	 * @return none
	 */
	public void setItem(int selection, boolean research) { 
		this.researchItems[selection]=research;		
	}
	
	
	/**
	 * getItem
	 * gets the research boolean of the item
	 * @param selection
	 * @return boolean 
	 */
	public boolean getItem(int selection) { 
		return this.researchItems[selection];		
	}
	

	/**
	 * @return the tier
	 */
	public int getTier() {
		return tier;
	}

	/**
	 * @param tier the tier to set
	 */
	public void setTier(int tier) {
		this.tier = tier;
	}

	public int getResearching() {
		return researching;
	}

	public void setResearching(int researching) {
		this.researching = researching;
	}
	
	/**
	 * getNumResearched
	 * Returns the number of fully researched technologies
	 * @param none
	 * @return int successfully researched technologies
	 */
	public int getNumResearched() {
		int count = 0;
		for (int i = 0; i < researchItems.length; i++) {
			if (researchItems[i]) {
				count++;
			} // End if
		} // End for loop
		return count;
	} // End getNumResearched method
	
} // End ResearchTree class