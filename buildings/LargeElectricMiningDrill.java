package b3yond.buildings;

import java.awt.Dimension;
import java.util.ArrayList;

import b3yond.items.Item;
import b3yond.views.GameWindow;

/**
 * LargeElectricMiningDrilljava
 * @author Aaron Ng
 * @date Jan 10, 2018
 * Mining Drill used to mine ores fast
 */
public class LargeElectricMiningDrill extends PoweredBuilding {

	private ArrayList<Item> ores;
	private Item[] inputInventory;
	private Item[] outputInventory;
	private long timeSinceExtract;
	
	

	public LargeElectricMiningDrill(int health, int level,ArrayList<Item> ores) {
		super(health, level,new Dimension(2,2),0,3);
		this.setDim(new Dimension(2,2));
		setOres(ores); //set the ores that the building can mine
		this.outputInventory=new Item[3];
		setTimeSinceExtract(System.nanoTime());  
	}
	
	public LargeElectricMiningDrill(int health, int level) {
		super(health, level,new Dimension(2,2),0,3);
		this.setDim(new Dimension(2,2));
		this.outputInventory=new Item[3];
		setTimeSinceExtract(System.nanoTime());  
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
	 * @return the ores
	 */
	public ArrayList<Item> getOres() {
		return ores;
	}

	/**
	 * setOres
	 * Resets the array of ores to the input array
	 * @param ores the ores to set
	 * @return Nothing
	 */
	public void setOres(ArrayList<Item> ores) {
		
		//Reset the ore list
		this.ores=new ArrayList<Item>();
		//Loop through the input ores and add it to possible ores this building can mine
		for(int i=0;i<ores.size();i++) {
			this.ores.add(ores.get(i));
		}
	}
	
	/**
	 * @return the timeSinceExtract
	 */
	public long getTimeSinceExtract() {
		return timeSinceExtract;
	}

	/**
	 * @param timeSinceExtract the timeSinceExtract to set
	 */
	public void setTimeSinceExtract(long timeSinceExtract) {
		this.timeSinceExtract = timeSinceExtract;
	}

	
	@Override
	/**
	 * update
	 * needed for each building so the map will use update 
	 * for every time its drawn
	 * @param nothing
	 * @return nothing
	 */
	public void update() {
		long timeNow= System.nanoTime();  		
		long timeElapsed= timeNow-timeSinceExtract;
		
		//for loops through all the possible ores it can mine
		for(int i=0;i<ores.size();i++) {
			
			//Checks to see if its the first output to create new ores
			if(outputInventory[i]==null && (timeElapsed/1.0E9)>=4) {
				outputInventory[i]= ores.get(i).deepCopy();
				outputInventory[i].setStack(2);
				GameWindow.outputButtons[i].setItem(outputInventory[i]);
				GameWindow.outputButtons[i].setImage(outputInventory[i].getClass().getSimpleName());
				GameWindow.outputButtons[i].updateToolTip();			
				timeSinceExtract=timeNow;
				
			//If outputInventory isn't null then only add 1 to the stack every 4 seconds	
			}else if((timeElapsed/1.0E9)>=4) {
				outputInventory[i].setStack( outputInventory[i].getStack() + 2);
				GameWindow.outputButtons[i].updateToolTip();		
				timeSinceExtract=timeNow;
			}
		}
	}

} // End LargeElectricMiningDrill
