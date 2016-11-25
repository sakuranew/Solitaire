package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TabcardPile extends Pile {
	// ����ֽ�ƴ�ֱ���
	int vInterval_up = 30;
	// ����ֽ�ƴ�ֱ���
	int vInterval_down = 10;

	public TabcardPile() {
		resizeList(13);//���ƶ����г�ʼ�����Ϊ13��
	}

	@Override
	public boolean check(ArrayList<Card> checkList) {
		// TODO �Զ����ɵķ������
		Card checkCard = checkList.get(0);
		if (mySize() == 0) {
			if (checkCard.getName() % 13 == 12)//�����������K���Ϳ��Է���
				return true;
			return false;
		} else {//�����ǰ�϶������е�һ�űȵ�ǰ�������һ��С1�����һ�ɫ��ͬ���Ϳ��Է��룬
			Card curCard = getList().get(mySize() - 1);
			int checkType = checkCard.getType() + curCard.getType();
			int checkName = curCard.getName() - checkCard.getName();
			if (checkType > 1000 && checkType < 2000 && checkName == 1)
				return true;
			return false;
		}
	}

	@Override
	public void remove(int n) {
		// TODO �Զ����ɵķ������
		super.remove(n);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO �Զ����ɵķ������
		// super.paintComponent(g);

		Image image;
		if (mySize() == 0) {//û���ƣ���NUll��ͼƬ
			image = new ImageIcon("src/images/null.png").getImage();
			g.drawImage(image, 0, 0, this);
		} else {
			for (int i = 0; i < last_rev_index() + 1; i++) {//�Ȼ����泯�ϵ��Ƶ�ͼƬ
				image = new ImageIcon("src/images/back.jpg").getImage();
				g.drawImage(image, 0, i * vInterval_down, this);
			}
			if (fir_up_index() != -1) {
				for (int i = fir_up_index(); i < mySize(); i++) {//���Ż����泯�ϵ��Ƶ�ͼƬ
					image = getList().get(i).getImage();
	//����滭������ж��Ǹ������泯�Ϻͱ��泯�ϵ��Ƶ����������Ƶĳ��ȣ����Ʊ��۵��ĳ��ȣ����������
					g.drawImage(image, 0,
							(last_rev_index() + 1) * vInterval_down + ((i - fir_up_index()) * vInterval_up), this);

				}
			}
		}
	}

	@Override
	public ArrayList<Card> checkXY(int x, int y) {
		// TODO �Զ����ɵķ������
		if (mySize() == 0)
			return null;
		int n;
		//�������ѡ���������ƶ����ж�Ҳ�Ǹ������泯�Ϻͱ��泯�ϵ��Ƶ����������Ƶĳ��ȣ�
		//���Ʊ��۵��ĳ������ֱ��жϵ� ���Ƚϸ��ӣ���β��Ժ͸Ķ��ŵõ������ս��
		if (y > (last_rev_index() + 1) * vInterval_down + (mySize() - fir_up_index() - 1) * vInterval_up) {
			ArrayList<Card> list = new ArrayList<>(1);
			list.add(getList().get(mySize() - 1));
			return list;
		}
		if (y - (last_rev_index() + 1) * vInterval_down < 0)
			return null;
		n = (y - (last_rev_index() + 1) * vInterval_down) / vInterval_up;

		if (n < 12 && n >= 0 && fir_up_index() >= 0) {
			ArrayList<Card> list = new ArrayList<>();
			for (int i = n + fir_up_index(); i < mySize(); i++) {
				list.add(getList().get(i));
			}
			return list;
		} else
			return null;
	}

	@Override
	public boolean setPileBounds(int x, int y) {
		// TODO �Զ����ɵķ������
		if (mySize() == 0)
			setBounds(x, y, 80, 120);
		else//���в�Ϊ0���������泯�Ϻͱ��泯�ϵ��Ƶ��������Ƶĳ��ȣ��۵����ȣ�����ó��ܹ������򳤶�
			setBounds(x, y, 80,
					vInterval_down * (last_rev_index() + 1) + vInterval_up * (mySize() - fir_up_index() - 1) + 120);
		return true;
	}

}
