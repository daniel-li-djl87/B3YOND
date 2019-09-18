/**
 * Building.java
 * @author Naymul Mohammed
 * @Date December 14, 2017
 * Building class that is used to connect all types of buildings and attach to tiles
 */

package b3yond.buildings;

import java.awt.Dimension;

import b3yond.items.Item;

public abstract class Building {
	private int health;
	private int level;
	private Dimension dim;
	public int x,y;
	public int inputInventorySize;
	public int outputInventorySize;
	

	/**
	 * Building
	 * Constructor for Building
	 * @param health,level, length,width
	 * @return none
	 */
	Building(int health, int level, Dimension dim,int inputInventorySize, int outputInventorySize) {
		this.health = health;
		this.level = level;
		this.dim=dim;
		this.inputInventorySize=inputInventorySize;
		this.outputInventorySize=outputInventorySize;
	}//end of constructor

	// Getters and Setters
	public int getLevel() {
		return level;
	}//end of getLevel

	/**
	 * @return the inputInventorySize
	 */
	public int getInputInventorySize() {
		return inputInventorySize;
	}

	/**
	 * @param inputInventorySize the inputInventorySize to set
	 */
	public void setInputInventorySize(int inputInventorySize) {
		this.inputInventorySize = inputInventorySize;
	}
	

	/**
	 * @return the outputInventorySize
	 */
	public int getOutputInventorySize() {
		return outputInventorySize;
	}

	/**
	 * @param outputInventorySize the outputInventorySize to set
	 */
	public void setOutputInventorySize(int outputInventorySize) {
		this.outputInventorySize = outputInventorySize;
	}

	/**
	 * getInputItem
	 * @param slot of item you want to get
	 * @return The item you wanted
	 */
	public abstract Item getInputItem(int slot);
	
	/**
	 * removeInputItem
	 * @param slot of item you want to remove
	 * @return The item you want to remove
	 */
	public abstract Item removeInputItem(int slot);
	
	/**
	 * addInputItem
	 * @param item you want to add
	 */
	public abstract void addInputItem(Item item);
	
	/**
	 * addInputItem
	 * @param item you want to add, and the slot of it
	 */
	public abstract void addInputItem(Item item,int slot);
	
	/**
	 * getOutputItem
	 * @param slot of item you want to get
	 * @return The item you wanted
	 */
	public abstract Item getOutputItem(int slot);
	
	/**
	 * removeOutputItem
	 * @param slot of item you want to remove
	 * @return The item you want to remove
	 */
	public abstract Item removeOutputItem(int slot);
	
	/**
	 * addOutputItem
	 * @param item you want to add
	 */
	public abstract void addOutputItem(Item item);
	
	/**
	 * addOutputItem
	 * @param item you want to add, and the slot of it
	 */
	public abstract void addOutputItem(Item item,int slot);
	
	
	

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}//end of getHealth
	

	/**
	 * @param the level
	 */
	public void setLevel(int level) {
		this.level = level;
	}//set level

	/**
	 * @param the health
	 */
	public void setHealth(int health) {
		this.health = health;
	}//set health
	
	
	/**
	 * update
	 * needed for each building so the map will use update 
	 * for every time its drawn
	 * @param nothing
	 * @return nothing
	 */
	public abstract void update();

	/**
	 * @return the dim
	 */
	public Dimension getDim() {
		return dim;
	}

	/**
	 * @param dim the dim to set
	 */
	public void setDim(Dimension dim) {
		this.dim = dim;
	}

}
