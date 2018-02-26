package texaspoker;

import java.awt.Graphics;
import java.io.ObjectInputStream.GetField;

public class Poker {
	public static int numOfPlayer,preferredSeat,maxType,indexOfPlayer,test,currentTurn;
	public static Role rolerole;
	public static Role[] roles1,roles;
	public static Dealer dealer;
	public static CardGroup cg;
	public static Cards newCard;
	public static Chair chair;
	static GUItable gT;
	public static boolean result = false;
	public static mainThread mt;
	public static gameThread gt;
	public static AThread at;
	public static void main(String[] args) throws Exception{
		GUIwelcome gw = new GUIwelcome("玩家设置");
	}
	public static void method1() throws Exception{
		rolerole = new Role();
		chair = new Chair();
		rolerole.initNameList();
		rolerole.setRole();
		roles = rolerole.role;
		Chair.initChair(roles);
		Chair.initBanker(roles);
		gT = new GUItable();
		at = new AThread();
		at.start();
	}
	public static void method3() throws Exception{
		dealer = new Dealer();
		newCard = new Cards();
		newCard.makeCard();
		dealer.shuffle();
		rolerole.showRole();
		newCard.makePoker();
		Dealer.deal();
		rolerole.showHand1(rolerole.role);
		cg = new CardGroup();
		roles1 = rolerole.initRole(roles);
		roles = roles1;
		Game.initGame(roles);
		for (int i = 0;i < roles.length;i++){
			if (roles[i].getKind().equals("玩家")){
				indexOfPlayer = i;
			}
		}
		mt = new mainThread();
		mt.start();
	}
	public static void method2() throws Exception{
		int banker = 0,countBet = 0,notInGame = 0;
		for (int i = 0;i < roles.length;i++){
			if (roles[i].isBanker()){
				banker = i;
			}
		}
			
		if (Dealer.publicCard.size() == 0){
			currentTurn = 1;
			a:for (int i = banker + 2;i < 1000;i++){
				int ingame = 0;int winner = 0;
				for (int j = 0;j < roles.length;j++){
					if (roles[j].isInGame()){
						ingame++;
						winner = j;
					}
				}
				if (ingame == 1){
					for (int k = 0;k < roles.length;k++){
						roles[k].setBanker(false);
						roles[winner].setBanker(true);
					}
					System.out.println("只剩一个玩家");
					result = true;
					break a;
				}
				if (roles[i%roles.length].isMyTurn()){
					Process.options4cpu1(roles[i%roles.length]);
					Process.options4player1(roles[i%roles.length]);
				}
				if (roles[i%roles.length].getOption().equals("加注")){
					countBet = 1;
					System.out.println("countbet"+countBet);
				}else if (roles[i%roles.length].getOption().equals("弃牌")){
					System.out.println(roles[i%roles.length].getName()+"弃牌");	
					notInGame++;
					ingame = 0;winner = 0;
					for (int j = 0;j < roles.length;j++){
						if (roles[j].isInGame()){
							ingame++;winner = j;
						}
					}
					if (ingame == 1){
						for (int k = 0;k < roles.length;k++){
							roles[k].setBanker(false);roles[winner].setBanker(true);
							System.out.println("只剩一个玩家");
						}
						result = true;break a;
					}
					System.out.println("countbet"+countBet);
				}else{
					countBet++;
					System.out.println(roles[i%roles.length].getName()+roles[i%roles.length].getOption());
					System.out.println("countbet"+countBet);
				}
				
				if (countBet >= roles.length - notInGame){
					Process.action += ("全部行动完毕\n");
					for (int p = 0;p < roles.length;p++){
						Role.pool += roles[p].getBet();
						roles[p].setBet(0);
					}
					currentTurn++;
					break a;
				}
				Process.nextOne(roles, i%(roles.length));
			}
			rolerole.showRoleG();
			if (result == false){
				Process.action += "本轮下注结束，发三张公共牌\n=========\n";
				Dealer.dealPublic();
				Dealer.dealPublic();
				Dealer.dealPublic();
				Dealer.showPublic();
			}
		}
		System.out.println("result:"+result);
		if (Dealer.publicCard.size() > 0){
			boolean endLoop = false;
			while(result==false&&endLoop==false){
				System.out.print("进入第" + currentTurn + "回合\n");
				Process.action += "进入第" + currentTurn + "回合\n";
				boolean firstPlayer = true;
				for (int x = 1;x < roles.length;x++){
					if (roles[x%roles.length].isInGame()){
						roles[x%roles.length].setMyTurn(false);
					}
				}
				roles[banker].setMyTurn(true);
				int previous = 0;countBet = 0;
				int ingame = 0;int winner = 0;
				for (int j = 0;j < roles.length;j++){
					if (roles[j].isInGame()){
						ingame++;winner = j;
					}
				}
				if (ingame == 1){
					for (int k = 0;k < roles.length;k++){
						roles[k].setBanker(false);
						roles[winner].setBanker(true);
					}
					System.out.println("只剩一个玩家");
					result = true;
				}
				for (int i = banker;i < 1000;i++){
					ingame = 0;winner = 0;
					for (int j = 0;j < roles.length;j++){
						if (roles[j].isInGame()){
							ingame++;winner = j;
						}
					}
					if (ingame == 1){
						for (int k = 0;k < roles.length;k++){
							roles[k].setBanker(false);
							roles[winner].setBanker(true);
						}
						System.out.println("只剩一个玩家");
						result = true;
					}
					for (int j = i - 1;j > 0;j--){
						if (roles[(j)%(roles.length)].isInGame()){
							previous = j;
							break;
						}
					}
					System.out.println(i%roles.length==banker);
					if (roles[i%roles.length].isInGame() && roles[i%roles.length].isMyTurn()){
						if (firstPlayer == true){
							Process.options4cpu3(roles[i%roles.length]);
							Process.options4player2(roles[i%roles.length]);
							firstPlayer = false;
						}else if (roles[previous%(roles.length)].getOption().equals("让牌")){
							Process.options4cpu2(roles[i%roles.length]);
							Process.options4player2(roles[i%roles.length]);
						}else{
							Process.options4cpu1(roles[i%roles.length]);
							Process.options4player1(roles[i%roles.length]);
						}
						if (roles[i%roles.length].getOption().equals("加注")){
							countBet = 1;
							System.out.println(roles[i%roles.length].getName()+"加注");
							System.out.println("countbet"+countBet);
						}else if (roles[i%roles.length].getOption().equals("弃牌")){
							System.out.println(roles[i%roles.length].getName()+"弃牌");	
							notInGame++;
							ingame = 0;winner = 0;
							System.out.println("countbet"+countBet);
							for (int j = 0;j < roles.length;j++){
								if (roles[j].isInGame()){
									ingame++;winner = j;
								}
							}
							if (ingame == 1){
								for (int k = 0;k < roles.length;k++){
									roles[k].setBanker(false);roles[winner].setBanker(true);
								}
								result = true;
							}
						}else{
							System.out.println(roles[i%roles.length].getName()+roles[i%roles.length].getOption());
							countBet++;
							System.out.println("countbet"+countBet);
						}
					}else{
						System.out.println(roles[i%roles.length].getName()+"已盖牌");
					}
					
					if (countBet >= roles.length - notInGame){
						for (int p = 0;p < roles.length;p++){
							Role.pool += roles[p].getBet();
							roles[p].setBet(0);
						}
						currentTurn++;
						break;
					}
					Process.nextOne(roles, i%(roles.length));
				}
				rolerole.showRoleG();
				if (result == false&&Dealer.publicCard.size() < 5){
					Process.action += "本轮下注结束，发一张公共牌\n=========\n";
					Dealer.dealPublic();
					Dealer.showPublic1();
				}else{
					endLoop = true;
				}
			}
		}
		if (result == false){
			cg.makeBest(roles, Dealer.publicCard);
			Role.showHand(roles);
			for (int i = 0;i < roles.length;i++){
				maxType = Win.setMaxType(roles[i],maxType);
			}
			System.out.println();
			Win.setWinner(roles,maxType);
			Game.endGame(roles);
			rolerole.showRoleGG();
			GUIend ge = new GUIend();
		}else{
			Win.setWinner(roles);
			Game.endGame(roles);
			rolerole.showRoleGG();
			GUIend ge = new GUIend();
		}
	}
}

class gameThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Poker.gT = new GUItable();
			while (true){
				Poker.gT.repaint();
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

class mainThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.currentThread().sleep(200);
			Poker.method2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class AThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Poker.method3();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
