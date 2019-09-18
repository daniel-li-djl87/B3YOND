package b3yond.buildings;

import java.awt.Dimension;

import b3yond.items.*;

/**
 * SteamEngine.java
 * @author Aaron Ng
 * @date Dec 18, 2017
 * Steam Engine consumes steam and creates electricity
 */
public class SteamEngine extends PoweredBuilding {

	private int smeltTime;
	private Item[] inputInventory;
	private Item[] outputInventory;
	private boolean smelting;
	
	/**
	 * SteamEngine
	 * Constructor for Steamengine
	 * @param health
	 * @param level
	 */
	public SteamEngine(int health, int level) {
		super(health, level, new Dimension(2,3),1,0);
		this.setDim(new Dimension(2,3));
		this.smeltTime = 1000;
		this.inputInventory = new Item[1];
		this.smelting = false;
	}

	public int getSmeltTime() {
		return smeltTime;
	}

	public void setSmeltTime(int smeltTime) {
		this.smeltTime = smeltTime;
	}

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
	 * gives you an item in a given slot
	 * @param slot of item you want to get
	 * @return The item you wanted
	 */
	public Item getOutputItem(int slot) {
		return outputInventory[slot];
	}

	@Override
	/**
	 * removeOutputItem
	 * removes an item from the inventory and returns it
	 * @param slot of item you want to remove
	 * @return The item you want to remove
	 */
	public Item removeOutputItem(int slot) {
		Item i= outputInventory[slot].deepCopy();
		outputInventory[slot]=null;
		return i;
	}

	@Override
	/**
	 * addOutputItem
	 * adds item to inventory if there is space
	 * @param item you want to add
	 */
	public void addOutputItem(Item item) {
		boolean check=true;                         //boolean to see if item was already added
		for(int k=0;k<outputInventory.length;k++) {
			if(check && outputInventory[k]==null) {    //Goes through for loop until it finds a empty spot
				check=false;                          //disable check
				outputInventory[k]=item; 
			}else if(check && outputInventory[k].getClass().equals(item.getClass())) { //Add to stack if its of same class
				check=false;                          //disable check
				outputInventory[k].setStack(outputInventory[k].getStack() +item.getStack()); 
			}
		}
	}

	@Override
	/**
	 * addOutputItem
	 * adds item to a certain slot in the inventory
	 * @param item you want to add, and the slot of it
	 */
	public void addOutputItem(Item item,int slot) {
		if(outputInventory[slot]==null) {    //Goes through for loop until it finds a empty spot                          //disable check
			outputInventory[slot]=item; 
		}else if(outputInventory[slot].getClass().equals(item.getClass())) { //Add to stack if its of same class
			outputInventory[slot].setStack(outputInventory[slot].getStack() +item.getStack()); 

		}
	}

	/*
	 * Smelting
	 * Separate thread to do smelting
	 */
	public class Smelting implements Runnable{
		/*
		 * Smelting
		 * Constructor for Smelting object
		 * @param Nothing
		 * @return nothing
		 */
		Smelting(){

		}

		public void run() {
			smelting = true;
			while (inputInventory[0] != null && inputInventory[0] instanceof Steam && inputInventory[0].getStack() >= 20) { // Only run steam engine if there is no power and inventory has items

			
					inputInventory[0].setStack(inputInventory[0].getStack() - 20); // subtract 1 from stack
					if (inputInventory[0].getStack() <= 0) { // Void item slot if stack becomes 0
						inputInventory[0] = null;
					} // End if
					setPower(true);
					
					try {
						Thread.sleep(smeltTime); // Delay loop to create illusion of power
					}catch(InterruptedException e) {
						e.printStackTrace();
					} // End try catch
			
			} // End while loop
			
			setPower(false);
			smelting = false;
		}		
	}

	@Override
	public void update() {
		if(smelting==false) {
			Thread t=new Thread(new Smelting());
			t.start();
		}

	}

} // End SteamEngine class
