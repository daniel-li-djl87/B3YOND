package b3yond.buildings;

import java.awt.Dimension;

import b3yond.items.Item;

/**
 * LargeStorageTank.java
 * @author Aaron Ng
 * @date Jan 10, 2018
 * Holds many items for mass storage
 */
public class LargeStorageTank extends Building {

	private Item[] inventory;
	
	/**
	 * LargeStorageTank
	 * Constructor for StorageTank
	 * @param health
	 * @param level
	 */
	public LargeStorageTank(int health, int level) {
		super(health, level, new Dimension(3,3),1,0);
		this.setDim(new Dimension(3,3));
		inventory = new Item[40];
	} // End StorageTank constructor

	@Override
	/**
	 * getInputItem
	 * Finds item at specified slot
	 * @param int slot
	 * @return Item
	 */
	public Item getInputItem(int slot) {
		return inventory[slot];
	} // End getInputItem

	@Override
	/**
	 * removeInputItem
	 * Removes item at specified slot
	 * @param int slot
	 * @return Removed item
	 */
	public Item removeInputItem(int slot) {
		Item item = inventory[slot].deepCopy();
		inventory[slot] = null;
		return item;
	} // End removeInputItem

	@Override
	/**
	 * addInputItem
	 * Adds item to first available slot
	 * @param Item
	 * @return none
	 */
	public void addInputItem(Item item) {
		boolean check=true;                         //boolean to see if item was already added
		for(int k=0;k<inventory.length;k++) {
			if(check && inventory[k]==null) {    //Goes through for loop until it finds a empty spot
				check=false;                          //disable check
				inventory[k]=item; 
			} // End if
		} // End for loop
		
	} // End addInputItem method

	@Override
	/**
	 * addInputItem
	 * Adds item to specified slot
	 * @param item, slot
	 * @return none
	 */
	public void addInputItem(Item item, int slot) {
		inventory[slot] = item;
	} // End addInputItem method

	@Override
	/**
	 * getOutputItem
	 * Void method due to lack of output array
	 */
	public Item getOutputItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * removeOutputItem
	 * Void method due to lack of output array
	 */
	public Item removeOutputItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * addOutputItem
	 * Void method due to lack of output array
	 */
	public void addOutputItem(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * getOutputItem
	 * Void method due to lack of output array
	 */
	public void addOutputItem(Item item, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * update
	 * Void method due to lack of output array
	 */
	public void update() {
		// TODO Auto-generated method stub

	}

}
