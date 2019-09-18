package b3yond.buildings;

import java.awt.Dimension;

import b3yond.items.Item;

/**
 * WindTurbine.java
 * @author Aaron Ng
 * @date Jan 10, 2018
 * Freely generates power
 */
public class WindTurbine extends PoweredBuilding {

	/**
	 * WindTurbine
	 * Constructor for WindTurbine
	 * @param health
	 * @param level
	 */
	public WindTurbine(int health, int level) {
		super(health, level, new Dimension(2,3),1,0);
		this.setDim(new Dimension(2,3));
		setPower(true);
	}
	
	
	@Override
	public Item getInputItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeInputItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addInputItem(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addInputItem(Item item, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public Item getOutputItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeOutputItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOutputItem(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addOutputItem(Item item, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
