package b3yond.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import b3yond.common.Map;

public class MiniMapPanel extends JPanel {
 
 private BufferedImage img;
 private Map map;
// private int xDir,yDir;
// private int x,y;
// private int tileSize;
 
 public MiniMapPanel(BufferedImage img, Map map) {
  this.img = img;
  this.map = map;
 }
 
 public BufferedImage getImage() {
  return this.img;
 }
 public void setImage(BufferedImage img) {
  this.img = img;
 }
 public void setMap(Map map) {
  this.map = map;
 }
 

 
 @Override
 /**
  * paint
  * Overrides paint method to draw map
  * @param Graphics g
  * @return none
  */
 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  g.drawImage(img, 0, 0, 354, 358, null);
  g.setColor(new Color(255, 199, 0));
  float mapSize = (float)map.getTileMap()[0].length;
  int focusX = (int) ((354.0*(map.getX()/(float)map.getTileSize()))/mapSize);
  int focusY = (int) ((358.0*(map.getY()/(float)map.getTileSize()))/mapSize);
  int focusWidth = (int) ((354.0*(map.getDim().getWidth()/(float)map.getTileSize()))/mapSize);
  int focusHeight = (int) ((358.0*(map.getDim().getHeight()/(float)map.getTileSize()))/mapSize);
  g.drawRect(focusX, focusY, focusWidth, focusHeight);
  repaint(); // Request panel refresh
 } // End paint method

// @Override
// public void keyPressed(KeyEvent e) {
//  switch (e.getKeyChar()) {
//  case 'a' :
//   xDir = -1;
//   break;
//  case 'd':
//   xDir = 1;
//   break;
//  case 'w' :
//   yDir = -1;
//   break;
//  case 's' :
//   yDir= 1;
//   break;
//  default :
//   break; 
//  } // End switch statement  
// }
//
// @Override
// public void keyReleased(KeyEvent e) {
//  char key = e.getKeyChar();
//  if (key == 'a' || key == 'd') {
//   xDir = 0;
//  }else if (key == 'w' || key == 's'){
//   yDir = 0;
//  } // End if statement  
// }
//
// @Override
// public void keyTyped(KeyEvent arg0) {
//  
// }
//
// @Override
// public void mouseWheelMoved(MouseWheelEvent e) {
//  // Determine if mouse scrolled up or down
//  if (e.getWheelRotation() < 0) {
//   if (tileSize < 150) { // Make sure zoomed tiles are drawn no larger than 150 pixels
//    tileSize = (int)Math.ceil(tileSize * 1.02); // Multiply the pixel size to have linear zoom effect
//   } // End if
//  }else if (e.getWheelRotation() > 0) {
//   if (tileSize > 40) { // Make sure zoomed tiles are drawn no smaller than 40 pixels
//    tileSize = (int)Math.floor(tileSize * 0.98); // Multiply the pixel size to have linear zoom effect
//   } // End if
//  } // End if
//  
// } // End mouseWheelMoved method
 
} // End MiniMapPanel class
