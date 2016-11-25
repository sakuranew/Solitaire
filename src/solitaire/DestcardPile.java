package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class DestcardPile extends Pile {

	private int onlyType;
	// ֽ�Ƴ���
	int length = 120;
	// ����ֽ�ƴ�ֱ���
	int vInterval_up = 30;
	// ����ֽ�ƴ�ֱ���
	int vInterval_down = 10;

	public DestcardPile() {
		resizeList(13);
	}

	public int getOnlyType() {
		return onlyType;
	}

	public void setOnlyType(int onlyType) {
		this.onlyType = onlyType;
	}

	@Override
	public boolean check(ArrayList<Card> checkList) {
		// TODO �Զ����ɵķ������
		int n = checkList.size();
		if (n != 1)
			return false;
		Card checkCard = checkList.get(0);
		if (mySize() == 0) {
			if (checkCard.getName() % 13 == 0) {
				setOnlyType(checkCard.getType());
				return true;
			}
			return false;
		} else {
			Card curCard = getList().get(mySize() - 1);
			int checkType = checkCard.getType();
			int checkName = curCard.getName() - checkCard.getName();
			if (checkType == getOnlyType() && checkName == -1)
				return true;
			return false;
		}
	}

	@Override
	public void remove(int n) {
		// TODO �Զ����ɵķ������
		if (mySize() != 0) {
			getList().remove(mySize() - 1);
			setLastCardIndex(mySize() - 2);
			if (mySize() == 0)
				setFirstUpwardsCardIndex(-1);
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
			image = getList().get(mySize() - 1).getImage();
			g.drawImage(image, 0, 0, this);
		}
	}

	@Override
	public ArrayList<Card> checkXY(int x, int y) {
		// TODO �Զ����ɵķ������
		ArrayList<Card> a = new ArrayList<>();
		a.add(getList().get(mySize() - 1));
		return a;
	}

	@Override
	public boolean setPileBounds(int x, int y) {
		// TODO �Զ����ɵķ������
		setBounds(x, y, 80, 120);
		return true;
	}

}
