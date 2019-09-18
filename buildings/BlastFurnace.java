package b3yond.buildings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import b3yond.items.*;
import b3yond.views.GameWindow;

/**
 * BlastFurnace.java
 * @author Aaron Ng & Naymul Mohammed
 * @date Dec 18, 2017
 * Blast Furnace melts hematite, cuprite, nickel and bauxite ore into molten metal with the use of coke
 */
public class BlastFurnace extends Building {

	private int smeltTime;
	private Item[] inputInventory;
	private Item powerSource;
	private Item[] outputInventory;
	private int powerTime;
	private Timer time;
	private boolean smelting;
	private ArrayList<Recipe> recipeBook;
	private int originalStack;
	private boolean firstCheck = true;
	/**
	 * BlastFurnace
	 * Constructor for BlastFurnace
	 * @param health,level
	 * @return none
	 */
	public BlastFurnace(int health, int level) {
		super(health, level,new Dimension(4,3),2,1);  //
		this.setDim(new Dimension(4,3));
		this.smeltTime = 500; 
		this.inputInventory=new Item[2];
		this.outputInventory=new Item[1];
		this.smelting=false;

		//Hard code each recipe since each building is different
			recipeBook= new ArrayList<Recipe>();
			recipeBook.add(new Recipe(new Item[] {new HematiteOre(1)}, new Item[] {new MoltenIron(8)}));
			recipeBook.add(new Recipe(new Item[] {new CupriteOre(1)}, new Item[] {new MoltenCopper(8)}));
			recipeBook.add(new Recipe(new Item[] {new NickelOre(1)}, new Item[] {new MoltenNickel(8)}));
			recipeBook.add(new Recipe(new Item[] {new BauxiteOre(1)}, new Item[] {new MoltenAluminum(8)}));

	}//end of constructor


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
	 * update
	 * smelts the items in the furnace
	 * @param none
	 * @return none
	 */
	public void update(){
		if(smelting==false) {
			Thread t=new Thread(new Smelting());
			t.start();
		}


	}//end of smelt

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
	 * @return the powerSource
	 */
	public Item getPowerSource() {
		return powerSource;
	}



	/**
	 * @param powerSource the powerSource to set
	 */
	public void setPowerSource(Item powerSource) {
		this.powerSource = powerSource;
	}



	/**
	 * @return the powerTime
	 */
	public int getPowerTime() {
		return powerTime;
	}



	/**
	 * @param powerTime the powerTime to set
	 */
	public void setPowerTime(int powerTime) {
		this.powerTime = powerTime;
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



	/**
	 * @return the recipeBook
	 */
	public ArrayList<Recipe> getRecipeBook() {
		return recipeBook;
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
			smelting=true; 							//let program know furnace is smelting
			if( GameWindow.inputButtons[0].getItem() instanceof Coke || GameWindow.inputButtons[1].getItem() instanceof Coke) {  //different power time for different products
				setPowerTime(15000 *((Coke) powerSource).getStack());
			}else {
				setPowerTime(0);
			}
			powerSource=null; //Uses the power source

			time = new Timer(powerTime, new timeListener());  // Create timer 
			time.start();

			
			if (inputInventory[0] != null && firstCheck == true) {
				originalStack = inputInventory[0].getStack();
				firstCheck = false;
			}

			
			while(powerTime>0) { //Only smelt if the furnace has power left
				try {
					Thread.sleep(smeltTime);   //sleep so it takes time to smelt items
				} catch (InterruptedException e) {
					e.printStackTrace();
				}// end of catch

				//Loop through all the items of the furnace inventory
				for(int i=0;i<inputInventory.length;i++) {
					
					//Go through all recipes for building
					for(int k=0;k<recipeBook.size();k++) {
					

						//Check if the input class and stack size are available
						if( (inputInventory[i]!=null) &&  (inputInventory[i].getClass().equals(recipeBook.get(k).getInput()[0].getClass())) && ((inputInventory[i].getStack()>=(recipeBook.get(k).getInput()[0].getStack())))) {
							
							if(outputInventory[i]==null) {
								inputInventory[i].setStack(inputInventory[i].getStack() - (recipeBook.get(k).getInput()[0].getStack()) );  //Subtract amount of stuff needed to make
								GameWindow.inputButtons[i].updateToolTip();
								outputInventory[i]= recipeBook.get(k).getOutput()[0].deepCopy();
								GameWindow.outputButtons[i].setItem(outputInventory[i]);
								GameWindow.outputButtons[i].setImage(outputInventory[i].getClass().getSimpleName());
								GameWindow.outputButtons[i].updateToolTip();			
								//If the item already exists in the output spot
								
							}else if(outputInventory[i].getClass().equals( recipeBook.get(k).getOutput()[0].getClass())) {
								inputInventory[i].setStack(inputInventory[i].getStack() - (recipeBook.get(k).getInput()[0].getStack()) );  //Subtract amount of stuff needed to make
								outputInventory[i].setStack( outputInventory[i].getStack() +recipeBook.get(k).getOutput()[0].getStack());
								GameWindow.inputButtons[i].updateToolTip();
								GameWindow.outputButtons[i].updateToolTip();
							}
							GameWindow.inputProgressBar.setValue(100 - (int) (100* ((double)(inputInventory[0].getStack())/originalStack)));
							GameWindow.outputProgressBar.setValue((int) (100* ((double)(inputInventory[0].getStack())/originalStack)));
							
							
							if(inputInventory[i].getStack()==0) {
								inputInventory[i]=null;
								GameWindow.inputProgressBar.setValue(0);
								GameWindow.outputProgressBar.setValue(0);
								GameWindow.inputButtons[i].setItem(null);
								GameWindow.inputButtons[i].setImage("null");
								GameWindow.inputButtons[i].updateToolTip();
							}
						}
					}
				}


			}//end of while loop
			smelting=false;
		}//end of run method

	}//end of Smelting runnable class


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
			powerTime=0;
			time.stop();
		} // End actionPerformed method

	} // End timeListener class

} // End BlastFurnace class
