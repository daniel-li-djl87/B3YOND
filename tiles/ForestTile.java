package b3yond.tiles;

import java.awt.image.BufferedImage;

import b3yond.common.Tile;

public class ForestTile extends Tile {
	
	private int quantity;
	private boolean veinStart;
	
	public ForestTile(BufferedImage image, boolean veinStart) {
		super(image);
		this.veinStart=veinStart;
		// TODO Auto-generated constructor stub
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean getVeinStart() {
		return veinStart;
	}

	public void setVeinStart(boolean veinStart) {
		this.veinStart = veinStart;
	}
	
}
