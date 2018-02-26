package texaspoker;

import java.util.Random;
import java.util.Scanner;

public class Process {
	public static int pAddition;
	public static String action = "";
	public static boolean hasMoved;
	
	public static void options4player1(Role role) throws InterruptedException{
		hasMoved = false;
		if (role.isInGame()&&role.isMyTurn()&&(role.getKind().equals("玩家"))&&(role.getMoney()!=0)){
			System.out.println(123);
			synchronized (Poker.mt) {
				Poker.mt.wait(30*1000);
			}
		}
	}
	
	
	public static void options4player2(Role role) throws InterruptedException{
		hasMoved = false;
		if (role.isInGame()&&role.isMyTurn()&&(role.getKind().equals("玩家"))&&(role.getMoney()!=0)){
			System.out.println(123);
			synchronized (Poker.mt) {
				Poker.mt.wait(30*1000);
			}
		}
	}
	
	public static void options4cpu1(Role role) throws InterruptedException{
		hasMoved = false;
		if (role.isInGame()&&role.isMyTurn()&&(role.getKind().equals("电脑"))&&(role.getMoney()!=0)){
			double temp = Math.random();
			String opt = "";
			Thread.currentThread().sleep(1000);
			if (temp > 0.1){
				role.setOption("跟注");
				a :for (int i = 15;i >= 0;i --){
					if (role.getName().equals(Poker.roles[i%Poker.roles.length].getName())){
						for (int j = i - 1;j >= 0;j--){
							if (Poker.roles[j%Poker.roles.length].isInGame() == true){
								int f = role.getBet();
								role.setBet(Poker.roles[j%Poker.roles.length].getBet());
								int n = role.getBet() - f;
								role.setMoney(role.getMoney() - n);
								break a;
							}
						}
					}
				}
				action += role.getName() + " 跟注\n";
//				changed = true;
			}else if (temp > 0.7){
				Random r = new Random();
				int[] range = {25,50};
				int choice = r.nextInt(2);
				role.setBet(50 + range[choice]);
				role.setOption("加注");
				a :for (int i = 15;i >= 0;i --){
					if (role.getName().equals(Poker.roles[i%Poker.roles.length])){
						for (int j = i - 1;j >= 0;j--){
							if (Poker.roles[j%Poker.roles.length].isInGame() == true){
								Poker.roles[i].setBet(Poker.roles[j%Poker.roles.length].getBet() + range[choice]);
								Poker.roles[i].setMoney(Poker.roles[i%Poker.roles.length].getMoney() - range[choice]);
								break a;
							}
						}
					}
				}
				action += role.getName() + " 加注 " + range[choice] + "\n";
//				changed = true;
			}else {
//				Thread.currentThread().sleep(500);
				role.setOption("弃牌");
				role.setInGame(false);
				action += role.getName() + " 弃牌\n";
			}
			
		}
	}
	
	public static void options4cpu2(Role role) throws InterruptedException{
		hasMoved = false;
		if (role.isInGame()&&role.isMyTurn()&&(role.getKind().equals("电脑"))&&(role.getMoney()!=0)){
			double temp = Math.random();
			String opt = "";
			Thread.currentThread().sleep(1000);
			if (temp > 0.1){
				role.setOption("让牌");
				action += role.getName() + " 让牌\n";
			}else if (temp > 0.6){
				Random r = new Random();
				int[] range = {25,50};
				int choice = r.nextInt(2);
				a :for (int i = 15;i >= 0;i --){
					if (role.getName().equals(Poker.roles[i%Poker.roles.length].getName())){
						for (int j = i - 1;j >= 0;j--){
							if (Poker.roles[j%Poker.roles.length].isInGame() == true){
								Poker.roles[i].setBet(Poker.roles[j%Poker.roles.length].getBet() + range[choice]);
								Poker.roles[i].setMoney(Poker.roles[j%Poker.roles.length].getBet() - range[choice]);
								break a;
							}
						}
					}
				}
				role.setOption("加注");
				action += role.getName() + " 加注 " + range[choice] + "\n";
			}else {
				role.setOption("弃牌");
				role.setInGame(false);
				action += role.getName() + " 弃牌\n";
			}
		}
	}
	public static void options4cpu3(Role role) throws InterruptedException{
		hasMoved = false;
		if (role.isInGame()&&role.isMyTurn()&&(role.getKind().equals("电脑"))&&(role.getMoney()!=0)){
			double temp = Math.random();
			String opt = "";
			Thread.currentThread().sleep(1000);
			if (temp > 0.3){
				Random r = new Random();
				int[] range = {25,50};
				int choice = r.nextInt(2);
				role.setBet(range[choice]);
				role.setMoney(role.getMoney() - range[choice]);
				role.setOption("加注");
				action += role.getName() + " 加注 " + range[choice] + "\n";
			}else {
				role.setOption("弃牌");
				role.setInGame(false);
				action += role.getName() + " 弃牌\n";
			}
		}
	}
	
	public static void nextOne(Role[] role,int i){
		if (role[i].isMyTurn()){
			role[i].setMyTurn(false);
			for (int j = i + 1;j < 1000;j++){
				if (role[j%role.length].isInGame()){
					role[j%role.length].setMyTurn(true);
					break ;
				}
			}
		}
	}
	
	
}
