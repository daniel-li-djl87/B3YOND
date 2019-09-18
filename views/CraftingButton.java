package b3yond.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import b3yond.items.Aluminum;
import b3yond.items.Coal;
import b3yond.items.Copper;
import b3yond.items.Glass;
import b3yond.items.Iron;
import b3yond.items.Item;
import b3yond.items.Lithium;
import b3yond.items.Nickel;
import b3yond.items.Steel;
import b3yond.items.Stone;
import b3yond.items.Titanium;
import b3yond.items.Uranium;
import b3yond.items.Wood;

/**
 * CraftingButton.java
 * @author Aaron Ng
 * @param <E>
 * @date Jan 9, 2018
 * Custom button that stores a specific recipe for crafting
 */
public class CraftingButton <E> extends JButton {
	
	private Item[] recipe;
	private E output;
	private String desc;
	
	/**
	 * CraftingButton
	 * Constructor for CraftingButton
	 * @param none
	 * @return none
	 */
	public CraftingButton() {
		
		this.setCost(null);
		this.setDesc("");

	} // End CraftingButton class
	
	/**
	 * setImage
	 * Sets button image icon to custom dimensions
	 * @param String image reference name, int dimensions
	 */
	public void setImage(String imgName, int width, int height) {
		// Draw image icon onto button
		BufferedImage img = null;
		if (imgName.equals("null")) {
			this.setIcon(null);
		} else {

			try {
				img = ImageIO.read(getClass().getResource("/b3yond/resources/" + imgName + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("The image cannot be found");
			} // End try catch statement
			
			Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage dimg = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2d = dimg.createGraphics();
			g2d.drawImage(tmp, 0, 0, null);
			g2d.dispose();

			this.setIcon(new ImageIcon(dimg));
		} // End if
	} // End setImage method
	
	/**
	 * getCraftable
	 * @param total list of resources available
	 * @return boolean; whether the recipe can be crafted or not
	 */
	public boolean getCraftable(Item[] itemList) {
		
		boolean craftable = true;
		
		for (int i = 0; i < recipe.length; i++) {
			
			if (recipe[i] instanceof Iron) {
				if (recipe[i].getStack() > itemList[0].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Copper) {
				if (recipe[i].getStack() > itemList[1].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Steel) {
				if (recipe[i].getStack() > itemList[2].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Nickel) {
				if (recipe[i].getStack() > itemList[3].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Aluminum) {
				if (recipe[i].getStack() > itemList[4].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Titanium) {
				if (recipe[i].getStack() > itemList[5].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Lithium) {
				if (recipe[i].getStack() > itemList[6].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Wood) {
				if (recipe[i].getStack() > itemList[7].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Coal) {
				if (recipe[i].getStack() > itemList[8].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Uranium) {
				if (recipe[i].getStack() > itemList[9].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Stone) {
				if (recipe[i].getStack() > itemList[10].getStack()) {
					craftable = false;
				}
			}else if (recipe[i] instanceof Glass) {
				if (recipe[i].getStack() > itemList[11].getStack()) {
					craftable = false;
				}
			} // End if
			
		} // End for loop
		
		return craftable;
	} // End getCraftable method
	
	public Item[] getCost() {
		return recipe;
	}

	public void setCost(Item[] recipe) {
		this.recipe = recipe;		
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public E getOutput() {
		return output;
	}

	public void setOutput(E output) {
		this.output = output;
	}
	
} // End CraftingButton class
