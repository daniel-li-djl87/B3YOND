package b3yond.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.swing.SwingConstants;

import b3yond.common.ResearchTree;
import b3yond.common.Tile;
import b3yond.tiles.*;
import b3yond.items.*;
import b3yond.buildings.*;

/**
 * GameWindow.java 
 * @author Aaron Ng, Daniel Li, Connor Mahon, Naymul Mohammed
 * @param <E>
 * @Date Dec 2, 2017 Window where game is played
 */
public class GameWindow<E> {
	public static GameWindow gameWindow;

	// Swing Components for basic HUD
	private JFrame frWindow;
	private GamePanel pnlContent;
	private JPanel pnlInfo;
	private MiniMapPanel pnlMiniMap;
	private JPanel pnlHotbar;

	// Swing Components for Glass Pane Overlay
	private JPanel pnlGlass;
	private ResearchPanel pnlResearch;
	private CraftPanel pnlCrafting;

	// Buttons for player interactions
	private StorageButton[] btnHotbar;
	private JButton btnResearchTree;
	private JButton btnBackToGame;
	private JButton btnSave;
	private JButton btnHelp;
	private JButton btnMainMenu;

	// Labels displaying map information
	private JLabel lblTileCoords2;
	private JLabel lblTileType2;
	private JLabel lblTileContents2;
	private JLabel lblEscTitle;
	private JLabel lblTileInputField;
	private JLabel lblTileOutputField;
	private JLabel lblHelpNotification;

	// Utility variables
	private Dimension dim; // Saves screen dimensions
	private boolean showHud; // Tracks whether HUD is shown
	private MiniMap minimap; // BufferedImage with simplified version of map
	private JLabel helpScreen;
	public ItemHand userHandItem;
	public static StorageButton[][] btnInventory = new StorageButton[6][10];
	public static BuildingButton[] inputButtons = new BuildingButton[3];
	public static BuildingButton[] outputButtons = new BuildingButton[3];
	public static JProgressBar inputProgressBar = new JProgressBar();
	public static JProgressBar outputProgressBar = new JProgressBar();


	/**
	 * GameWindow
	 * Constructor for GameWindow 
	 * @param none
	 * @return none
	 */

	GameWindow(String mapStr, String buildingStr, String inventoryStr){
		gameWindow = this;
		frWindow = new JFrame();
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frWindow.setSize(dim);
		// gameIcon
		frWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/b3yond/resources/menuBackground.jpg")));
		frWindow.setTitle("B3yond");
		frWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frWindow.setFocusable(false);
		frWindow.setUndecorated(true);
		frWindow.setVisible(true);
		showHud = true;

		initComponents(mapStr);
		createEvents();
		(frWindow.getContentPane()).add(pnlContent);

		if (inventoryStr != "") {
			String currentItem;
			String itemType;
			int stackSize;
			inventoryStr = inventoryStr.substring(2);

			for (int i=0;i<btnHotbar.length;i++) {
				currentItem = inventoryStr.substring(0, inventoryStr.indexOf("["));
				inventoryStr = inventoryStr.substring(inventoryStr.indexOf("[")+1);

				itemType = currentItem.substring(0,currentItem.indexOf(","));
				stackSize = Integer.parseInt(currentItem.substring(currentItem.indexOf(",")+1));

				if (!itemType.equals("null")){
					Class newItem;
					Item itemFinal = null;
					Building buildFinal = null;
					Constructor buildConstruct;
					try {
						newItem = Class.forName("b3yond.items." + itemType);
						buildConstruct = newItem.getConstructor(int.class);
						itemFinal = (Item) buildConstruct.newInstance(stackSize);
					} catch (ClassNotFoundException e) {
						itemFinal = null;
						try {
							newItem = Class.forName("b3yond.buildings." + itemType);
							buildConstruct = newItem.getConstructor(int.class, int.class);
							buildFinal = (Building) buildConstruct.newInstance(500,1);
						}catch (Exception e2) {
							e2.printStackTrace();
						}
					} catch (NoSuchMethodException e) {
						try {
							newItem = Class.forName("b3yond.items." + itemType);
							buildConstruct = newItem.getConstructor();
							itemFinal = (Item) buildConstruct.newInstance();
						}catch (Exception e2) {
							e2.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					if(itemFinal != null) {
						btnHotbar[i].setItem(itemFinal);
						btnHotbar[i].setImage(itemFinal.getClass().getSimpleName());
					}else {
						btnHotbar[i].setItem(buildFinal);
						btnHotbar[i].setImage(buildFinal.getClass().getSimpleName());
					}

				}
			}

			for (int i=0;i<btnInventory.length;i++) {
				for (int j=0;j<btnInventory[i].length;j++) {

					currentItem = inventoryStr.substring(0, inventoryStr.indexOf("["));
					inventoryStr = inventoryStr.substring(inventoryStr.indexOf("[")+1);

					itemType = currentItem.substring(0,currentItem.indexOf(","));
					stackSize = Integer.parseInt(currentItem.substring(currentItem.indexOf(",")+1));

					if (!itemType.equals("null")){
						Class newItem;
						Item itemFinal = null;
						Building buildFinal = null;
						Constructor buildConstruct;
						try {
							newItem = Class.forName("b3yond.items." + itemType);
							buildConstruct = newItem.getConstructor(int.class);
							itemFinal = (Item) buildConstruct.newInstance(stackSize);
						} catch (ClassCastException e) {
							itemFinal = null;
							try {
								newItem = Class.forName("b3yond.buildings." + itemType);
								buildConstruct = newItem.getConstructor(int.class, int.class);
								buildFinal = (Building) buildConstruct.newInstance(500,1);
							}catch (Exception e2) {
								e2.printStackTrace();
							}
						} catch (NoSuchMethodException e) {
							try {
								newItem = Class.forName("b3yond.items." + itemType);
								buildConstruct = newItem.getConstructor();
								itemFinal = (Item) buildConstruct.newInstance();
							}catch (Exception e2) {
								e2.printStackTrace();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						if(itemFinal != null) {
							btnInventory[i][j].setItem(itemFinal);
							btnInventory[i][j].setImage(itemFinal.getClass().getSimpleName());
						}else {
							btnInventory[i][j].setItem(buildFinal);
							btnInventory[i][j].setImage(buildFinal.getClass().getSimpleName());
						}
					}
				}
			}

		}else {
			btnInventory[0][0].setItem(new Iron(50));
			btnInventory[0][0].setImage(btnInventory[0][0].getItem().getClass().getSimpleName());
			btnInventory[0][1].setItem(new Wood(50));
			btnInventory[0][1].setImage(btnInventory[0][1].getItem().getClass().getSimpleName());
			btnInventory[0][2].setItem(new Copper(50));
			btnInventory[0][2].setImage(btnInventory[0][2].getItem().getClass().getSimpleName());

			SuperHeatedWater sw = new SuperHeatedWater(100);

			btnHotbar[0].setItem(new Axe());
			btnHotbar[1].setItem(new Bucket());
			btnHotbar[2].setItem(new CoolingTower(500, 1));
			btnHotbar[3].setItem(new SuperHeatedWater(10));
			btnHotbar[4].setItem(new Furnace(1, 100));
			btnHotbar[5].setItem(new BucketWheelExcavator(1, 100));
			btnHotbar[6].setItem(new BlastFurnace(1,100));
			btnHotbar[7].setItem(new HematiteOre(10));

		}
		
		for ( int i = 0; i < btnHotbar.length; i++) {
			btnHotbar[i].updateToolTip();
			if (	btnHotbar[i].getItem() == null) {
				btnHotbar[i].setImage("null");
			}else {
				btnHotbar[i].setImage(btnHotbar[i].getItem().getClass().getSimpleName());
				btnHotbar[i].updateToolTip();
			}
		}
	}

	// End GameWindow constructor

	/* @@@@@@@@@@@@@@@@@@@@@@@ Create basic UI components @@@@@@@@@@@@@@@@@@@@@@@ */

	/**
	 * initComponents
	 * Creates panels, buttons and labels for basic hud
	 * @param none
	 * @return none
	 */
	public void initComponents(String mapStr) {

		//Intialize inventoryPanel
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 10; j++) {
				btnInventory[i][j] = new StorageButton();
				btnInventory[i][j].addActionListener(new InventoryListener());
			} // End for loop
		}

		userHandItem = new ItemHand();
		userHandItem.setItem(null);

		// Initialize Buttons
		btnBackToGame = new JButton("Back To Game");
		btnSave = new JButton("Save");
		btnHelp = new JButton("Help");
		btnMainMenu = new JButton("Return to Main Menu");

		pnlResearch = new ResearchPanel(new ResearchTree()); // Create Research Panel
		btnHotbar = new StorageButton[10];
		//add Dim to parameters
		pnlCrafting = new CraftPanel(btnInventory, btnHotbar, pnlResearch.getResearchTree(), dim);

		// Create Content and Glass Pane
		pnlContent = new GamePanel(dim, mapStr);
		pnlContent.setLayout(new BorderLayout());
		pnlGlass = (JPanel) frWindow.getGlassPane();
		pnlGlass.setFocusable(false);
		pnlGlass.setLayout(new BorderLayout());

		// Create Info/HUD Panel
		pnlInfo = new JPanel();
		pnlInfo.setFocusable(false);
		pnlInfo.setOpaque(false);
		pnlContent.add(pnlInfo, BorderLayout.CENTER);
		JPanel pnlRight = new JPanel();
		pnlRight.setBackground(Color.PINK);
		pnlRight.setFocusable(false);
		minimap = new MiniMap(pnlContent.getMap().getTileMap(), BufferedImage.TYPE_INT_RGB);
		pnlMiniMap = new MiniMapPanel(minimap, pnlContent.getMap());
		pnlMiniMap.setFocusable(false);
		pnlHotbar = new JPanel();
		pnlHotbar.setBackground(Color.PINK);
		pnlHotbar.setFocusable(false);
		JPanel pnlTileInfo = new JPanel();
		pnlTileInfo.setFocusable(false);

		// Create and Format Labels
		JLabel lblTileCoords = new JLabel("Tile Coords:");
		lblTileCoords.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTileCoords2 = new JLabel("x, y");
		lblTileCoords2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTileCoords2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblTileType = new JLabel("Type:");
		lblTileType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTileType2 = new JLabel("");
		lblTileType2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTileType2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblTileContents = new JLabel("Quantity:");
		lblTileContents.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTileContents2 = new JLabel("0");
		lblTileContents2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTileContents2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblTileInput = new JLabel("Input:");
		lblTileInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTileInputField = new JLabel("0");
		lblTileInputField.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTileInputField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblTileOutput = new JLabel("Output:");
		lblTileOutput.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTileOutputField = new JLabel("0");
		lblTileOutputField.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTileOutputField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHelpNotification = new JLabel("Press ESC for Help");
		lblHelpNotification.setFont(new Font("Tahoma", Font.PLAIN, 18));

		// Format buttons
		btnBackToGame.setFocusable(false);
		btnSave.setFocusable(false);
		btnHelp.setFocusable(false);
		btnMainMenu.setFocusable(false);
		btnResearchTree = new JButton("Press R to Open Research Tree");
		btnResearchTree.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnResearchTree.setFocusable(false);

		for (int i = 0; i < btnHotbar.length; i++) {
			btnHotbar[i] = new StorageButton();
			btnHotbar[i].addActionListener(new HotbarListener());
			// System.out.println("The item in button " + i + " is " +
			// btnHotbar[i].getItem().getClass().getSimpleName());
		} // End for loop

		// Import help screen image
		helpScreen = null;
		try {
			BufferedImage img;
			img = ImageIO.read(getClass().getResource("/b3yond/resources/helpScreen.jpg"));
			ImageIcon icon = new ImageIcon(img);
			helpScreen = new JLabel(icon);
		} catch(IOException e) {
			System.out.println("Failed to load help screen image");
			e.printStackTrace();
		} // End try catch statement


		/* @@@@@@@@@@@@@@@@@@@@@@@ Create GroupLayouts for Formatting Panels @@@@@@@@@@@@@@@@@@@@@@@ */

		GroupLayout gl_pnlInfo = new GroupLayout(pnlInfo);
		gl_pnlInfo.setHorizontalGroup(gl_pnlInfo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlInfo.createSequentialGroup().addGroup(gl_pnlInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlInfo.createSequentialGroup().addContainerGap()
								.addComponent(pnlMiniMap, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 1266, Short.MAX_VALUE)
								.addComponent(pnlRight, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlInfo.createSequentialGroup().addGap(dim.width / 2 - 198).addComponent(pnlHotbar,
								GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_pnlInfo.setVerticalGroup(gl_pnlInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInfo.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlMiniMap, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlRight, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE))
						.addGap(dim.height - 379 - 121)
						.addComponent(pnlHotbar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlInfo.setLayout(gl_pnlInfo);

		GroupLayout gl_pnlRight = new GroupLayout(pnlRight);
		gl_pnlRight.setHorizontalGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_pnlRight.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlRight.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlTileInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 260,
								Short.MAX_VALUE)
						.addComponent(btnResearchTree, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 260,
								Short.MAX_VALUE))
				.addContainerGap()));
		gl_pnlRight.setVerticalGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRight.createSequentialGroup().addContainerGap()
						.addComponent(btnResearchTree, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(pnlTileInfo, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
						.addContainerGap()));

		GroupLayout gl_pnlTileInfo = new GroupLayout(pnlTileInfo);
		gl_pnlTileInfo.setHorizontalGroup(gl_pnlTileInfo.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTileInfo
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlTileInfo.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								gl_pnlTileInfo.createSequentialGroup().addComponent(lblTileCoords).addGap(18)
								.addComponent(lblTileCoords2, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlTileInfo.createSequentialGroup().addComponent(lblTileType).addGap(18)
								.addComponent(lblTileType2, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlTileInfo.createSequentialGroup().addComponent(lblTileContents).addGap(18)
								.addComponent(lblTileContents2, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlTileInfo.createSequentialGroup().addComponent(lblTileInput).addGap(18)
								.addComponent(lblTileInputField, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlTileInfo.createSequentialGroup().addComponent(lblTileOutput).addGap(18)
								.addComponent(lblTileOutputField, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlTileInfo.createSequentialGroup().addComponent(lblHelpNotification).addGap(18)))
				.addContainerGap()));
		gl_pnlTileInfo.setVerticalGroup(gl_pnlTileInfo.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTileInfo
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlTileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTileCoords)
						.addComponent(lblTileCoords2))
				.addGap(18)
				.addGroup(gl_pnlTileInfo.createParallelGroup(Alignment.BASELINE).addComponent(lblTileType)
						.addComponent(lblTileType2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlTileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTileContents, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTileContents2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlTileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTileInput, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTileInputField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlTileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTileOutput, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTileOutputField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlTileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHelpNotification, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(141, Short.MAX_VALUE)));
		pnlTileInfo.setLayout(gl_pnlTileInfo);
		pnlRight.setLayout(gl_pnlRight);

		GroupLayout gl_pnlInventory = new GroupLayout(pnlHotbar);
		gl_pnlInventory.setHorizontalGroup(gl_pnlInventory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInventory.createSequentialGroup().addContainerGap()
						.addGroup(
								gl_pnlInventory.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnHotbar[5], Alignment.LEADING, 0, 0,
										Short.MAX_VALUE)
								.addComponent(btnHotbar[0], Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 65,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnlInventory.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlInventory
								.createSequentialGroup()
								.addComponent(btnHotbar[1], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnHotbar[2], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnHotbar[3], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnHotbar[4], GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlInventory.createSequentialGroup()
										.addComponent(btnHotbar[6], GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnHotbar[7], GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnHotbar[8], GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnHotbar[9],
												GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(52, Short.MAX_VALUE)));
		gl_pnlInventory.setVerticalGroup(gl_pnlInventory.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_pnlInventory.createSequentialGroup().addContainerGap().addGroup(gl_pnlInventory
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlInventory.createSequentialGroup()
								.addComponent(btnHotbar[4], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
								.addComponent(btnHotbar[9], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlInventory.createSequentialGroup()
								.addComponent(btnHotbar[3], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
								.addComponent(btnHotbar[8], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlInventory.createSequentialGroup()
								.addComponent(btnHotbar[2], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
								.addComponent(btnHotbar[7], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_pnlInventory.createSequentialGroup()
								.addComponent(btnHotbar[1], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
								.addComponent(btnHotbar[6], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_pnlInventory.createSequentialGroup()
								.addComponent(btnHotbar[0], GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnHotbar[5], GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
				.addContainerGap()));
		pnlHotbar.setLayout(gl_pnlInventory);

		/* @@@@@@@@@@@@@@@@@@@@@@@ End of Create GroupLayouts for Formatting Panels @@@@@@@@@@@@@@@@@@@@@@@ */

	} // End initComponents method

	/* @@@@@@@@@@@@@@@@@@@@@@@ End of Create basic UI components @@@@@@@@@@@@@@@@@@@@@@@ */

	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Override methods for keyboard and mouse commands @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */

	/**
	 * createEvents
	 * Creates action listeners for general keyboard and mouse events
	 * @param none
	 * @return none
	 */
	public void createEvents() {

		/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */

		pnlContent.addKeyListener(new KeyListener() {
			@Override
			/**
			 * keyPressed Overrides key presses for game command keys
			 * 
			 * @param KeyEvent
			 *            e
			 * @return none
			 */
			public void keyPressed(KeyEvent e) {
				pnlMiniMap.setMap(pnlContent.getMap());
				if (e.getKeyChar() == 'h') {
					// If "h" is pressed, toggle heads-up display
					if (showHud) {
						showHud = false;
						pnlContent.removeAll();
					} else {
						showHud = true;
						pnlContent.add(pnlInfo);
					} // End if

				} else if (e.getKeyCode() == 27) { 
					// If "Escape" key is pressed, toggle in-game menu
					pnlGlass.removeAll();
					pnlGlass.add(buildEscapePanel(), BorderLayout.CENTER);
					pnlGlass.setVisible(!pnlGlass.isVisible());
					lblHelpNotification.setText("");

				} else if (e.getKeyChar() == 'e') {
					// If "e" is pressed, toggle inventory
					pnlGlass.removeAll();
					pnlGlass.add(buildInventoryPanel(), BorderLayout.CENTER);
					pnlGlass.setVisible(!pnlGlass.isVisible());

				} else if (e.getKeyChar() == 'm') {
					// If "m" is pressed, toggle full terrain map
					pnlGlass.removeAll();
					pnlGlass.add(buildMiniMapPanel(), BorderLayout.CENTER);
					pnlGlass.setVisible(!pnlGlass.isVisible());

					if (pnlGlass.isVisible()) {
						pnlContent.removeAll(); // Turn off HUD
					}else if (showHud) {
						pnlContent.add(pnlInfo);
					}// End if

				} else if (e.getKeyChar() == 'r') {
					// If "r" is pressed, toggle research menu
					pnlGlass.removeAll();
					pnlGlass.add(pnlResearch, BorderLayout.CENTER);
					pnlGlass.setVisible(!pnlGlass.isVisible());

				} else if (e.getKeyChar() == 'c') {
					// If "c" is pressed, toggle research menu
					pnlGlass.removeAll();
					pnlCrafting.determineCraftable();
					pnlGlass.add(pnlCrafting, BorderLayout.CENTER);
					pnlGlass.setVisible(!pnlGlass.isVisible());

				} else if (e.getKeyCode() == 112) {
					// If "Function Key 1 (F1)" is pressed, toggle help menu

				} else if (e.getKeyChar() == 'z') {
					// If "z" is pressed, toggle upgrade research tier override (DEVELOPER TOOL)
					pnlResearch.getResearchTree().setTier(pnlResearch.getResearchTree().getTier() + 1);
					pnlResearch.updateResearchedTech();

				} else if (e.getKeyChar() == 'x'){
					// If "x" is pressed, add excessive amount of resources to inventory (DEVELOPER TOOL)
					btnInventory[5][0].setItem(new Iron(50000));
					btnInventory[5][1].setItem(new Copper(50000));
					btnInventory[5][2].setItem(new Nickel(50000));
					btnInventory[5][3].setItem(new Aluminum(50000));
					btnInventory[5][4].setItem(new Titanium(50000));
					btnInventory[5][5].setItem(new Lithium(50000));
					btnInventory[5][6].setItem(new Wood(100000));
					btnInventory[5][7].setItem(new Coal(50000));
					btnInventory[5][8].setItem(new Stone(50000));
					btnInventory[5][9].setItem(new Glass(50000));
					for (int i = 0; i < 10; i ++) {
						btnInventory[5][i].setImage(btnInventory[5][i].getItem().getClass().getSimpleName());
					}	
				} // End if

			} // End keyPressed Method

			// Unused KeyListener Methods
			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});

		pnlContent.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				
				// Find coordinates of mouse cursor on the tile map
				int x = (int) ((double) (e.getX() + pnlContent.getMap().getX())
						/ (double) pnlContent.getMap().getTileSize());
				int y = (int) ((double) (e.getY() + pnlContent.getMap().getY())
						/ (double) pnlContent.getMap().getTileSize());
				
				// If user clicked on a tile containing a building, open building panel
				if (((Tile) pnlContent.getMap().getTile(x,y)).getBuilding() != null) {
					// Do not open building panel if building is a pipe
					if (((Tile) pnlContent.getMap().getTile(x,y)).getBuilding() instanceof Pipe) {
						// If user right-clicked on a pipe, change direction
						if (e.getButton() == MouseEvent.BUTTON3) {
							((Pipe) ((Tile) pnlContent.getMap().getTile(x,y)).getBuilding()).switchPipe();
							lblTileInputField.setText(((Pipe) ((Tile) pnlContent.getMap().getTile(x,y)).getBuilding()).getInput());
							lblTileOutputField.setText(((Pipe) ((Tile) pnlContent.getMap().getTile(x,y)).getBuilding()).getOutput());
						} // End if
					}else {
						pnlGlass.removeAll();
						pnlGlass.add(buildBuildingPanel(y,x,((Tile) pnlContent.getMap().getTile(x,y)).getBuilding()), BorderLayout.CENTER);
						pnlGlass.setVisible(!pnlGlass.isVisible());
					} // End if
				} // End if

				if (userHandItem == null) {
					// If hand is empty, update coordinates and tile type
					lblTileCoords2.setText(x + "," + y);
					lblTileType2.setText(pnlContent.getMap().getTile(x, y).getClass().getSimpleName());
				} else if (userHandItem.getItem() instanceof Item) {
					// If user is holding item, mine ore when available
					if (userHandItem.getItem() instanceof Axe) {
						if (pnlContent.getMap().getTile(x,y) instanceof ForestTile) {
							addToInventory(new Wood(1));
							((ForestTile) pnlContent.getMap().getTile(x,y)).setQuantity(((ForestTile) pnlContent.getMap().getTile(x,y)).getQuantity()-1);
						}
					}else if(userHandItem.getItem() instanceof Pickaxe) {
						if (pnlContent.getMap().getTile(x,y) instanceof ConoreTile) {
							addToInventory(new Conore(1));
							addToInventory(new Stone(1));
							((ConoreTile) pnlContent.getMap().getTile(x,y)).setQuantity(((ConoreTile) pnlContent.getMap().getTile(x,y)).getQuantity()-1);
						}else if (pnlContent.getMap().getTile(x,y) instanceof KannaiteTile) {
							addToInventory(new Kannaite(1));
							addToInventory(new Stone(1));
							((KannaiteTile) pnlContent.getMap().getTile(x,y)).setQuantity(((KannaiteTile) pnlContent.getMap().getTile(x,y)).getQuantity()-1);
						}else if (pnlContent.getMap().getTile(x,y) instanceof FuelTile) {
							addToInventory(new Fuel(1));
							addToInventory(new Stone(1));
							((FuelTile) pnlContent.getMap().getTile(x,y)).setQuantity(((FuelTile) pnlContent.getMap().getTile(x,y)).getQuantity()-1);
						}
					}
					//do something when item is used
				} else if (userHandItem.getItem() instanceof Building && ((Tile) pnlContent.getMap().getTile(x,y)).getBuilding() == null ) {
					Dimension buildingDimensions = ((b3yond.buildings.Building) userHandItem.getItem()).getDim();
					int buildingHeight = (int) buildingDimensions.getHeight();
					int buildingWidth = (int) buildingDimensions.getWidth();
					pnlContent.setCursor(null);
					pnlGlass.setCursor(null);
					//					System.out.println("The height is: " + buildingHeight + " and the width is: " + buildingWidth);
					if (x+buildingWidth <= 200 && y+buildingHeight <= 200) {
						for (int i = x; i < x + buildingWidth; i ++) {
							for ( int j = y; j < y + buildingHeight; j++) {
								((Tile) pnlContent.getMap().getTile(i,j)).setBuilding((Building)userHandItem.getItem());
							}
						}
						//		BufferedImage img = setBuildingImage((Building)userHandItem.getItem(),(Tile) pnlContent.getMap().getTile(x,y));
						Building b = (Building)userHandItem.getItem();
						b.x = x;
						b.y = y;
						GamePanel.buildingList.add(b);
						userHandItem.setItem(null);
					}
				}
				lblTileCoords2.setText(x + "," + y);
				if (((Tile) pnlContent.getMap().getTile(x, y)).getBuilding() == null) {
					lblTileType2.setText(pnlContent.getMap().getTile(x, y).getClass().getSimpleName());
				} else {
					lblTileType2.setText(((Tile) pnlContent.getMap().getTile(x, y)).getBuilding().getClass().getSimpleName());
				}

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

		/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */

		pnlContent.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// We will probably use this for mass selection of tiles/objects
			} // End mouseDragged method

			@Override
			/**
			 * mouseMoved Change tile details as mouse hovers over coordinates
			 * 
			 * @param MouseEvent
			 *            e
			 * @return none
			 */
			public void mouseMoved(MouseEvent e) {
				int x = (int) ((double) (e.getX() + pnlContent.getMap().getX())
						/ (double) pnlContent.getMap().getTileSize());
				int y = (int) ((double) (e.getY() + pnlContent.getMap().getY())
						/ (double) pnlContent.getMap().getTileSize());

				lblTileCoords2.setText(x + "," + y);
				if (((Tile) pnlContent.getMap().getTile(x, y)).getBuilding() == null) {
					lblTileType2.setText(pnlContent.getMap().getTile(x, y).getClass().getSimpleName());
				} else {
					lblTileType2.setText(((Tile) pnlContent.getMap().getTile(x, y)).getBuilding().getClass().getSimpleName());
				}
				Object currentTile = pnlContent.getMap().getTile(x, y);

				if (currentTile instanceof ConoreTile){
					lblTileContents2.setText(Integer.toString(((ConoreTile)currentTile).getQuantity()));
				}else if(currentTile instanceof KannaiteTile){
					lblTileContents2.setText(Integer.toString(((KannaiteTile)currentTile).getQuantity()));
				}else if( currentTile instanceof FuelTile) {
					lblTileContents2.setText(Integer.toString(((FuelTile)currentTile).getQuantity()));
				}else if (currentTile instanceof ForestTile){
					lblTileContents2.setText(Integer.toString(((ForestTile)currentTile).getQuantity()));
				}else if (((Tile) currentTile).getPowered()) {
					lblTileContents2.setText("Powered");
				}else {
					lblTileContents2.setText("Not Powered");
				} // End if
				
			} // End mouseMoved method
		});

		/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */

		btnBackToGame.addActionListener(new ActionListener() {
			@Override
			/**
			 * actionPerformed Remove glass pane when button is pressed
			 * 
			 * @param ActionEvent
			 * @return none
			 */
			public void actionPerformed(ActionEvent arg0) {
				pnlGlass.setVisible(false);
			} // End actionPerformed method
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSave.setText("Saving!");
				String inventoryStr = inventoryToString();
				String mapStr = pnlContent.getMap().mapToString();
				String buildingStr = pnlContent.getMap().buildingToString();
				try{
					FileOutputStream out = new FileOutputStream("map.txt");
					for (int i=0;i<mapStr.length();i++) {
						out.write(mapStr.charAt(i));
					}
					out = new FileOutputStream("buildings.txt");
					for (int i=0; i<buildingStr.length();i++){
						out.write(buildingStr.charAt(i));
					}
					out = new FileOutputStream("inventory.txt");
					for (int i=0; i<inventoryStr.length();i++){
						out.write(inventoryStr.charAt(i));
					}
					out.close();
				}catch (IOException e2) {
					System.out.println("Failed to save!");
				}
				btnSave.setText("Save");
			} // End actionPerformed method

			private String inventoryToString() {
				String inventoryStr = "c";
				for (int i=0; i<btnHotbar.length;i++) {
					inventoryStr += "[";
					Object obj = btnHotbar[i].getItem();
					if (obj != null) {
						inventoryStr += obj.getClass().getSimpleName();
						if (obj instanceof Item) {
							inventoryStr += ","+((Item)obj).getStack();
						}else {
							inventoryStr += ",0";
						}
					}else {
						inventoryStr += "null,0";
					}
				}
				for (int i=0; i<btnInventory.length;i++) {
					for(int j=0; j<btnInventory[i].length;j++) {
						inventoryStr += "[";
						Object obj = btnInventory[i][j].getItem();
						if (obj!=null) {
							inventoryStr += obj.getClass().getSimpleName();
							if(obj instanceof Item) {
								inventoryStr += "," + ((Item)obj).getStack();
							}else {
								inventoryStr += ",0";
							}
						}else {
							inventoryStr += "null,0";
						}
					}
				}
				inventoryStr += "[null,0";
				return inventoryStr;
			}
		});

		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			/**
			 * actionPerformed
			 * Saves and closes game, returns to main menu
			 * @param ActionEvent
			 * @return none
			 */
			public void actionPerformed(ActionEvent e) {
				// Save the game
				frWindow.dispose(); // Remove GameWindow
				new Menu(); // Open Main Menu
			}
		});

		btnHelp.addActionListener(new ActionListener() {

			@Override
			/**
			 * actionPerformed
			 * Displays help screen containing list of keyboard shortcuts
			 * @param ActionEvent
			 * @return none
			 */
			public void actionPerformed(ActionEvent arg0) {
				pnlGlass.removeAll();

				JPanel pnlHelpScreen = new JPanel();
				GroupLayout gl_pnlHelpScreen = new GroupLayout(pnlHelpScreen);
				gl_pnlHelpScreen.setHorizontalGroup(
						gl_pnlHelpScreen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlHelpScreen.createSequentialGroup()
								.addGap(dim.width/2 - 698)
								.addComponent(helpScreen, GroupLayout.PREFERRED_SIZE, 1397, GroupLayout.PREFERRED_SIZE))
						);
				gl_pnlHelpScreen.setVerticalGroup(
						gl_pnlHelpScreen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlHelpScreen.createSequentialGroup()
								.addGap(dim.height/2 - 297)
								.addComponent(helpScreen, GroupLayout.PREFERRED_SIZE, 595, GroupLayout.PREFERRED_SIZE))
						);
				pnlHelpScreen.setLayout(gl_pnlHelpScreen);
				pnlHelpScreen.setOpaque(false);
				pnlGlass.add(pnlHelpScreen, BorderLayout.CENTER);
				pnlGlass.setVisible(true);			
				pnlGlass.updateUI();
			} // End actionPerformed method

		});

		/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */

		btnResearchTree.addActionListener(new ActionListener() {

			@Override
			/**
			 * actionPerformed
			 * Opens research panel
			 * @param ActionEvent
			 * @return none
			 */
			public void actionPerformed(ActionEvent arg0) {
				pnlGlass.removeAll();
				pnlGlass.add(pnlResearch, BorderLayout.CENTER);
				pnlGlass.setVisible(true);
			} // End actionPerformed method

		});

	} // End createEintvents Method


	/**
	 * setBuildingImage
	 * Assigns new building image to tile
	 * @param building; the building image to set
	 * @return none
	 */
	public BufferedImage setBuildingImage(b3yond.buildings.Building building2) {
		BufferedImage img = null;
		if (building2.equals(null)) {
			System.out.println("Error, building is null");
			return null;
		} else {

			try {
				img = resize(ImageIO.read(getClass().getResource("/b3yond/resources/" + building2.getClass().getSimpleName() + ".png")),pnlContent.getMap().getTileSize(), pnlContent.getMap().getTileSize());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("The image cannot be found");
			}
			return img;
		}

	}

	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ End of Override methods for keyboard and mouse commands @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */

	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Methods to build menus on key presses @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

	/**
	 * buildEscapePanel
	 * Builds a panel for the escape menu
	 * @param none
	 * @return JPanel
	 */
	private JPanel buildEscapePanel() {

		JPanel pnlTmp = new JPanel(new GridLayout(5, 1, 10, 10));
		JPanel pnlTmpBackground = new JPanel(new GridLayout(3, 5));
		pnlTmpBackground.setBackground(new Color(0, 0, 0, 175));
		pnlTmpBackground.setFocusable(false);
		for (int i = 0; i < 15; i++) {
			if (i == 7) {
				pnlTmpBackground.add(pnlTmp);
			} else {
				pnlTmpBackground.add(new JLabel(""));
			} // End if
		} // End for loop
		lblEscTitle = new JLabel(
				"<html><b><font size = '9' face = 'Comic Sans MS' color = '#e900ff'>Directory</font></b></html>");
		lblEscTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTmp.add(lblEscTitle);
		pnlTmp.add(btnBackToGame);
		pnlTmp.add(btnSave);
		pnlTmp.add(btnHelp);
		pnlTmp.add(btnMainMenu);
		pnlTmp.setFocusable(false);
		pnlTmp.setBackground(new Color(139, 175, 54));

		return pnlTmpBackground;
	} // End buildEscapePanel method

	/**
	 * buildMiniMapPanel Builds a panel for minimap
	 * @param none
	 * @return JPanel
	 */
	private JPanel buildMiniMapPanel() {

		minimap = new MiniMap(pnlContent.getMap().getTileMap(), BufferedImage.TYPE_INT_RGB);
		// Create simplified version of map using colors to represent different tiles
		JPanel pnlTmp = (new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(minimap, (dim.width / 2 - dim.height / 2), 0, dim.height, dim.height, null); 
				// Draw centered image at max size
			} // End paintComponent method
		}); // Create new JPanel of flow layout

		JPanel pnlLegend = new JPanel();
		pnlLegend.setLayout(new BoxLayout(pnlLegend, BoxLayout.Y_AXIS));
		pnlLegend.add(new JLabel("<html><font size = '24'><b><u>LEGEND</u></b></font></html>", SwingConstants.CENTER));
		pnlLegend.add(new JLabel("<html>  <font color = '#6bce31'> @@@ </font>= Grass Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#4060d6'> @@@ </font>= Water Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#236628'> @@@ </font>= Forest Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#59402f'> @@@ </font>= Mountain Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#6d6b6b'> @@@ </font>= Conore Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#8e1386'> @@@ </font>= Kannaite Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#a88223'> @@@ </font>= Fuel Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#000000'> @@@ </font>= Oil Tile</html>"));
		pnlLegend.add(new JLabel("<html>  <font color = '#fff6b7'> @@@ </font>= Desert Tile</html>"));

		GroupLayout gl_pnlTmp = new GroupLayout(pnlTmp);
		gl_pnlTmp.setHorizontalGroup(
				gl_pnlTmp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTmp.createSequentialGroup()
						.addGap(dim.width/2 - dim.height/2 - 200)
						.addComponent(pnlLegend, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				);
		gl_pnlTmp.setVerticalGroup(
				gl_pnlTmp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTmp.createSequentialGroup()
						.addGap(dim.height/2 - 100)
						.addComponent(pnlLegend, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				);
		pnlTmp.setLayout(gl_pnlTmp);
		pnlTmp.setFocusable(false);
		return pnlTmp;

	} // End buildMiniMapPanel method


	private JPanel buildBuildingPanel (int x, int y, Building tempBuilding) {
		System.out.println("TempBuilding is" + tempBuilding.getClass().getSimpleName());
		System.out.println("x is" + x);
		System.out.println("y is" + y);
	
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(x + (int) tempBuilding.getDim().getWidth(), y + (int) tempBuilding.getDim().getHeight(), 470, 155);

		BufferedImage img = null;
		JLabel lblNewLabel = new JLabel("");
		try {
			img = resize(ImageIO.read(getClass().getResource("/b3yond/resources/" + tempBuilding.getClass().getSimpleName() + ".png")), 50, 50);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The image cannot be found");
		}
		lblNewLabel.setIcon(new ImageIcon(img));

		inputProgressBar.setMinimum(0);
		inputProgressBar.setMaximum(100); 
		
		outputProgressBar.setMinimum(0);
		outputProgressBar.setMaximum(100);
		
		JLabel lblInput = new JLabel("Input:");
		JLabel lblOutput = new JLabel("Output:");
		
		int inputLength = tempBuilding.getInputInventorySize();
		int outputLength = tempBuilding.getOutputInventorySize();
	
		
		for ( int i = 0; i < 3; i ++) {
			inputButtons[i] = new BuildingButton() {

				@Override
				public void setBuildingItem(Item item) {
					// TODO Auto-generated method stub
					for ( int i = 0; i < tempBuilding.getInputInventorySize(); i ++) {
						if ( item != null) {
							tempBuilding.addInputItem(item,i);
							tempBuilding.update();
						}
					}
				}
			};
			inputButtons[i].setBuilding(tempBuilding);
			inputButtons[i].addActionListener(new BuildingPanelListener());
			
			outputButtons[i] = new BuildingButton() {

				@Override
				public void setBuildingItem(Item item) {
					if ( item != null) {
						tempBuilding.addOutputItem(item);
						tempBuilding.update();
					}
					
				}
				
			};
			outputButtons[i].addActionListener(new BuildingPanelListener());
		}
		
		for ( int i = 2; i > tempBuilding.getInputInventorySize()-1; i --) {
			inputButtons[i].disable();
		}
		
		for ( int i = 2; i > tempBuilding.getOutputInventorySize()-1; i --) {
			outputButtons[i].disable();
		}
		

		
		for ( int j = 0; j < tempBuilding.getInputInventorySize(); j++ ) {
			if ( inputButtons[j].getItem() != null) {
				tempBuilding.addInputItem((Item) inputButtons[j].getItem());
			}
		}
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(16)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInput, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(inputProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblOutput, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
										.addGap(12)
										.addComponent(inputButtons[0], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
										.addGap(5)
										.addComponent(inputButtons[1], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(inputButtons[2], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(outputButtons[0], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
										.addGap(2)
										.addComponent(outputButtons[1], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
										.addGap(1)
										.addComponent(outputButtons[2], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(outputProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(6)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(20)
						.addComponent(lblInput)
						.addGap(4)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(8)
										.addComponent(inputProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblOutput))
								.addComponent(inputButtons[0], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputButtons[1], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputButtons[2], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGap(12)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(outputButtons[0], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(outputButtons[1], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(outputButtons[2], GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(12)
										.addComponent(outputProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				);
		panel.setLayout(gl_panel);
		
		JPanel pnlBackground = new JPanel();
		
		GroupLayout gl_pnlBackground = new GroupLayout(pnlBackground);
		gl_pnlBackground.setHorizontalGroup(
				gl_pnlBackground.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBackground.createSequentialGroup()
						.addGap(dim.width/2 - 235)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))
				);
		gl_pnlBackground.setVerticalGroup(
				gl_pnlBackground.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBackground.createSequentialGroup()
						.addGap(dim.height/2 - 77)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
				);
		
		pnlBackground.setOpaque(false);
		pnlBackground.setLayout(gl_pnlBackground);
		return pnlBackground;
	}
	/**
	 * buildInventoryPanel
	 * Builds panel for inventory interactions
	 * @param none
	 * @return JPanel
	 */
	public JPanel buildInventoryPanel() {
		// Create and format inventory display panel
		JPanel pnlTmp = new JPanel(new BorderLayout(5, 5));
		pnlTmp.setFocusable(false);
		pnlTmp.setBackground(Color.PINK);
		pnlTmp.add(new JLabel("<html><ul><font size='36' face='Arial Black'><u>Your Inventory</u></font></ul></html>"), BorderLayout.NORTH);
		// Initialize inventory button array
		JPanel pnlTmpInventory = new JPanel(new GridLayout(6, 10, 5, 5));
		pnlTmpInventory.setFocusable(false);
		pnlTmpInventory.setOpaque(false);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 10; j++) {
				pnlTmpInventory.add(btnInventory[i][j]);
				btnInventory[i][j].updateToolTip();
			} // End for loop
		} // End for loop
		pnlTmp.add(pnlTmpInventory, BorderLayout.CENTER);

		// Format the panel to be in the center of screen
		JPanel pnlTmpBackground = new JPanel(new GridLayout(3, 3));
		for (int i = 0; i < 9; i++) {
			if (i == 4) {
				pnlTmpBackground.add(pnlTmp);
			} else {
				pnlTmpBackground.add(new JLabel(""));
			} // End if
		} // End for loop
		pnlTmpBackground.setFocusable(false);
		pnlTmpBackground.setOpaque(false);

		return pnlTmpBackground;

	} // End buildInventoryPanel method

	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ End of Methods to build menus on key presses @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Action listeners for inventory interactions @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/


	class HotbarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (userHandItem.getItem() == null){
				if ( ((StorageButton) e.getSource()).getItem() == null){
					//Hand and button are empty
					//do nothing
					//System.out.println("***HAND IS NULL AND BUTTON ITEM IS NULL***");
				} else {
					//Hand is empty but button isn't empty
					//Add item to hand and remove item from button
					//					System.out.println("***HAND IS NULL BUT BUTTON ISN'T NULL***");
					//					System.out.println("The Item in the button clicked is: " + ((StorageButton) e.getSource()).getItem().getClass().getSimpleName());
					changeCursor(((StorageButton) e.getSource()));
					userHandItem.setItem(((StorageButton) e.getSource()).getItem());
					((StorageButton) e.getSource()).setItem(null);
					((StorageButton) e.getSource()).setImage("null");
				}
			} else {
				if ( ((StorageButton) e.getSource()).getItem() == null){
					//Hand isn't empty but button is empty
					//Add item to button and remove item from hand
					//					System.out.println("***HAND ISN'T NULL BUT BUTTON ITEM IS NULL***");
					//					System.out.println("Item hand in hand is: "  + userHandItem.getItem().getClass().getSimpleName());
					changeCursor(((StorageButton) e.getSource()));
					((StorageButton) e.getSource()).setItem(userHandItem.getItem());
					((StorageButton) e.getSource()).setImage(userHandItem.getItem().getClass().getSimpleName());
					userHandItem.setItem(null);
				} else {
					//Hand and button aren't empty
					//Swap hand and button item
					//					System.out.println("***ITEM IN HAND AND BUTTON***");
					//					System.out.println("Item hand in hand is: "  + userHandItem.getItem().getClass().getSimpleName());
					//					System.out.println("The Item in the button clicked is: " + ((StorageButton) e.getSource()).getItem().getClass().getSimpleName());
					((StorageButton) e.getSource()).setImage(userHandItem.getItem().getClass().getSimpleName());
					changeCursor(((StorageButton) e.getSource()));
					E tempItem = (E) userHandItem.getItem();
					userHandItem.setItem(((StorageButton) e.getSource()).getItem());
					((StorageButton) e.getSource()).setItem(tempItem);
				}

			}

			((StorageButton) e.getSource()).updateToolTip();

		}

	} // End HotbarListener class

	/**
	 * changeCursor
	 * Grabs item from clicked slot, dumps currently held item (if any) into slot
	 * @param tempButton storing item/building
	 * @return none
	 */
	private void changeCursor(StorageButton tempButton){
		if (tempButton.getItem() == null) {
			pnlContent.setCursor(null);
			pnlGlass.setCursor(null);
		} else {
			String itemName = tempButton.getItem().getClass().getSimpleName();
			BufferedImage newCursor = null;
			try {
				newCursor = ImageIO.read(getClass().getResource("/b3yond/resources/" + itemName + ".png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (tempButton.getItem() instanceof Item) {
				try {
					newCursor = resize (ImageIO.read(getClass().getResource("/b3yond/resources/" + itemName + ".png")), pnlContent.getMap().getTileSize(), pnlContent.getMap().getTileSize());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (tempButton.getItem() instanceof Building) {
				Building tempBuilding = (Building) tempButton.getItem();
				int width = (int) ((int) pnlContent.getMap().getTileSize() * tempBuilding.getDim().getWidth());
				int height = (int) ((int) pnlContent.getMap().getTileSize() * tempBuilding.getDim().getHeight());
				try {
					newCursor = resize (ImageIO.read(getClass().getResource("/b3yond/resources/" + itemName + ".png")), width, height);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newCursor.createGraphics();
			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(newCursor, new Point(pnlContent.getX(), pnlContent.getY()), "");
			pnlContent.setCursor(cursor);
			pnlGlass.setCursor(cursor);
		}
	} // End changeCursor method
	
	
	private void changeCursor(BuildingButton tempButton){
		if (tempButton.getItem() == null) {
			pnlContent.setCursor(null);
			pnlGlass.setCursor(null);
		} else {
			String itemName = tempButton.getItem().getClass().getSimpleName();
			BufferedImage newCursor = null;
			try {
				newCursor = ImageIO.read(getClass().getResource("/b3yond/resources/" + itemName + ".png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (tempButton.getItem() instanceof Item) {
				try {
					newCursor = resize (ImageIO.read(getClass().getResource("/b3yond/resources/" + itemName + ".png")), pnlContent.getMap().getTileSize(), pnlContent.getMap().getTileSize());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (tempButton.getItem() instanceof Building) {
				Building tempBuilding = (Building) tempButton.getItem();
				int width = (int) ((int) pnlContent.getMap().getTileSize() * tempBuilding.getDim().getWidth());
				int height = (int) ((int) pnlContent.getMap().getTileSize() * tempBuilding.getDim().getHeight());
				try {
					newCursor = resize (ImageIO.read(getClass().getResource("/b3yond/resources/" + itemName + ".png")), width, height);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newCursor.createGraphics();
			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(newCursor, new Point(pnlContent.getX(), pnlContent.getY()), "");
			pnlContent.setCursor(cursor);
			pnlGlass.setCursor(cursor);
		}
	} // End changeCursor method
	
	
	private static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
	
	class BuildingPanelListener implements ActionListener {
		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			BuildingButton bg = ((BuildingButton) e.getSource());
			Building building = bg.getBuilding();
			Item item = (Item) bg.getItem();
			
			if (userHandItem.getItem() == null){
				
				if ( item == null){
					//Hand and button are empty
					//do nothing
					//System.out.println("***HAND IS NULL AND BUTTON ITEM IS NULL***");
					bg.setItem(null);
					bg.setImage("null");
				} else {
					//Hand is empty but button isn't empty
					//Add item to hand and remove item from button
					changeCursor(bg);
					userHandItem.setItem(item);
					//System.out.println("The item in the button is: " + ((BuildingButton) e.getSource()).getItem().getClass().getSimpleName() );
					bg.setItem(null);
					bg.setImage("null");
				}
			} else {
				if ( item == null){
					//Hand isn't empty but button is empty
					//Add item hand
					changeCursor(bg);
					Item tempItem = (Item) userHandItem.getItem();
					bg.setItem(userHandItem.getItem());
					bg.setImage(userHandItem.getItem().getClass().getSimpleName());
					userHandItem.setItem(null);
					//System.out.println("The item in the button is: " + ((BuildingButton) e.getSource()).getItem().getClass().getSimpleName() );
				} else {
					//Hand and button aren't empty
					//Swap hand and button item
					((BuildingButton) e.getSource()).setImage(userHandItem.getItem().getClass().getSimpleName());
					changeCursor(((BuildingButton) e.getSource()));
					E tempItem = (E) userHandItem.getItem();
					userHandItem.setItem(((BuildingButton) e.getSource()).getItem());
					((BuildingButton) e.getSource()).setItem(tempItem);
					//System.out.println("The item in the button is: " + ((BuildingButton) e.getSource()).getItem().getClass().getSimpleName() );
				}

			}
			
			bg.updateToolTip();

		}

	}


	class InventoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (userHandItem.getItem() == null){
				if ( ((StorageButton) e.getSource()).getItem() == null){
					//Hand and button are empty
					//do nothing
					//					System.out.println("***HAND IS NULL AND BUTTON ITEM IS NULL***");
				} else {
					//Hand is empty but button isn't empty
					//Add item to hand and remove item from button
					//					System.out.println("***HAND IS NULL BUT BUTTON ISN'T NULL***");
					//					System.out.println("The Item in the button clicked is: " + ((StorageButton) e.getSource()).getItem().getClass().getSimpleName());
					changeCursor(((StorageButton) e.getSource()));
					userHandItem.setItem(((StorageButton) e.getSource()).getItem());
					((StorageButton) e.getSource()).setItem(null);
					((StorageButton) e.getSource()).setImage("null");
				}
			} else {
				if ( ((StorageButton) e.getSource()).getItem() == null){
					//Hand isn't empty but button is empty
					//Add item to button and remove item from hand
					//					System.out.println("***HAND ISN'T NULL BUT BUTTON ITEM IS NULL***");
					//					System.out.println("Item hand in hand is: "  + userHandItem.getItem().getClass().getSimpleName());
					changeCursor(((StorageButton) e.getSource()));
					((StorageButton) e.getSource()).setItem(userHandItem.getItem());
					((StorageButton) e.getSource()).setImage(userHandItem.getItem().getClass().getSimpleName());
					userHandItem.setItem(null);
				} else {
					//Hand and button aren't empty
					//Swap hand and button item
					//					System.out.println("***ITEM IN HAND AND BUTTON***");
					//					System.out.println("Item hand in hand is: "  + userHandItem.getItem().getClass().getSimpleName());
					//					System.out.println("The Item in the button clicked is: " + ((StorageButton) e.getSource()).getItem().getClass().getSimpleName());
					((StorageButton) e.getSource()).setImage(userHandItem.getItem().getClass().getSimpleName());
					changeCursor(((StorageButton) e.getSource()));
					E tempItem = (E) userHandItem.getItem();
					userHandItem.setItem(((StorageButton) e.getSource()).getItem());
					((StorageButton) e.getSource()).setItem(tempItem);
				}

			}

			((StorageButton) e.getSource()).updateToolTip();

		}

	} // End InventoryListener class

	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ End of Action listeners for inventory interactions @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

	private <E> void addToInventory(Object item) {
		int firstX=0,firstY=0;
		boolean first = false;
		int stackX=0,stackY=0;
		boolean hotbar = false;
		boolean stack = false;

		boolean added = false;
		// Loop through hotbar to find empty space
		for (int i = 0; i < btnHotbar.length; i++) {
			// If empty space is found, set item and image to newly crafted item
			if (btnHotbar[i].getItem() == null) {
				if (!first) {
					firstX = i;
					hotbar = true;
					first = true;
				}
			}else if(btnHotbar[i].getItem().getClass().equals(item.getClass()) && !stack){
				if(!(item instanceof Building)) {
					stackX = i;
					stack = true;
					hotbar= true;
				}
			} // End if
		} // End for loop

		// Loop through inventory to find empty space
		if (!stack) {
			for (int i = 0; i < btnInventory.length; i++) {
				for (int j = 0; j < btnInventory[i].length; j++) {
					// If empty space is found, set item and image to newly crafted item
					if (btnInventory[i][j].getItem() == null) {
						if( !first) {
							firstX=i;
							firstY=j;
							hotbar=false;
							first = true;
						}
					}else if(btnInventory[i][j].getItem().getClass().equals(item.getClass()) && !stack){
						if(!(item instanceof Building)) {
							stackX=i;
							stackY=j;
							stack=true;
							hotbar=false;
						}
					}//End if
				} // End for loop
			} // End for loop
		}

		if (hotbar) {
			if (stack) {
				int stackSize = ((Item)btnHotbar[stackX].getItem()).getStack();
				((Item) item).setStack(((Item) item).getStack()+stackSize);
				btnHotbar[stackX].setItem(item);
				btnHotbar[stackX].setImage(item.getClass().getSimpleName());
			}else {
				btnHotbar[firstX].setItem(item);
				btnHotbar[firstX].setImage(item.getClass().getSimpleName());		}
		}else {
			if (stack) {
				int stackSize = ((Item)btnInventory[stackX][stackY].getItem()).getStack();
				((Item) item).setStack(((Item) item).getStack()+stackSize);
				btnInventory[stackX][stackY].setItem(item);
				btnInventory[stackX][stackY].setImage(item.getClass().getSimpleName());
			}else {
				btnInventory[firstX][firstY].setItem(item);
				btnInventory[firstX][firstY].setImage(item.getClass().getSimpleName());
			}

		}
	}

}// End GameWindow class