package b3yond.buildings;

import java.awt.Dimension;
import java.util.ArrayList;

import b3yond.items.BauxiteOre;
import b3yond.items.Conore;
import b3yond.items.CupriteOre;
import b3yond.items.HematiteOre;
import b3yond.items.Item;
import b3yond.items.Kannaite;
import b3yond.items.Lithium;
import b3yond.items.MixedConore;
import b3yond.items.MixedKannaite;
import b3yond.items.NickelOre;
import b3yond.items.Recipe;
import b3yond.items.RutileOre;

/**
 * OreSorter.java
 * @author Naymul Mohammed
 * @date December 17,2017
 * Building to sort ores 
 */
public class OreSorter extends PoweredBuilding {

	private int smeltTime;
	private Item[] inputInventory;
	private Item[] outputInventory;
	private boolean sorting;
	private ArrayList<Recipe> recipeBook;

	/**
	 * OreSorter
	 * Constructor for the OreSorter building
	 * @param health
	 * @param level
	 */
	public OreSorter(int health, int level) {
		super(health, level, new Dimension(2,2),1,4);
		this.setDim(new Dimension(2,2));
		this.setSmeltTime(1); 
		this.inputInventory=new Item[1];
		this.outputInventory=new Item[4];
		this.sorting=false;

		//Hard code each recipe since each building is different
		recipeBook= new ArrayList<Recipe>();

		recipeBook.add(new Recipe(new Item[] {new Conore(1)}, new Item[] {new HematiteOre(1), new NickelOre(1)}));
		recipeBook.add(new Recipe(new Item[] {new Kannaite(1)}, new Item[] {new CupriteOre(1), new BauxiteOre(1)}));
		recipeBook.add(new Recipe(new Item[] {new MixedConore(1)}, new Item[] {new HematiteOre(1),new NickelOre(1), new RutileOre(1)}));
		recipeBook.add(new Recipe(new Item[] {new MixedKannaite(1)}, new Item[] {new CupriteOre(1),new BauxiteOre(1), new Lithium(1)}));


	}

	/**
	 * @return the smeltTime
	 */
	public int getSmeltTime() {
		return smeltTime;
	}

	/**
	 * @param smeltTime the smeltTime to set
	 */
	public void setSmeltTime(int smeltTime) {
		this.smeltTime = smeltTime;
	}

	@Override
	/**
	 * update
	 * sort the items in the oreSorter
	 * @param none
	 * @return none
	 */
	public void update(){
		if(sorting==false) {
			Thread t=new Thread(new Sorting());
			t.start();
		}


	}//end of update


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
	 * Sorting
	 * Separate thread to do Sorting
	 */
	public class Sorting implements Runnable{
		/*
		 * Sorting
		 * Constructor for Sorting object
		 * @param Nothing
		 * @return nothing
		 */
		Sorting(){

		}
		public void run() {
			sorting=true; 							//let program know ore is Sorting
		boolean isSort=false;

			while(getPower()) { //Only smelt if the furnace has power left
				try {
					Thread.sleep(smeltTime);   //sleep so it takes time to smelt items
				} catch (InterruptedException e) {
					e.printStackTrace();
				}// end of catch

				//Loop through all the items of the furnace inventory


				//Go through all recipes for building
				for(int k=0;k<recipeBook.size();k++) {


					//Check if the input class and stack size are available
					if( (inputInventory[0]!=null) &&  (inputInventory[0].getClass().equals(recipeBook.get(k).getInput()[0].getClass())) && ((inputInventory[0].getStack()>=(recipeBook.get(k).getInput()[0].getStack())))) {

						//Go through output recipe array
						for(int i=0;i<recipeBook.get(k).getOutput().length;i++) {

			
								if(outputInventory[i]==null) {
									outputInventory[i]= recipeBook.get(k).getOutput()[i].deepCopy();
									isSort=true;

									//If the item already exists in the output spot
								}else if(outputInventory[i].getClass().equals( recipeBook.get(k).getOutput()[i].getClass())) {
									outputInventory[i].setStack( outputInventory[i].getStack() +recipeBook.get(k).getOutput()[i].getStack());
									isSort=true;
								}else {
									isSort=false;
								}


							
						}
						//Subtract from input once all output is done only if there was sorting done
						if(isSort){
							inputInventory[0].setStack(inputInventory[0].getStack() - (recipeBook.get(k).getInput()[0].getStack()) );
							isSort=false;
						}

						
						if(inputInventory[0].getStack()==0) {  //Delete input if stack is 0
							inputInventory[0]=null;
						}

					}
				}





			}//end of while loop
			sorting=false;
		}//end of run method

	}//end of Sorting runnable class


}
