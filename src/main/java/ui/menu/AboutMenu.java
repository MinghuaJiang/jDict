package ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ui.action.AboutAction;

public class AboutMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4096480718266539473L;
	private JMenuItem helpItem = new JMenuItem("帮助");
	private JMenuItem aboutItem = new JMenuItem("作者");
	private static AboutMenu aboutMenu = new AboutMenu("关于");
	private AboutMenu(String title){
		super(title);
		add(helpItem);
		add(aboutItem);
		aboutItem.setMnemonic('A');
		AboutAction aboutAction = new AboutAction(this);
		helpItem.addActionListener(aboutAction);
		aboutItem.addActionListener(aboutAction);
	}
	
	public static AboutMenu getInstance(){
		return aboutMenu;
	}
	public JMenuItem getHelpItem() {
		return helpItem;
	}
	public JMenuItem getAboutItem() {
		return aboutItem;
	}
}
