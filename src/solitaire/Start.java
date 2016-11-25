package solitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Start {
	HashMap<Object, Object> orderMap;
	Card card[];
	Image image[];
	JFrame jf;
	Mypanel jp;
//	JMenuBar jm;
//	JMenu jme;
	
	ArrayList<Card> curList;
	Pile correctPile = null;
	boolean dragFlag = false;
	// boolean enterFlag=false;
	boolean releaseFlag = false;
	int pressX;
	int pressY;
	int destindex;
	int tabindex;

	DeckCardPile deck;
	DestcardPile dest[];
	DiscardPile dis;
	TabcardPile tab[];
	TempcardPile temp;

	int top_interval = 60;
	int left_interval = 204;
	int mid_interval = 30;
	int pile_interval = 40;
	int width = 1208;
	int height = 680;

	public static void main(String[] args) {
		// TODO 自动生成的方法
		new Start().createFrame();

	}

	private void createFrame() {
		// TODO 自动生成的方法存根
		jf = new JFrame("Solitaire");
		jp = new Mypanel();
	
		deck = new DeckCardPile();
		dis = new DiscardPile();
		temp = new TempcardPile();
		tab = new TabcardPile[7];
		dest = new DestcardPile[4];
		for (int i = 0; i < 7; i++)
			tab[i] = new TabcardPile();
		for (int i = 0; i < 4; i++)
			dest[i] = new DestcardPile();
		jp.setLayout(null);
		jf.getContentPane().add(jp);
		jp.add(deck);
		jp.add(dis);
		jp.add(temp);
		jp.add(dest[0]);
		jp.add(dest[1]);
		jp.add(dest[2]);
		jp.add(dest[3]);
		for (int i = 0; i < 7; i++)
			jp.add(tab[i]);
		initialize();
		jf.setBounds(30, 10, width, height);
//		jm=new JMenuBar();
//		jme=new JMenu("New Game");
//		jm.add(jme);
//		jf.setJMenuBar(jm);
//		
		begin();

	}

	private void setBounds() {
		// TODO 自动生成的方法存根
		
		deck.setPileBounds(left_interval, top_interval);
		dis.setPileBounds(left_interval + pile_interval + 80, top_interval);
		// temp.setPileBounds(0, 0);
		for (int i = 0; i < 4; i++)
			dest[i].setPileBounds(left_interval + (pile_interval + 80) * (3 + i), top_interval);
		for (int i = 0; i < 7; i++)
			tab[i].setPileBounds(left_interval + (pile_interval + 80) * i, top_interval + mid_interval + 120);
	}

	public void initialize() {
		// TODO 自动生成的方法存根
		orderMap = new HashMap<>(13);
		card = new Card[52];

		orderMap.put(1, "A");
		orderMap.put(2, "2");
		orderMap.put(3, "3");
		orderMap.put(4, "4");
		orderMap.put(5, "5");
		orderMap.put(6, "6");
		orderMap.put(7, "7");
		orderMap.put(8, "8");
		orderMap.put(9, "9");
		orderMap.put(10, "10");
		orderMap.put(11, "J");
		orderMap.put(12, "Q");
		orderMap.put(13, "K");
		String str;
		for (int i = 0; i < 13; i++) {
			// type数字关系到判断算法
			str=String.format("src/images/%d.jpg", i+1);
			card[i] = new Card(i, 100, false, new ImageIcon(str).getImage());
			str=String.format("src/images/%d.jpg", i+14);
			card[i + 13] = new Card(i, 200, false, new ImageIcon(str).getImage());
			str=String.format("src/images/%d.jpg", i+27);
			card[i + 26] = new Card(i, 1000, false, new ImageIcon(str).getImage());
			str=String.format("src/images/%d.jpg", i+40);
			card[i + 39] = new Card(i, 1200, false, new ImageIcon(str).getImage());
		}

	}

	private void begin() {
		// TODO 自动生成的方法存根
		ArrayList<Card> templist = new ArrayList<>();
		int random[] = new int[7];
		Random ran = new Random();
		int a, b;
		int index = 7;
		//利用生成随机数来洗牌
		for (int i = 0; i < 10000; i++) {
			a = ran.nextInt(52);
			b = ran.nextInt(52);
			while (a == b)
				b = ran.nextInt(52);
			swap(a, b);
		}
		//选出7张正面朝上的牌分别放到TabCardPile中
		for (int i = 0; i < 7; i++) {
			card[i].setUpwards(true);
		}
		//为7个TabCardPile分别放1,2,3,4...7张牌
		for (int i = 0; i < 7; i++) {
			templist.clear();
			for (int n = i; n > 0; n--) {
				templist.add(card[index++]);
			}
			templist.add(card[i]);
			tab[i].add(templist);
		}
		templist.clear();
		//剩余牌加到DeckCardPile中
		for (int i = index; i < 52; i++)
			templist.add(card[i]);
		deck.add(templist);

		setBounds();
		addLister();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// jp.repaint();
		jf.setVisible(true);
	}

	private void addLister() {
		// TODO 自动生成的方法存根

		deck.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(e.getButton()!=1)
					return;
				super.mouseClicked(e);
				deck.click(dis);
				setBounds();
				deck.repaint();
				dis.repaint();

			}

		});
		dis.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO 自动生成的方法存根
				// super.mouseEntered(e);
				// System.out.println("dragin");
				
				if (curList != null) {
					if (dragFlag == true) {
						dragFlag = false;
						releaseFlag=true;
						dis.remove(1);
						temp.add(curList);
					}
					setTempBounds(e.getX(), e.getY(), e.getSource());

					setBounds();

					jp.repaint();
				}
				// System.out.println(e.getX());
			}

		});
		dis.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				// super.mouseEntered(e);
				// System.out.println("dragin");
				if(e.getButton()!=1)
					return;
				dragFlag = true;

				pressX = e.getX();
				pressY = e.getY();
				curList = dis.checkXY(e.getX(), e.getY());
				// System.out.println(e.getX());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				// super.mouseEntered(e);
				// System.out.println("dragin");
				if(e.getButton()!=1)
					return;
				if(releaseFlag){
				if (correctPile == null) {
					dis.add(curList);
					temp.remove(temp.mySize());
					temp.setPileBounds(0, 0);
				} else {
					deck.remove(curList.get(0));
					correctPile.add(curList);
					temp.remove(temp.mySize());
					temp.setPileBounds(0, 0);
				}
				setBounds();

				jp.repaint();
			}
			releaseFlag=false;}}

		);
		for (destindex = 0; destindex < 4; destindex++) {
			dest[destindex].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					
					if(curList!=null){
						DestcardPile curDest=(DestcardPile)e.getSource();
						if (curDest.check(curList))
							correctPile = curDest;
						checkWin();
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseExited(e);
					
					correctPile = null;
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					// System.out.println("dragin");
					if(e.getButton()!=1)
						return;
					DestcardPile curDest=(DestcardPile)e.getSource();
					dragFlag = true;
					pressX = e.getX();
					pressY = e.getY();
					curList = curDest.checkXY(e.getX(), e.getY());
					// System.out.println(e.getX());
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					// System.out.println("dragin");
					if(e.getButton()!=1)
						return;
					if(releaseFlag){
						
						DestcardPile curDest=(DestcardPile)e.getSource();
						boolean f=false;
						if(e.getX()>0&&e.getX()<curDest.getWidth()&&e.getY()>0&&e.getY()<curDest.getHeight())
							f=true;
						if (correctPile == null||f) {
							curDest.add(curList);
							temp.remove(temp.mySize());
							temp.setPileBounds(0, 0);
						} else {
							
							correctPile.add(curList);
							
							temp.remove(temp.mySize());
							temp.setPileBounds(0, 0);
						}
						setBounds();
						
						jp.repaint();
					}
					releaseFlag=false;
				}

			});

		
		}
		for (destindex = 0; destindex < 4; destindex++) {
			dest[destindex].addMouseMotionListener(new MouseAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					
					DestcardPile curDest=(DestcardPile)e.getSource();
					if (curList != null) {
						if (dragFlag == true) {
							dragFlag = false;
							releaseFlag=true;
							temp.add(curList);
							curDest.remove(curList.size());
						}
						setTempBounds(e.getX(), e.getY(), e.getSource());

						setBounds();

						jp.repaint();
					}}});}
		for (tabindex = 0; tabindex < 7; tabindex++) {
			tab[tabindex].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					
					if(curList!=null){
					TabcardPile curTab=(TabcardPile)e.getSource();
					if (curTab.check(curList))
						correctPile = curTab;
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseExited(e);
					
					correctPile = null;
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					// System.out.println("dragin");
					if(e.getButton()!=1)
						return;
					TabcardPile curTab=(TabcardPile)e.getSource();
					dragFlag = true;
					pressX = e.getX();
					pressY = e.getY();
					curList = curTab.checkXY(e.getX(), e.getY());
					// System.out.println(e.getX());
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					// System.out.println("dragin");
					if(e.getButton()!=1)
						return;
					if(releaseFlag){
						
						TabcardPile curTab=(TabcardPile)e.getSource();
						boolean f=false;
						if(e.getX()>0&&e.getX()<curTab.getWidth()&&e.getY()>0&&e.getY()<curTab.getHeight())
							f=true;
						if (correctPile == null||f) {
							curTab.add(curList);
							temp.remove(temp.mySize());
							temp.setPileBounds(0, 0);
						} else {
							correctPile.add(curList);
							curTab.reverse();
							temp.remove(temp.mySize());
							temp.setPileBounds(0, 0);
						}
						setBounds();
						
						jp.repaint();
					}
					releaseFlag=false;
				}

			});
		}
		for (tabindex = 0; tabindex < 7; tabindex++) {
			tab[tabindex].addMouseMotionListener(new MouseAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO 自动生成的方法存根
					// super.mouseEntered(e);
					
					TabcardPile curTab=(TabcardPile)e.getSource();
					if (curList != null) {
						if (dragFlag == true) {
							dragFlag = false;
							releaseFlag=true;
							temp.add(curList);
							curTab.remove(curList.size());
						}
						setTempBounds(e.getX(), e.getY(), e.getSource());

						setBounds();

						jp.repaint();
					}}});}

//		jm.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO 自动生成的方法存根
//				begin();
//				jp.repaint();
//			}
//		});
			
		}

	

	protected void checkWin() {
		// TODO 自动生成的方法存根
		 boolean a=true;
		for(int i=0;i<4;i++)
		{
			if(dest[i].mySize()!=13)
				a=false;
		}
		if(a)
		{
			JOptionPane.showMessageDialog(jf, "YOU WIN");
//			restart();;
//			//createFrame();
		}
	}

	protected void setTempBounds(int x, int y, Object object) {
		// TODO 自动生成的方法存根
		Pile p=(Pile) object;
		if ( p== dis) {

			temp.setPileBounds(x + dis.getX() - pressX + dis.mySize() * dis.hInterval,
					y + dis.getY() - pressY);
		}
		else
			temp.setPileBounds(x + p.getX() - pressX,
y + p.getY() - pressY+(p.last_rev_index()+1)*10+(p.mySize()-1-p.last_rev_index())*30);
			
	}

	private void swap(int a, int b) {
		// TODO 自动生成的方法存根
		Card temp;
		temp = card[a];
		card[a] = card[b];
		card[b] = temp;
	}
	}


class Mypanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(new ImageIcon("src/images/background.jpg").getImage(), 0, 0, this);
		// super.paintComponent(g);
	}
}
