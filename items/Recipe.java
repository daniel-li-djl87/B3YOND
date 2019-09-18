package b3yond.items;

/**
 * Receipe.java
 * @author Naymul Mohammed
 * @date December 17,2017
 *  Used to store the recipes of buildings
 */
public class Recipe {
	
	private Item[] input;
	private Item[] output;

	/**
	 * Recipe
	 * Constructor for recipe
	 * @param input,output
	 * @return nothing
	 */
	public Recipe(Item [] input, Item [] output) {
	this.setInput(input);
	this.setOutput(output);
	}//end of constructor

	/**
	 * @return the input
	 */
	public Item[] getInput() {
		return input;
	}//end of getInput

	/**
	 * @param input the input to set
	 */
	public void setInput(Item[] input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public Item[] getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(Item[] output) {
		this.output = output;
	}
	
	

}
