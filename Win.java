package texaspoker;

import java.util.LinkedList;
import java.util.TreeSet;

public class Win{
	public static int setMaxType(Role role,int maxType){
			if (role.getCardType().equals("高牌")&&role.isInGame()){
				int tempType = 0;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("对子")&&role.isInGame()){
				int tempType = 1;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("两对")&&role.isInGame()){
				int tempType = 2;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("三条")&&role.isInGame()){
				int tempType = 3;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("同花")&&role.isInGame()){
				int tempType = 4;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("顺子")&&role.isInGame()){
				int tempType = 5;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("葫芦")&&role.isInGame()){
				int tempType = 6;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("金刚")&&role.isInGame()){
				int tempType = 7;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("同花顺")&&role.isInGame()){
				int tempType = 8;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			if (role.getCardType().equals("皇家同花顺")&&role.isInGame()){
				int tempType = 9;
				if (tempType > maxType){
					maxType = tempType;
				}
			}
			return maxType;
	}
	
	public static void setWinner(Role[] role,int maxType) throws InterruptedException{	
//		System.out.println("最大牌型:\t" + CardGroup.cardTypeList[maxType]);
		
		LinkedList<Role> winner = new LinkedList();
		for (int i = 0;i < role.length;i++){
			if (role[i].getCardType().equals(CardGroup.cardTypeList[maxType])&&role[i].isInGame()){
				winner.add(role[i]);
			}
		}
//		System.out.println("赢家数量" + winner.size());
		if (winner.size() == 1){
			System.out.println("=========\n胜利者是:");
			Chair.changeBanker(role, winner.get(0).getName());
			Thread.currentThread().sleep(1500);
			System.out.println("\t" + winner.get(0).getName() + "[" + winner.get(0).getCardType() + "]");
			Process.action += "\t胜利者是:" + winner.get(0).getName() + "[" + winner.get(0).getCardType() + "]\n";
			System.out.println("下把庄家:" + winner.get(0).getName());
			Process.action += "\t下把庄家:" + winner.get(0).getName();
			for (int i = 0;i < role.length;i++){
				if (role[i].getName().equals(winner.get(0).getName())){
					role[i].setMoney(role[i].getMoney() + Role.pool);
				}
			}
		}else if (winner.size() >= 2){
			a:for (int j = 0;j < 5;j++){
				int[] single = new int[winner.size()];
				for (int i = 0;i < winner.size();i++){
					single[i] = winner.get(i).getBestFive().get(j).getRealNum();
				}
				int max = 0;
				for (int i = 0;i < single.length;i++){
					if (single[i] >= max){
						max = single[i];
					}
				}
				for (int i = 0;i < winner.size();i++){
					if (single[i] < max){
						winner.remove(i);
					}
				}
			}
			if(winner.size() == 1){
				System.out.println("=========\n胜利者是:");
				Chair.changeBanker(role, winner.get(0).getName());
				Thread.currentThread().sleep(1500);
				System.out.println("\t" + winner.get(0).getName() + "[" + winner.get(0).getCardType() + "]");
				Process.action += "\t胜利者是:" + winner.get(0).getName() + "[" + winner.get(0).getCardType() + "]\n";
				System.out.println("下把庄家:" + winner.get(0).getName());
				Process.action += "\t下把庄家:" + winner.get(0).getName();
				for (int i = 0;i < role.length;i++){
					if (role[i].getName().equals(winner.get(0).getName())){
						role[i].setMoney(role[i].getMoney() + Role.pool);
					}
				}
			}else if (winner.size() == 2){
				System.out.println("=========\n胜利者是:");
				Thread.currentThread().sleep(1500);
				Chair.changeBanker(role, winner.get(0).getName());
				System.out.println("\t" + winner.get(0).getName() + "[" + winner.get(0).getCardType() + "]" + "--" + winner.get(1).getName() + "[" + winner.get(1).getCardType() + "]");
				Process.action += "\t胜利者是:" + winner.get(0).getName() + "[" + winner.get(0).getCardType() + "]" + "--" + winner.get(1).getName() + "[" + winner.get(1).getCardType() + "]\n";
				System.out.println("下把庄家:" + winner.get(0).getName());
				Process.action += "\t下把庄家:" + winner.get(0).getName();
				for (int i = 0;i < role.length;i++){
					if (role[i].getName().equals(winner.get(0).getName())){
						role[i].setMoney(role[i].getMoney() + Role.pool/2);
					}
					if (role[i].getName().equals(winner.get(1).getName())){
						role[i].setMoney(role[i].getMoney() + Role.pool/2);
					}else{
						role[i].setMoney(role[i].getMoney());
					}
					role[i].setBet(0);
				}
			}
		}
		
	}
	public static void setWinner(Role[] role) throws InterruptedException{
		for (int i = 0;i < role.length;i++){
			if (role[i].isInGame()){
				int x = i;
				System.out.println("=========\n胜利者是1:");
				Chair.changeBanker(role, role[x].getName());
//				Thread.currentThread().sleep(1500);
				System.out.println("\t" + role[x].getName());
				Process.action += "\t胜利者是:" + role[x].getName();
				System.out.println("\n\t下把庄家:" + role[x].getName());
				for (int j = 0;j < role.length;j++){
					if (role[j].getName().equals(role[x].getName())){
						role[j].setMoney(role[j].getMoney() + Role.pool);
					}
				}
			}
		}
	}
}
