package texaspoker;

import java.util.Scanner;

public class RoleInGame extends Role{
	@Override
	public void showRole() {
		// TODO Auto-generated method stub
		System.out.println("----------------------------\n当前局势");
		System.out.println("NAME            MONEY\tISBANKER\tBET");
		int pool = 0;
		for (int i = 0;i < role.length;i++){
			role[i].setMoney(role[i].getMoney() - role[i].getBet());
			if (!role[i].isAbandon()){
				System.out.println("[" + role[i].getKind() + "]" + role[i].getName() + "\t" + role[i].getMoney() + "\t" + role[i].isBanker() + "\t" + role[i].getBet());
			}else{
				System.out.println("[" + role[i].getKind() + "]" + role[i].getName() + "\t" + role[i].getMoney() + "\t" + role[i].isBanker() + "\t" + role[i].getBet() + "\t弃牌");
			}
			pool += role[i].getBet();
		}
		System.out.println("POOL:" + pool);
		System.out.println("----------------------------");
	}
	
	public void initBet(){
		role[0].setBet(25);
		role[1].setBet(50);
	}
	
}
