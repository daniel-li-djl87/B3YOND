package b3yond.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import b3yond.buildings.Building;
import b3yond.common.Clock;
import b3yond.common.Map;

/**
 * GamePanel.java
 * @author Aaron Ng
 * @Date Dec 2, 2017
 * Panel where tile map is drawn
 */
class GamePanel extends JPanel implements KeyListener, MouseWheelListener{

	private Map map;
	private Dimension dim;
	private Clock clock;
	/**
	 * GamePanel
	 * Constructor for GamePanel
	 * @param none
	 * @return none
	 */
	GamePanel(Dimension dim, String mapStr){
		this.dim = dim;
		if (mapStr.equals("")) {
			map = new Map(dim);
		}else {
			map = new Map(dim, mapStr);
		}
		clock = new Clock();
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
		addMouseWheelListener(this);
	} // End GamePanel constructor

	/**
	 * getMap
	 * Gets current map
	 * @param none
	 * @return the map
	 */
	public Map getMap() {
		return this.map;
	} // End getMap method

	public static List<Building> buildingList = new ArrayList<Building>();
	public static int buildingX;
	public static int buildingY;
	
	@Override
	/**
	 * paint
	 * Overrides paint method to draw map
	 * @param Graphics g
	 * @return none
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		clock.update(); // Update time on clock
		map.updatePosition(clock);
		map.draw(g); // Draw tile map
		// Draw fps counter
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.setColor(Color.WHITE);
		g.drawString(clock.getFrameRate(), 10, dim.height - 10);
		//
		for(Building b : buildingList) {
			BufferedImage bi = GameWindow.gameWindow.setBuildingImage(b);
			BufferedImage newImage = resize(bi,(int)b.getDim().getWidth()*map.getTileSize() ,(int)b.getDim().getHeight()*map.getTileSize() );
			g.drawImage(newImage, b.x*map.getTileSize() - (int)map.getX(),b.y*map.getTileSize() - (int) map.getY(), null);
		}
		
		repaint(); // Request panel refresh
	} // End paint method
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  

	@Override
	/**
	 * keyPressed
	 * Overrides key presses to change movement directions
	 * @param KeyEvent e
	 * @return none
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'a' :
			map.setXDir(-1);
			break;
		case 'd':
			map.setXDir(1);
			break;
		case 'w' :
			map.setYDir(-1);
			break;
		case 's' :
			map.setYDir(1);
			break;
		default :
			break;	
		} // End switch statement
	} // End keyPressed method

	@Override
	/**
	 * keyReleased
	 * Overrides key releases to change movement directions
	 * @param KeyEvent e
	 * @return none
	 */
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == 'a' || key == 'd') {
			map.setXDir(0);
		}else if (key == 'w' || key == 's'){
			map.setYDir(0);
		} // End if statement
	} // End keyReleased method

	@Override
	/**
	 * keyTyped
	 * Overrides key typed method
	 * @param KeyEvent e
	 * @return none
	 */
	public void keyTyped(KeyEvent e) {
		// Determine which direction to move
		switch (e.getKeyChar()) {
		case 'b' : // Draw easter egg
			map.toggleBestTrap();
		default :
			break;
		} // End switch statement
	} // End keyTyped method

	@Override
	/**
	 * mouseWheelMoved
	 * Overrides mouse wheel for map zoom feature
	 * @param MouseWheelEvent e
	 * @return none
	 */
	public void mouseWheelMoved(MouseWheelEvent e) {
		// Determine if mouse scrolled up or down
		if (e.getWheelRotation() < 0) {
			if (map.getTileSize() < 150) { // Make sure zoomed tiles are drawn no larger than 150 pixels
				map.setTileSize((int)Math.ceil(map.getTileSize() * 1.02)); // Multiply the pixel size to have linear zoom effect
				//map.setX((float) (map.getX() + (dim.getWidth()/map.getTileSize()) * (3)));
				//map.setY((float) (map.getY()+ (dim.getHeight()/map.getTileSize()) * (3)));
			} // End if
		}else if (e.getWheelRotation() > 0) {
			if (map.getTileSize() > 40) { // Make sure zoomed tiles are drawn no smaller than 40 pixels
				map.setTileSize((int)Math.floor(map.getTileSize() * 0.98)); // Multiply the pixel size to have linear zoom effect
				//				map.setX((float) (map.getX()-(dim.getWidth()/map.getTileSize()) * (3)));
				//				map.setY((float) (map.getY()-(dim.getHeight()/map.getTileSize()) * (3)));
			} // End if
		} // End if
	} // End mouseWheelMoved method

} // End GamePanel class