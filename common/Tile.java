package b3yond.common;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Tile.java
 * @author Aaron Ng
 * @Date Dec 2, 2017
 * @param <E>
 * Tile class that stores its map coordinates and buildings it contains
 */
public abstract class Tile {

	private b3yond.buildings.Building building;
	private BufferedImage img;
	
	private boolean canBuild = true;
	private boolean powered = false;

	/**
	 * Tile
	 * Constructor for tile
	 * @param xMapPos, yMapPos, xSize, ySize
	 * @return none
	 */
	public Tile(BufferedImage image) {
		building = null;
		this.img = image;
	} // End Tile constructor

	/**
	 * getBuilding
	 * Returns building on tile
	 * @param none
	 * @return the building
	 */
	public b3yond.buildings.Building getBuilding() {
		return building;
	} // End getBuilding method

	/**
	 * setBuilding
	 * Assigns new building value to building
	 * @param building; the building to set
	 * @return none
	 */
	public void setBuilding(b3yond.buildings.Building building2) {
		this.building = building2;
		setCanBuild(false);
	} // End setBuilding method
	
	
	
	
	/**
	 * resize
	 * Resizes an image to new size
	 * @param image, newW, newH, image new width and height
	 * @return BufferedImage
	 */
	private static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	/**
	 * removeBuilding
	 * Clears value of building
	 * @param none
	 * @return none
	 */
	public void removeBuilding() {
		this.building = null;
		setCanBuild(true);
	} // End removeBuilding method

	/**
	 * hasBuilding
	 * Checks if tile has building
	 * @param none
	 * @return boolean 
	 */
	public boolean hasBuilding() {
		if (this.building == null) {
			return false;
		}else {
			return true;
		} // End if
	} // End hasBuilding method

	/**
	 * getImg
	 * Gets tile image
	 * @param none
	 * @return the img
	 */
	public BufferedImage getImg() {
		return img;
	} // End getImg method

	/**
	 * setImg
	 * Assigns new image to tile
	 * @param the img to set
	 * @return none
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
	} // End setImg method
	
	/**
	 * draw
	 * Draws tile to screen with given coordinates and size
	 * @param Graphics g, int coordinate x, int coordinate y, int degree of zoom
	 * @return none
	 */
	public void draw(Graphics g, int x, int y, int zoom) {
		g.drawImage(img, x, y, zoom, zoom, null);
	} // End draw method

	public boolean getCanBuild() {
		return canBuild;
	}

	public void setCanBuild(boolean canBuild) {
		this.canBuild = canBuild;
	}

	public boolean getPowered() {
		return powered;
	}

	public void setPowered(boolean powered) {
		this.powered = powered;
	}


} // End Tile Class
