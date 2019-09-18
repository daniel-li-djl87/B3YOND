/**
 * HandCrankGenerator.java
 * @author Naymul Mohammed
 * @Date December 14, 2017
 * Object to generate power by clicking
 */

package b3yond.buildings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import b3yond.items.Item;




public class HandCrankGenerator extends PoweredBuilding {

	private	Timer time;
	private Item[] inputInventory;
	private Item[] outputInventory;

	/**
	 * HandCrankGenerator
	 * Constructor for HandCrankGenerator
	 * @param health,level
	 * @return none
	 */
	public HandCrankGenerator(int health, int level) {
		super(health, level,new Dimension(2,2),0,0);
		this.setDim(new Dimension(2,2));
	}//end of constructor

	@Override
	/**
	 * update
	 * needed for each building so the map will use update 
	 * for every time its drawn
	 * @param nothing
	 * @return nothing
	 */
	public  void update() {
	}

	/**
	 * clickGenerate
	 * Adds power to the generator once clicked
	 * @param none
	 * @return none
	 */
	public void clickGenerate(){
		setPower(true);
		time = new Timer(5000, new timeListener());  // Create timer 
		time.start();

	} //end of clickGenerate method

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

	
	
	/**
	 * timeListener
	 * goes off every time its set to and runs whats in the class
	 * @param none
	 * @return none
	 */
	class timeListener implements ActionListener{

		/**
		 * actionPerformed
		 * runs the code once action is performed
		 * @param none
		 * @return none
		 */
		public void actionPerformed (ActionEvent e){
			setPower(false);
			time.stop();


		} // End actionPerformed method

	} // End timeListener class


}// End of HandCrankGenerator class
