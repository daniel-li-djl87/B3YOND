package b3yond.buildings;

import java.awt.Dimension;

import b3yond.items.Item;
import b3yond.items.SuperHeatedWater;
import b3yond.views.GameWindow;

/**
 * CoolingTower.java
 * @author Aaron Ng
 * @date Dec 18, 2017
 * Cooling Tower vents away superheated water
 */
public class CoolingTower extends Building {

	private int smeltTime;
	private Item[] inputInventory;
	private Item[] outputInventory;
	private boolean smelting;
	private long time;
	/**
	 * CoolingTower
	 * Constructor for CoolingTower
	 * @param health,level
	 * @return none
	 */
	public CoolingTower(int health, int level) {
		super(health, level, new Dimension(2, 4),3,0);
		this.setDim(new Dimension(2,4));
		this.smeltTime = 500;
		this.inputInventory = new Item[1];
		this.outputInventory = new Item[0];
		this.smelting = false;
	}

	public int getInputInventorySize() {
		return inputInventory.length;
	}

	public int getOutputInventorySize() {
		return outputInventory.length;
	}

	// Getters
	public int getSmeltTime() {
		return smeltTime;
	}//end of getSmeltTime

	// Setters
	public void setSmeltTime(int smeltTime) {
		this.smeltTime = smeltTime;
	}//end of setSmeltTime

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
		if (inputInventory[slot] == null) {
			return null;
		}
		Item i = inputInventory[slot].deepCopy();
		inputInventory[slot] = null;
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

	/**
	 * @return the smelting
	 */
	public boolean isSmelting() {
		return smelting;
	}

	/**
	 * @param smelting the smelting to set
	 */
	public void setSmelting(boolean smelting) {
		this.smelting = smelting;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
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
			int originalStack  = 0;
			boolean firstCheck = true;
			while (inputInventory[0] != null) { // Only smelt if cooling tower contains items

				try {
					Thread.sleep(smeltTime);
				}catch(InterruptedException e) {
					e.printStackTrace();
				} // End try catch

				//Loop through all the items of the furnace inventory
				if (inputInventory[0] != null && firstCheck == true) {
					originalStack = inputInventory[0].getStack();
					firstCheck = false;
				}
				for (int i = 0; i < inputInventory.length; i++) {

					if (inputInventory[i] != null) { // Check if inventory slot is void

						if (inputInventory[i] instanceof SuperHeatedWater) { // Check if input slot contains superheated water
							inputInventory[i].setStack(inputInventory[i].getStack() - 1); // subtract 1 from stack
							GameWindow.inputProgressBar.setValue(100 - (int) (100* ((double)(inputInventory[i].getStack())/originalStack)));
							GameWindow.outputProgressBar.setValue((int) (100* ((double)(inputInventory[i].getStack())/originalStack)));
							if (inputInventory[i].getStack() <= 0) { // Void item slot if stack becomes 0
								inputInventory[i] = null;
								GameWindow.inputButtons[i].setItem(null);
								GameWindow.inputButtons[i].setImage("null");
								GameWindow.inputProgressBar.setValue(0);
								GameWindow.outputProgressBar.setValue(0);
								GameWindow.inputButtons[i].updateToolTip();
								System.out.println("The item has been removed");
							} // End if
						} // End if

					} // End if

				}

			} // End while loop
			smelting = false;

		} // End run method
	} // End Smelting thread

	@Override
	public void update() {
		if(smelting==false) {
			Thread t=new Thread(new Smelting());
			t.start();
		}
	}

} // End CoolingTower class