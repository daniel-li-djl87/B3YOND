package b3yond.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import b3yond.buildings.Building;
import b3yond.items.Item;

/**
 * StorageButton.java
 * @author Aaron Ng & Daniel Li
 * @date Dec 14, 2017
 * Custom JButton that stores and returns an item
 */
abstract public class BuildingButton <E> extends JButton {

	private E item;
	private Building tempBuilding;

	/**
	 * StorageButton
	 * Constructor for StorageButton
	 * @param none
	 * @return none
	 */
	abstract public void setBuildingItem(Item item);

	
	public BuildingButton() {
		item = null;
		setFocusable(false);
	} // End StorageButton constructor
	
	
	public Building getBuilding() {
		return this.tempBuilding;
	}
	
	public void setBuilding (Building tempBuilding) {
		this.tempBuilding = tempBuilding;
	}

	public E getItem() {
		return this.item;
	} // End getItem method

	public void setItem(E item) {
		this.item = item;
		setBuildingItem((Item) item);
	} // End setItem method

	
	@Override
    public JToolTip createToolTip() {
        //keep default behaviour 
		if (tempBuilding == null) {
			JToolTip toReturn = super.createToolTip();
			return toReturn;
		}
		this.updateToolTip();
		for (int i = 0; i < tempBuilding.getInputInventorySize(); i++) {
			Item item = tempBuilding.getInputItem(i);
			if (item == null) {
				this.setItem(null);
				this.setImage("null");
			}
		}

		JToolTip toReturn = super.createToolTip();
		return toReturn;
    }
	
	/**
	 * updateToolTip
	 * Updates buttons to display item in StorageButton
	 * @param none
	 * @return none
	 */
	public void updateToolTip() {
		if (item == null) {
			this.setToolTipText("");
		}else if (item instanceof Item) {
			this.setToolTipText(item.getClass().getSimpleName() + ": " + ((Item) item).getStack());
		}else if (item instanceof Building) {
			this.setToolTipText(item.getClass().getSimpleName());
		}else {
			this.setToolTipText("Unknown Item");
		} // End if		
	} // End updateToolTip method

	/**
	 * setImage
	 * Sets new image icon for button
	 * @param img
	 * @return none
	 */
	public void setImage(String str) {
		BufferedImage img = null;
		if (str.equals("null")) {
			this.setIcon(null);
		} else {

			try {
				img = ImageIO.read(getClass().getResource("/b3yond/resources/" + str + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("The image cannot be found");
			}
			img = resize(img, 32, 32);
			this.setIcon(new ImageIcon(img));
		}

	} // End setImage class
	
	/**
	 * resize
	 * Resizes given buffered image to specified dimensions
	 * @param Image, x-Dimension, y-Dimension
	 * @return Resized image
	 */
	private static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	} // End resize method


} // End StorageButton class
