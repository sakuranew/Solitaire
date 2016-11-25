package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TabcardPile extends Pile {
	// 正面纸牌垂直间隔
	int vInterval_up = 30;
	// 反面纸牌垂直间隔
	int vInterval_down = 10;

	public TabcardPile() {
		resizeList(13);//该牌堆牌列初始化最大为13张
	}

	@Override
	public boolean check(ArrayList<Card> checkList) {
		// TODO 自动生成的方法存根
		Card checkCard = checkList.get(0);
		if (mySize() == 0) {
			if (checkCard.getName() % 13 == 12)//如果这张牌是K，就可以放入
				return true;
			return false;
		} else {//如果当前拖动的牌列第一张比当前牌列最后一张小1，并且花色不同，就可以放入，
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
		// TODO 自动生成的方法存根
		super.remove(n);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		// super.paintComponent(g);

		Image image;
		if (mySize() == 0) {//没有牌，画NUll的图片
			image = new ImageIcon("src/images/null.png").getImage();
			g.drawImage(image, 0, 0, this);
		} else {
			for (int i = 0; i < last_rev_index() + 1; i++) {//先画背面朝上的牌的图片
				image = new ImageIcon("src/images/back.jpg").getImage();
				g.drawImage(image, 0, i * vInterval_down, this);
			}
			if (fir_up_index() != -1) {
				for (int i = fir_up_index(); i < mySize(); i++) {//接着画正面朝上的牌的图片
					image = getList().get(i).getImage();
	//这里绘画区域的判断是根据正面朝上和背面朝上的牌的数量，和牌的长度，和牌被折叠的长度，计算得来的
					g.drawImage(image, 0,
							(last_rev_index() + 1) * vInterval_down + ((i - fir_up_index()) * vInterval_up), this);

				}
			}
		}
	}

	@Override
	public ArrayList<Card> checkXY(int x, int y) {
		// TODO 自动生成的方法存根
		if (mySize() == 0)
			return null;
		int n;
		//这里对于选定牌列来移动的判断也是根据正面朝上和背面朝上的牌的数量，和牌的长度，
		//和牌被折叠的长度来分别判断的 ，比较复杂，多次测试和改动才得到的最终结果
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
		// TODO 自动生成的方法存根
		if (mySize() == 0)
			setBounds(x, y, 80, 120);
		else//牌列不为0，根据正面朝上和背面朝上的牌的数量和牌的长度，折叠长度，计算得出总共的区域长度
			setBounds(x, y, 80,
					vInterval_down * (last_rev_index() + 1) + vInterval_up * (mySize() - fir_up_index() - 1) + 120);
		return true;
	}

}
