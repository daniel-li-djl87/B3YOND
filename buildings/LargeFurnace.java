/**
 * LargeFurnace.java
 * @author Naymul Mohammed
 * @Date December 14, 2017
 * Furnace class is for the furnace object in the game to smelt various ores
 */


package b3yond.buildings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import b3yond.items.*;


public class LargeFurnace extends Building {
	private int smeltTime;
	private Item[] inputInventory;
	private Item powerSource;
	private Item[] outputInventory;
	private int powerTime;
	private Timer time;
	private boolean smelting;

	/**
	 * LargeFurnace
	 * Constructor for LargeFurnace
	 * @param health,level
	 * @return none
	 */
	public LargeFurnace(int health, int level) {
		super(health, level,new Dimension(2,3),10,10);
		this.setDim(new Dimension(2,3));
		this.smeltTime = 1000; 
		this.inputInventory=new Item[10];
		this.outputInventory=new Item[10];
		this.smelting=false;
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

	
	
	/**
	 * upgrade
	 * upgrades the furnace
	 * @param none
	 * @return none
	 */
	public void upgrade() {
		this.setLevel(this.getLevel() + 1);

		if (this.getLevel() == 2) {
			setSmeltTime(800);
		} else if (this.getLevel() == 3) {
			setSmeltTime(500);
		} else if (this.getLevel() == 4) {
			setSmeltTime(200);
		}

	}//end of upgrade

	

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
			smelting=true;                        //Let program know its smelting
			if(powerSource instanceof Sapling) { //different power time for different products
				setPowerTime(10000*((Sapling) powerSource).getStack());
			}else if(powerSource instanceof Tree) { 
				setPowerTime(20000	*((Tree) powerSource).getStack());
			}else if(powerSource instanceof Wood) { 
				setPowerTime(30000*((Wood) powerSource).getStack());
			}else if(powerSource instanceof Coal) { 
				setPowerTime(50000	*((Coal) powerSource).getStack());
			}else if(powerSource instanceof Coke) { 
				setPowerTime(100000 *((Coke) powerSource).getStack());
			}else {
				setPowerTime(0);
			}
			powerSource=null; //Uses the power source
			
			time = new Timer(powerTime, new timeListener());  // Create timer 
			time.start();
			
			while(powerTime>0) { //Only smelt if the furnace has power left
			try {
				Thread.sleep(smeltTime);   //sleep so it takes time to smelt items
			} catch (InterruptedException e) {
				e.printStackTrace();
			}// end of catch

			//Loop through all the items of the furnace inventory
			for(int i=0;i<10;i++) {
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof Wood && ((Wood) inputInventory[i]).getStack()>0) { 
					((Wood) inputInventory[i]).setStack( ((Wood) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Coal(1);
					}else {
						((Coal) outputInventory[i]).setStack(((Coal) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof Coal && ((Coal) inputInventory[i]).getStack()>0) { 
					((Coal) inputInventory[i]).setStack( ((Coal) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Coke(1);
					}else {
						((Coke) outputInventory[i]).setStack(((Coke) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof Conore && ((Conore) inputInventory[i]).getStack()>0) { 
					((Conore) inputInventory[i]).setStack( ((Conore) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Iron(1);
					}else {
						((Iron) outputInventory[i]).setStack(((Iron) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof Kannaite && ((Kannaite) inputInventory[i]).getStack()>0) { 
					((Kannaite) inputInventory[i]).setStack( ((Kannaite) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Copper(1);
					}else {
						((Copper) outputInventory[i]).setStack(((Copper) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof HematiteOre && ((HematiteOre) inputInventory[i]).getStack()>0) { 
					((HematiteOre) inputInventory[i]).setStack( ((HematiteOre) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Iron(1);
					}else {
						((Iron) outputInventory[i]).setStack(((Iron) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof CupriteOre && ((CupriteOre) inputInventory[i]).getStack()>0) { 
					((CupriteOre) inputInventory[i]).setStack( ((CupriteOre) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Copper(1);
					}else {
						((Copper) outputInventory[i]).setStack(((Copper) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof Sand && ((Sand) inputInventory[i]).getStack()>0) { 
					((Sand) inputInventory[i]).setStack( ((Sand) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Glass(1);
					}else {
						((Glass) outputInventory[i]).setStack(((Glass) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof NickelOre && ((NickelOre) inputInventory[i]).getStack()>0) { 
					((NickelOre) inputInventory[i]).setStack( ((NickelOre) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Nickel(1);
					}else {
						((Nickel) outputInventory[i]).setStack(((Nickel) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof BauxiteOre && ((BauxiteOre) inputInventory[i]).getStack()>0) { 
					((BauxiteOre) inputInventory[i]).setStack( ((BauxiteOre) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Aluminum(1);
					}else {
						((Aluminum) outputInventory[i]).setStack(((Aluminum) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof ZincOre && ((ZincOre) inputInventory[i]).getStack()>0) { 
					((ZincOre) inputInventory[i]).setStack( ((ZincOre) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Zinc(1);
					}else {
						((Zinc) outputInventory[i]).setStack(((Zinc) outputInventory[i]).getStack() +1 ); //Adds one to new stack
					}
					
				}
				
				//Checks the different types of things that can be smelted
				if(inputInventory[i] instanceof RutileOre && ((RutileOre) inputInventory[i]).getStack()>0) { 
					((RutileOre) inputInventory[i]).setStack( ((RutileOre) inputInventory[i]).getStack() -1); //Removes one from stack
					
					//If first item being smelted then make a new item to add on to later
					if(outputInventory[i]==null) {
						outputInventory[i]= new Titanium(1);
					}else {
						((Titanium) outputInventory[i]).setStack(((Titanium) outputInventory[i]).getStack() +1 ); //Adds one to new stack
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


}//end of LargeFurnace class