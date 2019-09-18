/**
 * ResearchLab.java
 * @author Naymul Mohammed
 * @Date December 14, 2017
 * ResearchLab object of the game used to research different technologies
 */

package b3yond.buildings;

import java.awt.Dimension;
import java.util.ArrayList;

import b3yond.items.*;

public class ResearchLab extends PoweredBuilding {

	private Item[] inputInventory;
	private Item[] outputInventory;
	private ArrayList<Recipe> recipeBook;
	private int tier;


	/**
	 * ResearchLab
	 * Constructor for ResearchLab
	 * @param health,level
	 * @return none
	 */
	public ResearchLab(int health, int level) {
		super(health, level,new Dimension(3,2),3,0);
		this.setDim(new Dimension(3,2));
		this.tier=1;	
		inputInventory=new Item[3];
		recipeBook= new ArrayList<Recipe>();

		//Tier 2 upgrade
		recipeBook.add(new Recipe(new Item[] {new Conore(10), new Copper(10), new Iron(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Conore(10), new Iron(10), new Copper(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Copper(10), new Conore(10), new Iron(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Copper(10), new Iron(10), new Conore(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Iron(10), new Copper(10), new Conore(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Iron(10), new Conore(10), new Copper(10)}, new Item[1]));


		//Tier 3 upgrade
		recipeBook.add(new Recipe(new Item[] {new Steel(10), new Kannaite(10), new Coal(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Steel(10), new Coal(10), new Kannaite(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Kannaite(10), new Steel(10), new Coal(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Kannaite(10), new Coal(10), new Steel(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Coal(10), new Kannaite(10), new Steel(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Coal(10), new Steel(10), new Kannaite(10)}, new Item[1]));


		//Tier 4 upgrade
		recipeBook.add(new Recipe(new Item[] {new Nickel(10), new Aluminum(10), new Wood(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Nickel(10), new Wood(10), new Aluminum(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Wood(10), new Nickel(10), new Aluminum(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Wood(10), new Aluminum(10), new Nickel(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Aluminum(10), new Wood(10), new Nickel(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Aluminum(10), new Nickel(10), new Wood(10)}, new Item[1]));

		//Tier 5 upgrade
		recipeBook.add(new Recipe(new Item[] {new Coke(100), new Sapling(100), new BauxiteOre(20)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Coke(100), new BauxiteOre(20), new Sapling(100)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new BauxiteOre(20), new Coke(100), new Sapling(100)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new BauxiteOre(20), new Sapling(100), new Coke(100)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Sapling(100), new BauxiteOre(20), new Coke(100)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Sapling(100), new Coke(100), new BauxiteOre(20)}, new Item[1]));


		//Tier 6 upgrade
		recipeBook.add(new Recipe(new Item[] {new Titanium(10), new Lithium(10), new Glass(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Titanium(10), new Glass(10), new Lithium(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Glass(10), new Titanium(10), new Lithium(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Glass(10), new Lithium(10), new Titanium(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Titanium(10), new Lithium(10), new Glass(10)}, new Item[1]));
		recipeBook.add(new Recipe(new Item[] {new Titanium(10), new Glass(10), new Lithium(10)}, new Item[1]));



	}//end of constructor

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
	 * upgrade
	 * Upgrades the tier of the research tree
	 */
	public void upgrade() {
		
		int previousTier=tier;

		//Different tier upgrades
		if(this.tier==1) {

			//Go through all recipes for building
			for(int k=0;k<6;k++) {

				//Check to see if the 3 input items of the research lab are accurate
				if( (inputInventory[0].getClass().equals(recipeBook.get(k).getInput()[0].getClass()))  &&   (inputInventory[0].getStack()>=(recipeBook.get(k).getInput()[0].getStack())) && (inputInventory[1].getClass().equals(recipeBook.get(k).getInput()[1].getClass()))  &&   (inputInventory[1].getStack()>=(recipeBook.get(k).getInput()[1].getStack())) && (inputInventory[2].getClass().equals(recipeBook.get(k).getInput()[2].getClass()))  &&   (inputInventory[2].getStack()>=(recipeBook.get(k).getInput()[2].getStack())) ) {
		
					tier++; //Add one to tier if the input is valid;
				}
			}
		}else if(tier==2) {
			//Go through all recipes for building
			for(int k=6;k<12;k++) {

				//Check to see if the 3 input items of the research lab are accurate
				if( (inputInventory[0].getClass().equals(recipeBook.get(k).getInput()[0].getClass()))  &&   (inputInventory[0].getStack()>=(recipeBook.get(k).getInput()[0].getStack())) && (inputInventory[1].getClass().equals(recipeBook.get(k).getInput()[1].getClass()))  &&   (inputInventory[1].getStack()>=(recipeBook.get(k).getInput()[1].getStack())) && (inputInventory[2].getClass().equals(recipeBook.get(k).getInput()[2].getClass()))  &&   (inputInventory[2].getStack()>=(recipeBook.get(k).getInput()[2].getStack())) ) {

					tier++; //Add one to tier if the input is valid;
				}
			}

		}else if(tier==3) {
			//Go through all recipes for building
			for(int k=12;k<18;k++) {

				//Check to see if the 3 input items of the research lab are accurate
				if( (inputInventory[0].getClass().equals(recipeBook.get(k).getInput()[0].getClass()))  &&   (inputInventory[0].getStack()>=(recipeBook.get(k).getInput()[0].getStack())) && (inputInventory[1].getClass().equals(recipeBook.get(k).getInput()[1].getClass()))  &&   (inputInventory[1].getStack()>=(recipeBook.get(k).getInput()[1].getStack())) && (inputInventory[2].getClass().equals(recipeBook.get(k).getInput()[2].getClass()))  &&   (inputInventory[2].getStack()>=(recipeBook.get(k).getInput()[2].getStack())) ) {
	
					tier++; //Add one to tier if the input is valid;
				}
			}

		}else if(tier==4) {
			//Go through all recipes for building
			for(int k=18;k<24;k++) {

				//Check to see if the 3 input items of the research lab are accurate
				if( (inputInventory[0].getClass().equals(recipeBook.get(k).getInput()[0].getClass()))  &&   (inputInventory[0].getStack()>=(recipeBook.get(k).getInput()[0].getStack())) && (inputInventory[1].getClass().equals(recipeBook.get(k).getInput()[1].getClass()))  &&   (inputInventory[1].getStack()>=(recipeBook.get(k).getInput()[1].getStack())) && (inputInventory[2].getClass().equals(recipeBook.get(k).getInput()[2].getClass()))  &&   (inputInventory[2].getStack()>=(recipeBook.get(k).getInput()[2].getStack())) ) {

					tier++; //Add one to tier if the input is valid;
				}
			}

		}else if(tier==5) {
			//Go through all recipes for building
			for(int k=24;k<30;k++) {

				//Check to see if the 3 input items of the research lab are accurate
				if( (inputInventory[0].getClass().equals(recipeBook.get(k).getInput()[0].getClass()))  &&  (inputInventory[0].getStack()>=(recipeBook.get(k).getInput()[0].getStack())) && (inputInventory[1].getClass().equals(recipeBook.get(k).getInput()[1].getClass()))  &&   (inputInventory[1].getStack()>=(recipeBook.get(k).getInput()[1].getStack())) && (inputInventory[2].getClass().equals(recipeBook.get(k).getInput()[2].getClass()))  &&   (inputInventory[2].getStack()>=(recipeBook.get(k).getInput()[2].getStack())) ) {

					tier++; //Add one to tier if the input is valid;
				}
			}

		}
		
		//Removes only if the upgrade is successful
		if(previousTier!=tier) {		
		inputInventory[0]=null;
		inputInventory[1]=null;
		inputInventory[2]=null;
		}
	}


}//end of ResearchLab class
