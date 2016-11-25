package solitaire;

import java.util.*;

import javax.swing.JPanel;

public abstract class Pile extends JPanel {
	private ArrayList<Card> list;
	private int firstUpwardsCardIndex = -1;
	private int lastCardIndex = -1;
	private int lastReverseCardIndex = -1;

	public int last_rev_index() {
		return lastReverseCardIndex;
	}

	public int fir_up_index() {
		return firstUpwardsCardIndex;

	}

	public ArrayList<Card> getList() {
		return list;
	}

	public int mySize() {
		return lastCardIndex + 1;

	}

	public int listSize() {
		return list.size();
	}

	public void setFirstUpwardsCardIndex(int firstUpwardsCardIndex) {
		this.firstUpwardsCardIndex = firstUpwardsCardIndex;
	}

	public void setLastCardIndex(int lastCardIndex) {
		this.lastCardIndex = lastCardIndex;
	}

	public void setLastReverseCardIndex(int lastReverseCardIndex) {
		this.lastReverseCardIndex = lastReverseCardIndex;
	}

	public boolean isEmpty() {
		return (mySize() == 0);
	}

	public void resizeList(int n) {
		list = new ArrayList<Card>(n);
	}

	public void add(ArrayList<Card> templist) {
		if (templist != null) {
			int n = templist.size();
			int i = 0;
			while (i < n && templist.get(i).isUpwards() == false) {
				lastReverseCardIndex++;
				lastCardIndex++;
				list.add(templist.get(i));
				i++;
			}
			while (i < n && templist.get(i).isUpwards() == true) {
				list.add(templist.get(i));
				lastCardIndex++;
				i++;
			}
			if (lastCardIndex > lastReverseCardIndex)
				firstUpwardsCardIndex = lastReverseCardIndex + 1;
		}
	}

	// 这个remove只给tabpile，其他重写,懒得改了
	public void remove(int n) {
		for (int i = n; i > 0; i--)
			list.remove(list.size() - 1);
		lastCardIndex -= n;
		if (lastCardIndex == lastReverseCardIndex)
			firstUpwardsCardIndex = -1;

	}

	public void reverse() {
		if (lastReverseCardIndex > -1 && lastCardIndex == lastReverseCardIndex) {
			getList().get(lastReverseCardIndex).setUpwards(true);
			lastReverseCardIndex--;
			firstUpwardsCardIndex = lastCardIndex;
		}
	}

	public abstract boolean check(ArrayList<Card> checkList);

	public abstract ArrayList<Card> checkXY(int x, int y);

	public abstract boolean setPileBounds(int x, int y);
}