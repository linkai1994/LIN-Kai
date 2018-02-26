package texaspoker;

import java.util.HashMap;
import java.util.LinkedList;

import javax.net.ssl.SSLEngineResult.Status;

public class CardGroup extends Cards{
	
	static String[] cardTypeList = {"高牌","对子","两对","三条","同花","顺子","葫芦","金刚","同花顺","皇家同花顺"};

	
	public void group(Role[] role,LinkedList publicCard) {
		for (int x = 0;x < role.length;x++){
			role[x].cardGroup = new Cards[Dealer.publicCard.size() + 2];
			for (int i = 0;i < role[x].cardGroup.length;i++){
				role[x].cardGroup[0] = role[x].hand[0];
				role[x].cardGroup[1] = role[x].hand[1];
				if (i > 1){
					role[x].cardGroup[i] = (Cards)publicCard.get(i - 2);
				}
			}
			setSeq(role[x].cardGroup);	
		}
	}
	
	public static void setSeq(Cards[] c){
		for (int i = 0;i < c.length;i++){
			for (int j = i;j < c.length;j++){
				if (c[i].getRealNum() > c[j].getRealNum()){
					Cards temp = c[i];
					c[i] = c[j];
					c[j] = temp;
				}
			}
		}
	}
	
	public void makeBest(Role[] role,LinkedList<Cards> publicCard){
		group(role,publicCard);
//		System.out.println(1);
		for (int z = 0;z < role.length;z++){
			boolean sameColor = false;
			boolean straight = false;
			String mostColor = "";
			for (int j = 0;j < colorList.length;j++){
				int countColor = 0;
				for(int m = 0;m < role[z].cardGroup.length;m++){
					if (role[z].cardGroup[m].getColor() == (colorList[j])){
						countColor++;
						if (countColor >= 5){
							mostColor = role[z].cardGroup[m].getColor();
							sameColor = true;
						}
					}
				}
			}
//			System.out.println("1.同花:"+sameColor);
			int x = role[z].cardGroup.length - 1;int mostRealNum = 0;
			a:while(x >= 4){
				int[] count = {0,0,0,0,0};
				for (int m = x; m >= 0;m--){
					for (int n = 0;n < count.length;n++){
						if (role[z].cardGroup[x].getRealNum() - n == role[z].cardGroup[m].getRealNum()){
							mostRealNum = role[z].cardGroup[x].getRealNum();
							count[n] = 1;
						}
						if(count[1]*count[2]*count[3]*count[4]*count[0] == 1){
							straight = true;
							break a;
						}
					}
				}
				x--;
			}
//		System.out.println("2.顺子:"+straight);
			//构造同花牌型
			if (sameColor){
				System.out.println("最多花色"+mostColor);
				role[z].bestFive.clear();
				int m = role[z].cardGroup.length - 1;
				while(role[z].bestFive.size() < 5){
//				for (int m = role[z].cardGroup.length - 1;m >= 0;m--){
					if (role[z].cardGroup[m].getColor().equals(mostColor)){
						System.out.println("m" + m);
						role[z].bestFive.add(role[z].cardGroup[m]);
					}
//					if (role[z].bestFive.size() == 5){
//						break;
//					}
					m--;
				}
				role[z].handStatus("同花",role[z].bestFive);
				mostColor = "";

			}
//			System.out.println("3.同花构造结束");

			//构造顺子牌型
			if (straight){
				System.out.println("顺子最大牌"+numList[mostRealNum]);
				int countStraight = 0;
				role[z].bestFive.clear();
				int m = role[z].cardGroup.length - 1;
				while(role[z].bestFive.size() < 5){
//				for (int m = role[z].cardGroup.length - 1;m >= 0;m--){
					if (role[z].cardGroup[m].getRealNum() == mostRealNum - countStraight){
						System.out.println("cs"+countStraight);
						role[z].bestFive.add(role[z].cardGroup[m]);
						countStraight++;
					}
//					if (countStraight == 5){
//						break;
//					}
					m--;
				}
				role[z].handStatus("顺子",role[z].bestFive);
				mostRealNum = 100;
			}
		
//			System.out.println("4.顺子构造结束");
			//构造同花顺牌型
			if(straight&&sameColor){
				int countStraight = 0;
				role[z].bestFive.clear();
				int m = role[z].cardGroup.length - 1;
				while(role[z].bestFive.size() < 5){
					if ((role[z].cardGroup[m].getRealNum() == mostRealNum - countStraight)&&(role[z].cardGroup[m].getColor().equals(mostColor))){
						role[z].bestFive.add(role[z].cardGroup[m]);
						countStraight++;
					}
				}
				role[z].handStatus("同花顺",role[z].bestFive);
				mostRealNum = 100;
				mostColor = "";
				if(role[z].bestFive.get(0).getNum().equals("A")){
					role[z].handStatus("皇家同花顺",role[z].bestFive);
					mostRealNum = 100;
					mostColor = "";
				}
			}
//			System.out.println("5.同花顺构造结束");
			//其他牌型
			if ((!sameColor)&(!straight)){
				int mostCount1 = 0;int i = 0;
				int mostRealNum1 = 0;int start1 = 0;
				a: for (i = start1;i < role[z].cardGroup.length;i++){
					int range = 0;int count = 0;
					while(range < role[z].cardGroup.length - i){
						if (role[z].cardGroup[i].getRealNum() == role[z].cardGroup[i + range].getRealNum()){
							count++;range++;
							if (count >= mostCount1){
								mostCount1 = count;
								mostRealNum1 = role[z].cardGroup[i].getRealNum();
							}
						}else{
							start1 = start1 + range;
							break;
						}
						if (i == role[z].cardGroup.length - 1){
							break a;
						}
					}
				}
				
				for (int k = 0;k < role[z].cardGroup.length;k++){
					if (role[z].cardGroup[k].getRealNum() == mostRealNum1){
						role[z].bestFive.add(role[z].cardGroup[k]);
					}
				}
				int mostCount2 = 0;int j = 0;
				int mostRealNum2 = 0;int start2 = 0;
				a:for (j = start2;j < role[z].cardGroup.length;j++){
					int range = 0;int count = 0;
					while(range < role[z].cardGroup.length - j){
						if ((role[z].cardGroup[j].getRealNum() == role[z].cardGroup[j + range].getRealNum())
								&&(!role[z].cardGroup[j].getNum().equals(numList[mostRealNum1]))
								&&(mostCount1 + mostCount2 <= 5)){
							count++;range++;
							if (count >= mostCount2){
								mostCount2 = count;
								mostRealNum2 = role[z].cardGroup[j].getRealNum();
							}
						}else{
							start2 = start2 + range;
							break;
						}
						if (j == role[z].cardGroup.length - 1){
							break a;
						}
					}
				}			
				
				for (int k = 0;k < role[z].cardGroup.length;k++){
					if (role[z].cardGroup[k].getRealNum() == mostRealNum2){
						role[z].bestFive.add(role[z].cardGroup[k]);
					}
				}

				int size = role[z].bestFive.size();
				while (role[z].bestFive.size() < 5){	
//					System.out.println("end" + z);
					for (int m = role[z].cardGroup.length - 1;m >= 0;m--){
						boolean flag = true;
						for (int n = 0;n < role[z].bestFive.size();n++){
							if (role[z].cardGroup[m].getNum().equals(role[z].bestFive.get(n).getNum())){
								flag = false;
							}
						}
						if (flag == true){
							role[z].bestFive.add(role[z].cardGroup[m]);
							break;
						}
					}
				}
				
				if (mostCount1 == 1){
					role[z].handStatus("高牌",role[z].bestFive);
				}else if ((mostCount1 == 2)&&(mostCount2 == 1)){
					role[z].handStatus("对子",role[z].bestFive);
				}else if((mostCount1 == 2)&&(mostCount2 == 2)){
					role[z].handStatus("两对",role[z].bestFive);
				}else if ((mostCount1 == 3)&&(mostCount2 == 1)){
					role[z].handStatus("三条",role[z].bestFive);
				}else if ((mostCount1 == 3)&&(mostCount2 == 2)){
					role[z].handStatus("葫芦",role[z].bestFive);
				}else if (mostCount1 == 4){
					role[z].handStatus("金刚",role[z].bestFive);
				}
			}
			
		}
	}
}
