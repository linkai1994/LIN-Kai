package texaspoker;

import java.util.Random;
import java.util.Scanner;

public class Chair {
	public void moveChair(Role[] role){
		Role temp = role[role.length];
		for (int i = 0;i < role.length - 1;i++){
			role[i] = role[i + 1];
			role[i].setBanker(false);
		}
		role[role.length] = role[0];
		role[0].setBanker(true);
	}
	
	public static void initChair(Role[] role){
		for (int i = 0;i < role.length;i++){
			if (role[i].getKind().equals("玩家")){
				Role temp = role[i];
				role[i] = role[Poker.preferredSeat - 1];
				role[Poker.preferredSeat - 1] = temp;
			}
		}
	}
	
	public static void initBanker(Role[] role){
		Random rand = new Random();
		int rand1 = rand.nextInt(role.length);
		role[rand1].setBanker(true);
	}
	
	public static void changeBanker(Role[] role,String name){
		for(int i = 0;i < role.length;i++){
			role[i].setBanker(false);
		}
		for(int i = 0;i < role.length;i++){
			if (role[i].getName().equals(name)){
				role[i].setBanker(true);
			}
		}
	}
}
