package b3yond.buildings;

import java.awt.Dimension;

import b3yond.items.Item;

/**
 * StorageTank.java
 * @author Aaron Ng
 * @date Jan 10, 2018
 * Holds items for storage
 */
public class StorageTank extends Building {
	
	private Item[] inputInventory;
	
	/**
	 * StorageTank
	 * Constructor for StorageTank
	 * @param health
	 * @param level
	 */
	public StorageTank(int health, int level) {
		super(health, level, new Dimension(3,3),1,0);
		this.setDim(new Dimension(3,3));
		inputInventory = new Item[20];
	} // End StorageTank constructor

	@Override
	/**
	 * getInputItem
	 * method gives you an item in the given slot
	 * @param slot of item you want to get
	 * @return The item you wanted
	 */
	public Item getInputItem(int slot) {
		return inputInventory[slot];
	}

	@Override
	/**
	 * removeInputItem
	 * removes an item from a slot and returns the same item
	 * @param slot of item you want to remove
	 * @return The item you want to remove
	 */
	public Item removeInputItem(int slot) {
		Item i= inputInventory[slot].deepCopy();
		inputInventory[slot]=null;
		return i;
	}

	@Override
	/**
	 * addInputItem
	 * adds item to inventory if there is space
	 * @param item you want to add
	 */
	public void addInputItem(Item item) {
		boolean check=true;                         //boolean to see if item was already added
		for(int k=0;k<inputInventory.length;k++) {
			if(check && inputInventory[k]==null) {    //Goes through for loop until it finds a empty spot
				check=false;                          //disable check
				inputInventory[k]=item; 
			}else if(check && inputInventory[k].getClass().equals(item.getClass())) { //Add to stack if its of same class
				check=false;                          //disable check
				inputInventory[k].setStack(inputInventory[k].getStack() +item.getStack()); 
			}
		}
	}

	@Override
	/**
	 * addInputItem
	 * adds item to a certain slot
	 * @param item you want to add, and the slot of it
	 */
	public void addInputItem(Item item,int slot) {
		if(inputInventory[slot]==null) {    //Goes through for loop until it finds a empty spot                          //disable check
			inputInventory[slot]=item; 
		}else if(inputInventory[slot].getClass().equals(item.getClass())) { //Add to stack if its of same class
			inputInventory[slot].setStack(inputInventory[slot].getStack() +item.getStack()); 

		}
	}

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

} // End StorageTank class
