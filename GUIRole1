package texaspoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIRole1 extends JFrame{
	JLabel cpuName;
	JButton next;
	GUIRole1(Role rolerole){
		setTitle("玩家一览");
		setSize(250,250);
		int s = 10;
		for (int i = 1;i < rolerole.role.length;i++){
			cpuName = new JLabel(rolerole.role[i].getName());
			add(cpuName);
			cpuName.setBounds(10,s,200,25);
			s += 25;
		}
		next = new JButton("继续");
		add(next);
		next.setBounds(90,s,70,25);
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Poker.at.run();
				dispose();
			}
		});
	}
}
