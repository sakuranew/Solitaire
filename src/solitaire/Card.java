package solitaire;

import java.awt.Image;

public class Card {
	private int name;
	private int type;
	private boolean isUpwards;
	private Image image;

	public int getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isUpwards() {
		return isUpwards;
	}

	public void setUpwards(boolean a) {
		this.isUpwards = a;
	}

	public Card(int name, int type, boolean isUpwards, Image image) {
		super();
		this.name = name;
		this.type = type;
		this.isUpwards = isUpwards;
		this.image = image;
	}

}
