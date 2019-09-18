package b3yond.tiles;

import java.awt.image.BufferedImage;

import b3yond.common.Tile;

public class WaterTile extends Tile {

	public WaterTile(BufferedImage image) {
		super(image);
		super.setCanBuild(false);
	}

}
