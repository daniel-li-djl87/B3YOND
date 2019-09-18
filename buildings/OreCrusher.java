package b3yond.buildings;

import java.awt.Dimension;
import java.util.ArrayList;

import b3yond.items.ConoreSlurry;
import b3yond.items.Item;
import b3yond.items.KannaiteSlurry;
import b3yond.items.MixedConore;
import b3yond.items.MixedKannaite;
import b3yond.items.Recipe;

/**
 * OreCrusher.java
 * @author Aaron Ng
 * @date Jan 10, 2018
 * Turns ore slurry into mixed ores
 */
public class OreCrusher extends PoweredBuilding {

	private int smeltTime;
	private Item[] inputInventory;
	private Item[] outputInventory;
	private boolean smelting;
	ArrayList <Recipe> recipeBook;
	/**
	 * LeachingPlant
	 * Constructor for LeachingPlant
	 * @param health
	 * @param level
	 */
	public OreCrusher(int health, int level) {
		super(health, level, new Dimension(3,3),2,2);
		this.setDim(new Dimension(3,3));
		this.smeltTime = 1000; 
		this.inputInventory=new Item[2];
		this.outputInventory=new Item[2];
		this.smelting=false;
		// Manually initialize recipes
		recipeBook = new ArrayList <Recipe> ();
		recipeBook.add(new Recipe(new Item[] {new ConoreSlurry(1)}, new Item[] {new MixedConore(2)}));
		recipeBook.add(new Recipe(new Item[] {new KannaiteSlurry(1)}, new Item[] {new MixedKannaite(2)}));

	} // End of Constructor

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

			smelting=true; 							//let program know ore is Sorting
			boolean isSort=false;

			while(getPower() && (inputInventory[0] != null || inputInventory[1] != null)) { //Only smelt if the furnace has power left

				try {
					Thread.sleep(smeltTime);   //sleep so it takes time to smelt items
				} catch (InterruptedException e) {
					e.printStackTrace();
				}// end of catch

				//Loop through all the items of the furnace inventory
				for (int j = 0; j < inputInventory.length; j++) {


					//Go through all recipes for building
					for(int k=0;k<recipeBook.size();k++) {

						//Check if the input class and stack size are available
						if( (inputInventory[j]!=null) &&  (inputInventory[j].getClass().equals(recipeBook.get(k).getInput()[0].getClass())) && ((inputInventory[j].getStack()>=(recipeBook.get(k).getInput()[0].getStack())))) {

							//Go through output recipe array
							for(int i=0;i<recipeBook.get(k).getOutput().length;i++) {

								if(outputInventory[j]==null) {
									outputInventory[j]= recipeBook.get(k).getOutput()[i].deepCopy();
									isSort=true;

									//If the item already exists in the output spot
								}else if(outputInventory[j].getClass().equals( recipeBook.get(k).getOutput()[i].getClass())) {
									outputInventory[j].setStack( outputInventory[j].getStack() +recipeBook.get(k).getOutput()[i].getStack());
									isSort=true;
								}else {
									isSort=false;
								} // End if

							} // End for loop cycling through output array

							//Subtract from input once all output is done only if there was sorting done
							if(isSort){
								inputInventory[j].setStack(inputInventory[j].getStack() - (recipeBook.get(k).getInput()[0].getStack()) );
								isSort=false;
							} // End if

							if(inputInventory[j].getStack()==0) {  //Delete input if stack is 0
								inputInventory[j]=null;
							} // End if

						} // End if

					} // End for loop cycling all recipes

				} // End for loop cycling all input slots

			}//end of while loop
			smelting=false;

		}//end of run method

	}//end of Smelting runnable class

	@Override
	/**
	 * update
	 * smelts the items in the furnace
	 * @param none
	 * @return none
	 */
	public void update() {
		if(smelting==false) {
			Thread t=new Thread(new Smelting());
			t.start();
		}
	}

}
