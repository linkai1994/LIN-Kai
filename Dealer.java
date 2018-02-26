package texaspoker;

import java.util.LinkedList;
import java.util.ListIterator;

public class Dealer {
	static LinkedList<Cards> publicCard = new LinkedList();
	public static void shuffle() throws Exception{
		for (int i = 0;i < 10000;i++){
			int rand1 = (int)(Math.abs(Math.random()*100 -52));
			int rand2 = (int)(Math.abs(Math.random()*100 -52));
			Cards temp = Cards.card[rand1];
			Cards.card[rand1] = Cards.card[rand2];
			Cards.card[rand2] = temp;
		}
	}
	
	public static void deal() throws Exception{ 
		for(int i = 0; i < Poker.roles.length;i++){
			try {
				Thread.currentThread().sleep(500);
				Poker.roles[i].hand[0] = Cards.poker.pop();
				Thread.currentThread().sleep(500);
				Poker.roles[i].hand[1] = Cards.poker.pop();
				System.out.println("发完一人");
//				CardGroup.setSeq(set[i].hand);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void setStatus(Role[] set)throws Exception{
		
	}
	
	public static void dealPublic() throws Exception{
		try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		publicCard.add((Cards)Cards.poker.pop());
		Poker.gT.repaint();
	}
	
	public static void showPublic() throws Exception{
		System.out.println("-----公共牌-----");

		for (int i = 0;i < publicCard.size();i++){
			System.out.print(publicCard.get(i).getColor() + publicCard.get(i).getNum() + "\t");
		}
		System.out.println();
	}
	
	public static void showPublic1() throws Exception{
		System.out.println("-----公共牌-----");
		for (int i = 0;i < publicCard.size() - 1;i++){
			System.out.print(publicCard.get(i).getColor() + publicCard.get(i).getNum() + "\t");
		}
		System.out.print(publicCard.get(publicCard.size() - 1).getColor() + publicCard.get(publicCard.size() - 1).getNum() + "\t");
		System.out.println();
	}
}
