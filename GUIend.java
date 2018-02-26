package texaspoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIend extends JFrame{
	GUIend(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("本轮游戏结束！");
		setLayout(null);
		JLabel info = new JLabel("游戏结束，是否继续游戏？");
		setSize(250,150);
		setResizable(false);
		JButton yes = new JButton("继续");
		JButton no = new JButton("退出");
		add(info);
		add(yes);
		add(no);
		info.setBounds(50, 20, 170, 20);
		yes.setBounds(50,60,60,25);
		no.setBounds(140,60,60,25);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Dealer.publicCard.clear();
//					Poker.gT.dispose();
					dispose();
//					Poker.gT = new GUItable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
}
