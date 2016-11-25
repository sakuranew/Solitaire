package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class TempcardPile extends Pile {
	// ����ֽ�ƴ�ֱ���
	private int vInterval_up = 30;

	public TempcardPile() {
		resizeList(13);
		setPileBounds(0, 0);
	}

	@Override
	public boolean check(ArrayList<Card> checkList) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO �Զ����ɵķ������
		super.paintComponent(g);
		if (mySize() == 0)
			return;
		Image image;
		// g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i < mySize(); i++) {
			image = getList().get(i).getImage();
			g.drawImage(image, 0, i * vInterval_up, this);
		}
	}

	@Override
	public ArrayList<Card> checkXY(int x, int y) {
		// TODO �Զ����ɵķ������
		return getList();
	}

	@Override
	public void remove(int n) {
		// TODO �Զ����ɵķ������
		for (int i = n; i > 0; i--)
			getList().remove(getList().size() - 1);
		setLastCardIndex(-1);
		setFirstUpwardsCardIndex(-1);
	}

	@Override
	public boolean setPileBounds(int x, int y) {
		// TODO �Զ����ɵķ������
		if (mySize() == 0) {
			setBounds(x, y, 0, 0);
		} else {
			setBounds(x, y, 80, vInterval_up * (mySize() - 1) + 120);
		}
		return true;
	}

}
