
package b3yond.buildings;
import java.awt.Dimension;
import b3yond.buildings.Building;
import b3yond.items.*;

/**
 * Pipe.java
 * @author Naymul Mohammed
 * @date January 14, 2018
 * This building moves items across the map
 */
public class Pipe extends Building {
	
	private String input;
	private String output;
	private Item[] inputInventory;
	private Item[] outputInventory;
	

	/**
	 * Pipe
	 * Constructor for the pipe building
	 * @param health,level,input,output
	 */
	public Pipe(int health, int level) {
		super(health, level, new Dimension(1,1),1,0);
		this.setDim(new Dimension(2,2));
		this.inputInventory=new Item[1];
		this.input="left";
		this.output="right";

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


	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	
	/**
	 * switchPipe
	 * switches the input and output of the pipe
	 * @param nothing
	 * @return void
	 */
	public void switchPipe() {

		//Change the input and output for every possible case
		if( (this.input.equals("left")) && (this.output.equals("right")) ) {
			this.input="right";
			this.output="left";
			
		}else if( (this.input.equals("right")) && (this.output.equals("left")) ){
			this.input="up";
			this.output="down";
			
		}else if( (this.input.equals("up")) && (this.output.equals("down")) ) {
			this.input="down";
			this.output="up";
			
		}else if( (this.input.equals("down")) && (this.output.equals("up")) ) {
			this.input="up";
			this.output="right";
			
		}else if( (this.input.equals("up")) && (this.output.equals("right")) ) {
			this.input="right";
			this.output="up";
		}
		
		//Change the input and output for every possible case
		if( (this.input.equals("right")) && (this.output.equals("up")) ) {
			this.input="up";
			this.output="left";
			
		}else if( (this.input.equals("up")) && (this.output.equals("left")) ) {
			this.input="left";
			this.output="up";
			
		}else if( (this.input.equals("left")) && (this.output.equals("up")) ) {
			this.input="left";
			this.output="down";
			
		}else if( (this.input.equals("left")) && (this.output.equals("down")) ) {
			this.input="down";
			this.output="left";
			
		}else if( (this.input.equals("down")) && (this.output.equals("left")) ) {
			this.input="down";
			this.output="right";
		}else if( (this.input.equals("down")) && (this.output.equals("right")) ) {
			this.input="right";
			this.output="down";
			
		}else if( (this.input.equals("right")) && (this.output.equals("down")) ) {
			this.input="left";
			this.output="right";
			
		}
		
	}

}
