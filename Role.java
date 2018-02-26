package texaspoker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Role {
	ArrayList<String> nameList = new ArrayList<String>();
	public static int pool;
	public static String pname;
	private String name;
	private int money, bet;
	private String cardType,temper,kind,option;
	private String num;
	static Role[] role = new Role[Poker.numOfPlayer];
	Cards[] hand = new Cards[2];
	LinkedList<Cards> bestFive = new LinkedList();
	Cards[] cardGroup = new Cards[Dealer.publicCard.size() + 2];
	private String num1;
	private String status;
	private boolean isBanker,myTurn,inGame;
	
	Role(){};
	Role(String name,String cardType,Cards[] hand,LinkedList<Cards> bestFive,Cards[] cardGroup, String string, int i, int j, boolean b, boolean d, boolean e,String option){
		this.name = name;
		this.cardType = cardType;
		this.hand = hand;
		this.bestFive = bestFive;
		this.cardGroup = cardGroup;
		this.kind = string;
		this.money = j;
		this.bet = i;
		this.isBanker = b;
		this.myTurn = d;
		this.inGame = e;
		this.setOption(option);
	}
	
	Role(String name,String temper,int money,String status,String kind,boolean isBanker,Boolean inGame,Cards[] hand){
		this.name = name;
		this.setTemper(temper);
		this.money = money;
		this.status = status;
		this.setKind(kind);
		this.setBanker(isBanker);
		this.inGame = inGame;
		this.hand = hand;
	}
	
//	Role(String name,String temper,int money,String status,String kind,boolean isBanker,boolean inGame){
//		this.name = name;
//		this.setTemper(temper);
//		this.money = money;
//		this.status = status;
//		this.setKind(kind);
//		this.setBanker(isBanker);
//		this.inGame = inGame;
//	}
	
	public void handStatus(String cardType,LinkedList<Cards> bestFive){
		this.cardType = cardType;
		this.bestFive = bestFive;
	}
	
	public void initNameList(){
		nameList.clear();
		nameList.add("科比");
		nameList.add("詹皇");
		nameList.add("铁杜");
		nameList.add("库里");
		nameList.add("哈登");
		nameList.add("威少");
		nameList.add("浓眉");
	}
	
	public int getPMoney() throws Exception{
		InputStreamReader isr = new InputStreamReader(new FileInputStream("src//texaspoker/player/player/money.txt"));
		BufferedReader br = new BufferedReader(isr);
		String rd = "";int readd = 0;
		try{
			while((rd = br.readLine()) != null){
				if (Integer.parseInt(rd) <= 2500){
					readd =  Integer.parseInt(rd);
				}else{
					readd = 2500;	
				}
				break;
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return readd;
	}
	private String[] temperList = {"冲动","机智","普通","冷静","胆小"};
	public void setRole() throws Exception{
		Scanner scan = new Scanner(System.in);
		String temp = "";
		for (int i = 0;i < Poker.numOfPlayer;i++){
			role[i] = new Role();
			if (i == 0){
				role[i] = new Role(pname,"没脾气",getPMoney(),"游戏中","玩家",false,false,new Cards[2]);
			}else{
				Random rand = new Random();
				int rand1 = 0;
				do{
					rand1 = rand.nextInt(nameList.size());
				}while(temp.contains(rand1 + ""));
				temp += rand1 + "";
				int rand2 = rand.nextInt(temperList.length);
				role[i] = new Role(nameList.get(rand1),temperList[rand2],2500,"游戏中","电脑",false,false,new Cards[2]);
			}
		}
	}
	public Role[] initRole(Role[] role){
		for (int x = 0;x < role.length;x++){
			role[x] = new Role(role[x].getName(),role[x].getCardType(),role[x].hand, role[x].bestFive,new Cards[Dealer.publicCard.size() + 2]
								,role[x].getKind(),role[x].getBet(),role[x].getMoney(),role[x].isBanker(),role[x].isMyTurn(),true,"");
		}
		return role;
	}
	
	public void showRole(){
		System.out.println("----------------------------\n入局玩家");
		System.out.println("NAME            MONEY\tISBANKER");
		for (int i = 0;i < role.length;i++){
			System.out.println("[" + role[i].getKind() + "]" + role[i].getName() + "\t" + role[i].getMoney() + "\t" + role[i].isBanker());
		}
		System.out.println("----------------------------");
	}
	public void showRoleG() {
		System.out.println("----------------------------\n当前局势");
		System.out.println("姓名                        钱包\t庄家\t下注");
		for (int i = 0;i < role.length;i++){
//			role[i].setMoney(role[i].getMoney() - role[i].getBet());
			if (role[i].isInGame()){
				System.out.println("[" + role[i].getKind() + "]" + role[i].getName() + "\t" + role[i].getMoney() + "\t" + role[i].isBanker() + "\t" + role[i].getBet());
			}else{
				System.out.println("[" + role[i].getKind() + "]" + role[i].getName() + "\t" + role[i].getMoney() + "\t" + role[i].isBanker() + "\t" + role[i].getBet() + "          \t弃牌");
			}
//			pool += role[i].getBet();
		}
		System.out.println("POOL:" + pool);
		System.out.println("----------------------------");
	}
	
	public void showRoleGG(){
		System.out.println("----------------------------\n本轮牌局结束");
		System.out.println("NAME            MONEY");
		for (int i = 0;i < role.length;i++){
			System.out.println("[" + role[i].getKind() + "]" + role[i].getName() + "\t" + role[i].getMoney());
		}
		System.out.println("----------------------------");
	}
	
	public static void showHand(Role[] set) throws Exception{
		for(int x = 0;x < set.length;x++){
			Thread.currentThread().sleep(500);
			System.out.println("\n-----" + set[x].getName() + "-----");
			String str = "[" + set[x].cardType + "]{";
			for (int j = 0;j < set[x].getBestFive().size() - 1;j++){
				str += set[x].getBestFive().get(j).getColor()+set[x].getBestFive().get(j).getNum() + "  ";
			}
			str += set[x].getBestFive().get(4).getColor()+set[x].getBestFive().get(4).getNum() + "}";
			System.out.println(set[x].hand[0].getColor() + set[x].hand[0].getNum() + "---" + set[x].hand[1].getColor() + set[x].hand[1].getNum() + "\t" + str);
		}
	}
	
	public static void showHand1(Role[] set) throws Exception{
		for(int x = 0;x < set.length;x++){
			System.out.println("\n-----" + set[x].getName() + "-----");
			System.out.println(set[x].hand[0].getColor() + set[x].hand[0].getNum() + "---" + set[x].hand[1].getColor() + set[x].hand[1].getNum());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public LinkedList<Cards> getBestFive() {
		return bestFive;
	}

	public void setBestFive(LinkedList<Cards> bestFive) {
		this.bestFive = bestFive;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTemper() {
		return temper;
	}
	public void setTemper(String temper) {
		this.temper = temper;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public boolean isBanker() {
		return isBanker;
	}
	public void setBanker(boolean isBanker) {
		this.isBanker = isBanker;
	}
	public boolean isMyTurn() {
		return myTurn;
	}
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
	public boolean isInGame() {
		return inGame;
	}
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public void setSpeak(String string) {
		// TODO 自动生成的方法存根
		
	}
	public boolean isAbandon() {
		// TODO 自动生成的方法存根
		return false;
	}
}
