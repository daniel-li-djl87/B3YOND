package b3yond.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * Menu
 * @author Aaron Ng, Connor Mahon
 * @Date Nov 28, 2017
 * Setup menu to launch main game
 */
public class Menu extends JFrame {

	private JPanel contentPane;
	private JLabel lblB3yond;
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnConnect;
	private JButton btnExit;
	private JPanel pnlMainMenu;
	private JPanel pnlGlass;
	private JPanel pnlInteractions;
	private Dimension dim;

	/**
	 * main
	 * Main method to launch menu window
	 * @param String[] args
	 * @returns
	 */
	public static void main(String[] args) {
		
		//Start the background music once the game starts	
		Thread t=new Thread(new musicPlayer());
		t.start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Menu();
				} catch (Exception e) {
					e.printStackTrace();
				} // End try catch statement
			} // End run method
		});
	} // End main method

	/**
	 * Menu
	 * Constructor for menu screen
	 * @param none
	 * @returns none 
	 */
	public Menu() {
		setVisible(true);
		initMainMenu();
		createEvents();
		
	} // End Menu constructor

	/**
	 * createEvents
	 * Creates actionListeners for user interactions
	 * @param none
	 * @returns none
	 */
	private void createEvents() {
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GameWindow("","","");
			}
		});
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				File mapFile = new File("map.txt");
				File buildingFile = new File("buildings.txt");
				File inventoryFile = new File("inventory.txt");
				Scanner input = new Scanner(mapFile);
				String mapStr = input.next();
				input = new Scanner(buildingFile);
				String buildingStr = input.next();
				input = new Scanner(inventoryFile);
				String inventoryStr = input.next();
				new GameWindow(mapStr,buildingStr,inventoryStr);
				dispose();
				}catch (IOException e){
					System.out.println("Failed to load game!");
					e.printStackTrace();
				}
			}
		});
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
	} // End createEvents method

	/**
	 * initMainMenu
	 * Initialize components for main menu
	 * @param none
	 * @returns none
	 */
	private void initMainMenu() {

		dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim);
		pnlGlass = (JPanel)getGlassPane();
		pnlGlass.setLayout(new BorderLayout());
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/b3yond/resources/finnaJail.jpg")));
		setTitle("B3yond");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new BgPanel();
		setContentPane(contentPane);
		pnlMainMenu = new JPanel();
		pnlMainMenu.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlInteractions = new JPanel();
		pnlInteractions.setOpaque(false);

		lblB3yond = new JLabel("B3yond");
		lblB3yond.setHorizontalAlignment(SwingConstants.CENTER);
		lblB3yond.setForeground(new Color(22, 255, 243));
		lblB3yond.setFont(new Font("Elephant", Font.PLAIN, 64));

		btnNewGame = new JButton("New Game");
		btnLoadGame = new JButton("Load Game");
		btnConnect = new JButton("Connect to Server");
		btnExit = new JButton("Exit Game");
		
		GroupLayout gl_pnlInteractions = new GroupLayout(pnlInteractions);
		gl_pnlInteractions.setHorizontalGroup(
				gl_pnlInteractions.createParallelGroup(Alignment.LEADING)
				.addComponent(lblB3yond, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
				.addGroup(gl_pnlInteractions.createSequentialGroup()
						.addGap(250)
						.addGroup(gl_pnlInteractions.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewGame, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addComponent(btnLoadGame, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addComponent(btnConnect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
						.addGap(250))
				);
		gl_pnlInteractions.setVerticalGroup(
				gl_pnlInteractions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInteractions.createSequentialGroup()
						.addGap(37)
						.addComponent(lblB3yond)
						.addGap(40)
						.addComponent(btnNewGame)
						.addGap(18)
						.addComponent(btnLoadGame)
						.addGap(18)
						.addComponent(btnConnect)
						.addGap(18)
						.addComponent(btnExit)
						.addContainerGap(147, Short.MAX_VALUE))
				);
		GroupLayout gl_pnlMainMenu = new GroupLayout(pnlMainMenu);
		gl_pnlMainMenu.setHorizontalGroup(
				gl_pnlMainMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMainMenu.createSequentialGroup()
						.addGap((int)(((double)dim.getWidth()/3.0)))
						.addComponent(pnlInteractions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(89, Short.MAX_VALUE))
				);
		gl_pnlMainMenu.setVerticalGroup(
				gl_pnlMainMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMainMenu.createSequentialGroup()
						.addGap((int)((double)dim.getHeight()/3.0))
						.addComponent(pnlInteractions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(68, Short.MAX_VALUE))
				);
		pnlInteractions.setLayout(gl_pnlInteractions);
		pnlMainMenu.setLayout(gl_pnlMainMenu);
		pnlMainMenu.setOpaque(false);
		pnlMainMenu.add(pnlInteractions);
		pnlGlass.add(pnlMainMenu, BorderLayout.CENTER);
		pnlGlass.setVisible(true);
	} // End initMainMenu method
} // End Window Class



/**
 * musicPlayer
 * @author Naymul 
 * @date January 16,2018
 * Runs music in the background
 */
class musicPlayer implements Runnable{
	
	public void run() {
		  try {
			   File file = new File("music.wav");
			   Clip clip = AudioSystem.getClip();
			   clip.open(AudioSystem.getAudioInputStream(file));
			   clip.start();
			   clip.loop(Clip.LOOP_CONTINUOUSLY);
			   Thread.sleep(clip.getMicrosecondLength());
			  } catch (Exception e) {
			   System.err.println(e.getMessage());
			  }
	}
}

/**
 * BgPanel.java
 * @author Aaron Ng
 * @Date Dec 2, 2017
 * JPanel to display menu background image
 */
class BgPanel extends JPanel{

	BufferedImage bg;
	Dimension dim;
	
	/**
	 * BgPanel
	 * Constructor for BgPanel
	 * @param none
	 * @return none
	 */
	BgPanel(){
		try {
			bg = ImageIO.read(getClass().getResource("/b3yond/resources/menuBackground.jpg")); // Load menu background image
		}catch(IOException e){
			System.out.println("failed to load image");
		}// End try catch statement
		dim = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen size
	} // End BgPanel constructor

	@Override
	/**
	 * paint
	 * Draws menu background image
	 * @param Graphics g
	 * @return none
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, (int)dim.getWidth(), (int)dim.getHeight(), null);
	} // End paint method

} // End BgPanel class