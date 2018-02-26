package texaspoker;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Cards {
	static Cards[] card = new Cards[52];
	private String num;
	private String color;
	private int realNum;
	static String[] numList = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	static String[] colorList = {"红桃","草花","黑桃","方块"};
	static LinkedList<Cards> poker = new LinkedList();
	static int ii = 0;
	Cards(){};
	Cards(String color, String num,int realNum){
		this.color = color ;
		this.num = num;
		this.realNum = realNum;
	}
	public static void makeCard(){
		a: for(int k = 0;k < colorList.length;k++)
		{
			b: for(int j = 0;j < numList.length;j++)
			{
				c: for(int i = ii;i < card.length;)
				{
					card[i] = new Cards(colorList[k],numList[j],j);
					ii++;break;
				}
			}
		}
	}
	
	public static void makePoker(){
		for (int i = 0;i < card.length;i++){
			poker.add(card[i]);
		}
	}
	
	public static void showPoker(){
		ListIterator<Cards> p = poker.listIterator();
		System.out.print("当前扑克牌顺序:\n\t");
		int count = 0;
		while(p.hasNext()){
			if((count > 0)&&(count%13 == 0)){
				System.out.print("\n\t");
			}
			System.out.print(p.next().getColor() + p.next().getNum()+ "\t");
			count++;
		}
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getRealNum() {
		return realNum;
	}

	public void setRealNum(int realNum) {
		this.realNum = realNum;
	}
}
