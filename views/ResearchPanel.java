package b3yond.views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import b3yond.common.ResearchTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

/**
 * ResearchPanel.java
 * @author Aaron Ng
 * @date Jan 2, 2018
 * Creates a visual format of the researchTree
 */
public class ResearchPanel extends JPanel {

	private ResearchTree rTree;
	private Color green; // Green colour used for finished research
	private Color yellow; // Yellow colour used for in progress research
	private Timer time; // Timer to delay research to simulate progress
	private boolean inProgress; // Boolean preventing multiple researches simultaneously

	private JPanel pnlTree;
	private JPanel pnlTier1;
	private JPanel pnlTier2;
	private JPanel pnlTier3;
	private JPanel pnlTier4;
	private JPanel pnlTier5;
	private JPanel pnlTier6;

	private JButton [] researchButtons;

	private JLabel lblUnlockTier3;
	private JLabel lblUnlockTier4;
	private JLabel lblUnlockTier5;
	private JLabel lblUnlockTier6;


	/**
	 * ResearchPanel
	 * Constructor for ResearchPanel
	 * @param researchTree
	 * @return none
	 */
	public ResearchPanel(ResearchTree researchTree){
		this.rTree = researchTree; // Create copy of research tree
		this.researchButtons = new JButton[25]; // Create button array
		inProgress = false;

		// Format main research panel
		setPreferredSize(new Dimension(1920, 1080));
		setLayout(new BorderLayout(0, 0));
		add(new JLabel("                                                                                                "), BorderLayout.EAST);
		add(new JLabel("                                                                                                "), BorderLayout.WEST);
		setFocusable(false);
		setOpaque(false);
		green = new Color(42, 150, 52);
		yellow = new Color(249, 185, 22);

		buildDefaultTree(); // Create default research tree
		for (int i = 0; i < researchButtons.length; i++) {
			researchButtons[i].setFocusable(false); // Set buttons unfocusable to not disrupt mouse and key listeners
		} // End for		
		updateResearchedTech(); // Modify research tree based on researched technologies
		addEventHandlers(); // Add action listeners to buttons

	} // End ResearchPanel constructor

	/**
	 * buildDefaultTree
	 * Build the basic research tree
	 * @param none
	 * @return none
	 */
	public void buildDefaultTree() {

		// Format research tree panel
		pnlTree = new JPanel();
		pnlTree.setBackground(Color.PINK);
		pnlTree.setBorder(BorderFactory.createLineBorder(Color.black));
		add(pnlTree, BorderLayout.CENTER);
		pnlTree.setLayout(new GridLayout(6, 1, 0, 0));

		// Format Tier 1 Panel
		pnlTier1 = new JPanel();
		pnlTier1.setOpaque(false);
		pnlTree.add(pnlTier1);

		researchButtons[1] = new JButton("Research Lab");
		researchButtons[1].setEnabled(false);
		researchButtons[1].setBackground(green);
		researchButtons[1].setFont(new Font("Tahoma", Font.PLAIN, 18));

		researchButtons[0] = new JButton("Tools");
		researchButtons[0].setEnabled(false);
		researchButtons[0].setBackground(green);
		researchButtons[0].setFont(new Font("Tahoma", Font.PLAIN, 18));

		researchButtons[2] = new JButton("Furnace");
		researchButtons[2].setEnabled(false);
		researchButtons[2].setBackground(green);
		researchButtons[2].setFont(new Font("Tahoma", Font.PLAIN, 18));

		GroupLayout gl_pnlTier1 = new GroupLayout(pnlTier1);
		gl_pnlTier1.setHorizontalGroup(
				gl_pnlTier1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier1.createSequentialGroup()
						.addGap(401)
						.addComponent(researchButtons[0], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[1], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[2], GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addGap(400))
				);
		gl_pnlTier1.setVerticalGroup(
				gl_pnlTier1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier1.createSequentialGroup()
						.addGap(54)
						.addGroup(gl_pnlTier1.createParallelGroup(Alignment.LEADING)
								.addComponent(researchButtons[2], GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(researchButtons[0], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[1], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGap(55))
				);
		pnlTier1.setLayout(gl_pnlTier1);

		// Format Tier 2 Panel
		pnlTier2 = new JPanel();
		pnlTier2.setOpaque(false);
		pnlTree.add(pnlTier2);		
		researchButtons[5] = new JButton("Electricity");
		researchButtons[5].setBackground(Color.RED);
		researchButtons[5].setFont(new Font("Tahoma", Font.PLAIN, 18));	
		researchButtons[4] = new JButton("Battery");
		researchButtons[4].setBackground(Color.RED);
		researchButtons[4].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[3] = new JButton("Mining Drill");
		researchButtons[3].setBackground(Color.RED);
		researchButtons[3].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[6] = new JButton("Ore Sorting");
		researchButtons[6].setBackground(Color.RED);
		researchButtons[6].setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_pnlTier2 = new GroupLayout(pnlTier2);
		gl_pnlTier2.setHorizontalGroup(
				gl_pnlTier2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier2.createSequentialGroup()
						.addGap(307)
						.addComponent(researchButtons[3], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[4], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[5], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[6], GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addGap(307))
				);
		gl_pnlTier2.setVerticalGroup(
				gl_pnlTier2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier2.createSequentialGroup()
						.addGap(54)
						.addGroup(gl_pnlTier2.createParallelGroup(Alignment.LEADING)
								.addComponent(researchButtons[6], GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(researchButtons[3], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[4], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[5], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGap(55))
				);
		pnlTier2.setLayout(gl_pnlTier2);

		// Format Tier 3 Panel
		pnlTier3 = new JPanel();
		pnlTier3.setOpaque(false);
		pnlTree.add(pnlTier3);		
		researchButtons[9] = new JButton("Steam Power");
		researchButtons[9].setEnabled(false);
		researchButtons[9].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[10] = new JButton("Base Expansion");
		researchButtons[10].setEnabled(false);
		researchButtons[10].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[8] = new JButton("Battery Mk2");
		researchButtons[8].setEnabled(false);
		researchButtons[8].setFont(new Font("Tahoma", Font.PLAIN, 18));
		researchButtons[7] = new JButton("Large Furnace");
		researchButtons[7].setEnabled(false);
		researchButtons[7].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblUnlockTier3 = new JLabel("Research 3 More Technologies to Access This Tier");
		lblUnlockTier3.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnlockTier3.setFont(new Font("Tahoma", Font.PLAIN, 14));

		researchButtons[11] = new JButton("Logistics");
		researchButtons[11].setFont(new Font("Tahoma", Font.PLAIN, 18));
		researchButtons[11].setEnabled(false);
		GroupLayout gl_pnlTier3 = new GroupLayout(pnlTier3);
		gl_pnlTier3.setHorizontalGroup(
				gl_pnlTier3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier3.createSequentialGroup()
						.addGap(214)
						.addComponent(researchButtons[7], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[8], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[9], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[10], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[11], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(213, Short.MAX_VALUE))
				.addGroup(gl_pnlTier3.createSequentialGroup()
						.addGap(522)
						.addComponent(lblUnlockTier3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(563))
				);
		gl_pnlTier3.setVerticalGroup(
				gl_pnlTier3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTier3.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblUnlockTier3, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(gl_pnlTier3.createParallelGroup(Alignment.LEADING)
								.addComponent(researchButtons[7], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[8], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlTier3.createParallelGroup(Alignment.BASELINE)
										.addComponent(researchButtons[9], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(researchButtons[10], GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
										.addComponent(researchButtons[11], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
						.addGap(55))
				);
		pnlTier3.setLayout(gl_pnlTier3);

		// Format Tier 4 Panel
		pnlTier4 = new JPanel();
		pnlTier4.setOpaque(false);
		pnlTree.add(pnlTier4);
		researchButtons[14] = new JButton("Battery Mk3");
		researchButtons[14].setEnabled(false);
		researchButtons[14].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[15] = new JButton("Ore Processing");
		researchButtons[15].setEnabled(false);
		researchButtons[15].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[16] = new JButton("Mass Production");
		researchButtons[16].setEnabled(false);
		researchButtons[16].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[13] = new JButton("Mining Drill Mk2");
		researchButtons[13].setEnabled(false);
		researchButtons[13].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[12] = new JButton("Oil Extraction");
		researchButtons[12].setEnabled(false);
		researchButtons[12].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblUnlockTier4 = new JLabel("Research 7 More Technologies to Access This Tier");
		lblUnlockTier4.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnlockTier4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_pnlTier4 = new GroupLayout(pnlTier4);
		gl_pnlTier4.setHorizontalGroup(
				gl_pnlTier4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier4.createSequentialGroup()
						.addGap(214)
						.addComponent(researchButtons[12], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[13], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[14], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[15], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[16], GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addGap(213))
				.addGroup(gl_pnlTier4.createSequentialGroup()
						.addGap(522)
						.addComponent(lblUnlockTier4, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(521, Short.MAX_VALUE))
				);
		gl_pnlTier4.setVerticalGroup(
				gl_pnlTier4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier4.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblUnlockTier4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_pnlTier4.createParallelGroup(Alignment.LEADING)
								.addComponent(researchButtons[12], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[13], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[16], GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(researchButtons[15], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[14], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGap(55))
				);
		pnlTier4.setLayout(gl_pnlTier4);

		// Format Tier 5 Panel
		pnlTier5 = new JPanel();
		pnlTier5.setOpaque(false);
		pnlTree.add(pnlTier5);		
		researchButtons[19] = new JButton("Green Energy");
		researchButtons[19].setEnabled(false);
		researchButtons[19].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[18] = new JButton("Oil Refining");
		researchButtons[18].setEnabled(false);
		researchButtons[18].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[20] = new JButton("Geothermal");
		researchButtons[20].setEnabled(false);
		researchButtons[20].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[17] = new JButton("Dendrology");
		researchButtons[17].setEnabled(false);
		researchButtons[17].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblUnlockTier5 = new JLabel("Research 11 More Technologies to Access This Tier");
		lblUnlockTier5.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnlockTier5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_pnlTier5 = new GroupLayout(pnlTier5);
		gl_pnlTier5.setHorizontalGroup(
				gl_pnlTier5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier5.createSequentialGroup()
						.addGap(307)
						.addComponent(researchButtons[17], GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(researchButtons[18], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[19], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[20], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(307))
				.addGroup(gl_pnlTier5.createSequentialGroup()
						.addGap(518)
						.addComponent(lblUnlockTier5)
						.addContainerGap(517, Short.MAX_VALUE))
				);
		gl_pnlTier5.setVerticalGroup(
				gl_pnlTier5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier5.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblUnlockTier5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_pnlTier5.createParallelGroup(Alignment.LEADING)
								.addComponent(researchButtons[17], GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(researchButtons[20], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[18], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[19], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGap(55))
				);
		pnlTier5.setLayout(gl_pnlTier5);

		// Format Tier 6 Panel
		pnlTier6 = new JPanel();
		pnlTier6.setOpaque(false);
		pnlTree.add(pnlTier6);		
		researchButtons[24] = new JButton("-<$ERROR#!");
		researchButtons[24].setEnabled(false);
		researchButtons[24].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[23] = new JButton("Rocket Ship");
		researchButtons[23].setEnabled(false);
		researchButtons[23].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[22] = new JButton("Mining Drill Mk3");
		researchButtons[22].setEnabled(false);
		researchButtons[22].setFont(new Font("Tahoma", Font.PLAIN, 18));		
		researchButtons[21] = new JButton("Nuclear");
		researchButtons[21].setEnabled(false);
		researchButtons[21].setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblUnlockTier6 = new JLabel("Research 14 More Technologies to Access This Tier");
		lblUnlockTier6.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnlockTier6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_pnlTier6 = new GroupLayout(pnlTier6);
		gl_pnlTier6.setHorizontalGroup(
				gl_pnlTier6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier6.createSequentialGroup()
						.addGap(307)
						.addComponent(researchButtons[21], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[22], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[23], GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(researchButtons[24], GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addGap(307))
				.addGroup(gl_pnlTier6.createSequentialGroup()
						.addGap(518)
						.addComponent(lblUnlockTier6, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(517, Short.MAX_VALUE))
				);
		gl_pnlTier6.setVerticalGroup(
				gl_pnlTier6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTier6.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblUnlockTier6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_pnlTier6.createParallelGroup(Alignment.LEADING)
								.addComponent(researchButtons[21], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[22], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[23], GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(researchButtons[24], GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
						.addGap(55))
				);
		pnlTier6.setLayout(gl_pnlTier6);


		// Format title panel
		JPanel pnlTitle = new JPanel();
		add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new BoxLayout(pnlTitle, BoxLayout.Y_AXIS));
		pnlTitle.add(new JLabel(" "));
		pnlTitle.add(new JLabel(" "));	
		pnlTitle.setOpaque(false);
		JLabel lblTitle = new JLabel("Research Tree");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Elephant", Font.PLAIN, 32));
		pnlTitle.add(lblTitle);
		pnlTitle.add(new JLabel(" "));

	} // End buildDefaultTree

	/**
	 * addResearchedTech
	 * Depending on tier and technologies researched, modify the buttons on the research tree
	 * @param none
	 * @return none
	 */
	public void updateResearchedTech() {

		/* @@@@@@@@@@@@@@@@@@@@@@@@@@@ Change notification text to correspond with the tier and the number of researched technologies @@@@@@@@@@@@@@@@@@@@@@@@@@@ */
		int numTech = rTree.getNumResearched(); // Number of researched technologies
		if (rTree.getTier() > 5) {
			lblUnlockTier6.setText("");
		}else {
			if (numTech < 17) {
				lblUnlockTier6.setText("Research " + (17-numTech) + " More Technologies to Access This Tier");
			}else {
				lblUnlockTier6.setText("Unlock the Next Research Tier in a Research Lab");
			} // End if
		} // End if

		if (rTree.getTier() > 4) {
			lblUnlockTier5.setText("");
		}else {
			if (numTech < 14) {
				lblUnlockTier5.setText("Research " + (14-numTech) + " More Technologies to Access This Tier");
			}else {
				lblUnlockTier5.setText("Unlock the Next Research Tier in a Research Lab");
			} // End if
		} // End if

		if (rTree.getTier() > 3) {
			lblUnlockTier4.setText("");
		}else {
			if (numTech < 10) {
				lblUnlockTier4.setText("Research " + (10-numTech) + " More Technologies to Access This Tier");
			}else {
				lblUnlockTier4.setText("Unlock the Next Research Tier in a Research Lab");
			} // End if
		} // End if

		if (rTree.getTier() > 2) {
			lblUnlockTier3.setText("");
		}else {
			if (numTech < 6) {
				lblUnlockTier3.setText("Research " + (6-numTech) + " More Technologies to Access This Tier");
			}else {
				lblUnlockTier3.setText("Unlock the Next Research Tier in a Research Lab");
			} // End if
		} // End if

		/* @@@@@@@@@@@@@@@@@@@@@ Limit for loop recolouring up to currently researched tier @@@@@@@@@@@@@@@@@@@@@ */
		int upperLimit; 
		if (rTree.getTier() == 2) {
			upperLimit = 7; // Limit loop to first 7 technologies
		}else if (rTree.getTier() == 3) {
			upperLimit = 12; // Limit loop to first 12 technologies
		}else if (rTree.getTier() == 4) {
			upperLimit = 17; // Limit loop to first 17 technologies
		}else if (rTree.getTier() == 5) {
			upperLimit = 21; // Limit loop to first 21 technologies
		}else if (rTree.getTier() == 6) {
			upperLimit = 25; // Limit loop to first 24 technologies
		}else {
			upperLimit = 0; // Don't run loop, error 
			System.out.println("Upper Limit not found; Current Tier: " + rTree.getTier());
		} // End if

		// Loop through buttons, if technology is researched, highlight green
		for (int i = 3; i < upperLimit; i++) {
			if (rTree.getItem(i)) {
				researchButtons[i].setBackground(green);
				researchButtons[i].setEnabled(false);
			}else {
				// If technology is not researched yet, highlight red
				researchButtons[i].setBackground(Color.RED);
				researchButtons[i].setEnabled(true);
			} // End if
		} // End for loop

		// Find currently researching technology and highlight yellow		
		if (rTree.getResearching() != -1) {
			researchButtons[rTree.getResearching()].setBackground(yellow);
			researchButtons[rTree.getResearching()].setEnabled(false);
		} // End if

	} // End addResearchedTech method

	/**
	 * addEventHandlers
	 * Add action listeners to all buttons
	 * @param none
	 * @return none
	 */
	private void addEventHandlers() {

		for (int i = 0; i < researchButtons.length; i++) {
			researchButtons[i].addActionListener(new buttonListener());
		} // End for loop

	} // End addEventHandlers method

	/**
	 * buttonListener
	 * @author Aaron Ng
	 * @date Dec 4, 2018
	 * Modified action listener used to handle button interactions
	 */
	class buttonListener implements ActionListener{

		@Override
		/**
		 * actionPerformed
		 * Overrides actionListener method; to handle button interactions
		 * @param ActionEvent e
		 * @return none
		 */
		public void actionPerformed(ActionEvent e) {

			JButton b = (JButton) e.getSource();
			
			if (!inProgress) {
				
				for (int i = 0; i < researchButtons.length; i++) {
					if (b == researchButtons[i]) {
						if (i >= 0 && i <= 2) {
							time = new Timer(1000, new timeListener(i));
						}else if (i >= 3 && i <= 6) {
							time = new Timer(2000, new timeListener(i));
						}else if (i >= 7 && i <= 11) {
							time = new Timer(3000, new timeListener(i));
						}else if (i >= 12 && i <= 16) {
							time = new Timer(4000, new timeListener(i));
						}else if (i >= 17 && i <= 20) {
							time = new Timer(5000, new timeListener(i));
						}else if (i >= 21 && i <= 24) {
							time = new Timer(10000, new timeListener(i));
						} // End if
						time.start();
						inProgress = true;
						b.setEnabled(false);
						b.setBackground(yellow);
					} // End if

				} // End for loop
				
			} // End if

		} // End actionPerformed method

	} // End buttonListener inner class

	/**
	 * timeListener
	 * @author Aaron Ng
	 * @date Dec 4, 2018
	 * Modified action listener with delay used to simulate research in progress
	 */
	class timeListener implements ActionListener{

		int buttonIndex;

		/**
		 * timeListener
		 * Constructor for timeListener
		 * @param int i
		 * @return none
		 */
		public timeListener(int i) {
			this.buttonIndex = i; 
		} // End timeListener constructor

		@Override
		/**
		 * actionPerformed
		 * Overrides action listener method; used to delay and simulate researching
		 * @param ActionEvent e
		 * @return none
		 */
		public void actionPerformed(ActionEvent e) {
			researchButtons[buttonIndex].setBackground(green);
			rTree.setItem(buttonIndex, true);
			time.stop();
			inProgress = false;
			updateResearchedTech();
		} // End actionPerformed method

	} // End timeListener class
	
	/**
	 * getResearchTree
	 * @return ResearchTree
	 */
	public ResearchTree getResearchTree() {
		return rTree;
	}
	
	/**
	 * setResearchTree
	 * @param ResearchTree
	 */
	public void setResearchTree(ResearchTree rTree) {
		this.rTree = rTree;
	}
	
} // End ResearchPanel class