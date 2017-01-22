package ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ui.menu.AboutMenu;

public class AboutAction implements ActionListener{
	private JMenuItem helpItem;
	private JMenuItem aboutItem;
	private String message;
	public AboutAction(AboutMenu menu){
		helpItem = menu.getHelpItem();
		aboutItem = menu.getAboutItem();
		message = "第一次使用先导入生词，然后在选项中," +
				"点击查看今日生词\n你可以每天都选择背不一样的数目\n," +
				"然后点击查看今日复习生词,会显示出今天所需复习生词\n, 查看已背生词是查看所有已经至少背过一次的生词\n" +
				"（包括今日选择背诵的生词）";
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == aboutItem){
			JOptionPane.showMessageDialog(null, "作者： 蒋明华\n版本： 1.0","关于",JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource() == helpItem){
			JOptionPane.showMessageDialog(null,message,"使用方式",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
