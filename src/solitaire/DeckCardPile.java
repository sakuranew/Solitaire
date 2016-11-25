package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class DeckCardPile extends Pile {

	public DeckCardPile() {
		resizeList(24);

	}

	// ��������,���ﲻɾ���б������card�ˣ�ֻ�ı�index
	public void click(DiscardPile disPile) {
		disPile.removeAll();
		if (mySize() > 2) {
			this.setLastCardIndex(mySize() - 4);
			disPile.addCards(3, mySize(), this);
			return;
		} else if (mySize() == 2) {
			this.setLastCardIndex(mySize() - 3);
			disPile.addCards(2, mySize(), this);
			return;
		} else if (mySize() == 1) {
			this.setLastCardIndex(mySize() - 2);
			disPile.addCards(1, mySize(), this);
			return;
		} else {
			// disPile.addCards(0,this);
			this.setLastCardIndex(listSize() - 1);
			this.setLastReverseCardIndex(listSize() - 1);
			return;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO �Զ����ɵķ������
		// super.paintComponent(g);
		Image image;
		if (mySize() == 0) {
			image = new ImageIcon("src/images/null.png").getImage();
			g.drawImage(image, 0, 0, this);
		} else {
			image = new ImageIcon("src/images/back.jpg").getImage();
			g.drawImage(image, 0, 0, this);
		}
	}

	@Override
	public boolean check(ArrayList<Card> checkList) {

		return false;
	}

	public void remove(Card card) {
		getList().remove(card);
		setLastCardIndex(mySize() - 2);
		setLastReverseCardIndex(last_rev_index() - 1);
	}

	public void remove(int n) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public ArrayList<Card> checkXY(int x, int y) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public boolean setPileBounds(int x, int y) {
		// TODO �Զ����ɵķ������
		setBounds(x, y, 80, 120);

		return true;
	}

}
