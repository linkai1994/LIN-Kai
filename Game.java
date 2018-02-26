package texaspoker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Game {
	public static void initGame(Role[] role){
		for(int i = 0;i < 100;i++){
			if (role[i%role.length].isBanker()){
				role[i%role.length].setBet(25);
				Process.action += role[i%role.length].getName() + " 加注25\n";
				System.out.println(role[i%role.length].getName() + " 加注25\n");
				role[i%role.length].setMoney(2475);
				role[(i + 1)%role.length].setBet(50);
				Process.action += role[(i + 1)%role.length].getName() + " 加注25\n";
				System.out.println(role[(i+1)%role.length].getName() + " 加注25\n");
				role[(i + 1)%role.length].setMoney(2450);
				role[(i + 2)%role.length].setMyTurn(true);
				break;
			}
		}
	}
	
	public static void endGame(Role[] role) throws IOException{
		InputStreamReader isr = new InputStreamReader(new FileInputStream("src//texaspoker/player/player/money.txt"));
		BufferedReader br = new BufferedReader(isr);
		for(int i = 0;i < role.length;i++){
			role[i].setBet(0);
			role[i].bestFive.clear();
		}
		String str = "";
		while ((str = br.readLine()) != null){
			str = (role[Poker.indexOfPlayer].getMoney() - 2500 + Integer.parseInt(str)) + "";
			System.out.println(str);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("src//texaspoker/player/player/money.txt"));
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(str);
			bw.flush();
			bw.close();
		}
		br.close();
		Role.pool = 0;
	}
}
