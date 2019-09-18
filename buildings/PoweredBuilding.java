/**
 * PoweredBuilding.java
 * @author Naymul Mohammed
 * @Date December 14, 2017
 * PoweredBuildingClass used for building objects that require power to operate
 */


package b3yond.buildings;

import java.awt.Dimension;

public abstract class PoweredBuilding extends Building {

	private boolean power;
	
	/**
	 * PoweredBuilding
	 * Constructor for PoweredBuilding
	 * @param health,level
	 * @return none
	 */
	PoweredBuilding(int health, int level, Dimension dim, int inputInventorySize, int outputInventorySize) {
		super(health, level,dim,inputInventorySize,outputInventorySize);
		this.power=false;
	}



	//Getters
	public boolean getPower() {
		return power;
	}//end of getPower


	//Setters
	public void setPower(boolean power) {
		this.power = power;
	}//end of setPower
	
}
