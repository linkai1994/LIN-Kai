package texaspoker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUItable extends JFrame{
//	JLabel[] seat = new JLabel[6];
	JLabel seat1 = new JLabel("空位");JLabel seat2 = new JLabel("空位");
	JLabel seat3 = new JLabel("空位");JLabel seat4 = new JLabel("空位");
	JLabel seat5 = new JLabel("空位");JLabel seat6 = new JLabel("空位");
	
	JLabel photoLabel1 = new JLabel();JLabel photoLabel2 = new JLabel();
	JLabel photoLabel3 = new JLabel();JLabel photoLabel4 = new JLabel();
	JLabel photoLabel5 = new JLabel();JLabel photoLabel6 = new JLabel();
	
	JLabel bet1 = new JLabel("下注:0");JLabel bet2 = new JLabel("下注:0");
	JLabel bet3 = new JLabel("下注:0");JLabel bet4 = new JLabel("下注:0");
	JLabel bet5 = new JLabel("下注:0");JLabel bet6 = new JLabel("下注:0");
	
	JLabel money1 = new JLabel("余额:");JLabel money2 = new JLabel("余额:");
	JLabel money3 = new JLabel("余额:");JLabel money4 = new JLabel("余额:");
	JLabel money5 = new JLabel("余额:");JLabel money6 = new JLabel("余额:");
	
	JLabel pool = new JLabel();
	
	JLabel clock1 = new JLabel();JLabel clock2 = new JLabel();
	JLabel clock3 = new JLabel();JLabel clock4 = new JLabel();
	JLabel clock5 = new JLabel();JLabel clock6 = new JLabel();
	
	JLabel status1 = new JLabel();JLabel status2 = new JLabel();
	JLabel status3 = new JLabel();JLabel status4 = new JLabel();
	JLabel status5 = new JLabel();JLabel status6 = new JLabel();
	
	JTextArea actions = new JTextArea("record");
	JScrollPane jsp = new JScrollPane(actions);
	
	JButton follow = new JButton("跟注");
	JButton add = new JButton("加注");
	JButton yeild = new JButton("让牌");
	JButton quit = new JButton("弃牌");
	
	JLabel card11 = new JLabel("11");JLabel card12 = new JLabel("12");
	JLabel card21 = new JLabel("21");JLabel card22 = new JLabel("22");
	JLabel card31 = new JLabel("31");JLabel card32 = new JLabel("32");
	JLabel card41 = new JLabel("41");JLabel card42 = new JLabel("42");
	JLabel card51 = new JLabel("51");JLabel card52 = new JLabel("52");
	JLabel card61 = new JLabel("61");JLabel card62 = new JLabel("62");
	JLabel cardP1 = new JLabel("P1");JLabel cardP2 = new JLabel("P2");
	JLabel cardP3 = new JLabel("P3");JLabel cardP4 = new JLabel("P4");
	JLabel cardP5 = new JLabel("P5");
	
	final int XS = 100;
	final int YS = 120;
	
	Role[] roles;
	GUItable() throws InterruptedException{
		this.roles = Poker.rolerole.role;
		String[] names = new String[6];
		for (int i = 0;i < roles.length;i++){
			if (roles[i].isBanker() == true){
				names[i] = roles[i].getName() + "(庄家)";
			}else{
				names[i] = roles[i].getName();
			}
		}
		for (int i = roles.length;i < 6;i++){
			names[i] = "空位";
		}
		final String NAME1 = names[0];
		final String NAME2 = names[1];
		final String NAME3 = names[2];
		final String NAME4 = names[3];
		final String NAME5 = names[4];
		final String NAME6 = names[5];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("牌局进行中");
		setVisible(true);
		setSize(1200, 700);
		setResizable(false);
		setLayout(null);
		JPanel p= new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);	
				setBackground(new Color(133,155,180));
//				g.setColor(new Color(00,33,00));
//				g.fillArc(XS, YS, 300, 300, 90, 180);
//				g.fillArc(XS+600, YS, 300, 300, 90, -180);
//				g.fillRect(XS+150,YS,600,300);
				g.setColor(Color.white);
				
				g.drawArc(XS, YS, 300, 300, 90, 180);
				g.drawArc(XS+600, YS, 300, 300, 90, -180);
				g.drawRect(XS+150,YS,600,300);
				
				g.drawRect(XS+205, YS+15, 45, 65);
				g.drawRect(XS+260, YS+15, 45, 65);
				
				g.drawRect(XS+655, YS+15, 45, 65);
				g.drawRect(XS+600, YS+15, 45, 65);
				
				g.drawRect(XS+205, YS+220, 45, 65);
				g.drawRect(XS+260, YS+220, 45, 65);
				
				g.drawRect(XS+655, YS+220, 45, 65);
				g.drawRect(XS+600, YS+220, 45, 65);
				
				g.drawRect(XS+35, YS+100, 45, 65);
				g.drawRect(XS+90, YS+100, 45, 65);
				
				g.drawRect(XS+760, YS+120, 45, 65);
				g.drawRect(XS+815, YS+120, 45, 65);
				
				g.drawArc(XS+290, YS+100, 100, 100, 90, 180);
				g.drawLine(XS+340, YS+100, XS+560, YS+100);
				g.drawLine(XS+340, YS+200, XS+560, YS+200);
				g.drawArc(XS+510, YS+100, 100, 100, 90, -180);
				//公共牌区域
				g.drawRect(XS+320, YS+118, 45, 65);
				g.drawRect(XS+375, YS+118, 45, 65);
				g.drawRect(XS+430, YS+118, 45, 65);
				g.drawRect(XS+485, YS+118, 45, 65);
				g.drawRect(XS+540, YS+118, 45, 65);
				
				add(jsp);jsp.setBounds(20,520,250,130);
				
				add(card11);add(card12);card11.setBounds(XS+35, YS+100, 45, 65);card12.setBounds(XS+90, YS+100, 45, 65);
				add(card21);add(card22);card21.setBounds(XS+205, YS+15, 45, 65);card22.setBounds(XS+260, YS+15, 45, 65);
				add(card31);add(card32);card61.setBounds(XS+205, YS+220, 45, 65);card62.setBounds(XS+260, YS+220, 45, 65);
				add(card41);add(card42);card31.setBounds(XS+600, YS+15, 45, 65);card32.setBounds(XS+655, YS+15, 45, 65);
				add(card51);add(card52);card51.setBounds(XS+600, YS+220, 45, 65);card52.setBounds(XS+655, YS+220, 45, 65);
				add(card61);add(card62);card41.setBounds(XS+760, YS+120, 45, 65);card42.setBounds(XS+815, YS+120, 45, 65);
				
				add(cardP1);add(cardP2);add(cardP3);add(cardP4);add(cardP5);
				cardP1.setBounds(XS+320, YS+118, 45, 65);
				cardP2.setBounds(XS+375, YS+118, 45, 65);
				cardP3.setBounds(XS+430, YS+118, 45, 65);
				cardP4.setBounds(XS+485, YS+118, 45, 65);
				cardP5.setBounds(XS+540, YS+118, 45, 65);
				
				add(pool);pool.setBounds(XS+345,YS+98,150,20);
				try{
					pool.setText("奖池: " + Role.pool);
				}catch(Exception e){
					pool.setText("奖池: " + 0);
				}
				
				add(seat1);add(photoLabel1);add(seat2);add(photoLabel2);
				add(seat3);add(photoLabel3);add(seat4);add(photoLabel4);
				add(seat5);add(photoLabel5);add(seat6);add(photoLabel6);
						
				seat1.setBounds(XS-65,YS+80,100,20);photoLabel1.setBounds(XS-65,YS+100,60,80);
				seat2.setBounds(XS+205,YS-25,100,20);photoLabel2.setBounds(XS+205,YS-105,60,80);
				seat6.setBounds(XS+205,YS+300,100,20);photoLabel6.setBounds(XS+205,YS+320,60,80);
				seat3.setBounds(XS+600,YS-25,100,20);photoLabel3.setBounds(XS+600,YS-105,60,80);
				seat5.setBounds(XS+600,YS+300,100,20);photoLabel5.setBounds(XS+600,YS+320,60,80);
				seat4.setBounds(XS+930,YS+80,100,20);photoLabel4.setBounds(XS+930,YS+100,60,80);
				
				add(bet1);bet1.setBounds(XS+35, YS+200, 80, 15);
				add(bet2);bet2.setBounds(XS+270, YS-45, 80, 15);
				add(bet3);bet3.setBounds(XS+665, YS-45, 80, 15);
				add(bet4);bet4.setBounds(XS+760, YS+220, 80, 15);
				add(bet5);bet5.setBounds(XS+665, YS+340, 80, 15);
				add(bet6);bet6.setBounds(XS+270, YS+340, 80, 15);
				
				add(money1);money1.setBounds(XS+35,YS+180,80,15);
				add(money2);money2.setBounds(XS+270,YS-65,80,15);
				add(money6);money6.setBounds(XS+270,YS+320,80,15);
				add(money3);money3.setBounds(XS+665,YS-65,80,15);
				add(money5);money5.setBounds(XS+665,YS+320,80,15);
				add(money4);money4.setBounds(XS+760,YS+200,80,15);
				
				add(clock1);clock1.setBounds(XS-65,YS+190,60,80);
				add(clock2);clock2.setBounds(XS+135,YS-105,60,80);
				add(clock6);clock6.setBounds(XS+135,YS+320,60,80);
				add(clock3);clock3.setBounds(XS+530,YS-105,60,80);
				add(clock5);clock5.setBounds(XS+530,YS+320,60,80);
				add(clock4);clock4.setBounds(XS+930,YS+10,60,80);
				
				JLabel[] seats = {seat1,seat2,seat3,seat4,seat5,seat6};
				JLabel[] clocks = {clock1,clock2,clock3,clock4,clock5,clock6};
				for (int i = 0;i < seats.length;i++){
					if (!seats[i].getText().equals("空位")){
						try{
							if (roles[i].isMyTurn()){
								clocks[i].setIcon(new ImageIcon("src/texaspoker/clock/clock.jpg"));
							}else{
								clocks[i].setIcon(null);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				
				add(status1);status1.setBounds(XS+35, YS+180, 40, 15);
				add(status2);status2.setBounds(XS+315, YS+15, 40, 15);
				add(status6);status6.setBounds(XS+315, YS+260, 40, 15);
				add(status3);status3.setBounds(XS+550, YS+15, 40, 15);
				add(status5);status5.setBounds(XS+550, YS+260, 40, 15);
				add(status4);status4.setBounds(XS+800, YS+200, 40, 15);
				
				add(follow);add(quit);add(add);add(yeild);
				follow.setBounds(1050,480,120,40);yeild.setBounds(1050,530,120,40);
				add.setBounds(1050,580,120,40);quit.setBounds(1050,630,120,40);
				
				quit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (roles[Poker.indexOfPlayer].isInGame()&&roles[Poker.indexOfPlayer].isMyTurn()&&(roles[Poker.indexOfPlayer].getMoney()!=0)&&(Process.hasMoved == false)){
							Process.hasMoved = true;
							roles[Poker.indexOfPlayer].setOption("弃牌");
							roles[Poker.indexOfPlayer].setInGame(false);
							Process.action += roles[Poker.indexOfPlayer].getName() + "弃牌\n";
							synchronized (Poker.mt) {
								Poker.mt.notify();
							}
						}	
					}
				});
				follow.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (roles[Poker.indexOfPlayer].isInGame()&&roles[Poker.indexOfPlayer].isMyTurn()&&(roles[Poker.indexOfPlayer].getMoney()!=0)&&(Process.hasMoved == false)){		
							Process.hasMoved = true;
							roles[Poker.indexOfPlayer].setOption("跟注");
							a :for (int i = 15;i >= 0;i --){
								if (roles[Poker.indexOfPlayer].getName().equals(roles[i%Poker.roles.length].getName())){
									for (int j = i - 1;j >= 0;j--){
										if (roles[j%Poker.roles.length].isInGame() == true){
											int f = roles[Poker.indexOfPlayer].getBet();
											roles[Poker.indexOfPlayer].setBet(roles[j%Poker.roles.length].getBet());
											int n = roles[Poker.indexOfPlayer].getBet() - f;
											roles[Poker.indexOfPlayer].setMoney(roles[Poker.indexOfPlayer].getMoney() - n);
											System.out.print(roles[Poker.indexOfPlayer].getMoney());
											Poker.gT.validate();
											break a;
										}
									}
								}
							}
							Process.action += roles[Poker.indexOfPlayer].getName() + " 跟注\n";
							synchronized (Poker.mt) {
								Poker.mt.notify();
							}
						}	
					}
				});
				yeild.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (roles[Poker.indexOfPlayer].isInGame()&&roles[Poker.indexOfPlayer].isMyTurn()&&(roles[Poker.indexOfPlayer].getMoney()!=0)&&(Process.hasMoved == false)){
							Process.hasMoved = true;
							roles[Poker.indexOfPlayer].setOption("让牌");
							Process.action += roles[Poker.indexOfPlayer].getName() + "让牌\n";
							synchronized (Poker.mt) {
								Poker.mt.notify();
							}
						}	
					}
				});
				add.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (roles[Poker.indexOfPlayer].isInGame()&&roles[Poker.indexOfPlayer].isMyTurn()&&(roles[Poker.indexOfPlayer].getMoney()!=0)&&(Process.hasMoved == false)){
							Process.hasMoved = true;
							roles[Poker.indexOfPlayer].setOption("加注");
							synchronized (Poker.mt) {
								Poker.mt.notify();
							}
						}	
					}
				});
				
				new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub		
						seat1.setText(NAME1);seat2.setText(NAME2);
						seat3.setText(NAME3);seat4.setText(NAME4);
						seat5.setText(NAME5);seat6.setText(NAME6);
					
						try {//1
							if (!seat1.getText().equals("空位")){
								if ((roles[0].isInGame()==true)&&roles[0].getKind().equals("电脑")){
									photoLabel1.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[0].getName() +".jpg"));
								}else if ((roles[0].isInGame()==false)&&roles[0].getKind().equals("电脑")){
									photoLabel1.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[0].getName() +"quit.jpg"));
								}
							}
						}catch(Exception e){
							photoLabel1.setText("无照片");
						}
						try {//2
							if (!seat2.getText().equals("空位")){
								if ((roles[1].isInGame()==true)&&roles[1].getKind().equals("电脑")){
									photoLabel2.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[1].getName() +".jpg"));
								}else if ((roles[1].isInGame()==false)&&roles[1].getKind().equals("电脑")){
									photoLabel2.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[1].getName() +"quit.jpg"));
								}
							}
						}catch(Exception e){
							photoLabel2.setText("无照片");
						}
						try {//3
							if (!seat3.getText().equals("空位")){
								if ((roles[2].isInGame()==true)&&roles[2].getKind().equals("电脑")){
									photoLabel3.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[2].getName() +".jpg"));
								}else if ((roles[2].isInGame()==false)&&roles[2].getKind().equals("电脑")){
									photoLabel3.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[2].getName() +"quit.jpg"));
								}
							}
						}catch(Exception e){
							photoLabel3.setText("无照片");
						}
						try {//4
							if (!seat4.getText().equals("空位")){
								if ((roles[3].isInGame()==true)&&roles[3].getKind().equals("电脑")){
									photoLabel4.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[3].getName() +".jpg"));
								}else if ((roles[3].isInGame()==false)&&roles[3].getKind().equals("电脑")){
									photoLabel4.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[3].getName() +"quit.jpg"));
								}
							}
						}catch(Exception e){
							photoLabel4.setText("无照片");
						}
						try {//5
							if (!seat5.getText().equals("空位")){
								if ((roles[4].isInGame()==true)&&roles[4].getKind().equals("电脑")){
									photoLabel5.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[4].getName() +".jpg"));
								}else if((roles[4].isInGame()==false)&&roles[4].getKind().equals("电脑")){
									photoLabel5.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[4].getName() +"quit.jpg"));
								}
							}
						}catch(Exception e){
							photoLabel5.setText("无照片");
						}
						try {//6
							if (!seat6.getText().equals("空位")){
								if ((roles[5].isInGame()==true)&&roles[5].getKind().equals("电脑")){
									photoLabel6.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[5].getName() +".jpg"));
								}else if ((roles[5].isInGame()==false)&&roles[5].getKind().equals("电脑")){
									photoLabel6.setIcon(new ImageIcon("src/texaspoker/cpu/" + roles[5].getName() +"quit.jpg"));
								}
							}
						}catch(Exception e){
							photoLabel6.setText("无照片");
						}
						try {//1
							if (!seat1.getText().equals("空位")){
								card11.setIcon(new ImageIcon("src/texaspoker/card/"+roles[0].hand[0].getColor()+"/"+roles[0].hand[0].getNum()+".jpg"));card11.repaint();
								card12.setIcon(new ImageIcon("src/texaspoker/card/"+roles[0].hand[1].getColor()+"/"+roles[0].hand[1].getNum()+".jpg"));card12.repaint();
							}
						}catch(Exception e){
							card11.setText("无");card12.setText("无");photoLabel1.setText("无照片");
						}
						try {//2
							if (!seat2.getText().equals("空位")){
								card21.setIcon(new ImageIcon("src/texaspoker/card/"+roles[1].hand[0].getColor()+"/"+roles[1].hand[0].getNum()+".jpg"));
								card22.setIcon(new ImageIcon("src/texaspoker/card/"+roles[1].hand[1].getColor()+"/"+roles[1].hand[1].getNum()+".jpg"));
							}
						}catch(Exception e){
							card21.setText("无");card22.setText("无");photoLabel2.setText("无照片");
						}
						try {//3
							if (!seat3.getText().equals("空位")){
								card31.setIcon(new ImageIcon("src/texaspoker/card/"+roles[2].hand[0].getColor()+"/"+roles[2].hand[0].getNum()+".jpg"));
								card32.setIcon(new ImageIcon("src/texaspoker/card/"+roles[2].hand[1].getColor()+"/"+roles[2].hand[1].getNum()+".jpg"));
							}
						}catch(Exception e){
							card31.setText("无");card32.setText("无");photoLabel3.setText("无照片");
						}
						try {//4
							if (!seat4.getText().equals("空位")){
								card41.setIcon(new ImageIcon("src/texaspoker/card/"+roles[3].hand[0].getColor()+"/"+roles[3].hand[0].getNum()+".jpg"));
								card42.setIcon(new ImageIcon("src/texaspoker/card/"+roles[3].hand[1].getColor()+"/"+roles[3].hand[1].getNum()+".jpg"));
							}
						}catch(Exception e){
							card41.setText("无");card42.setText("无");photoLabel4.setText("无照片");
						}
						try {//5
							if (!seat5.getText().equals("空位")){
								card51.setIcon(new ImageIcon("src/texaspoker/card/"+roles[4].hand[0].getColor()+"/"+roles[4].hand[0].getNum()+".jpg"));
								card52.setIcon(new ImageIcon("src/texaspoker/card/"+roles[4].hand[1].getColor()+"/"+roles[4].hand[1].getNum()+".jpg"));
							}
						}catch(Exception e){
							card51.setText("无");card52.setText("无");photoLabel5.setText("无照片");
						}
						try {//6
							if (!seat6.getText().equals("空位")){
								card61.setIcon(new ImageIcon("src/texaspoker/card/"+roles[5].hand[0].getColor()+"/"+roles[5].hand[0].getNum()+".jpg"));
								card62.setIcon(new ImageIcon("src/texaspoker/card/"+roles[5].hand[1].getColor()+"/"+roles[5].hand[1].getNum()+".jpg"));
							}
						}catch(Exception e){
							card61.setText("无");card62.setText("无");photoLabel6.setText("无照片");
						}
						
						//下注1
						if (!seat1.getText().equals("空位")){
							bet1.setText("下注: " + Poker.roles[0].getBet());
							money1.setText("余额: " + roles[0].getMoney());
						}
							
						
						//下注2
						if (!seat2.getText().equals("空位")){
							bet2.setText("下注: " + roles[1].getBet());
							money2.setText("余额: " + roles[1].getMoney());
						}
							
					
						//下注3
						if (!seat3.getText().equals("空位")){
							bet3.setText("下注: " + roles[2].getBet());
							money3.setText("余额: " + roles[2].getMoney());
						}
							
						
						//下注4
						if (!seat4.getText().equals("空位")){
							bet4.setText("下注: " + roles[3].getBet());
							money4.setText("余额: " + roles[3].getMoney());
						}
							
						
						//下注5
						if (!seat5.getText().equals("空位")){
							bet5.setText("下注: " + roles[4].getBet());
							money5.setText("余额: " + roles[4].getMoney());
						}
							
						
						//下注6
						if (!seat6.getText().equals("空位")){
							bet6.setText("下注: " + roles[5].getBet());
							money6.setText("余额: " + roles[5].getMoney());
						}
							
						
						
						try {//public
							cardP1.setIcon(new ImageIcon("src/texaspoker/card/"+Dealer.publicCard.get(0).getColor()+"/"+Dealer.publicCard.get(0).getNum()+".jpg"));
							cardP2.setIcon(new ImageIcon("src/texaspoker/card/"+Dealer.publicCard.get(1).getColor()+"/"+Dealer.publicCard.get(1).getNum()+".jpg"));
							cardP3.setIcon(new ImageIcon("src/texaspoker/card/"+Dealer.publicCard.get(2).getColor()+"/"+Dealer.publicCard.get(2).getNum()+".jpg"));
							cardP4.setIcon(new ImageIcon("src/texaspoker/card/"+Dealer.publicCard.get(3).getColor()+"/"+Dealer.publicCard.get(3).getNum()+".jpg"));
							cardP5.setIcon(new ImageIcon("src/texaspoker/card/"+Dealer.publicCard.get(4).getColor()+"/"+Dealer.publicCard.get(4).getNum()+".jpg"));
						}catch(Exception e){
							cardP1.setText("无");cardP2.setText("无");cardP3.setText("无");cardP4.setText("无");cardP5.setText("无");
						}
					}		
				}).start();	
				new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
//							if (Process.changed == true){
								actions.setText(Process.action);
//							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}).start();
			}
		};
		add(p);
		p.setBounds(0, 0, 1200, 700);
	}
}
