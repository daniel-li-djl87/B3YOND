package b3yond.views;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ItemHand<E> {

	private E tempItem;

	public ItemHand() {

	}

	public E getItem() {
		return this.tempItem;
	} // End getItem method

	public void setItem(E item) {
		this.tempItem = item;
	} // End setItem method
	

}
