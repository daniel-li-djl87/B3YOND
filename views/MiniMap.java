	package b3yond.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import b3yond.common.Tile;
import b3yond.tiles.*;

/**
 * MiniMap.java
 * @author Aaron Ng
 * @date Dec 14, 2017
 * Draws simplified minimap as bufferedimage
 */
public class MiniMap extends BufferedImage {
	
	/**
	 * MiniMap
	 * Constructor for MiniMap
	 * @param tileMap, imageType
	 * @return none
	 */
	public MiniMap(Tile [][] tMap, int imageType) {
		super(tMap[0].length, tMap.length, imageType); // Return to superclass
		
		Graphics2D g2d = this.createGraphics(); // Create graphics for image
		
		// Loop through tilemap drawing different colored pixels based on tile type
		for (int i = 0; i < tMap.length; i++){
			for (int j = 0; j < tMap[0].length; j++){
				if (tMap[i][j] instanceof GrassTile){
					// Draw green
					g2d.setColor(new Color(107, 206, 49));
				}else if (tMap[i][j] instanceof WaterTile){
					// Draw blue
					g2d.setColor(new Color(64, 96, 214));
				}else if (tMap[i][j] instanceof ForestTile){
					// Draw dark green
					g2d.setColor(new Color(35, 102, 40));
				}else if (tMap[i][j] instanceof MountainTile){
					// Draw brown
					g2d.setColor(new Color(89, 64, 47));
				}else if (tMap[i][j] instanceof ConoreTile){
					// Draw light gray
					g2d.setColor(new Color(109, 107, 107));
				}else if (tMap[i][j] instanceof KannaiteTile){
					// Draw pink
					g2d.setColor(new Color(142, 19, 134));
				}else if (tMap[i][j] instanceof FuelTile){
					// Draw dark yellow
					g2d.setColor(new Color(168, 130, 35));
				}else if (tMap[i][j] instanceof OilTile){
					// Draw black
					g2d.setColor(new Color(0, 0, 0));
				}else if (tMap[i][j] instanceof DesertTile){
					// Draw beige
					g2d.setColor(new Color(255, 246, 183));
				}else {
					g2d.setColor(Color.white);
				} // End if
				g2d.fillRect(i, j, 1, 1);
			} // End for loop
		} // End for loop
	} // End MiniMap Constructor
	
} // End MiniMap class