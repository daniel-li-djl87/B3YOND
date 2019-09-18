package b3yond.views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import b3yond.buildings.*;
import b3yond.common.ResearchTree;
import b3yond.items.*;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * CraftPanel
 * @author Aaron Ng
 * @date Dec 14, 2017
 * JPanel that takes in user's inventory and determines what materials they can craft
 */
public class CraftPanel extends JPanel {

	private Item[] itemList;
	private StorageButton[][] btnInventory;
	private StorageButton[] btnHotbar;
	private ResearchTree rTree;
	private Color black;
	private Color red;
	private Color green;
	private CraftingButton btnSelected;
	private Timer t;
	private ImageIcon icon;
	private String tmpText;
	private Dimension dim;
	private boolean crafting;

	// Swing components for general panel
	private JPanel pnlRecipes;
	private JLabel lblCraftingRecipes;
	private JLabel lblDescription;
	private CraftingButton[] btnList;
	// Swing components for description panel
	private JPanel pnlDescription;
	private JLabel lblName;
	private JLabel lblDetails;
	private JLabel lblResearch;
	private JLabel lblCost;
	private JLabel[] lblResourceList;
	private JButton btnCraft;

	/**
	 * CraftPanel
	 * Constructor for CraftPanel
	 * @param btnInventory
	 * @return none
	 */
	public CraftPanel(StorageButton[][] btnInventory, StorageButton[] btnHotbar, ResearchTree rTree, Dimension dim) {

		this.btnInventory = btnInventory;
		this.btnHotbar = btnHotbar;
		this.rTree = rTree;
		this.dim = dim;
		this.crafting = false;

		setPreferredSize(new Dimension(1920, 1080));
		setFocusable(false);
		setOpaque(false);
		black = Color.BLACK;
		red = Color.RED;
		green = new Color(42, 150, 52);
		btnSelected = null;
		try {
			BufferedImage img;
			img = ImageIO.read(getClass().getResource("/b3yond/resources/deWay.jpg"));
			icon = new ImageIcon(img);
		}catch(IOException e){
			e.printStackTrace();
		}

		createRecipes();
		initComponents();
		addEventHandlers();

	} // End CraftPanel constructor

	/**
	 * createRecipes
	 * Creates recipes for the array of buttons
	 * @param none
	 * @return none
	 */
	private void createRecipes() {

		btnList = new CraftingButton[42];
		for (int i = 0; i < btnList.length; i++) {
			btnList[i] = new CraftingButton();
			btnList[i].setFocusable(false);
		} // End for loop

		btnList[0].setOutput(new Pickaxe());
		btnList[1].setOutput(new Axe());
		btnList[2].setOutput(new Bucket());
		btnList[3].setOutput(new ResearchLab(500, 1));
		btnList[4].setOutput(new Furnace(500, 1));
		btnList[5].setOutput(new ElectricMiningDrill(500, 1));
		btnList[6].setOutput(new LowTierBattery(500, 1));
		btnList[7].setOutput(new HandCrankGenerator(500, 1));
		btnList[8].setOutput(new PowerLine(500, 1));
		btnList[9].setOutput(new OreSorter(500, 1));
		btnList[10].setOutput(new LargeFurnace(500, 1));
		btnList[11].setOutput(new LargeBattery(500, 1));
		btnList[12].setOutput(new WaterPump(500, 1));
		btnList[13].setOutput(new Boiler(500, 1));
		btnList[14].setOutput(new SteamEngine(500, 1));
		btnList[15].setOutput(new LargePowerLine(500, 1));
		btnList[16].setOutput(new FlagPole(500, 1));
		btnList[17].setOutput(new AssemblyMachine(500, 1));
		btnList[18].setOutput(new Pipe(500, 1));

		btnList[19].setOutput(new Pumpjack(500, 1));
		btnList[20].setOutput(new OilBoiler(500, 1));
		btnList[21].setOutput(new StorageTank(500, 1));
		btnList[22].setOutput(new LargeElectricMiningDrill(500, 1));
		btnList[23].setOutput(new IndustrialGradeBattery(500, 1));
		btnList[24].setOutput(new OreCrusher(500, 1));
		btnList[25].setOutput(new LeachingPlant(500, 1));
		btnList[26].setOutput(new SedimentationPlant(500, 1));
		btnList[27].setOutput(new BlastFurnace(500, 1));
		btnList[28].setOutput(new CastingMachine(500, 1));
		btnList[29].setOutput(new Sapling(2));
		btnList[30].setOutput(new Greenhouse(500, 1));
		btnList[31].setOutput(new OilRefinery(500, 1));
		btnList[32].setOutput(new LargeStorageTank(500, 1));
		btnList[33].setOutput(new SolarPanel(500, 1));
		btnList[34].setOutput(new WindTurbine(500, 1));
		btnList[35].setOutput(new GeothermalWell(500, 1));
		btnList[36].setOutput(new NuclearReactor(500, 1));
		btnList[37].setOutput(new SteamTurbine(500, 1));
		btnList[38].setOutput(new CoolingTower(500, 1));
		btnList[39].setOutput(new BucketWheelExcavator(500, 1));
		btnList[40].setOutput(new RocketPart());
		btnList[41].setOutput(new Stick());

		// Set image icons
		btnList[0].setImage("Pickaxe", 40, 40);
		btnList[1].setImage("Axe", 40, 40);
		btnList[2].setImage("Bucket", 40, 40);
		btnList[3].setImage("ResearchLab", 40, 40);
		btnList[4].setImage("Furnace", 40, 40);
		btnList[5].setImage("ElectricMiningDrill", 40, 40);
		btnList[6].setImage("LowTierBattery", 40, 40);
		btnList[7].setImage("HandCrankGenerator", 40, 40);
		btnList[8].setImage("Powerline", 40, 40);
		btnList[9].setImage("OreSorter", 40, 40);
		btnList[10].setImage("LargeFurnace", 40, 40);
		btnList[11].setImage("LargeBattery", 40, 40);
		btnList[12].setImage("WaterPump", 40, 40);
		btnList[13].setImage("Boiler", 40, 40);
		btnList[14].setImage("SteamEngine", 40, 40);
		btnList[15].setImage("LargePowerLine", 40, 40);
		btnList[16].setImage("FlagPole", 40, 40);
		btnList[17].setImage("AssemblyMachine", 40, 40);
		btnList[18].setImage("Pipe", 40, 40);
		btnList[19].setImage("Pumpjack", 40, 40);
		btnList[20].setImage("OilBoiler", 40, 40);
		btnList[21].setImage("StorageTank", 40, 40);
		btnList[22].setImage("LargeElectricMiningDrill", 40, 40);
		btnList[23].setImage("IndustrialGradeBattery", 40, 40);
		btnList[24].setImage("OreCrusher", 40, 40);
		btnList[25].setImage("LeechingPlant", 40, 40);
		btnList[26].setImage("SedimentationPlant", 40, 40);
		btnList[27].setImage("BlastFurnace", 40, 40);
		btnList[28].setImage("CastingMachine", 40, 40);
		btnList[29].setImage("Sapling", 40, 40);
		btnList[30].setImage("Greenhouse", 40, 40);
		btnList[31].setImage("OilRefinery", 40, 40);
		btnList[32].setImage("LargeStorageTank", 40, 40);
		btnList[33].setImage("SolarPanel", 40, 40);
		btnList[34].setImage("WindTurbine", 40, 40);
		btnList[35].setImage("GeothermalHeatPump", 40, 40);
		btnList[36].setImage("NuclearTower", 40, 40);
		btnList[37].setImage("SteamTurbine", 40, 40);
		btnList[38].setImage("CoolingTower", 40, 40);
		btnList[39].setImage("BucketWheelExcavator", 40, 40);
		btnList[40].setImage("RocketShip", 40, 40);
		btnList[41].setImage("Stick", 40, 40);

		// Set recipes for items
		btnList[0].setCost(new Item[] {new Iron(3), new Wood(2)});
		btnList[1].setCost(new Item[] {new Iron(2), new Wood(2)});
		btnList[2].setCost(new Item[] {new Iron(3)});
		btnList[3].setCost(new Item[] {new Iron(10), new Copper(10), new Stone(10)});
		btnList[4].setCost(new Item[] {new Wood(15), new Stone(50)});
		btnList[5].setCost(new Item[] {new Iron(200), new Copper(100), new Steel(50)});
		btnList[6].setCost(new Item[] {new Copper(50), new Nickel(25)});
		btnList[7].setCost(new Item[] {new Copper(10), new Steel(10)});
		btnList[8].setCost(new Item[] {new Copper(3), new Wood(3)});
		btnList[9].setCost(new Item[] {new Iron(200), new Copper(150)});
		btnList[10].setCost(new Item[] {new Iron(600), new Coal(200)});
		btnList[11].setCost(new Item[] {new Copper(100), new Nickel(50)});
		btnList[12].setCost(new Item[] {new Iron(125), new Wood(30)});
		btnList[13].setCost(new Item[] {new Iron(300), new Coal(150), new Stone(200)});
		btnList[14].setCost(new Item[] {new Iron(500), new Copper(300), new Steel(100)});
		btnList[15].setCost(new Item[] {new Copper(5), new Steel(5)});
		btnList[16].setCost(new Item[] {new Copper(2), new Steel(2), new Nickel(2), new Aluminum(2)});
		btnList[17].setCost(new Item[] {new Iron(150), new Copper(150), new Steel(25)});
		btnList[18].setCost(new Item[] {new Iron(2)});
		btnList[19].setCost(new Item[] {new Copper(1000), new Steel(200)});
		btnList[20].setCost(new Item[] {new Copper(300), new Steel(200)});
		btnList[21].setCost(new Item[] {new Iron(700)});
		btnList[22].setCost(new Item[] {new Copper(800), new Steel(900), new Titanium(300)});
		btnList[23].setCost(new Item[] {new Copper(200), new Lithium(100)});
		btnList[24].setCost(new Item[] {new Iron(2000), new Copper(1000), new Steel(600)});
		btnList[25].setCost(new Item[] {new Iron(5000), new Copper(2000), new Aluminum(500)});
		btnList[26].setCost(new Item[] {new Iron(4500), new Copper(1000)});
		btnList[27].setCost(new Item[] {new Iron(1500), new Steel(400), new Coal(300)});
		btnList[28].setCost(new Item[] {new Iron(500), new Copper(1000)});
		btnList[29].setCost(new Item[] {new Wood(1)});
		btnList[30].setCost(new Item[] {new Iron(1000), new Glass(500)});
		btnList[31].setCost(new Item[] {new Iron(7000), new Steel(10000), new Titanium(3000)});
		btnList[32].setCost(new Item[] {new Steel(1200)});
		btnList[33].setCost(new Item[] {new Copper(2000), new Aluminum(2000), new Glass(2000)});
		btnList[34].setCost(new Item[] {new Copper(1500), new Aluminum(1500)});
		btnList[35].setCost(new Item[] {new Copper(7000), new Steel(4000)});
		btnList[36].setCost(new Item[] {new Steel(12000), new Uranium(200), new Stone(10000)});
		btnList[37].setCost(new Item[] {new Copper(2000), new Titanium(3000)});
		btnList[38].setCost(new Item[] {new Steel(500), new Stone(1000)});
		btnList[39].setCost(new Item[] {new Iron(2000), new Copper(2000), new Steel(500), new Titanium(200)});
		btnList[40].setCost(new Item[] {new Iron(5000), new Copper(2500), new Nickel(1000), new Aluminum(1000), new Titanium(500), new Lithium(500)});
		btnList[41].setCost(new Item[] {new Wood(50000)});

		btnList[0].setDesc("Pickaxe@Used to manually mine ore@Tools");
		btnList[1].setDesc("Axe@sed to manually cut down forests@Tools");
		btnList[2].setDesc("Bucket@Used to manually transport fluids@Tools");
		btnList[3].setDesc("Research Lab@Used for unlocking new technologies@Research Lab");
		btnList[4].setDesc("Furnace@Used for smelting ores into metal@Furnace");
		btnList[5].setDesc("Electric Mining Drill@Automatically mines ore tiles@Mining Drill");
		btnList[6].setDesc("Small Battery@Stores small capacity of power@Battery");
		btnList[7].setDesc("Handcrank Generator@Used to manually generate power@Electricity");
		btnList[8].setDesc("Power Line@Extends powered area@Electricity");
		btnList[9].setDesc("Ore Sorter@Used to sort ores into more materials@Ore Sorting");
		btnList[10].setDesc("Large Furnace@Used for mass smelting ores into metal@Large Furnace");
		btnList[11].setDesc("Large Battery@Stores medium capacity of power@Battery Mk2");
		btnList[12].setDesc("Water Pump@Draws water from water tile@Steam Power");
		btnList[13].setDesc("Boiler@Heats water into steam@Steam Power");
		btnList[14].setDesc("Steam Engine@Uses steam to generate power@Steam Power");
		btnList[15].setDesc("Large Power Line@Extends powered area longer distances@Steam Power");
		btnList[16].setDesc("Flag Pole@Extends buildable area@Base Expansion");
		btnList[17].setDesc("Assembly Machine@Used to automatically craft items@Logistics");
		btnList[18].setDesc("Pipe@Used to transport items@Logistics");
		btnList[19].setDesc("Pumpjack@Extracts oil from oil tiles@Oil Extraction");
		btnList[20].setDesc("Oil Boiler@Heats water into steam using oil@Oil Extraction");
		btnList[21].setDesc("Storage Tank@Stores fluids@Oil Extraction");
		btnList[22].setDesc("Large Electric Mining Drill@Automtically mines ore tiles fast@Mining Drill Mk2");
		btnList[23].setDesc("Industrial Grade Battery@Stores large capacity of power@Battery Mk3");
		btnList[24].setDesc("Ore Crusher@Creates crushed ore@Ore Processing");
		btnList[25].setDesc("Leaching Plant@Turns crushed ore into slurry@Ore Processing");
		btnList[26].setDesc("Sedimentation Plant@Turns slurry into mixed ores@Ore Processing");
		btnList[27].setDesc("Blast Furnace@Turns ore into molten metal@Mass Production");
		btnList[28].setDesc("Casting Machine@Casts molten metal into plates@Mass Production");
		btnList[29].setDesc("Sapling@Creates saplings for trees@Dendrology");
		btnList[30].setDesc("Greenhouse@Grows saplings into trees@Dendrology");
		btnList[31].setDesc("Oil Refinery@Refines oil into other products@Oil Refining");
		btnList[32].setDesc("Large Storage Tank@Stores large amount of fluids@Oil Refining");
		btnList[33].setDesc("Solar Panel@Generates electricity from the sun@Green Energy");
		btnList[34].setDesc("Wind Turbine@Generates electricity from wind@Green Energy");
		btnList[35].setDesc("Geothermal Well@Heats water to steam from ground@Geothermal");
		btnList[36].setDesc("Nuclear Reactor@Heats water with nuclear energy@Nuclear Power");
		btnList[37].setDesc("Steam Turbine@Uses steam to generate mass power@Nuclear Power");
		btnList[38].setDesc("Cooling Tower@Vents superheated water@Nuclear Power");
		btnList[39].setDesc("Bucket Wheel Excavator@Automatically mines ore tiles very fast@Mining Drill Mk3");
		btnList[40].setDesc("Rocket Part@Only craftable in assembly machine@Rocket Ship");
		btnList[41].setDesc("Nicc@is connor or naymul the bigger snek@-<$ERROR#!");

	} // End createRecipes method

	/*@@@@@@@@@@@@@@@@@@ Create Craft Panel UI @@@@@@@@@@@@@@@@@@*/

	/**
	 * initComponents
	 * Method to create basic UI of craft panel
	 * @param none
	 * @return none
	 */
	private void initComponents() {

		lblResourceList = new JLabel[7];
		for (int i = 0; i < lblResourceList.length; i++) {
			lblResourceList[i] = new JLabel(Integer.toString(i+1));
		} // End for loop

		pnlRecipes = new JPanel();
		pnlRecipes.setBackground(Color.PINK);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(dim.width/2 - 422)
						.addComponent(pnlRecipes, GroupLayout.PREFERRED_SIZE, 844, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(dim.width/2 - 422, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(dim.height/2 - 382)
						.addComponent(pnlRecipes, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(dim.height/2 - 382, Short.MAX_VALUE))
				);

		lblCraftingRecipes = new JLabel("Crafting Recipes");
		lblCraftingRecipes.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));

		pnlDescription = new JPanel();
		GroupLayout gl_pnlRecipes = new GroupLayout(pnlRecipes);
		gl_pnlRecipes.setHorizontalGroup(
				gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRecipes.createSequentialGroup()
						.addGap(124)
						.addComponent(lblCraftingRecipes)
						.addPreferredGap(ComponentPlacement.RELATED, 364, Short.MAX_VALUE)
						.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addGap(132))
				.addGroup(gl_pnlRecipes.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlRecipes.createSequentialGroup()
										.addComponent(btnList[34], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnList[35], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlRecipes.createSequentialGroup()
										.addComponent(btnList[36], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnList[37], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnList[38], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnList[39], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnList[40], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnList[41], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlRecipes.createSequentialGroup()
												.addComponent(btnList[0], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[1], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[2], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[3], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[4], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlRecipes.createSequentialGroup()
												.addComponent(btnList[5], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[6], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[7], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[8], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[9], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnlRecipes.createSequentialGroup()
														.addComponent(btnList[10], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnList[11], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnList[12], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnList[13], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnList[14], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_pnlRecipes.createSequentialGroup()
														.addComponent(btnList[15], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnList[16], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnList[17], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnList[18], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_pnlRecipes.createSequentialGroup()
												.addComponent(btnList[19], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[20], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[21], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[22], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[23], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlRecipes.createSequentialGroup()
												.addComponent(btnList[24], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[25], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[26], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[27], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[28], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlRecipes.createSequentialGroup()
												.addComponent(btnList[29], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(btnList[30], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(btnList[31], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(btnList[32], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnList[33], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
						.addComponent(pnlDescription, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		gl_pnlRecipes.setVerticalGroup(
				gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRecipes.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCraftingRecipes)
								.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlRecipes.createSequentialGroup()
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addComponent(btnList[1], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[0], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[2], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[3], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[4], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGap(30)
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addComponent(btnList[5], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[6], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[7], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[8], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[9], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGap(30)
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addComponent(btnList[10], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[11], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[12], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[13], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[14], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addComponent(btnList[15], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[16], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[17], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[18], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGap(30)
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addComponent(btnList[19], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[20], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[21], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[22], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[23], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addComponent(btnList[24], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[25], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[26], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[27], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[28], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGap(30)
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnlRecipes.createSequentialGroup()
														.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
																.addComponent(btnList[29], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
																.addComponent(btnList[30], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
																.addComponent(btnList[34], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
																.addComponent(btnList[35], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
												.addComponent(btnList[31], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[32], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[33], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGap(30)
										.addGroup(gl_pnlRecipes.createParallelGroup(Alignment.LEADING)
												.addComponent(btnList[36], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[37], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[38], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[39], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnList[40], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnList[41], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addComponent(pnlDescription, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(39, Short.MAX_VALUE))
				);

		btnCraft = new JButton("CRAFT ITEM");
		btnCraft.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCraft.setFocusable(false);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblDetails = new JLabel("Details:");
		lblDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResearch = new JLabel("Research Needed");
		lblResearch.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblCost = new JLabel("Cost");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResourceList[0] = new JLabel("Resource 1");
		lblResourceList[0].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResourceList[1] = new JLabel("Resource2");
		lblResourceList[1].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResourceList[2] = new JLabel("Resource 1");
		lblResourceList[2].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResourceList[3] = new JLabel("Resource 1");
		lblResourceList[3].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResourceList[4] = new JLabel("Resource 1");
		lblResourceList[4].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResourceList[5] = new JLabel("Resource 1");
		lblResourceList[5].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblResourceList[6] = new JLabel("Resource 1");
		lblResourceList[6].setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblResourceList[6].setVerticalAlignment(SwingConstants.TOP);
		GroupLayout gl_pnlDescription = new GroupLayout(pnlDescription);
		gl_pnlDescription.setHorizontalGroup(
				gl_pnlDescription.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addGap(113)
						.addComponent(btnCraft, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(112))
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblDetails, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResearch, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCost, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResourceList[0], GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResourceList[1], GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResourceList[2], GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResourceList[3], GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResourceList[4], GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResourceList[5], GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblResourceList[6], GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_pnlDescription.setVerticalGroup(
				gl_pnlDescription.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDescription.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblDetails, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblResearch, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblCost, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblResourceList[0], GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblResourceList[1], GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblResourceList[2], GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblResourceList[3], GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblResourceList[4], GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblResourceList[5], GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblResourceList[6], GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
						.addComponent(btnCraft)
						.addGap(36))
				);
		pnlDescription.setLayout(gl_pnlDescription);
		pnlRecipes.setLayout(gl_pnlRecipes);
		setLayout(groupLayout);

	} // End initComponents method

	/*@@@@@@@@@@@@@@@@@@ End of Create Craft Panel UI @@@@@@@@@@@@@@@@@@*/

	/**
	 * determineCraftable
	 * Enables/Disables crafting buttons based on player's research and inventory
	 * @param btnInventory
	 */
	public void determineCraftable() {

		// Run through inventory, finding all Items (Exclude blank slots and buildings)
		itemList = new Item[] {new Iron(0), new Copper(0), new Steel(0), new Nickel(0), new Aluminum(0), new Titanium(0), new Lithium(0), new Wood(0), new Coal(0), new Uranium(0), new Stone(0), new Glass(0)};

		for (int i = 0; i < btnInventory.length; i++) {
			for (int j = 0; j < btnInventory[i].length; j++) {

				if (btnInventory[i][j].getItem() != null) {
					if (btnInventory[i][j].getItem() instanceof Item) {

						Item tmp = (Item) btnInventory[i][j].getItem(); // Temporary reference to item
						// If item is any of the 12 crafting materials, add to sum
						if (tmp instanceof Iron) {
							itemList[0].setStack(itemList[0].getStack() + tmp.getStack());
						}else if (tmp instanceof Copper) {
							itemList[1].setStack(itemList[1].getStack() + tmp.getStack());
						}else if (tmp instanceof Steel) {
							itemList[2].setStack(itemList[2].getStack() + tmp.getStack());
						}else if (tmp instanceof Nickel) {
							itemList[3].setStack(itemList[3].getStack() + tmp.getStack());
						}else if (tmp instanceof Aluminum) {
							itemList[4].setStack(itemList[4].getStack() + tmp.getStack());
						}else if (tmp instanceof Titanium) {
							itemList[5].setStack(itemList[5].getStack() + tmp.getStack());
						}else if (tmp instanceof Lithium) {
							itemList[6].setStack(itemList[6].getStack() + tmp.getStack());
						}else if (tmp instanceof Wood) {
							itemList[7].setStack(itemList[7].getStack() + tmp.getStack());
						}else if (tmp instanceof Coal) {
							itemList[8].setStack(itemList[8].getStack() + tmp.getStack());
						}else if (tmp instanceof Uranium) {
							itemList[9].setStack(itemList[9].getStack() + tmp.getStack());
						}else if (tmp instanceof Stone) {
							itemList[10].setStack(itemList[10].getStack() + tmp.getStack());
						}else if (tmp instanceof Glass) {
							itemList[11].setStack(itemList[11].getStack() + tmp.getStack());
						} // End if

					} // End if
				} // End if

			} // End for loop
		} // End for loop

		// Run through hotbar finding craftable items
		for (int i = 0; i < btnHotbar.length; i++) {

			if (btnHotbar[i].getItem() != null) {
				if (btnHotbar[i].getItem() instanceof Item) {

					Item tmp = (Item) btnHotbar[i].getItem(); // Temporary reference to item
					// If item is any of the 12 crafting materials, add to sum
					if (tmp instanceof Iron) {
						itemList[0].setStack(itemList[0].getStack() + tmp.getStack());
					}else if (tmp instanceof Copper) {
						itemList[1].setStack(itemList[1].getStack() + tmp.getStack());
					}else if (tmp instanceof Steel) {
						itemList[2].setStack(itemList[2].getStack() + tmp.getStack());
					}else if (tmp instanceof Nickel) {
						itemList[3].setStack(itemList[3].getStack() + tmp.getStack());
					}else if (tmp instanceof Aluminum) {
						itemList[4].setStack(itemList[4].getStack() + tmp.getStack());
					}else if (tmp instanceof Titanium) {
						itemList[5].setStack(itemList[5].getStack() + tmp.getStack());
					}else if (tmp instanceof Lithium) {
						itemList[6].setStack(itemList[6].getStack() + tmp.getStack());
					}else if (tmp instanceof Wood) {
						itemList[7].setStack(itemList[7].getStack() + tmp.getStack());
					}else if (tmp instanceof Coal) {
						itemList[8].setStack(itemList[8].getStack() + tmp.getStack());
					}else if (tmp instanceof Uranium) {
						itemList[9].setStack(itemList[9].getStack() + tmp.getStack());
					}else if (tmp instanceof Stone) {
						itemList[10].setStack(itemList[10].getStack() + tmp.getStack());
					}else if (tmp instanceof Glass) {
						itemList[11].setStack(itemList[11].getStack() + tmp.getStack());
					} // End if

				} // End if
			} // End if

		} // End for loop

		/*@@@@@@@@@@@@@@ Colouring Buttons according to craftablility @@@@@@@@@@@@@@*/

		// Colour all buttons white for error checking
		for (int i = 0; i < btnList.length; i++) {
			btnList[i].setBackground(Color.WHITE);
		}

		// Colour buttons black for not researched yet
		if (!rTree.getItem(0)) {
			btnList[0].setBackground(black);
			btnList[1].setBackground(black);
			btnList[2].setBackground(black);
		}
		if (!rTree.getItem(1)) {
			btnList[3].setBackground(black);
		}
		if (!rTree.getItem(2)) {
			btnList[4].setBackground(black);
		}
		if (!rTree.getItem(3)) {
			btnList[5].setBackground(black);
		}
		if (!rTree.getItem(4)) {
			btnList[6].setBackground(black);
		}
		if (!rTree.getItem(5)) {
			btnList[7].setBackground(black);
			btnList[8].setBackground(black);
		}
		if (!rTree.getItem(6)) {
			btnList[9].setBackground(black);
		}
		if (!rTree.getItem(7)) {
			btnList[10].setBackground(black);
		}
		if (!rTree.getItem(8)) {
			btnList[11].setBackground(black);
		}
		if (!rTree.getItem(9)) {
			btnList[12].setBackground(black);
			btnList[13].setBackground(black);
			btnList[14].setBackground(black);
			btnList[15].setBackground(black);
		}
		if (!rTree.getItem(10)) {
			btnList[16].setBackground(black);
		}
		if (!rTree.getItem(11)) {
			btnList[17].setBackground(black);
			btnList[18].setBackground(black);
		}
		if (!rTree.getItem(12)) {
			btnList[19].setBackground(black);
			btnList[20].setBackground(black);
			btnList[21].setBackground(black);
		}
		if (!rTree.getItem(13)) {
			btnList[22].setBackground(black);
		}
		if (!rTree.getItem(14)) {
			btnList[23].setBackground(black);
		}
		if (!rTree.getItem(15)) {
			btnList[24].setBackground(black);
			btnList[25].setBackground(black);
			btnList[26].setBackground(black);
		}
		if (!rTree.getItem(16)) {
			btnList[27].setBackground(black);
			btnList[28].setBackground(black);
		}
		if (!rTree.getItem(17)) {
			btnList[29].setBackground(black);
			btnList[30].setBackground(black);
		}
		if (!rTree.getItem(18)) {
			btnList[31].setBackground(black);
			btnList[32].setBackground(black);
		}
		if (!rTree.getItem(19)) {
			btnList[33].setBackground(black);
			btnList[34].setBackground(black);
		}
		if (!rTree.getItem(20)) {
			btnList[35].setBackground(black);
		}
		if (!rTree.getItem(21)) {
			btnList[36].setBackground(black);
			btnList[37].setBackground(black);
			btnList[38].setBackground(black);
		}
		if (!rTree.getItem(22)) {
			btnList[39].setBackground(black);
		}
		if (!rTree.getItem(23)) {
			btnList[40].setBackground(black);
		}
		if (!rTree.getItem(24)) {
			btnList[41].setBackground(black);
		}

		// Check button craftability and colour buttons accordingly
		for (int i = 0; i < btnList.length; i++) {
			// Omit black coloured buttons as they require research to unlock to craft
			if (btnList[i].getBackground() != black) {
				// Colour Green for craftable, red for craftable but missing resources,
				if (btnList[i].getCraftable(itemList)) {
					btnList[i].setBackground(green);
				}else {
					btnList[i].setBackground(red);
				} // End if
			} // End if
		} // End for loop

	} // End determineCraftable method

	/**
	 * addEventHandlers
	 * Add action listeners to respond to button presses
	 * @param none
	 * @return none
	 */
	private void addEventHandlers() {

		for (int i = 0; i < btnList.length; i++) {
			btnList[i].addActionListener(new craftingListener());
		} // End for loop

		btnCraft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (btnSelected != null) {
					if (!crafting) {
						addToInventory(btnSelected);
					}
				}else{

					t = new Timer(5000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							lblResourceList[6].setText(tmpText);
							lblResourceList[6].setIcon(null);
							t.stop();
						}
					});
					tmpText = lblResourceList[6].getText();
					lblResourceList[6].setText("");
					lblResourceList[6].setIcon(icon);
					t.start();

				} // End if

			} // End actionPerformed method

		});

	} // End addEventHandlers method

	/**
	 * addToInventory
	 * Finds empty slot in hotbar or inventory, adds item to slot
	 * @param <E>
	 * @param item to be added
	 * @return none
	 */
	private <E> void addToInventory(CraftingButton button) {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				String spot = "";
				int i_Index = 0;
				int j_Index = 0;
				crafting = false;
				
				// Loop through hotbar to find empty space
				for (int i = 0; i < btnHotbar.length && !crafting; i++) {
					// If empty space is found, set item and image to newly crafted item
					if (btnHotbar[i].getItem() == null) {
						spot = "hotbar";
						i_Index = i;
						crafting = true;
					} // End if
				} // End for loop

				// Loop through inventory to find empty space
				for (int i = 0; i < btnInventory.length && !crafting; i++) {
					for (int j = 0; j < btnInventory[i].length && !crafting; j++) {
						// If empty space is found, set item and image to newly crafted item
						if (btnInventory[i][j].getItem() == null) {
							spot = "inventory";
							i_Index = i;
							j_Index = j;
							crafting = true;
						} // End if
					} // End for loop
				} // End for loop

				if (crafting) {
					try {
						btnCraft.setText("Crafting: 5");
						Thread.sleep(1000);
						btnCraft.setText("Crafting: 4");
						Thread.sleep(1000);
						btnCraft.setText("Crafting: 3");
						Thread.sleep(1000);
						btnCraft.setText("Crafting: 2");
						Thread.sleep(1000);
						btnCraft.setText("Crafting: 1");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} // End try catch statement
					
					if (spot.equals("hotbar")) {
						btnHotbar[i_Index].setItem(button.getOutput());
						btnHotbar[i_Index].setImage(button.getOutput().getClass().getSimpleName());
					}else if (spot.equals("inventory")) {
						btnInventory[i_Index][j_Index].setItem(button.getOutput());
						btnInventory[i_Index][j_Index].setImage(button.getOutput().getClass().getSimpleName());
					}
					
					// Remove crafting materials from inventory
					Item[] removeCost = new Item[button.getCost().length];
					// Copy over cost data to separate array
					for (int i = 0; i < removeCost.length; i++) {
						removeCost[i] = button.getCost()[i].deepCopy();
						removeCost[i].setStack(button.getCost()[i].getStack());
					} // End for loop

					for (int i = 0; i < btnHotbar.length; i++) { // Loop through hotbar in search of cost materials
						for (int k = 0; k < removeCost.length; k++) { // Loop through removeCost array to check if materials match

							if (removeCost[k] != null && btnHotbar[i].getItem() != null && btnHotbar[i].getItem() instanceof Item) {

								Item focusItem = (Item) btnHotbar[i].getItem();

								if (focusItem.getClass() == removeCost[k].getClass()) { // Determine if items are the same
									// Subtract cost materials from inventory. If cost materials are greater than stack, move onto next inventory slot
									if (focusItem.getStack() > removeCost[k].getStack()) {
										focusItem.setStack(focusItem.getStack() - removeCost[k].getStack());
										removeCost[k] = null;
									}else if (focusItem.getStack() == removeCost[k].getStack()) {
										focusItem = null;
										removeCost[k] = null;
									}else if (focusItem.getStack() < removeCost[k].getStack()) {
										removeCost[k].setStack(removeCost[k].getStack() - focusItem.getStack());
										focusItem = null;
									} // End if

								} // End determining if items are the same

							} // End if
						} // End for loop through remove cost array
					} // End for loop through hotbar

					for (int i = 0; i < btnInventory.length; i++) { // Loop through inventory in search of cost materials
						for (int j = 0; j < btnInventory[i].length; j++) {
							for (int k = 0; k < removeCost.length; k++) { // Loop through removeCost array to check if materials match
								if (removeCost[k] != null && btnInventory[i][j].getItem() != null && btnInventory[i][j].getItem() instanceof Item) {

									Item focusItem = (Item) btnInventory[i][j].getItem();

									if (focusItem.getClass() == removeCost[k].getClass()) { // Determine if items are the same
										// Subtract cost materials from inventory. If cost materials are greater than stack, move onto next inventory slot
										if (focusItem.getStack() > removeCost[k].getStack()) {
											focusItem.setStack(focusItem.getStack() - removeCost[k].getStack());
											removeCost[k] = null;
										}else if (focusItem.getStack() == removeCost[k].getStack()) {
											btnInventory[i][j].setItem(null);
											removeCost[k] = null;
										}else if (focusItem.getStack() < removeCost[k].getStack()) {
											removeCost[k].setStack(removeCost[k].getStack() - focusItem.getStack());
											btnInventory[i][j].setItem(null);
										} // End if

									} // End determining if items are the same

								} // End if
							} // End for loop through remove cost array
						} // End for loop through inventory
					} // End for loop through inventory

					determineCraftable();
					// Update tool tips
					for (int i = 0; i < btnHotbar.length; i++) {
						btnHotbar[i].updateToolTip();
					}
					for (int i = 0; i < btnInventory.length; i++) {
						for (int j = 0; j < btnInventory[i].length; j++) {
							btnInventory[i][j].updateToolTip();
						}
					}

					crafting = false;
					btnCraft.setText("CRAFT");
				} // End if

			} // End run method

		});
		thread.start();

} // End addToInventory method

/**
 * craftingListener.java
 * @author Aaron Ng
 * @date Jan 7, 2018
 * Responds to button presses by crafting items
 */
class craftingListener implements ActionListener{

	@Override
	/**
	 * actionPerformed
	 * Crafts selected item depending on button
	 * @param ActionEvent e
	 * @return none
	 */
	public void actionPerformed(ActionEvent e) {

		btnSelected = (CraftingButton) e.getSource();

		// Set Resource List labels
		for (int k = 0; k < lblResourceList.length; k++) {
			if (k < btnSelected.getCost().length) {
				Item i = btnSelected.getCost()[k];
				lblResourceList[k].setText(i.getStack() + " x " + i.getClass().getSimpleName());
			}else {
				lblResourceList[k].setText("");
			} // End if
		} // End for loop

		String desc = "";
		String descCopy = "";
		desc = btnSelected.getDesc();
		descCopy = "" + desc;

		lblName.setText(descCopy.substring(0, descCopy.indexOf("@")));
		descCopy = descCopy.substring(descCopy.indexOf("@") + 1);
		lblDetails.setText(descCopy.substring(0, descCopy.indexOf("@")));
		descCopy = descCopy.substring(descCopy.indexOf("@") + 1);
		lblResearch.setText("Research Required: " + descCopy);

		if (btnSelected.getBackground() == green) {
			btnCraft.setEnabled(true);
		}else {
			btnCraft.setEnabled(false);
		}

	} // End actionPerformed method

} // End craftingListener class

} // End CraftPanel class