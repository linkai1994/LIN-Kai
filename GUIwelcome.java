package texaspoker;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIwelcome extends JFrame{ 
	JLabel pnumLabel = new JLabel("选择游戏人数");
	JComboBox pNumCombo = new JComboBox();
	JLabel pseatLabel = new JLabel("选择座位");
	JComboBox pSeatCombo = new JComboBox();
	JLabel pNameLabel = new JLabel("输入你的姓名");
	JTextField pName = new JTextField();
	JLabel taitou = new JLabel("游戏参数设置");
	
	JButton accept1 = new JButton("确定");
    public GUIwelcome(String title) {
    	setTitle(title);
    	int s = 5;
    	setSize(250,250);
    	setResizable(false);
    	setLayout(null);
    	this.setVisible(true);
    	add(taitou);
    	taitou.setBounds(70,s,110,25);
    	add(pnumLabel);
    	add(pNumCombo);
    	pnumLabel.setBounds(5, s+45, 100, 25);
    	pNumCombo.setBounds(125,s+45,100,25);
    	pNumCombo.addItem(2);
    	pNumCombo.addItem(3);
    	pNumCombo.addItem(4);
    	pNumCombo.addItem(5);
    	pNumCombo.addItem(6);
    	add(pNameLabel);
    	pNameLabel.setBounds(5,s+115,100,25);
    	add(pName);
    	pName.setBounds(125,s+115,100,25);
    	add(accept1);
    	add(pseatLabel);
    	pseatLabel.setBounds(5,s+80,100,25);
    	pSeatCombo.addItem(1);pSeatCombo.addItem(2);
    	add(pSeatCombo);
    	pSeatCombo.setBounds(125, s+80, 100, 25);
    	accept1.setBounds(90,s+170,70,25);
    	pName.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode()==(KeyEvent.VK_ENTER)){
					if (!pName.getText().trim().equals(null)&&(!pName.getText().trim().equals(""))){
						Poker.numOfPlayer = Integer.parseInt(pNumCombo.getSelectedItem().toString());
						Poker.preferredSeat = Integer.parseInt(pSeatCombo.getSelectedItem().toString());
						Role.pname = pName.getText();
						dispose();
						try {
							Poker.method1();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else{
						GUIerror ge = new GUIerror();
					}
					
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    	accept1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pName.getText().trim().equals(null)&&(!pName.getText().trim().equals(""))){
					Poker.numOfPlayer = Integer.parseInt(pNumCombo.getSelectedItem().toString());
					Poker.preferredSeat = Integer.parseInt(pSeatCombo.getSelectedItem().toString());
					Role.pname = pName.getText();
					dispose();
					try {
						Poker.method1();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else{
					GUIerror ge = new GUIerror();
				}
			}
		});
    	
    	
    	
    	pNumCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pSeatCombo.removeAllItems();
		    	for (int i = 1;i <= Integer.parseInt(pNumCombo.getSelectedItem().toString());i++){
		    		pSeatCombo.addItem(i);
		    	}
			}
		});
	}
}
