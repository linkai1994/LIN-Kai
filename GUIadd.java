package texaspoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUIadd extends JFrame{
	JTextField amount = new JTextField("000");
	Role[] roles;
	GUIadd(){
		this.roles = Poker.roles;
		setTitle("加注数额");
		setVisible(true);
		JButton yes = new JButton("确定");
		setSize(250,200);
		setResizable(false);
		setLayout(null);
		add(amount);
		add(yes);
		amount.setBounds(75,60,100,30);
		final int AMOUNT = Integer.parseInt(amount.getText());
		yes.setBounds(100,120,50,30);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					if (Integer.parseInt(amount.getSelectedText())<roles[Poker.indexOfPlayer].getMoney()){
						Process.pAddition = Integer.parseInt(amount.getSelectedText());
						roles[Poker.indexOfPlayer].setBet(50 + Process.pAddition);
						roles[Poker.indexOfPlayer].setSpeak(roles[Poker.indexOfPlayer].getName() + "加注" + roles[Poker.indexOfPlayer].getBet() + "\n");
						dispose();
						Poker.mt.notify();
					}
				}catch(Exception e1){
					GUIerror ge = new GUIerror();
				}
			}
		});
	}
}
