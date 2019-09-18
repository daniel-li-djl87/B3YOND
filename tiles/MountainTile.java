package b3yond.tiles;

import java.awt.image.BufferedImage;

import b3yond.common.Tile;

public class MountainTile extends Tile {
	
	private boolean veinStart;

	public MountainTile(BufferedImage image, boolean veinStart) {
		super(image);
		super.setCanBuild(false);
		this.setVeinStart(veinStart);
	}

	public boolean getVeinStart() {
		return veinStart;
	}

	public void setVeinStart(boolean veinStart) {
		this.veinStart = veinStart;
	}

}
