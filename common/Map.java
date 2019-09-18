package b3yond.common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import b3yond.buildings.*;
import b3yond.items.Item;
import b3yond.tiles.*;



/**
 * Map.java
 * @author Aaron Ng, Connor Mahon
 * @Date Dec 2, 2017
 * Map class storing 2D array of tiles with methods to draw map
 */
public class Map {

	float x, y; // Player scrolled coordinates
	private int xDir, yDir; // Direction player is headed
	private int tSize;
	private Tile[][] tileMap;
	private BufferedImage dirt;
	private BufferedImage kanna;
	private BufferedImage hideri;
	private BufferedImage water;
	private BufferedImage conore;
	private BufferedImage desert;
	private BufferedImage fuel;
	private BufferedImage forest;
	private BufferedImage mountain;
	private BufferedImage oil;
	private boolean bestTrap;
	private Dimension dim;

	//Vars for map generation
	int conoreCount = 0, kannaiteCount = 0, fuelCount = 0, forestCount = 0;
	boolean conorePass = false;
	boolean kannaitePass = false;
	boolean fuelPass = false;
	boolean forestPass = false;
	/**
	 * Map
	 * Constructor for Map
	 * @param none
	 * @return none
	 */
	public Map(Dimension dim){
		this.dim = dim;
		initiateMap();
		createWorldMap();
	} // End Map constructor for new game

	public Map(Dimension dim, String mapStr) {
		this.dim = dim;
		initiateMap();
		stringToMap(mapStr);
	}

	public void initiateMap() {
		tileMap = new Tile[200][200];
		setX(1);
		setY(1);
		setXDir(0);
		setYDir(0);
		setTileSize(50);
		try {
			dirt = ImageIO.read(getClass().getResource("/b3yond/resources/dirt.jpg")); // Load menu background image
			water =  ImageIO.read(getClass().getResource("/b3yond/resources/water.png"));
			conore = ImageIO.read(getClass().getResource("/b3yond/resources/conore.png"));
			kanna = ImageIO.read(getClass().getResource("/b3yond/resources/kannaite.png")); // Load menu background image
			hideri = ImageIO.read(getClass().getResource("/b3yond/resources/hideri.png")); // Load menu background image
			desert = ImageIO.read(getClass().getResource("/b3yond/resources/desert.jpg"));
			fuel = ImageIO.read(getClass().getResource("/b3yond/resources/fuel.png"));
			forest = ImageIO.read(getClass().getResource("/b3yond/resources/forest.jpg"));
			mountain =  ImageIO.read(getClass().getResource("/b3yond/resources/mountain.jpg"));
			oil =  ImageIO.read(getClass().getResource("/b3yond/resources/oil.jpg"));
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} // End try catch statement
		bestTrap = false;
	}//End of initiate map method

	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ World Generation method @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

	/**
	 * createWorldMap
	 * Method that generates map
	 * @param none
	 * @return none
	 */
	public void createWorldMap() {
		do {
			//		@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Recursive map generation method over here@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//Generation loop 1 *ADDS VEIN STARTS AND GRASS*

			for (int i = 0; i < tileMap.length; i++) {
				for (int j = 0; j < tileMap[i].length; j++) {

					if (Math.random()*10000>9999) { //randomly spawn a conore vein start
						tileMap[i][j] = new ConoreTile (conore, true);
						conoreCount++;
					}else if (Math.random()*10000>9999){
						tileMap[i][j] = new KannaiteTile(kanna, true);
						kannaiteCount++;
					}else if(Math.random()*10000>9999){
						tileMap[i][j] = new FuelTile(fuel, true);
						fuelCount++;
					}else if(Math.random()*10000>9999) {
						tileMap[i][j] = new ForestTile(forest, true);
						forestCount++;
					}else if(Math.random()*10000>9999){
						tileMap[i][j] = new OilTile(oil,true);
					}else if(Math.random()*10000>9997) {
						tileMap[i][j] = new MountainTile(mountain, true);
					}else {
						tileMap[i][j] = new GrassTile(dirt);
					}

				} // End for loop 
			} // End for loop
			//End generation loop 1

			//Generation loop 2 *EXPANDS ON THE VEINS*
			do {
				if (conoreCount<6) {
					tileMap[(int)Math.random()*200][(int)Math.random()*200] = new ConoreTile (conore,true);
					conoreCount++;
				}else {
					conorePass = true;
				}
				if (kannaiteCount<6) {
					tileMap[(int)Math.random()*200][(int)Math.random()*200] = new KannaiteTile (kanna,true);
					kannaiteCount++;
				}else {
					kannaitePass = true;
				}
				if (fuelCount<6) {
					tileMap[(int)Math.random()*200][(int)Math.random()*200] = new FuelTile (fuel,true);
					fuelCount++;
				}else {
					fuelPass = true;
				}
				if (forestCount<6) {
					tileMap[(int)Math.random()*200][(int)Math.random()*200] = new ForestTile (forest,true);
					forestCount++;
				}else {
					forestPass = true;
				}

				System.out.println("Conore: " + conoreCount + " - " + conorePass);
				System.out.println("Kannaite: " + kannaiteCount + " - " + kannaitePass);
				System.out.println("Fuel: " + fuelCount + " - " + fuelPass);
				System.out.println("Forest: " + forestCount + " - " + forestPass);
				System.out.println("");
			}while(!conorePass || !kannaitePass || !fuelPass || !forestPass);

			for (int i = 0; i < tileMap.length; i++) {
				for (int j = 0; j < tileMap[i].length; j++) {
					buildConore(i,j);
					buildKannaite(i,j);
					buildFuel(i,j);
					buildForest(i,j);
				}//End of for loop
			}//End of for loop
			//End of generation loop 2

			//Generation loop 3 *COUNT ORES*
			int loop3Count = 0;
			conorePass = false;
			kannaitePass = false;
			fuelPass = false;
			forestPass = false;
			do {
				conoreCount = 0;
				kannaiteCount = 0;
				fuelCount = 0;
				forestCount = 0;
				for (int i = 0; i < tileMap.length; i++) {
					for (int j = 0; j < tileMap[i].length; j++) {
						if (tileMap[i][j] instanceof ConoreTile) {
							conoreCount++;
						}else if(tileMap[i][j] instanceof KannaiteTile) {
							kannaiteCount++;
						}else if(tileMap[i][j] instanceof FuelTile) {
							fuelCount++;
						}else if(tileMap[i][j] instanceof ForestTile) {
							forestCount++;
						}
					}
				}
				for (int i = 0; i < tileMap.length; i++) {
					for (int j = 0; j < tileMap[i].length; j++) {
						if (conoreCount < 220) {
							buildConore(i,j);
						}else {
							conorePass = true;
						}
						if (kannaiteCount < 220) {
							buildKannaite(i,j);
						}else {
							kannaitePass = true;
						}
						if (fuelCount< 220) {
							buildFuel(i,j);
						}else {
							fuelPass = true;
						}
						if (forestCount < 220) {
							buildForest(i,j);
						}else {
							forestPass = true;
						}
					}
				}
				System.out.println("Conore: " + conoreCount + " - " + conorePass);
				System.out.println("Kannaite: " + kannaiteCount + " - " + kannaitePass);
				System.out.println("Fuel: " + fuelCount + " - " + fuelPass);
				System.out.println("Forest: " + forestCount + " - " + forestPass);
				System.out.println("");
				loop3Count++;
				if (loop3Count > 100) {
					System.out.println("map generation failed! restarting");
					break;
				}
			}while(!conorePass || !kannaitePass || !fuelPass || !forestPass);
			//END OF LOOP 3

			//LOOP 4: THE MOUNTAIN & OIL LOOP
			for (int i = 0; i < tileMap.length; i++) {
				for (int j = 0; j < tileMap[i].length; j++) {
					buildMountain(i,j);
					buildOil(i,j);
				}
			}//End of THE Mountain & OIL LOOP

			//ADD MINIMUM AMOUNT OF ORES

			//Generation Loop 5 *FINAL SETUP*
			for (int i = 0; i < tileMap.length; i++) {
				for (int j = 0; j < tileMap[i].length; j++) {

					if(i == 1 || j == 1 || i == tileMap.length-2 || j == tileMap[i].length-2) {
						tileMap[i][j] = new DesertTile(desert);
					}

					if (i == 0 || j == 0 || i == tileMap.length-1 || j == tileMap[i].length-1) {
						tileMap[i][j] = new WaterTile(water);
					}

				}//End of for loop
			}//End of for loop
			//End of generation loop 5
			//mapToString();//TEST RUN
		} while(!conorePass || !kannaitePass || !fuelPass || !forestPass); // End createWorldMap method
	}

	private void buildOil(int i, int j) {
		if (tileMap[i][j] instanceof OilTile) {
			if(((OilTile)tileMap[i][j]).getVeinStart()){
				int size = (int) (Math.random()*6+4);
				for (int x = 0; x<size;x++) {
					for (int y = 0; y<size;y++) {
						if (Math.random()*10>9) {
							if (i+x<tileMap.length && j+y<tileMap[0].length) {
								tileMap[i+x][j+y] = new OilTile(oil,false);
								((OilTile)tileMap[i+x][y+j]).setQuantity((int)(4000+ Math.random() * ((7000-4000) +1)));
							}
						}
					}
				}
			}
		}
	}

	private void buildMountain(int i, int j) {
		if (tileMap[i][j] instanceof MountainTile) {
			if(((MountainTile)tileMap[i][j]).getVeinStart()){
				int type = (int) (Math.random()*4);
				if (type == 0) {
					for(int x=0;x<Math.random()+6+2;x++) {
						if (i+x<tileMap.length && j+x<tileMap[0].length) {
							tileMap[i+x][j+x] = new MountainTile(mountain, false);
						}
					}
				}else if(type == 1) {
					for(int x=0;x<Math.random()+6+2;x++) {
						if (i-x>0 && j+x<tileMap[0].length) {
							tileMap[i-x][j+x] = new MountainTile(mountain, false);
						}
					}
				}else if(type == 2) {
					for(int x=0;x<Math.random()+6+2;x++) {
						if (i+x<tileMap.length && j-x>0) {
							tileMap[i+x][j-x] = new MountainTile(mountain, false);
						}
					}
				}else if(type == 3) {
					for(int x=0;x<Math.random()+6+2;x++) {
						if (i-x>0 && j-x>0) {
							tileMap[i-x][j-x] = new MountainTile(mountain, false);
						}
					}
				}
			}
		}
	}

	private void buildFuel (int i, int j) {
		if (tileMap[i][j] instanceof FuelTile) {
			if (((FuelTile) tileMap[i][j]).getVeinStart()){
				int veinWide = (int) (Math.random()*12+2);
				int veinLong = (int) (Math.random()*12+2);
				for (int x = 0; x<veinWide;x++) {
					for (int y = 0; y<veinLong;y++) {
						if (i+x<tileMap.length && j+y<tileMap[0].length) {
							tileMap[i+x][y+j] = new FuelTile(fuel, false);
							((FuelTile)tileMap[i+x][y+j]).setQuantity((int)(4000+ Math.random() * ((7000-4000) +1)));
						}
					}
				}
				((FuelTile) tileMap[i][j]).setVeinStart(true);
			}
		}
	}

	private void buildKannaite (int i, int j) {
		if (tileMap[i][j] instanceof KannaiteTile) {
			if (((KannaiteTile) tileMap[i][j]).getVeinStart()){
				int veinWide = (int) (Math.random()*10+2);
				int veinLong = (int) (Math.random()*10+2);
				for (int x = 0; x<veinWide;x++) {
					for (int y = 0; y<veinLong;y++) {
						if (i+x<tileMap.length && j+y<tileMap[0].length) {
							tileMap[i+x][y+j] = new KannaiteTile(kanna, false);
							((KannaiteTile)tileMap[i+x][y+j]).setQuantity((int)(4000+ Math.random() * ((7000-4000) +1)));
						}
					}
				}
				((KannaiteTile) tileMap[i][j]).setVeinStart(true);
			}
		}
	}

	private void buildConore (int i, int j) {
		if (tileMap[i][j] instanceof ConoreTile) {
			if (((ConoreTile) tileMap[i][j]).getVeinStart()){
				int veinWide = (int) (Math.random()*10+2);
				int veinLong = (int) (Math.random()*10+2);
				for (int x = 0; x<veinWide;x++) {
					for (int y = 0; y<veinLong;y++) {
						if (i+x<tileMap.length && j+y<tileMap[0].length) {
							tileMap[i+x][y+j] = new ConoreTile(conore, false);
							((ConoreTile)tileMap[i+x][y+j]).setQuantity((int)(4000+ Math.random() * ((7000-4000) +1)));
						}
					}
				}
				((ConoreTile) tileMap[i][j]).setVeinStart(true);
			}
		}
	}

	private void buildForest (int i, int j) {
		if (tileMap[i][j] instanceof ForestTile) {
			if (((ForestTile) tileMap[i][j]).getVeinStart()){
				int veinWide = (int) (Math.random()*14+4);
				int veinLong = (int) (Math.random()*14+4);
				for (int x = 0; x<veinWide;x++) {
					for (int y = 0; y<veinLong;y++) {
						if (i+x<tileMap.length && j+y<tileMap[0].length) {
							tileMap[i+x][y+j] = new ForestTile(forest, false);
							((ForestTile)tileMap[i+x][y+j]).setQuantity((int)(4000+ Math.random() * ((7000-4000) +1)));
						}
					}
				}
				((ForestTile) tileMap[i][j]).setVeinStart(true);
			}
		}
	}

	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Getter/Setter methods @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

	/**
	 * getX
	 * Gets view x coordinate
	 * @param none
	 * @return x
	 */
	public float getX() {
		return x;
	} // End getX method

	/**
	 * setX
	 * Sets new view x coordinate
	 * @param x; the x to set
	 * @return none
	 */
	public void setX(float x) {
		this.x = x;
	} // End setX method

	/**
	 * getY
	 * Gets view y coordinate
	 * @param none
	 * @return y
	 */
	public float getY() {
		return y;
	} // End getY method

	/**
	 * setY
	 * Sets new view y coordinate
	 * @param y; the y to set
	 * @return none
	 */
	public void setY(float y) {
		this.y = y;
	} // End setY method

	/**
	 * getTileSize
	 * Gets size (pixels) of each tile
	 * @param none
	 * @return the tSize
	 */
	public int getTileSize() {
		return tSize;
	} // End getTileSize method

	/**
	 * setTileSize
	 * Sets size (pixels) of each tile
	 * @param tSize; the tSize to set
	 * @return none
	 */
	public void setTileSize(int tSize) {
		this.tSize = tSize;
	} // End setTileSize method

	/**
	 * getXDir
	 * Gets direction of horizontal movement
	 * @param none
	 * @return the xDir
	 */
	public int getXDir() {
		return xDir;
	} // End getXDir method

	/**
	 * setXDir
	 * Sets new direction of horizontal movement
	 * @param int the xDir to set
	 * @return none
	 */
	public void setXDir(int xDir) {
		this.xDir = xDir;
	} // End setXDir method

	/**
	 * getYDir
	 * Gets the direction of vertical movement
	 * @param none
	 * @return the yDir
	 */
	public int getYDir() {
		return yDir;
	} // End getYDir method

	/**
	 * setYDir
	 * Sets new direction of horizontal movement
	 * @param yDir the yDir to set
	 * @return none
	 */
	public void setYDir(int yDir) {
		this.yDir = yDir;
	} // End setYDir method

	/**
	 * getTileMap
	 * Gets the tile map
	 * @param none
	 * @return Tile 2D array
	 */
	public Tile[][] getTileMap(){
		return tileMap;
	} // End getTileMap method

	/**
	 * setTileMap
	 * Sets new tile map
	 * @param tileMap
	 * @return none
	 */
	public void setTileMap(Tile[][] tileMap) {
		this.tileMap = tileMap;
	} // End setTileMap method

	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Non-getter/setter methods @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

	public void toggleBestTrap() {
		bestTrap = !bestTrap;
	}

	/**
	 * draw
	 * Draws out entire tile map
	 * @param Graphics g
	 * @return none
	 */
	public void draw(Graphics g) {
		this.update();
		int drawLength = (int) (dim.getWidth()/tSize) + (int) (x/tSize);
		if (drawLength >= tileMap[0].length-2) {
			drawLength = tileMap[0].length-2;
		}
		int drawHeight = (int) (dim.getHeight()/tSize)+ (int) (y/tSize);
		if (drawHeight>= tileMap.length-2) {
			drawHeight = tileMap.length-2;
		}
		for (int i = (int) (x/tSize); i < drawLength+2; i++) {
			for (int j = (int) (y/tSize); j < drawHeight+2; j++) {
				tileMap[i][j].draw(g, i*tSize - (int)x, j*tSize - (int)y, tSize);
			} // End for loop
		} // End for loop
		if (bestTrap) {
			g.drawImage(hideri, (int)(dim.getWidth()/2) - 332, (int)(dim.getHeight()/2) - 250, 663, 500, null);
		} // End if
	} // End draw method


	public void updatePosition(Clock clock) {
		if ((x > 0 && xDir < 0) || ((x + dim.getWidth()) < (tileMap[0].length * tSize) && xDir > 0)) { // Ensure that the player does not go horizontally out of bounds
			x += xDir * 500 * clock.getElapsedTime();
		} // End if
		if ((y > 0 && yDir < 0) || ((y + dim.getHeight()) < (tileMap.length * tSize) && yDir > 0)) { // Ensure that the player does not go vertically out of bounds
			y += yDir * 500 * clock.getElapsedTime();
		} // End if	
		if ((x+dim.getWidth())>(tileMap[0].length*tSize)){ //Correct for user going out of bounds via zoom
			x += -1 * 500 * clock.getElapsedTime();
		}
		if ((y+dim.getHeight())>(tileMap[0].length*tSize)){ //Correct for user going out of bounds via zoom
			y += -1 * 500 * clock.getElapsedTime();
		}
		if (x<0) {
			x += 1 * 500 * clock.getElapsedTime();
		}
		if (y<0) {
			y += 1 * 500 * clock.getElapsedTime();
		}
	} // End updatePosition method

	public Object getTile(int x2, int y2) {
		return tileMap[x2][y2];
	}

	public Dimension getDim() {
		return this.dim;
	}

	public String mapToString() { //Convert the map data to be sent to the user or to be used to save

		//int testCount = 0;

		String mapStr = "b";//Initialize the string and mark as map with "b"
		String tileStr = "";

		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {//Loop through the tile map

				tileStr = "[";//Indicate new tile

				//Find the tile type and assign to string
				if (tileMap[i][j] instanceof GrassTile) {
					tileStr += "0,0";
				}else if (tileMap[i][j] instanceof WaterTile) {
					tileStr += "1,0";
				}else if (tileMap[i][j] instanceof ForestTile) {
					tileStr += "2";
					tileStr += "," + ((ForestTile)tileMap[i][j]).getQuantity();
				}else if (tileMap[i][j] instanceof MountainTile) {
					tileStr += "3,0";
				}else if (tileMap[i][j] instanceof ConoreTile) {
					tileStr += "4";
					tileStr += "," + ((ConoreTile)tileMap[i][j]).getQuantity();
				}else if (tileMap[i][j] instanceof KannaiteTile) {
					tileStr += "5";
					tileStr += "," + ((KannaiteTile)tileMap[i][j]).getQuantity();
				}else if (tileMap[i][j] instanceof FuelTile) {
					tileStr += "6";
					tileStr += "," + ((FuelTile)tileMap[i][j]).getQuantity();
				}else if (tileMap[i][j] instanceof OilTile) {
					tileStr += "7";
					tileStr += "," + ((OilTile)tileMap[i][j]).getQuantity();
				}else if (tileMap[i][j] instanceof DesertTile) {
					tileStr += "8,0";
				}

				//Check if tile is powered
				if (tileMap[i][j].getPowered()) {
					tileStr += ",p";
				}else {
					tileStr += ",o";
				}

				mapStr += tileStr;
				//testCount++;
				//System.out.println(testCount + ": " + tileStr);				

			}
		}
		//mapStr += "[";
		//System.out.println(mapStr); //TEST OUTPUT
		mapStr += "[1,0,o";
		return mapStr;
	}

	public void stringToMap(String mapStr) {

		String tileVal = ""; //Stores the string of the current tile
		String currentVal = ""; //Stores the string of the current value being determined
		mapStr = mapStr.substring(2);

		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {//Loop through the tile map
				try {
					tileVal = mapStr.substring(0, mapStr.indexOf('['));//Find the current tile in the string
				}catch (Exception e) {
					System.out.println(i + "," + j);
				}
				mapStr = mapStr.substring(mapStr.indexOf('[')+1);//Shorten the map string for later

				currentVal = tileVal.substring(0,tileVal.indexOf(",")); //Determine the type of tile
				tileVal = tileVal.substring(tileVal.indexOf(",")+1); //Shorten the string
				if (currentVal.equals("0")) {
					tileMap[i][j] = new GrassTile(dirt);
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
				}else if (currentVal.equals("1")) {
					tileMap[i][j] = new WaterTile(water);
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
				}else if (currentVal.equals("2")) {
					tileMap[i][j] = new ForestTile(forest, false);
					currentVal = tileVal.substring(0,tileVal.indexOf(","));
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
					((ForestTile)tileMap[i][j]).setQuantity(Integer.parseInt(currentVal));
				}else if (currentVal.equals("3")) {
					tileMap[i][j] = new MountainTile(mountain, false);
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
				}else if (currentVal.equals("4")) {
					tileMap[i][j] = new ConoreTile(conore,false);
					currentVal = tileVal.substring(0,tileVal.indexOf(","));
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
					((ConoreTile)tileMap[i][j]).setQuantity(Integer.parseInt(currentVal));
				}else if (currentVal.equals("5")) {
					tileMap[i][j] = new KannaiteTile(kanna,false);
					currentVal = tileVal.substring(0,tileVal.indexOf(","));
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
					((KannaiteTile)tileMap[i][j]).setQuantity(Integer.parseInt(currentVal));
				}else if (currentVal.equals("6")) {
					tileMap[i][j] = new FuelTile(fuel,false);
					currentVal = tileVal.substring(0,tileVal.indexOf(","));
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
					((FuelTile)tileMap[i][j]).setQuantity(Integer.parseInt(currentVal));
				}else if (currentVal.equals("7")) {
					tileMap[i][j] = new OilTile(oil, false);
					currentVal = tileVal.substring(0,tileVal.indexOf(","));
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
					((OilTile)tileMap[i][j]).setQuantity(Integer.parseInt(currentVal));
				}else if (currentVal.equals("8")) {
					tileMap[i][j] = new DesertTile(desert);
					tileVal = tileVal.substring(tileVal.indexOf(",")+1);
				}else {
					System.out.println("gameBroke");
				}

				if (tileVal.equals("p")) { //Set power if applicable
					tileMap[i][j] .setPowered(true);
				}

			}
		}
	}

	public String buildingToString() {
		String buildingStr = "d";
		boolean[][] checkedTiles = new boolean[tileMap.length][tileMap[0].length];

		for (int i = 0; i < checkedTiles.length; i++) {
			for (int j = 0; j < checkedTiles[i].length; j++) {
				checkedTiles[i][j] = false;
			}
		}

		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {//Loop through the tile map
				if (tileMap[i][j].getBuilding() != null) {
					if (!checkedTiles[i][j]) {
						Dimension buildDim = tileMap[i][j].getBuilding().getDim();
						for (int i2 = i; i2 < buildDim.getWidth()+i; i2++) {
							for (int j2 = j; j2 < buildDim.getHeight()+j; j2++) {
								checkedTiles[i2][j2] = true;
								System.out.println(i2 + "," + j2);
							}
						}
						buildingStr += "[";
						buildingStr += tileMap[i][j].getBuilding().getClass().getSimpleName();
						buildingStr += "," + i + "," + j;
					}
				}
			}
		}
		return buildingStr;
	}

	public ArrayList<Building> stringToBuilding(String buildingStr) {

		String currentBuilding = "";
		String buildType = "";
		int buildX;
		int buildY;
		boolean nextBuilding = false;
		try {
			buildingStr = buildingStr.substring(2);

			ArrayList<Building> buildings = new ArrayList<Building>();

			do {
				nextBuilding = false;
				try {
					currentBuilding = buildingStr.substring(0, buildingStr.indexOf("["));
					buildingStr = buildingStr.substring(buildingStr.indexOf("[")+1);
					nextBuilding = true;
				}catch(Exception e) {
					currentBuilding = buildingStr;
					nextBuilding = false;
				}
				buildType = currentBuilding.substring(0,currentBuilding.indexOf(","));
				currentBuilding = currentBuilding.substring(currentBuilding.indexOf(",")+1);
				buildY = Integer.parseInt(currentBuilding.substring(0,currentBuilding.indexOf(",")));
				buildX = Integer.parseInt(currentBuilding.substring(currentBuilding.indexOf(",")+1));

				Class newBuild;
				Building buildFinal = null;
				try {
					newBuild = Class.forName("b3yond.buildings." + buildType);
					Constructor buildConstruct = newBuild.getConstructor(int.class, int.class);
					buildFinal = (Building) buildConstruct.newInstance(500,1);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Dimension buildingDimensions = (buildFinal.getDim());
				int buildingHeight = (int) buildingDimensions.getHeight();
				int buildingWidth = (int) buildingDimensions.getWidth();

				if (buildX+buildingWidth <= 200 && buildY+buildingHeight <= 200) {
					for (int i = 0; i <buildingWidth; i ++) {
						for ( int j = 0; j <buildingHeight; j++) {
							tileMap[buildY+i][buildX+j].setBuilding(buildFinal);
							System.out.println("Building added: " + buildFinal.getClass().getName() + " - " + (buildY+i) + "," + (buildX+j));
						}
					}
				}



				buildFinal.x=buildY;
				buildFinal.y=buildX;
				buildings.add(buildFinal);
			}while(nextBuilding);

			return buildings;
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * update
	 * refreshes the tiles
	 * @param nothing
	 * @return nothing
	 */
	public void update() {

		for(int p=0;p<200;p++) {
			for(int k=0;k<200;k++) {

				if(tileMap[p][k].getBuilding()!=null) {
					Building m=tileMap[p][k].getBuilding();
					//update the buildings
					tileMap[p][k].getBuilding().update();

					//Check if battery is giving off power
					if( (m instanceof LowTierBattery) || (m instanceof LargeBattery) || (m instanceof IndustrialGradeBattery) ) {

						//If it's a Battery
						LowTierBattery f =(LowTierBattery) tileMap[p][k].getBuilding();	

						//Check if its getting power and charging
						if(checkPowerLine(p,k,3)) {
							powerSwitcher(p,k,2,true); //If it is then give off power
						}else {
							powerSwitcher(p,k,2,f.getCapacity()>0);   ///if it isnt then only give power if there is capacity
						}
						//If a building is generating power add power to tiles;
					}else if( (m instanceof SteamEngine) || (m instanceof HandCrankGenerator) || (m instanceof SteamTurbine) || (m instanceof WindTurbine) ) {

						powerSwitcher(p,k,3,((PoweredBuilding) m).getPower()); //Adds power or remove power based on the status of the generators

						//Powerline only get power from other powerline or buildings
					}else if(m instanceof PowerLine) {

						powerSwitcher(p,k,5,checkPowerLine(p,k,5)); //Checks if the powerline is powered by other buildings then give it to powerSwitch method

					}else if(m instanceof LargePowerLine) {
						powerSwitcher(p,k,10,checkPowerLine(p,k,10)); //Checks if the powerline is powered by other buildings then give it to powerSwitch method

						//If its just a powered building enable it if the tile under it is powered;
					}else if((m instanceof PoweredBuilding)) {
						((PoweredBuilding) tileMap[p][k].getBuilding()).setPower(tileMap[p][k].getPowered());
					}


					//Pipe movement
					if(m instanceof Pipe ) {
						pipeCheck(p,k,m);
					}else if(m.getOutputInventorySize()>0) {
						buildingPipeCheck(p,k,m);
					}


				}
			}
		}
	}

	/**
	 * powerSwitcher
	 * checks the tiles
	 * @param nothing
	 * @return nothing
	 */
	public void powerSwitcher(int x, int y, int radius, boolean power) {		

		for(int k=1;k<radius+1;k++) {      //Add power to tiles around building if its possible
			if(x-k>=0) {					//First check if tile can be checked
				if(power) {
					tileMap[x-k][y].setPowered(true);   //If we are adding power add or else remove
				}else {
					tileMap[x-k][y].setPowered(false);
				}	
			} 
			if(x+k<201) {                          //Repeat for all directions 
				if(power) { 
					tileMap[x+k][y].setPowered(true);
				}else {
					tileMap[x+k][y].setPowered(false);
				}	
			}
			if(y-k>=0) {
				if(power) {
					tileMap[x][y-k].setPowered(true);
				}else {
					tileMap[x][y-k].setPowered(false);
				}	
			}
			if(y+k<201) {
				if(power) {
					tileMap[x][y+k].setPowered(true);
				}else {
					tileMap[x][y+k].setPowered(false);
				}	
			}
		}
	}


	/**
	 * checkPowerLine
	 * Checks to see if the powerline can be powered by anything
	 * @param int x, int y
	 * @return boolean if it is powered
	 */
	public boolean checkPowerLine(int x,int y,int radius) {

		for(int k=1;k<radius+1;k++) {      //Add power to tiles around building if its possible

			//Checks to see if tiles around it can be checked first
			if(x-k>=0) {
				Building m=tileMap[x-k][y].getBuilding();

				//If a acceptable power generating building is near the power line then it is considered powered as well;
				if( (m instanceof PowerLine) || (m instanceof LargePowerLine) || (m instanceof SteamEngine) || (m instanceof HandCrankGenerator) || (m instanceof SteamTurbine) || (m instanceof WindTurbine) ) {
					if(((PoweredBuilding) m).getPower()) {
						((PoweredBuilding) tileMap[x][y].getBuilding()).setPower(true); //Enable if has power
						return true;  
					}
				}
			}	
			if(x+k<201) {
				Building m =tileMap[x+k][y].getBuilding();

				//If a acceptable power generating building is near the power line then it is considered powered as well
				if( (m instanceof PowerLine) || (m instanceof LargePowerLine) || (m instanceof SteamEngine) || (m instanceof HandCrankGenerator) || (m instanceof SteamTurbine) || (m instanceof WindTurbine) ) {
					if(((PoweredBuilding) m).getPower()) {
						((PoweredBuilding) tileMap[x][y].getBuilding()).setPower(true); //Enable if has power
						return true;
					}
				}
			}
			if(y-k>=0) {
				Building m=tileMap[x][y-k].getBuilding();

				//If a acceptable power generating building is near the power line then it is considered powered as well
				if( (m instanceof PowerLine) || (m instanceof LargePowerLine) || (m instanceof SteamEngine) || (m instanceof HandCrankGenerator) || (m instanceof SteamTurbine) || (m instanceof WindTurbine) ) {
					if(((PoweredBuilding) m).getPower()) {
						((PoweredBuilding) tileMap[x][y].getBuilding()).setPower(true); //Enable if has power
						return true;
					}
				}

			}
			if(y+k<201) {
				Building m=tileMap[x][y+k].getBuilding();

				//If a acceptable power generating building is near the power line then it is considered powered as well
				if( (m instanceof PowerLine) || (m instanceof LargePowerLine) || (m instanceof SteamEngine) || (m instanceof HandCrankGenerator) || (m instanceof SteamTurbine) || (m instanceof WindTurbine) ) {
					if(((PoweredBuilding) m).getPower()) {
						((PoweredBuilding) tileMap[x][y].getBuilding()).setPower(true); //Enable if has power
						return true;
					}
				}

			}

		}
		((PoweredBuilding) tileMap[x][y].getBuilding()).setPower(false); //Disable it since no power was found
		return false; //Return false if no power source was found
	}

	/**
	 * pipeCheck
	 * Check if there are any pipes near the building
	 * @param x,y building
	 * @return void
	 */
	public void pipeCheck(int x, int y, Building pipe) {	
		if(pipe.getInputItem(0)!=null) {


			//make variable for item in pipe
			Item pipeItem=pipe.getInputItem(0);

			//If the pipes output is upwards
			if(((Pipe) pipe).getOutput().equals("left")) {

				//error check
				if(x-1>=0) {
					//Check if it can send items up and if its a pipe
					if((tileMap[x-1][y].getBuilding() instanceof Pipe)) {

						Building m=tileMap[x-1][y].getBuilding();
						//Only go into the other pipe if its input is down
						if(((Pipe) m).getInput().equals("right")) {

							//Only add item if there is space or item is already in the pipe
							if(m.getInputItem(0)==null || (m.getInputItem(0).getClass().equals(pipe.getInputItem(0).getClass()))) {

								//add the item to the building
								tileMap[x-1][y].getBuilding().addInputItem(pipe.getInputItem(0));
								tileMap[x][y].getBuilding().removeInputItem(0);
							} //end of if checking items
						} //end of if checking the pipes input
						//if the building isnt a pipe then try to put item into building
					}else if( (tileMap[x-1][y].getBuilding()!=null) && (tileMap[x-1][y].getBuilding().getInputInventorySize()>0)) {
						boolean check=false;
						//for loop to find a spot to put into building
						for(int i=0;i<tileMap[x-1][y].getBuilding().getInputInventorySize();i++) {

							//if item wasnt added already only add if there is space or it exists in the building
							if( !(check) && ( (tileMap[x-1][y].getBuilding().getInputItem(i).getClass().equals(pipeItem.getClass()))   || (tileMap[x][y+1].getBuilding().getInputItem(i)==null))) {
								check=true;  //change to true so it doesnt add anymore items
								tileMap[x-1][y].getBuilding().addInputItem(pipeItem); //adds item to the building
							}	//end of adding check if
						} //end of for loop 	
					}	//end of building add if
				} //end of error check if
			}  //end of pipe  if

			//If the pipes output is downwards
			if(((Pipe) pipe).getOutput().equals("right")) {

				if(x+1<201){
					//Check if it can send items down and if its a pipe
					if((tileMap[x+1][y].getBuilding() instanceof Pipe)) {

						Building m=tileMap[x+1][y].getBuilding();
						//Only go into the other pipe if its input is up
						if(((Pipe) m).getInput().equals("left")) {

							//Only add item if there is space or item is already in the pipe
							if(m.getInputItem(0)==null || (m.getInputItem(0).getClass().equals(pipe.getInputItem(0).getClass()))) {

								//add the item to the building
								tileMap[x+1][y].getBuilding().addInputItem(pipe.getInputItem(0));
								tileMap[x][y].getBuilding().removeInputItem(0);

							} //end of if checking items
						} //end of if checking the pipes input

						//if the building isnt a pipe then try to put item into building
					}else if( (tileMap[x+1][y].getBuilding()!=null) && (tileMap[x+1][y].getBuilding().getInputInventorySize()>0)) {

						boolean check=false;
						//for loop to find a spot to put into building
						for(int i=0;i<tileMap[x+1][y].getBuilding().getInputInventorySize();i++) {

							//if item wasnt added already only add if there is space or it exists in the building
							if( !(check) && ( (tileMap[x+1][y].getBuilding().getInputItem(i).getClass().equals(pipeItem.getClass()))   || (tileMap[x+1][y].getBuilding().getInputItem(i)==null))) {
								check=true;  //change to true so it doesnt add anymore items
								tileMap[x+1][y].getBuilding().addInputItem(pipeItem); //adds item to the building
							}	//end of adding check if
						} //end of for loop 	
					}	//end of building add if
				} //end of error check if
			}  //end of pipe  if


			//If the pipes output is left
			if(((Pipe) pipe).getOutput().equals("up")) {


				if (y-1>=0){
					//Check if it can send items down and if its a pipe
					if((tileMap[x][y-1].getBuilding() instanceof Pipe)) {

						Building m=tileMap[x][y-1].getBuilding();
						//Only go into the other pipe if its input is down
						if(((Pipe) m).getInput().equals("down")) {

							//Only add item if there is space or item is already in the pipe
							if(m.getInputItem(0)==null || (m.getInputItem(0).getClass().equals(pipe.getInputItem(0).getClass()))) {

								//add the item to the building
								tileMap[x][y-1].getBuilding().addInputItem(pipe.getInputItem(0));
								tileMap[x][y].getBuilding().removeInputItem(0);
							} //end of if checking items
						} //end of if checking the pipes input

						//if the building isnt a pipe then try to put item into building
					}else if( (tileMap[x][y-1].getBuilding()!=null) && (tileMap[x][y-1].getBuilding().getInputInventorySize()>0)) {

						boolean check=false;
						//for loop to find a spot to put into building
						for(int i=0;i<tileMap[x][y-1].getBuilding().getInputInventorySize();i++) {

							//if item wasnt added already only add if there is space or it exists in the building
							if( !(check) && ( (tileMap[x][y-1].getBuilding().getInputItem(i).getClass().equals(pipeItem.getClass()))   || (tileMap[x][y-1].getBuilding().getInputItem(i)==null))) {
								check=true;  //change to true so it doesnt add anymore items
								tileMap[x][y-1].getBuilding().addInputItem(pipeItem); //adds item to the building
							}	//end of adding check if
						} //end of for loop 	
					}	//end of building add if
				} //end of error check if
			}  //end of pipe  if

			//If the pipes output is downwards
			if(((Pipe) pipe).getOutput().equals("down")) {


				if(y+1<201){
					//Check if it can send items down and if its a pipe
					if((tileMap[x][y+1].getBuilding() instanceof Pipe)) {

						Building m=tileMap[x][y+1].getBuilding();
						//Only go into the other pipe if its input is down
						if(((Pipe) m).getInput().equals("up")) {
							System.out.println("ffound pipe");

							//Only add item if there is space or item is already in the pipe
							if(m.getInputItem(0)==null || (m.getInputItem(0).getClass().equals(pipe.getInputItem(0).getClass()))) {

								//add the item to the building
								tileMap[x][y+1].getBuilding().addInputItem(pipe.getInputItem(0));
								tileMap[x][y].getBuilding().removeInputItem(0);
							} //end of if checking items
						} //end of if checking the pipes input

						//if the building isnt a pipe then try to put item into building
					}else if( (tileMap[x][y+1].getBuilding()!=null) && (tileMap[x][y+1].getBuilding().getInputInventorySize()>0)) {

						boolean check=false;
						//for loop to find a spot to put into building
						for(int i=0;i<tileMap[x][y+1].getBuilding().getInputInventorySize();i++) {

							//if item wasnt added already only add if there is space or it exists in the building
							if( !(check) && ( (tileMap[x][y+1].getBuilding().getInputItem(i).getClass().equals(pipeItem.getClass()))   || (tileMap[x][y+1].getBuilding().getInputItem(i)==null))) {
								check=true;  //change to true so it doesnt add anymore items
								tileMap[x][y+1].getBuilding().addInputItem(pipeItem); //adds item to the building
							}	//end of adding check if
						} //end of for loop 	
					}	//end of building add if
				} //end of error check if
			}  //end of pipe  if
		}//end of null item if
	}//end of pipeCheck


	/**
	 * buildingPipeCheck
	 * @param x,y, and building
	 * @return none
	 */

	public void buildingPipeCheck(int x, int y, Building m){

		Item item=null;
		//find an item to send to pipe
		for(int k=0;k<m.getOutputInventorySize();k++) {
			//check for items in the inventory
			if(m.getOutputItem(k)!=null) {
				item=m.getOutputItem(k);
				k=99; //leave the for loop
			}
		}

		//only check for pipes if there is an item in question
		if(item!=null) {
			//Add item to pipe if there is one and if its the right input direction
			if( (x-1>=0) && (tileMap[x-1][y].getBuilding() instanceof Pipe) && (((Pipe) tileMap[x-1][y].getBuilding()).getInput().equals("right")) ) {				
				Building pipe= tileMap[x-1][y].getBuilding();
				if( (pipe.getInputItem(0)==null)  || (pipe.getInputItem(0).getClass().equals(item.getClass())) ) {  //Add item to pipe only if there is space or already in there
					tileMap[x-1][y].getBuilding().addInputItem(item);
				}
			} 

			//Add item to pipe if there is one and if its the right input direction
			if( (x+1<201) && (tileMap[x+1][y].getBuilding() instanceof Pipe) && (((Pipe) tileMap[x+1][y].getBuilding()).getInput().equals("left")) ) {

				Building pipe= tileMap[x+1][y].getBuilding();
				if( (pipe.getInputItem(0)==null)  || (pipe.getInputItem(0).getClass().equals(item.getClass())) ) {  //Add item to pipe only if there is space or already in there
					tileMap[x+1][y].getBuilding().addInputItem(item);
				}
			}

			//Add item to pipe if there is one and if its the right input direction
			if( (y-1>=0) && (tileMap[x][y-1].getBuilding() instanceof Pipe) && (((Pipe) tileMap[x][y-1].getBuilding()).getInput().equals("down")) ) {

				Building pipe= tileMap[x][y-1].getBuilding();
				if( (pipe.getInputItem(0)==null)  || (pipe.getInputItem(0).getClass().equals(item.getClass())) ) {  //Add item to pipe only if there is space or already in there
					tileMap[x][y-1].getBuilding().addInputItem(item);
				}
			}
			if( (y+1<201)  && (tileMap[x][y+1].getBuilding() instanceof Pipe) && (((Pipe) tileMap[x][y+1].getBuilding()).getInput().equals("up")) ) {

				System.out.println("pipe found");
				Building pipe= tileMap[x][y+1].getBuilding();
				if( (pipe.getInputItem(0)==null)  || (pipe.getInputItem(0).getClass().equals(item.getClass())) ) {  //Add item to pipe only if there is space or already in there
					tileMap[x][y+1].getBuilding().addInputItem(item);
				}
			}
		}
	}

} // End Map class