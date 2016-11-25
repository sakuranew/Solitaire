package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class DiscardPile extends Pile {
	// 纸牌水平间隔
	int hInterval = 30;

	public DiscardPile() {
		resizeList(3);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		Image image;
		if (mySize() == 0) {
			return;
		}
		super.paintComponent(g);
		for (int i = 0; i < mySize(); i++) {
			getList().get(i).setUpwards(true);
			image = getList().get(i).getImage();
			g.drawImage(image, i * hInterval, 0, this);
		}
	}

	@Override
	public boolean check(ArrayList<Card> checkList) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void remove(int n) {
		// TODO 自动生成的方法存根
		getList().remove(listSize() - 1);
		setLastCardIndex(mySize() - 2);
	}

	public void removeAll() {
		// TODO 自动生成的方法存根
		getList().clear();
		setLastCardIndex(-1);
	}

	public void addCards(int i, int index, DeckCardPile deckCardPile) {
		// TODO 自动生成的方法存根
		ArrayList<Card> tempArrayList = new ArrayList<Card>(i);
		for (int n = 0; n < i; n++) {
			tempArrayList.add(deckCardPile.getList().get(n + index));
		}
		add(tempArrayList);
	}

	@Override
	public ArrayList<Card> checkXY(int x, int y) {
		// TODO 自动生成的方法存根
		int n = this.mySize() - 1;
		ArrayList<Card> list = new ArrayList<>(1);
		if (n == 2 && x >= n * hInterval) {
			list.add(getList().get(n));
			return list;
		} else if (n == 1 && x >= n * hInterval) {
			list.add(getList().get(n));
			return list;
		} else if (n == 0) {
			list.add(getList().get(n));
			return list;
		}
		return null;
	}

	@Override
	public boolean setPileBounds(int x, int y) {
		// TODO 自动生成的方法存根
		if (mySize() == 0)
			setBounds(x, y, 80, 120);
		else
			setBounds(x, y, hInterval * (mySize() - 1) + 80, 120);
		return true;
	}

}
