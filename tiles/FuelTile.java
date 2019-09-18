package b3yond.tiles;

import java.awt.image.BufferedImage;

import b3yond.common.Tile;

public class FuelTile extends Tile {
	
	private int quantity;
	private boolean veinStart;

	public FuelTile(BufferedImage image, boolean veinStart) {
		super(image);
		this.veinStart = veinStart;
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
