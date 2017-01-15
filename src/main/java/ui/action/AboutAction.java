package ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ui.menu.AboutMenu;

public class AboutAction implements ActionListener{
	//private JMenuItem helpItem;
	private JMenuItem aboutItem;
	private String message;
	public AboutAction(AboutMenu menu){
		//helpItem = menu.getHelpItem();
		aboutItem = menu.getAboutItem();
		//message = "将五个以上同色球组成一直线，\n即可消除并得分，球的移动必须\n是有路径的";
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == aboutItem){
			JOptionPane.showMessageDialog(null, "作者： 蒋明华\n版本： 1.0","关于",JOptionPane.INFORMATION_MESSAGE);
		}
		//if(e.getSource() == helpItem){
			//JOptionPane.showMessageDialog(null,message,"游戏规则",JOptionPane.INFORMATION_MESSAGE);
		//}
	}

}
