package b3yond.tiles;

import java.awt.image.BufferedImage;

import b3yond.common.Tile;

public class ConoreTile extends Tile {
	
	private int quantity;
	private boolean veinStart;

	public ConoreTile(BufferedImage image, boolean veinStart) {
		super(image);
		this.setVeinStart(veinStart);
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
